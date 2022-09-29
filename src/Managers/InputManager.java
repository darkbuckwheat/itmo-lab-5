package Managers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

import Exceptions.InvalidInputException;

public class InputManager {
    private final Stack<Scanner> scanners = new Stack<>();
    private final Stack<File> files = new Stack<>();
    private boolean scriptMode = false;
    private final OutputManager outputManager;

    public InputManager(InputStream inputStream, OutputManager outputManager) {
        scanners.push(new Scanner(inputStream));
        this.outputManager = outputManager;
    }

    public String read() throws InvalidInputException {
        if (scanners.peek().hasNextLine()) {
            return scanners.peek().nextLine();
        } else {
            if (scriptMode) {
                finishReadScript();
                outputManager.printlnColorMessage("Reached the end of the file", OutputColor.GREEN);
                return read();
            }  else {
                throw new InvalidInputException("");
            }
        }
    }

    public void startReadScript(String fileName) {
        File scriptFile = new File(fileName);
        if (files.capacity() > 49){
            outputManager.printlnImportantColorMessage("Too deep a dive. " +
                    "The program can only provide 50 files in depth at a time." +
                    "Executing stopped on " + scriptFile.getName(), OutputColor.RED);
        } else if (!scriptFile.canRead()){
            outputManager.printlnImportantColorMessage("The program does not have rights to read the file"
                    + scriptFile.getName(), OutputColor.RED);
        } else if (files.contains(scriptFile)) {
            outputManager.printlnImportantColorMessage("Recursion detected in file " + files.peek().getName()
                    + ". The script " + scriptFile.getName() + " will not be executed twice!", OutputColor.RED);
        }else {
            try {
                outputManager.println("Start reading from file " + scriptFile.getName() + "...");
                scanners.push(new Scanner(scriptFile));
                files.push(scriptFile);
                scriptMode = true;
                outputManager.muteNotifications();
            } catch (IOException e) {
                outputManager.printlnImportantColorMessage("Cannot find file " + scriptFile.getName(), OutputColor.RED);
            }
        }
    }

    public boolean getScriptMode() {
        return scriptMode;
    }

    public void finishReadScript() {
        if (scriptMode) {
            if (scanners.size() == 2) {
                scriptMode = false;
                outputManager.enableNotifications();
            }
            scanners.pop();
            outputManager.println("Reading from file " + files.pop().getName() + " was finished");
        }
    }
}
