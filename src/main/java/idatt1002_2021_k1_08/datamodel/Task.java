package idatt1002_2021_k1_08.datamodel;

import java.time.LocalDate;

public class Task {

    private String taskName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String priority;
    private String status;

    //Constructor if all parameters are good and written in their respective FXML textfields
    public Task (String taskName, String description, LocalDate startDate, LocalDate endDate, String priority, String status){
        this.description = description;
        this.endDate = endDate;
        this.startDate = startDate;
        this.priority = priority;
        this.taskName = taskName;
        this.status = status;
    }
    //Constructor if there is no priority, start or end date to task.
    public Task(String taskName, String description, String status){
        this.description = description;
        this.taskName = taskName;
        this.status = status;
        this.startDate = LocalDate.now();
        this.endDate = null;
    }

    public String getTaskName(){
        return taskName;
    }

    public void setTaskName(String taskName) {
        if(taskName.equals("") || taskName == null){
            throw new IllegalArgumentException("Cannot be null or empty");
        }else{
            this.taskName = taskName;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description.equals("")||description == null){
            throw new IllegalArgumentException("Cannot be null or empty");
        }else{
            this.description = description;
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate == null || startDate.equals("") || startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Cannot be null or empty");
        }else {
            this.startDate = startDate;
        }
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if(endDate.isBefore(startDate) || endDate.equals("") || endDate == null){
            throw new IllegalArgumentException("Cannot be null or empty");
        }else{
            this.endDate = endDate;
        }
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        if(priority.equals("") || priority == null){
            throw new IllegalArgumentException("Cannot be null or empty");
        }else {
            this.priority = priority;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(status.equals("") || status == null){
            throw new IllegalArgumentException("Cannot be null or empty");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task: " +
                "\ntaskName: " + taskName +
                "\ndescription: " + description +
                "\nstartDate: " + startDate +
                "\nendDate: " + endDate +
                "\npriority: " + priority +
                "\nstatus: " + status ;
    }
}
