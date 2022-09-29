package Creators;

import Classes.Color;
import Classes.Coordinates;
import Classes.Dragon;
import Classes.DragonCave;

import Exceptions.FieldNullException;
import Exceptions.IncorrectFieldValueException;
import Exceptions.InvalidInputException;

import Managers.CollectionManager;
import Managers.InputManager;
import Managers.OutputManager;

import java.util.Arrays;
import java.util.Objects;

/**
 * Class that creates new Dragon
 * @see Dragon
 */
public class DragonCreator {
    private final OutputManager outputManager;
    private final InputManager inputManager;
    private final CollectionManager collectionManager;
    private final CaveCreator caveCreator;
    private final CoordinatesCreator coordinatesCreator;

    /**
     * Constructor.
     * @param inputManager object of class {@link InputManager InputManager}
     * @param outputManager object of class {@link OutputManager OutputManager}
     * @param collectionManager object of class {@link CollectionManager CollectionManager}
     * @param caveCreator object of class {@link CaveCreator CaveCreator}
     * @param coordinatesCreator object of class {@link CoordinatesCreator CoordinatesCreator}
     * @see InputManager
     * @see OutputManager
     * @see CollectionManager
     * @see CaveCreator
     * @see CoordinatesCreator
     */
    public DragonCreator(InputManager inputManager, OutputManager outputManager,
                         CollectionManager collectionManager, CaveCreator caveCreator,
                         CoordinatesCreator coordinatesCreator){
        this.inputManager = inputManager;
        this.outputManager = outputManager;
        this.collectionManager = collectionManager;
        this.caveCreator = caveCreator;
        this.coordinatesCreator = coordinatesCreator;
    }

    public CoordinatesCreator getCoordinatesCreator(){
        return coordinatesCreator;
    }

    public CaveCreator getCaveCreator(){
        return caveCreator;
    }

    /**
     * Method that creates new Dragon with data taken from user
     * @return new {@link Dragon Dragon}
     * @throws InvalidInputException
     * @throws FieldNullException
     * @throws IncorrectFieldValueException
     * @see Dragon
     */
    public Dragon createNewDragon() throws InvalidInputException, FieldNullException, IncorrectFieldValueException {
        outputManager.println("Введите имя дракона");
        String name = inputManager.read();
        Coordinates coordinates = coordinatesCreator.CreateNewCoordinates();
        outputManager.println("Введите возраст дракона");
        int age = Integer.parseInt(inputManager.read());
        outputManager.println("Введите размах крыльев дракона");
        float wingspan = Float.parseFloat(inputManager.read());
        outputManager.println("Ваш дракон говорящий?(да/y/yes или нет/n/no)");
        String ans = inputManager.read();
        boolean speaking;
        if (Objects.equals(ans.toLowerCase(), "да") || Objects.equals(ans.toLowerCase(), "yes") || Objects.equals(ans.toLowerCase(), "y")){
            speaking = true;
        } else if (Objects.equals(ans.toLowerCase(), "нет") || Objects.equals(ans.toLowerCase(), "no") || Objects.equals(ans.toLowerCase(), "n")){
            speaking = false;
        } else {
            throw new InvalidInputException(ans);
        }
        outputManager.println("Выберите цвет вашего дракона. Он может быть: " + Arrays.toString(Color.values()) + "(можно по-английски)");
        Color color = Color.getColorByName(inputManager.read());
        DragonCave cave = caveCreator.createNewDragonCave();
        return new Dragon(name, coordinates, age, wingspan, speaking, color, cave);
    }
}
