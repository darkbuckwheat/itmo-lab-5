package Managers;

import Commands.AbstractCommand;
import Commands.InfoCommand;
import Commands.ShowCommand;
import Commands.AddCommand;
import Commands.UpdateIdCommand;
import Commands.RemoveByIdCommand;
import Commands.ClearCommand;
import Commands.SaveCommand;
import Commands.ExecuteScriptCommand;
import Commands.ExitCommand;
import Commands.RemoveGreaterCommand;
import Commands.RemoveLowerCommand;
import Commands.HistoryCommand;
import Commands.FilterGreaterThenSpeakingCommand;
import Commands.FilterLessThenSpeakingCommand;
import Commands.PrintByWingspanCommand;
import Commands.HelpCommand;

import Creators.DragonCreator;

import java.util.ArrayList;

public class CommandManager {
    private final ArrayList<AbstractCommand> commands = new ArrayList<>();
    private final ArrayList<AbstractCommand> history = new ArrayList<>();

    public CommandManager(CollectionManager collectionManager,
                          InputManager inputManager, OutputManager outputManager, DragonCreator dragonCreator) {
        commands.add(new InfoCommand(collectionManager, outputManager, this));
        commands.add(new ShowCommand(collectionManager, outputManager, this));
        commands.add(new AddCommand(collectionManager, outputManager, dragonCreator, this));
        commands.add(new UpdateIdCommand(collectionManager, inputManager, outputManager, this, dragonCreator));
        commands.add(new RemoveByIdCommand(collectionManager, this));
        commands.add(new ClearCommand(collectionManager, this));
        commands.add(new SaveCommand(collectionManager, outputManager, inputManager, this));
        commands.add(new ExecuteScriptCommand(inputManager, this));
        commands.add(new ExitCommand());
        commands.add(new RemoveGreaterCommand(collectionManager, outputManager, this, dragonCreator));
        commands.add(new RemoveLowerCommand(collectionManager, outputManager, this, dragonCreator));
        commands.add(new HistoryCommand(outputManager, this));
        commands.add(new FilterGreaterThenSpeakingCommand(collectionManager, outputManager, this));
        commands.add(new FilterLessThenSpeakingCommand(collectionManager, outputManager, this));
        commands.add(new PrintByWingspanCommand(collectionManager, outputManager, this));
        commands.add(new HelpCommand(commands, outputManager, this));
    }

    public ArrayList<AbstractCommand> getCommands() {
        return commands;
    }

    public ArrayList<AbstractCommand> getHistory(){
        return history;
    }

    public void addToHistory(AbstractCommand command){
        history.add(command);
        if (history.size() > 15){
            history.remove(0);
        }
    }
}
