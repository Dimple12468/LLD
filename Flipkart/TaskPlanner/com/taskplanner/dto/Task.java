package com.taskplanner.dto;
import java.util.Date;

import com.taskplanner.enums.TaskStatus;
import com.taskplanner.enums.TaskType;

public abstract class Task {

    public String title;
    public String creator;
    public String assignee;
    public TaskStatus status;
    public TaskType type;
    public Date dueDate;

    public Task(String title, String creator, String assignee, TaskStatus status, TaskType type, Date dueDate) {
        this.title = title;
        this.creator = creator;
        this.assignee = assignee;
        this.status = status;
        this.type = type;
        this.dueDate = dueDate;
    }

    public abstract void updateStatus(TaskStatus newStatus);

    public void changeAssignee(String newAssignee) {
        this.assignee = newAssignee;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignee() {
        return assignee;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskType getType() {
        return type;
    }
    
}
