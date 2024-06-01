# Space Zork Game Application

## Table of Contents
1. [Introduction](#introduction)
2. [Project Structure](#project-structure)
3. [Game Requirements](#game-requirements)
4. [Design Patterns](#design-patterns)
5. [Classes and Packages](#classes-and-packages)
6. [Logging](#logging)
7. [Testing](#testing)
8. [How to Play](#how-to-play)

## Introduction
Welcome to the documentation for the Zork console game application. This document provides an overview of the project's structure, the game requirements, the design patterns used, and detailed information about the classes and packages within the project. Additionally, it includes instructions for logging and testing, as well as a guide on how to play the game.

## Game Requirements
The game has been developed to meet the following requirements:

1. **Storyline**: A simple story where the player navigates through rooms, collects items, fights enemies, and ultimately defeats a final boss.
2. **CommandLineUi as Singleton**: Ensures a single instance of `CommandLineUi` class.
3. **Error Handling**: The game does not crash on invalid user input. Instead, it informs the user of the error and allows continued play.
4. **Resetting Game Data**: Game data can be reset easily without reinitializing commands.
5. **Command Pattern**: Encapsulates logic in command classes, ensuring immutability and proper data propagation.
6. **Commands Implemented**: `go`, `end`, `restart`, `equip`, `attack`, `pick up`, `put down`, `look around`.
7. **Enemy Implementation**: Uses Flyweight and Builder patterns for immutable enemy parts.
8. **Weapon Implementation**: Commands for equipping and attacking with weapons.
9. **Final Boss**: A special enemy in a locked room, requiring a key to access.
10. **Inventory Commands**: Commands for picking up and putting down items, with fixed inventory capacity.
11. **Log4j2 Logging**: Logs commands to a file with timestamps.
12. **Observer Pattern**: Inventory changes are observed and logged.
13. **Command Tests**: Unit tests for each command.

## Design Patterns
Several design patterns have been implemented:

1. **Singleton**: `CommandLineUi` class to ensure a single instance.
2. **Command**: Encapsulates all game commands.
3. **Flyweight + Builder**: For creating enemy objects.
4. **Observer**: For inventory change notifications.

## Classes and Packages
### `command` Package
- **AttackCommand**: Executes an attack.
- **Command**: Base class for all commands.
- **EndCommand**: Ends the game.
- **EquipCommand**: Equips a weapon.
- **GoCommand**: Moves the player to another room.
- **HelpCommand**: Displays help information.
- **InventoryCommand**: Manages inventory.
- **LookAroundCommand**: Displays information about the current room.
- **PickUpCommand**: Picks up an item.
- **PutDownCommand**: Puts down an item.
- **ResetCommand**: Resets the game.

### `game` Package
- **Game**: Main game logic.
- **GameData**: Interface for game data.
- **GameDataImpl**: Implementation of game data.
- **GameImpl**: Implementation of game logic.
- **Room**: Represents a room.
- **RoomImpl**: Implementation of a room.

#### `enemy` Subpackage
- **Enemy**: Represents an enemy.
- **EnemyBuilder**: Interface for building enemies.
- **EnemyBuilderImpl**: Implementation of enemy builder.
- **EnemyFactory**: Factory for creating enemies.
- **EnemyImpl**: Implementation of an enemy.
- **EnemyType**: Types of enemies.

#### `equipable` Subpackage
- **Inventory**: Manages the player's inventory.
- **Item**: Represents an item.
- **ItemImpl**: Implementation of an item.
- **Key**: Represents a key item.
- **Potion**: Represents a potion item.
- **Weapon**: Represents a weapon.
- **WeaponImpl**: Implementation of a weapon.

##### `observer` Subpackage
- **InventoryObservable**: Observable inventory.
- **InventoryObserver**: Observer for inventory changes.

### `main` Package
- **Main**: Entry point of the game.

### `ui` Package
- **CommandLineUi**: User interface for the command line.

## Logging
Logging is configured using Log4j2. The configuration file `log4j2.xml` is located in the `src/main/resources` directory. The `CommandLineUi` class includes log statements that log commands to `prikazy.log`.

## Testing
Unit tests for the command classes are located in the `src/test/zork/command` directory. Each command class has a corresponding test class.

## How to Play
1. **Start the Game**: Run the `Main` class to start the game.
2. **Commands**: Use commands like `go`, `end`, `restart`, `equip`, `attack`, `pick up`, `put down`, and `look around` to interact with the game.
3. **Navigating**: Use the `go` command to move between rooms.
4. **Inventory**: Use `pick up` to collect items and `put down` to drop them.
5. **Combat**: Use `attack` to fight enemies.
6. **Winning the Game**: Defeat the final boss to win the game.

Enjoy playing Zork!
