package Commands;

import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.CommandManager;

public class ClearCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public ClearCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("clear", "Полностью очищает коллекцию");
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public void execute(String arg) throws NumberOfArgumentException {
        if (!arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        } else {
            collectionManager.clear();
            commandManager.addToHistory(this);
        }
    }
}
