package controller;

import model.Task;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }
    
    public void createTask(Task taskItem) {
        taskList.add(taskItem);
    }
    
    public void deleteTask(int taskId) {
        Task toRemove = null;
        for (Task taskItem : taskList) {
            if (taskItem.getTaskId() == taskId) {
                toRemove = taskItem;
                break;
            }
        }
        if (toRemove != null) {
            taskList.remove(toRemove);
        }
    }
    
    public void modifyTask(int taskId, String taskName, String taskDescription, String taskStatus) {
        for (Task taskItem : taskList) {
            if (taskItem.getTaskId() == taskId) {
                taskItem.setTaskName(taskName);
                taskItem.setTaskDescription(taskDescription);
                taskItem.setStatus(taskStatus);
                return;
            }
        }
    }
    
    public ArrayList<Task> viewAllTasks() {
        return this.taskList;
    }
    
    public Task findTask(int taskId) {
        for (int i = 0; i < taskList.size(); i++) {
            Task taskItem = taskList.get(i);
            if (taskItem.getTaskId() == taskId) {
                return taskItem;
            }
        }
        return null;
    }
    
    public int generateNewId() {
        int maxId = 0;
        for (Task taskItem : taskList) {
            int currentId = taskItem.getTaskId();
            if (currentId > maxId) {
                maxId = currentId;
            }
        }
        return maxId + 1;
    }
}