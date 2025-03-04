package com.taskplanner.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.taskplanner.dto.Task;
import com.taskplanner.enums.TaskStatus;

public class TaskService {
    private Map<String, Task> tasks = new HashMap<>();

    public void createTask(Task task) {
        tasks.put(task.getTitle(), task);
    }

    public void changeStatus(String title, TaskStatus newStatus) {
        if (tasks.containsKey(title)) {
            tasks.get(title).updateStatus(newStatus);
        }
    }

    public List<Task> getTasksByAssignee(String assignee) {
        return tasks.values().stream()
                .filter(task -> task.getAssignee().equals(assignee))
                .collect(Collectors.toList());
    }
}
