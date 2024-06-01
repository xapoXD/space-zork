package zork.ui;

import zork.game.Game;
import zork.game.GameImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Scanner;

/**
 *  Represents command line ui view
 */
public class CommandLineUi {
    public static final Logger log = LoggerFactory.getLogger(CommandLineUi.class);
    private static CommandLineUi INSTANCE = new CommandLineUi();
    private Game game = new GameImpl();


    private CommandLineUi(){ // Private constructor to prevent instantiation from outside the class
    }


    //TODO NOT SINGLETON... Asi hotovo
    public static CommandLineUi getInstance(){

        if (INSTANCE == null) {
            INSTANCE = new CommandLineUi();
        }
        return INSTANCE;

    }
    /**
     *  After ui is started, application prints welcome messages and waits for user input
     *  then proceeds to process user input and return messages back here
     *  -> to be represented on this view.
     */
    public void start(){
        log.info("application started");

        try(Scanner scanner = new Scanner(System.in)){
            System.out.println(this.game.welcomeMessage());

            String save = "";
            while(this.game.isFinished()){
                System.out.print("> ");
                save = scanner.nextLine();
                System.out.println(this.game.processTextCommand(save));
            }

            if(!Objects.equals(save, "end")){
                System.out.println(this.game.endMessage());
            }
        }
    }

}
