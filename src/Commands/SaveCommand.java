package Commands;

import Exceptions.InvalidInputException;
import Exceptions.NumberOfArgumentException;

import Managers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final InputManager inputManager;
    private final CommandManager commandManager;

    public SaveCommand(CollectionManager collectionManager, OutputManager outputManager, InputManager inputManager,
                       CommandManager commandManager){
        super("save", "Сохраняет коллекцию в файл");
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.inputManager = inputManager;
        this.commandManager = commandManager;
    }

    public void execute(String args) throws IOException, NumberOfArgumentException, InvalidInputException {
        if (!args.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        }
        try{
            File file = new File(collectionManager.getFilePath());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(Parser.convertToJSON(collectionManager).getBytes());
            fileOutputStream.close();
            commandManager.addToHistory(this);
        } catch(FileNotFoundException e){
            outputManager.printlnImportantColorMessage("File dosen't exist or program dosen't have rights to change it." +
                    "Input another file to save your data and use this command again.", OutputColor.RED);
            outputManager.println("Type new filepath");
            File file = new File(inputManager.read());
            if (file.exists()){
                collectionManager.setFilePath(file.toString());
            }
        }
    }
}
