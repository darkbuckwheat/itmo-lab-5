package Commands;

import Exceptions.IncorrectCommandArgumentException;
import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.CommandManager;

public class RemoveByIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final CommandManager commandManager;

    public RemoveByIdCommand(CollectionManager collectionManager, CommandManager commandManager){
        super("remove_by_id", "Удаляет из коллекции элемент с соответствующим id");
        this.collectionManager = collectionManager;
        this.commandManager = commandManager;
    }

    public void execute(String arg) throws NumberOfArgumentException, IncorrectCommandArgumentException {
        if (arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 1, 0);
        } else if (arg.split(" ").length > 1) {
            throw new NumberOfArgumentException(this.getName(), 1, arg.split(" ").length);
        } else {
            try{
                Long.parseLong(arg);
            }
            catch(NumberFormatException e){
                throw new IncorrectCommandArgumentException(arg, this.getName());
            }
            collectionManager.removeElementById(Long.parseLong(arg));
            commandManager.addToHistory(this);
        }
    }
}
