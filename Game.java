import java.io.*;
import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner scanner;
    private static final String SAVE_FILE = "game_save.txt";

    public Game() {
        player = new Player();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Adventure Game!");
        System.out.print("Do you want to load a saved game? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            loadGame();
        } else {
            System.out.print("Enter your name: ");
            player.setName(scanner.nextLine());
            player.setCurrentLocation("start");
        }
        System.out.println("Hello " + player.getName() + "! Your adventure begins now...");
        navigate(player.getCurrentLocation());
    }

    private void navigate(String location) {
        System.out.println("Navigating to: " + location); // Debug statement
        player.setCurrentLocation(location);

        switch (location) {
            case "start":
                firstChoice();
                break;
            case "leftPath":
                pathLeft();
                break;
            case "rightPath":
                pathRight();
                break;
            case "treasure":
                findTreasure();
                break;
            case "cave":
                enterCave();
                break;
            case "village":
                reachVillage();
                break;
            case "lake":
                visitLake();
                break;
            default:
                System.out.println("Unknown location. Game over.");
                break;
        }
    }

    private void firstChoice() {
        System.out.println("You are in a dark forest. You see a path to your left and a river to your right.");
        System.out.print("Do you go left or right? (left/right/save): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("left")) {
            navigate("leftPath");
        } else if (choice.equalsIgnoreCase("right")) {
            navigate("rightPath");
        } else if (choice.equalsIgnoreCase("save")) {
            saveGame();
            firstChoice();
        } else {
            System.out.println("Invalid choice. Try again.");
            firstChoice();
        }
    }

    private void pathLeft() {
        System.out.println("You walk down the path and find a treasure chest.");
        System.out.print("Do you open it? (yes/no/save): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            navigate("treasure");
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("You walk away and find a mysterious cave. Do you enter it? (yes/no/save): ");
            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                navigate("cave");
            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("You get lost in the forest. Game over.");
            } else if (choice.equalsIgnoreCase("save")) {
                saveGame();
                pathLeft();
            } else {
                System.out.println("Invalid choice. Try again.");
                pathLeft();
            }
        } else if (choice.equalsIgnoreCase("save")) {
            saveGame();
            pathLeft();
        } else {
            System.out.println("Invalid choice. Try again.");
            pathLeft();
        }
    }

    private void pathRight() {
        System.out.println("You approach the river and see a boat.");
        System.out.print("Do you take the boat? (yes/no/save): ");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            navigate("village");
        } else if (choice.equalsIgnoreCase("no")) {
            System.out.println("You walk along the river and find a beautiful lake. Do you explore it? (yes/no/save): ");
            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                navigate("lake");
            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("You get lost in the forest. Game over.");
            } else if (choice.equalsIgnoreCase("save")) {
                saveGame();
                pathRight();
            } else {
                System.out.println("Invalid choice. Try again.");
                pathRight();
            }
        } else if (choice.equalsIgnoreCase("save")) {
            saveGame();
            pathRight();
        } else {
            System.out.println("Invalid choice. Try again.");
            pathRight();
        }
    }

    private void findTreasure() {
        System.out.println("You found gold! You win!");
    }

    private void enterCave() {
        System.out.println("You found a hidden treasure! You win!");
    }

    private void reachVillage() {
        System.out.println("You safely cross the river and find a village. You win!");
    }

    private void visitLake() {
        System.out.println("You find a serene lake and decide to rest. While resting, you find a magical stone. You win!");
    }

    private void saveGame() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SAVE_FILE))) {
            writer.println(player.getName());
            writer.println(player.getCurrentLocation());
            System.out.println("Game saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the game.");
        }
    }

    private void loadGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            player.setName(reader.readLine());
            player.setCurrentLocation(reader.readLine());
            System.out.println("Game loaded. Hello " + player.getName() + "!");
        } catch (IOException e) {
            System.out.println("An error occurred while loading the game.");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
