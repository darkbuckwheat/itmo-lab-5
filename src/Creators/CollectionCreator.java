package Creators;

import Exceptions.FieldNullException;
import Exceptions.IncorrectFieldValueException;

import Managers.CollectionManager;
import Managers.OutputManager;
import Managers.OutputColor;
import Managers.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 * Class that creates new CollectionManager
 * @see CollectionManager
 */
public class CollectionCreator {
    private CollectionCreator(){}

    /**
     * Method that load information from json-file into new {@link CollectionManager CollectionManager} and returns it
     * @param filePath string that contains path to json-file with data
     * @param outputManager object of class {@link OutputManager OutputManager}
     * @return new {@link CollectionManager CollectionManager}
     * @throws FieldNullException
     * @throws IncorrectFieldValueException
     * @throws FileNotFoundException
     * @see CollectionManager
     * @see OutputManager
     */
    public static CollectionManager load(String filePath, OutputManager outputManager)
            throws FieldNullException, IncorrectFieldValueException, FileNotFoundException {
        CollectionManager collectionManager;
        if (filePath == null){
            collectionManager = new CollectionManager(new HashSet<>(), "");
            outputManager.printlnImportantColorMessage("Filepath is null. A new empty collection"
                    + "has been created.", OutputColor.RED);
            return collectionManager;
        }
        File file = new File(filePath);
        if (file.exists() && file.length() != 0) {
            collectionManager = Parser.convertToJavaObject(file);
            collectionManager.setFilePath(filePath);
            outputManager.printlnImportantColorMessage("The collection was successfully loaded from the file " + filePath,
                    OutputColor.GREEN);
        } else {
            collectionManager = new CollectionManager(new HashSet<>(), filePath);
            if (!file.exists()) {
                outputManager.printlnImportantColorMessage("No file with this name was found. A new empty collection"
                        + "has been created.", OutputColor.RED);
            } else {
                outputManager.printlnImportantColorMessage("The collection was successfully loaded from the file"
                        + filePath, OutputColor.GREEN);
            }
        }
        return collectionManager;
    }
}
