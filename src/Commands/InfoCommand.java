package Commands;

import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.OutputManager;

public class InfoCommand extends AbstractCommand{
    private final OutputManager outputManager;
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public InfoCommand(CollectionManager collectionManager, OutputManager outputManager, CommandManager commandManager){
        super("info", "Выводит информацию о коллекции");
        this.outputManager = outputManager;
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public void execute(String arg) throws NumberOfArgumentException {
        if (!arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        }
        outputManager.printlnImportantMessage("Некоторая информация о коллекции, с которой работает программа:");
        outputManager.printlnImportantMessage("Тип коллекции: " + collectionManager.getCollection().getClass().getName());
        outputManager.printlnImportantMessage("Дата инициализации: " + collectionManager.getInitializationDate());
        outputManager.printlnImportantMessage("Количество элементов: " + collectionManager.getCollection().size());
        commandManager.addToHistory(this);
    }
}
