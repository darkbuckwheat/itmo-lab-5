package Commands;

import Classes.Dragon;

import Creators.DragonCreator;

import Exceptions.FieldNullException;
import Exceptions.IncorrectFieldValueException;
import Exceptions.InvalidInputException;
import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.OutputColor;
import Managers.OutputManager;

public class AddCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final DragonCreator dragonCreator;
    private final CommandManager commandManager;

    public AddCommand(CollectionManager collectionManager, OutputManager outputManager,
                      DragonCreator dragonCreator, CommandManager commandManager){
        super("add", "Добавляет элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.dragonCreator = dragonCreator;
        this.commandManager = commandManager;
    }

    public void execute(String arg) throws NumberOfArgumentException, InvalidInputException,
            FieldNullException, IncorrectFieldValueException {
        if (!arg.isEmpty()){
            throw new NumberOfArgumentException(this.getName(), 0);
        } else {
            try {
                Dragon dragon = dragonCreator.createNewDragon();
                collectionManager.addElement(dragon);
                commandManager.addToHistory(this);
            }
            catch (InvalidInputException | FieldNullException | IncorrectFieldValueException | NumberFormatException e){
                outputManager.printlnImportantColorMessage("You typed an incorrect value, try again from the beginning",
                        OutputColor.RED);
                //this.execute("");
            }
        }
    }
}
