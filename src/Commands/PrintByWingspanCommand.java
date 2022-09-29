package Commands;

import Classes.Dragon;

import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.CommandManager;
import Managers.OutputManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PrintByWingspanCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final OutputManager outputManager;
    private final CommandManager commandManager;

    public PrintByWingspanCommand(CollectionManager collectionManager, OutputManager outputManager, CommandManager commandManager){
        super("print_field_ascending_wingspan", "Выводит на экран элементы коллекции в порядке возрастания значения поля wingspan");
        this.collectionManager = collectionManager;
        this.outputManager = outputManager;
        this.commandManager = commandManager;
    }

    public void execute(String args) throws NumberOfArgumentException {
        if (!args.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        }
        Comparator<Dragon> compareByWingspan = (o1, o2) -> {
            if (o1.getWingspan() - o2.getWingspan() > 0){
                return 1;
            }
            else if(o1.getWingspan() - o2.getWingspan() == 0){
                return 0;
            }
            else{
                return -1;
            }
        };

        List<Dragon> list = new ArrayList<>(collectionManager.getCollection());
        list.sort(compareByWingspan);
        for(Dragon dragon : list){
            outputManager.printlnImportantMessage(String.valueOf(dragon.getWingspan()));
        }
        commandManager.addToHistory(this);
    }
}
