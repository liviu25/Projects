package domain;

public abstract class Task {



    private String taskID,descriere;
    public Task(String taskID, String descriere) {
        this.taskID = taskID;
        this.descriere = descriere;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    public abstract void execute();

    @Override
    public String toString(){
        return this.taskID+" "+this.descriere;
    }
}
