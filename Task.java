package model;

public class Task extends AbstractTask {
    private String status;
    
    public Task() {
        super();
        this.status = "NOT STARTED";
    }
    
    public Task(int id, String name, String description, String taskStatus) {
        super(id, name, description);
        status = taskStatus;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String taskStatus) {
        status = taskStatus;
    }
}