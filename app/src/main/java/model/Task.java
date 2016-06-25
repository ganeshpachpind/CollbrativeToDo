package model;

public class Task {


    // should be either public or should have getter setter
    String taskName;
    Integer priority;

    public Task(String taskName, Integer priority) {
        this.taskName = taskName;
        this.priority = priority;
    }

    // should empty constructor
    public Task() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
