package Commands;

import Exceptions.NumberOfArgumentException;

import Managers.CommandManager;
import Managers.OutputManager;

public class HistoryCommand extends AbstractCommand{
    private final OutputManager outputManager;
    private final CommandManager commandManager;

    public HistoryCommand(OutputManager outputManager, CommandManager commandManager){
        super("history", "Выводит последние 15 команд без их аргументов");
        this.outputManager = outputManager;
        this.commandManager = commandManager;
    }

    public void execute(String arg) throws NumberOfArgumentException {
        if (!arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        }
        outputManager.println(commandManager.getHistory().toString());
        commandManager.addToHistory(this);
    }
}
