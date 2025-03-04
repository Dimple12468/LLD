package com.taskplanner;

import com.taskplanner.dao.SprintService;
import com.taskplanner.dao.TaskService;
import com.taskplanner.enums.*;

import java.util.Date;

import com.taskplanner.dao.*;
import com.taskplanner.dto.*;

public class App {
    public static void main(String[] args) {
        // Create service instances
        TaskService taskService = new TaskService();
        SprintService sprintService = new SprintService();

        // Create some tasks
        Task featureTask = new Feature("Feature 1", "Creator1", "Assignee1", TaskStatus.OPEN, new Date(), "Feature Summary", FeatureImpact.HIGH);
        Task bugTask = new Bug("Bug 1", "Creator2", "Assignee2", TaskStatus.OPEN, new Date(), BugSeverity.P1);
        Task storyTask = new Story("Story 1", "Creator3", "Assignee3", TaskStatus.OPEN, new Date(), "Story Summary");

        // Add tasks to TaskService
        taskService.createTask(featureTask);
        taskService.createTask(bugTask);
        taskService.createTask(storyTask);

        // Change status of tasks
        taskService.changeStatus("Feature 1", TaskStatus.IN_PROGRESS);
        taskService.changeStatus("Bug 1", TaskStatus.IN_PROGRESS);

        // Create a sprint
        sprintService.createSprint("Sprint 1");

        // Add tasks to sprint
        sprintService.addTaskToSprint("Sprint 1", featureTask);
        sprintService.addTaskToSprint("Sprint 1", bugTask);
        sprintService.addTaskToSprint("Sprint 1", storyTask);

        // Display tasks by assignee
        System.out.println("Tasks assigned to Assignee1:");
        taskService.getTasksByAssignee("Assignee1").forEach(task -> System.out.println(task.getTitle()));

        // Display sprint snapshot
        Sprint sprint = sprintService.getSprintByName("Sprint 1");
        System.out.println("Sprint Snapshot:");
        sprint.getTasks().forEach(task -> System.out.println(task.getTitle() + " - Status: " + task.getStatus()));
    }
}
