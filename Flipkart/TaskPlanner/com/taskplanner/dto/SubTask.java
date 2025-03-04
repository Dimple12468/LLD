package com.taskplanner.dto;
import com.taskplanner.enums.SubTaskStatus;

public class SubTask {
    private String title;
    private SubTaskStatus status;

    public SubTask(String title, SubTaskStatus status) {
        this.title = title;
        this.status = status;
    }

    public void updateStatus(SubTaskStatus newStatus) {
        this.status = newStatus;
    }
}
