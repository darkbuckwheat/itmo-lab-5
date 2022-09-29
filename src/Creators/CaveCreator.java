package Creators;

import Classes.DragonCave;

import Exceptions.InvalidInputException;

import Managers.InputManager;
import Managers.OutputManager;

/**
 * Class that creates new DragonCaves
 * @see DragonCave
 */
public class CaveCreator {
    private final InputManager inputManager;
    private final OutputManager outputManager;

    /**
     * Constructor.
     * @param inputManager object of class {@link InputManager InputManager}
     * @param outputManager object of class {@link OutputManager OutputManager}
     * @see InputManager
     * @see OutputManager
     */
    public CaveCreator(InputManager inputManager, OutputManager outputManager){
        this.inputManager = inputManager;
        this.outputManager = outputManager;
    }

    /**
     * This method takes input from user and creates DragonCave with inputted depth.
     * @return new {@link DragonCave DragonCave}
     * @throws InvalidInputException
     * @see DragonCave
     */
    public DragonCave createNewDragonCave() throws InvalidInputException {
        outputManager.println("Задайте глубину пещеры");
        int depth = Integer.parseInt(inputManager.read());
        return new DragonCave(depth);
    }
}
