package com.taskplanner.dto;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.taskplanner.enums.TaskStatus;
import com.taskplanner.enums.TaskType;

public class Story extends Task {
    private String summary;
    private List<SubTask> subTasks;

    public Story(String title, String creator, String assignee, TaskStatus status, Date dueDate, String summary) {
        super(title, creator, assignee, status, TaskType.STORY, dueDate);
        this.summary = summary;
        this.subTasks = new ArrayList<>();
    }

    public void addSubTask(SubTask subTask) {
        if (this.status == TaskStatus.COMPLETED) {
            throw new IllegalStateException("Cannot add subtask to a completed Story.");
        }
        this.subTasks.add(subTask);
    }

    @Override
    public void updateStatus(TaskStatus newStatus) {
        if (newStatus == TaskStatus.OPEN || newStatus == TaskStatus.IN_PROGRESS || newStatus == TaskStatus.COMPLETED) {
            this.status = newStatus;
        } else {
            throw new IllegalStateException("Invalid status transition for Story.");
        }
    }

}
