package com.taskplanner.dto;
import java.util.Date;

import com.taskplanner.enums.FeatureImpact;
import com.taskplanner.enums.TaskStatus;
import com.taskplanner.enums.TaskType;

public class Feature extends Task {
    private String summary;
    private FeatureImpact impact;

    public Feature(String title, String creator, String assignee, TaskStatus status, Date dueDate, String summary, FeatureImpact impact) {
        super(title, creator, assignee, status, TaskType.FEATURE, dueDate);
        this.summary = summary;
        this.impact = impact;
    }

    @Override
    public void updateStatus(TaskStatus newStatus) {
        if (status == TaskStatus.OPEN && newStatus == TaskStatus.IN_PROGRESS ||
            status == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.TESTING ||
            status == TaskStatus.TESTING && newStatus == TaskStatus.DEPLOYED ||
            status == TaskStatus.IN_PROGRESS && newStatus == TaskStatus.DEPLOYED) {
            this.status = newStatus;
        } else {
            throw new IllegalStateException("Invalid status transition for Feature.");
        }
    }

}
