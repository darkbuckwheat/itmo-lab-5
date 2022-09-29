package Commands;

import Exceptions.NumberOfArgumentException;

import Managers.CommandManager;
import Managers.InputManager;

public class ExecuteScriptCommand extends AbstractCommand{
    private final InputManager inputManager;
    private final CommandManager commandManager;

    public ExecuteScriptCommand(InputManager inputManager, CommandManager commandManager){
        super("execute_script", "Выполняет скрипт из файла по указанному пути");
        this.inputManager = inputManager;
        this.commandManager = commandManager;
    }

    public void execute(String arg) throws NumberOfArgumentException {
        if (arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 1, 0);
        }
        else if (arg.split(" ").length > 1){
           throw new NumberOfArgumentException(this.getName(), 1, arg.split(" ").length);
        }
        inputManager.startReadScript(arg);
        commandManager.addToHistory(this);
    }
}
