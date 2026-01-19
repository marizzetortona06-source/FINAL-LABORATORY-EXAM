package model;

public abstract class AbstractTask {
    private int taskId;
    private String taskName;
    private String taskDescription;
    
    public AbstractTask() {
        this.taskId = 0;
        this.taskName = "";
        this.taskDescription = "";
    }
    
    public AbstractTask(int id, String name, String description) {
        taskId = id;
        taskName = name;
        taskDescription = description;
    }
    
    public int getTaskId() {
        return this.taskId;
    }
    
    public String getTaskName() {
        return this.taskName;
    }
    
    public String getTaskDescription() {
        return this.taskDescription;
    }
    
    public void setTaskId(int id) {
        taskId = id;
    }
    
    public void setTaskName(String name) {
        taskName = name;
    }
    
    public void setTaskDescription(String description) {
        taskDescription = description;
    }
}
