import Creators.CaveCreator;
import Creators.CollectionCreator;
import Creators.CoordinatesCreator;
import Creators.DragonCreator;

import Exceptions.FieldNullException;
import Exceptions.IncorrectFieldValueException;
import Exceptions.InvalidInputException;

import Managers.OutputColor;
import Managers.OutputManager;
import Managers.InputManager;
import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.ConsoleManager;

import java.io.FileNotFoundException;

/**
 * Main class of the project
 */
public final class Client {
    private Client(){
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    /**
     * Main function of the project. Here I set up all utility-classes and get accuses to file with data.
     * @param args takes all params from command line, when program starts.
     */
    public static void main(String[] args){
        OutputManager outputManager = new OutputManager(System.out);
        InputManager inputManager = new InputManager(System.in, outputManager);
        if (!(args.length == 0)) {
            outputManager.printlnImportantColorMessage("This program takes no arguments. " +
                            "Path to file with collection must be set in environment variable \"DATA_PATH\"",
                    OutputColor.RED);
        }
        String filepath = System.getenv("DATA_PATH");
        if ((filepath == null) || (filepath.equals(""))){
            outputManager.printlnImportantColorMessage("Path to data-file is empty. Data will be saved to Data.json", OutputColor.RED);
            filepath = "Data.json";
        }
        try {
            CollectionManager collectionManager = CollectionCreator.load(filepath, outputManager);
            CaveCreator caveCreator = new CaveCreator(inputManager, outputManager);
            CoordinatesCreator coordinatesCreator = new CoordinatesCreator(inputManager,outputManager);
            DragonCreator dragonCreator = new DragonCreator(inputManager, outputManager,
                    collectionManager, caveCreator, coordinatesCreator);
            CommandManager commandManager = new CommandManager(collectionManager, inputManager,
                    outputManager, dragonCreator);
            ConsoleManager consoleManager = new ConsoleManager(commandManager, inputManager, outputManager);
            consoleManager.start();
        }
        catch (InvalidInputException e) {
            outputManager.printlnImportantColorMessage(e.getMessage(), OutputColor.RED);
        } catch (FieldNullException | IncorrectFieldValueException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
