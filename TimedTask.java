package model;

public class TimedTask extends Task {
    private int estimatedMinutes;
    
    public TimedTask() {
        super();
        this.estimatedMinutes = 0;
    }
    
    public TimedTask(int id, String name, String description, String taskStatus, int minutes) {
        super(id, name, description, taskStatus);
        estimatedMinutes = minutes;
    }
    
    public int getEstimatedMinutes() {
        return this.estimatedMinutes;
    }
    
    public void setEstimatedMinutes(int minutes) {
        estimatedMinutes = minutes;
    }
}