package com.taskplanner.dto;
import java.util.ArrayList;
import java.util.List;

public class Sprint {
    public String sprintName;
    public List<Task> tasks;

    public Sprint(String sprintName) {
        this.sprintName = sprintName;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
