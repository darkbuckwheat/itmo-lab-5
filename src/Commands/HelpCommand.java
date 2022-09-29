package Commands;

import Exceptions.NumberOfArgumentException;

import Managers.CommandManager;
import Managers.OutputManager;

import java.util.ArrayList;

public class HelpCommand extends AbstractCommand{
    private final OutputManager outputManager;
    private final CommandManager commandManager;
    private final ArrayList<AbstractCommand> commands;

    public HelpCommand(ArrayList<AbstractCommand> commands, OutputManager outputManager, CommandManager commandManager){
        super("help", "Выводит справку по доступным командам");
        this.outputManager = outputManager;
        this.commandManager = commandManager;
        this.commands = commands;
    }

    public void execute(String arg) throws NumberOfArgumentException {
        if (!arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        }
        outputManager.printlnImportantMessage("Список доступных команд:");
        for (AbstractCommand command : commands) {
            outputManager.printlnImportantMessage(command.getName() + ": " + command.getDescription());
        }
        commandManager.addToHistory(this);
    }
}
