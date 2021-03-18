package Rescource;

import java.time.LocalDateTime;

public class Task {
    private String TaskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String priority;
    private Category category;
    private String description;

    public Task(String taskName, LocalDateTime startTime, LocalDateTime endTime, String priority,
                Category category, String description){
        if (taskName == null || taskName.equals("")){
            throw new IllegalArgumentException("Task must have a name");
        }
        if (startTime.equals("")){

        }

    }

    public Task(String taskName, LocalDateTime startTime, LocalDateTime endTime, String priority,
                Category category){}
}
