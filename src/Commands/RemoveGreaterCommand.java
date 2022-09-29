package Commands;

import Classes.Dragon;

import Creators.DragonCreator;

import Exceptions.NumberOfArgumentException;
import Exceptions.InvalidInputException;
import Exceptions.IncorrectCommandArgumentException;
import Exceptions.FieldNullException;
import Exceptions.IncorrectFieldValueException;

import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.OutputColor;
import Managers.OutputManager;

import java.util.ArrayList;
import java.util.List;

public class RemoveGreaterCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final CommandManager commandManager;
    private final DragonCreator dragonCreator;

    public RemoveGreaterCommand(CollectionManager collectionManager, OutputManager outputManager,
                                CommandManager commandManager, DragonCreator dragonCreator){
        super("remove_greater", "Удаляет из коллекции элементы, превосходящие введённый");
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.commandManager = commandManager;
        this.dragonCreator = dragonCreator;
    }

    public void execute(String arg) throws NumberOfArgumentException, IncorrectCommandArgumentException,
            InvalidInputException, FieldNullException, IncorrectFieldValueException {
        if (!arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        } else {
            try {
                Dragon dragon = dragonCreator.createNewDragon();
                List<Dragon> list = new ArrayList<>(collectionManager.getCollection());
                list = list.stream().filter(o-> o.compareTo(dragon) > 0).toList();
                for (Long id : list.stream().map(Dragon::getId).toList()){
                    collectionManager.removeElementById(id);
                }
                commandManager.addToHistory(this);
            }
            catch (InvalidInputException | FieldNullException | IncorrectFieldValueException | NumberFormatException e) {
                outputManager.printlnImportantColorMessage("You typed an incorrect value, try again from the beginning",
                        OutputColor.RED);
                this.execute("");
            }
        }
    }
}
