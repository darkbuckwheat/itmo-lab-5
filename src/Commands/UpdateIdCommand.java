package Commands;

import Classes.Color;
import Classes.Coordinates;
import Classes.Dragon;
import Classes.DragonCave;

import Creators.DragonCreator;

import Exceptions.IncorrectCommandArgumentException;
import Exceptions.IncorrectFieldValueException;
import Exceptions.InvalidInputException;
import Exceptions.NumberOfArgumentException;

import Managers.CollectionManager;
import Managers.InputManager;
import Managers.OutputManager;
import Managers.CommandManager;
import Managers.OutputColor;

import java.util.Arrays;
import java.util.Objects;

public class UpdateIdCommand extends AbstractCommand{
    private final CollectionManager collectionManager;
    private final InputManager inputManager;
    private final OutputManager outputManager;
    private final CommandManager commandManager;
    private final DragonCreator dragonCreator;

    public UpdateIdCommand(CollectionManager collectionManager, InputManager inputManager, OutputManager outputManager,
                           CommandManager commandManager, DragonCreator dragonCreator){
        super("update", "Обновляет значения полей элемента коллекции по id");
        this.collectionManager = collectionManager;
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.commandManager = commandManager;
        this.dragonCreator = dragonCreator;
    }

    public void execute(String arg) throws NumberOfArgumentException, InvalidInputException,
            IncorrectFieldValueException, IncorrectCommandArgumentException {
        if (arg.isEmpty()){
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
            try {
                Dragon ob = (Dragon) collectionManager.getCollection().stream().filter(
                        it -> it.getId().equals(Long.parseLong(arg))).toArray()[0];
                outputManager.println("Введите имя дракона");
                String name = inputManager.read();
                ob.setName(name);
                Coordinates coordinates = dragonCreator.getCoordinatesCreator().CreateNewCoordinates();
                ob.setCoordinates(coordinates);
                outputManager.println("Введите возраст дракона");
                int age = Integer.parseInt(inputManager.read());
                ob.setAge(age);
                outputManager.println("Введите размах крыльев дракона");
                float wingspan = Float.parseFloat(inputManager.read());
                ob.setWingspan(wingspan);
                outputManager.println("Ваш дракон говорящий?(да/нет)");
                String ans = inputManager.read();
                boolean speaking;
                if (Objects.equals(ans, "да")){
                    speaking = true;
                } else if (Objects.equals(ans, "нет")){
                    speaking = false;
                } else {
                    throw new InvalidInputException(ans);
                }
                ob.setSpeaking(speaking);
                outputManager.println("Выберите цвет вашего дракона. Он может быть: " + Arrays.toString(Color.values()));
                Color color = Color.getColorByName(inputManager.read());
                ob.setColor(color);
                DragonCave cave = dragonCreator.getCaveCreator().createNewDragonCave();
                ob.setCave(cave);
                commandManager.addToHistory(this);
            }
            catch (InvalidInputException | IncorrectFieldValueException | NumberFormatException e){
                outputManager.printlnImportantColorMessage("You typed an incorrect value, try again from the beginning",
                        OutputColor.RED);
                this.execute(arg);
            }
        }
    }
}
