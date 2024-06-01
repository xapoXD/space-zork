package zork.game;

public interface Game {

    String welcomeMessage();
    String endMessage();
    boolean isFinished();

    String processTextCommand(String line);
}
