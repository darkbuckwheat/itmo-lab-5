package Commands;

public abstract class AbstractCommand {
    private final String name;
    private final String description;
    private boolean executeFlag = true;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getExecuteFlag() {
        return executeFlag;
    }

    public void setExecuteFlag(boolean executeFlag) {
        this.executeFlag = executeFlag;
    }

    public abstract void execute(String args) throws Exception;

    public String toString(){
        return name;
    }
}
