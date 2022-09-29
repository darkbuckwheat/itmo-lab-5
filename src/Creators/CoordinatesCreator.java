package Creators;

import Classes.Coordinates;

import Classes.DragonCave;
import Exceptions.IncorrectFieldValueException;
import Exceptions.InvalidInputException;

import Managers.InputManager;
import Managers.OutputManager;

/**
 * Class that creates new Coordinates
 * @see Coordinates
 */
public class CoordinatesCreator {
    private final OutputManager outputManager;
    private final InputManager inputManager;

    /**
     * Constructor.
     * @param inputManager object of class {@link InputManager InputManager}
     * @param outputManager object of class {@link OutputManager OutputManager}
     * @see InputManager
     * @see OutputManager
     */
    public CoordinatesCreator(InputManager inputManager, OutputManager outputManager){
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }

    /**
     * This method takes input from user and creates Coordinates with provided x and y.
     * @return new {@link Coordinates Coordinates}
     * @throws InvalidInputException
     * @throws IncorrectFieldValueException
     * @see Coordinates
     */
    public Coordinates CreateNewCoordinates() throws IncorrectFieldValueException, InvalidInputException {
        outputManager.println("Задайте координату x");
        long x = Long.parseLong(inputManager.read());
        outputManager.println("Задайте координату y");
        double y = Double.parseDouble(inputManager.read());
        return new Coordinates(x, y);
    }
}
