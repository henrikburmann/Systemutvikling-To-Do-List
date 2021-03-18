package Rescource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


public class Task {
    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String priority;
    private Category category;
    private String description;

    public Task(String taskName, LocalDateTime startTime, LocalDate endTime, String priority,
                Category category, String description){
        if (taskName == null || taskName.equals("")){
            throw new IllegalArgumentException("Task must have a name");
        }
        this.taskName = taskName;
        if (startTime.equals("")){
            this.startTime = LocalDateTime.now();
        }
        this.startTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(),
                startTime.getHour(), startTime.getMinute(), 0);

        this.endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(),
                startTime.getHour(), startTime.getMinute(), 0);
        this.priority = priority;
        this.category = category;
        this.description = description;
    }

    public Task(String taskName, LocalDateTime startTime, LocalDateTime endTime, String priority,
                Category category){
        if (taskName == null || taskName.equals("")){
            throw new IllegalArgumentException("Task must have a name");
        }
        this.taskName = taskName;
        if (startTime.equals("")){
            this.startTime = LocalDateTime.now();
        }
        this.startTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(),
                startTime.getHour(), startTime.getMinute(), 0);

        this.endTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(),
                startTime.getHour(), startTime.getMinute(), 0);
        this.priority = priority;
        this.category = category;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName) &&
                Objects.equals(startTime, task.startTime) &&
                Objects.equals(endTime, task.endTime) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(category, task.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, startTime, endTime, priority, category);
    }

    public String toString(){
        return taskName + "\nStart time: " + startTime + "\nEnd time: " + endTime + "\nPriority: " + priority +
                "\nCategory: " + category.getCategoryName() + "\nDescription: " + description;
    }
}
