package Managers;

import Commands.ExitCommand;
import Exceptions.InvalidInputException;
import Exceptions.NoCommandException;
import Exceptions.IncorrectCommandArgumentException;
import Exceptions.NumberOfArgumentException;

import Commands.AbstractCommand;

import java.util.Optional;
import java.util.Locale;

public class ConsoleManager{
    private final CommandManager commandManager;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    public boolean executeFlag = true;

    public ConsoleManager(CommandManager commandManager, InputManager inputManager, OutputManager outputManager) {
        this.commandManager = commandManager;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }

    public void start() throws InvalidInputException {
        while (executeFlag) {
            String input = "";
            try{
                input = inputManager.read();
            }
            catch (InvalidInputException e){
                outputManager.printlnImportantColorMessage("You entered ctrl+d. That means you have ended input. " +
                        "So program should end. >:(", OutputColor.RED);
                try {
                    commandManager.getCommands().stream()
                            .filter(v -> v.getName().equals("exit")).findFirst().get().execute("");
                    break;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (!input.trim().isEmpty()) {
                String inputCommand = input.split(" ")[0].toLowerCase(Locale.ROOT);
                String argument = "";
                if (input.split(" ").length > 1) {
                    argument = input.replaceFirst(inputCommand + " ", "");
                }
                Optional<AbstractCommand> optional = commandManager.getCommands().stream()
                        .filter(v -> v.getName().equals(inputCommand)).findFirst();
                if (optional.isPresent()) {
                    try {
                        AbstractCommand command = optional.get();
                        command.execute(argument);
                        executeFlag = command.getExecuteFlag();
                        outputManager.printlnColorMessage("The command completed", OutputColor.GREEN);
                    } catch (NoCommandException | IncorrectCommandArgumentException | NumberOfArgumentException e) {
                        inputManager.finishReadScript();
                        outputManager.printlnImportantColorMessage(e.getMessage(), OutputColor.RED);
                    } catch (NumberFormatException e) {
                        inputManager.finishReadScript();
                        outputManager.printlnImportantColorMessage("Wrong number format", OutputColor.RED);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (inputManager.getScriptMode()) {
                        inputManager.finishReadScript();
                        outputManager.printlnImportantColorMessage("Unknown command detected: " + inputCommand,
                                OutputColor.RED);
                    } else {
                        outputManager.printlnColorMessage("No such command. Type \"help\" to get all commands with"
                                + "their names and descriptions", OutputColor.RED);
                    }
                }
            } else {
                outputManager.printlnColorMessage("Please type any command. To see list of command type \"help\"",
                        OutputColor.RED);
            }
        }
    }
}
