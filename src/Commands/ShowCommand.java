package Commands;

import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.OutputManager;

public class ShowCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final CommandManager commandManager;

    public ShowCommand(CollectionManager collectionManager, OutputManager outputManager, CommandManager commandManager){
        super("show", "Выводит все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.commandManager = commandManager;
    }
    public void execute(String arg) throws NumberOfArgumentException {
        if (!arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        }
        outputManager.printlnImportantMessage(collectionManager.toString());
        commandManager.addToHistory(this);
    }
}
