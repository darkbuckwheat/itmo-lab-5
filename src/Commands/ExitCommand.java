package Commands;

import Exceptions.NumberOfArgumentException;

public class ExitCommand extends AbstractCommand{
    public ExitCommand(){
        super("exit", "Прекращает работу программы без сохранения в файл");
    }

    public void execute(String arg) throws NumberOfArgumentException {
        if (!arg.isEmpty()) {
            throw new NumberOfArgumentException(this.getName(), 0);
        } else {
           setExecuteFlag(false);
        }
    }
}
