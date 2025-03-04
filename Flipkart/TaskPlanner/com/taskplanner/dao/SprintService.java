package com.taskplanner.dao;

import java.util.HashMap;
import java.util.Map;

import com.taskplanner.dto.Sprint;
import com.taskplanner.dto.Task;

public class SprintService {

    private Map<String, Sprint> sprints = new HashMap<>();

    public void createSprint(String sprintName) {
        sprints.put(sprintName, new Sprint(sprintName));
    }

    public void addTaskToSprint(String sprintName, Task task) {
        if (sprints.containsKey(sprintName)) {
            sprints.get(sprintName).addTask(task);
        }
    }

    public Sprint getSprintByName(String sprintName) {
        return sprints.get(sprintName); // Return the sprint by name
    }

}
