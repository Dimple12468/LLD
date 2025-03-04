package com.taskplanner.dto;
import java.util.Date;

import com.taskplanner.enums.*;

public class Bug extends Task {
    private BugSeverity severity;

    public Bug(String title, String creator, String assignee, TaskStatus status, Date dueDate, BugSeverity severity) {
        super(title, creator, assignee, status, TaskType.BUG, dueDate);
        this.severity = severity;
    }

    @Override
    public void updateStatus(TaskStatus newStatus) {
        if (status == TaskStatus.OPEN && newStatus == TaskStatus.IN_PROGRESS ||
            status == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.FIXED) {
            this.status = newStatus;
        } else {
            throw new IllegalStateException("Invalid status transition for Bug.");
        }
    }

}
