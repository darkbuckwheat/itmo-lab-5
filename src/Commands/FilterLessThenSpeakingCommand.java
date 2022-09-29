package Commands;

import Classes.Dragon;

import Exceptions.IncorrectCommandArgumentException;
import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.OutputManager;

import java.util.ArrayList;
import java.util.List;

public class FilterLessThenSpeakingCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final CommandManager commandManager;

    public FilterLessThenSpeakingCommand(CollectionManager collectionManager, OutputManager outputManager, CommandManager commandManager){
        super("filter_less_than_speaking", "Выводит все элементы, у которых значение speaking меньше(?) заданного");
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.commandManager = commandManager;
    }

    public void execute(String arg) throws NumberOfArgumentException, IncorrectCommandArgumentException {
        if (arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 1, 0);
        } else if (arg.split(" ").length > 1) {
            throw new NumberOfArgumentException(this.getName(), 1, arg.split(" ").length);
        }
        else if (!arg.equals("true") && !arg.equals("false")){
            throw new IncorrectCommandArgumentException(arg, this.getName());
        }
        else {
            List<Dragon> list = new ArrayList<>(collectionManager.getCollection());
            list = list.stream().filter(o-> Boolean.parseBoolean(arg) && !o.getSpeaking()).toList();
            for(Dragon dragon : list){
                outputManager.printlnImportantMessage(dragon.toString());
            }
            commandManager.addToHistory(this);
        }
    }
}
