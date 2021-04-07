package idatt1002_2021_k1_08;

import java.time.LocalDate;

/**
 * The type Task.
 */
public class Task {

    private String taskName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String priority;
    private String status;

    /**
     * Instantiates a new Task.
     *
     * @param taskName    the task name
     * @param description the description
     * @param startDate   the start date
     * @param endDate     the end date
     * @param priority    the priority
     * @param status      the status
     */
//Constructor if all parameters are good and written in their respective FXML textfields
    public Task (String taskName, String description, LocalDate startDate, LocalDate endDate, String priority, String status){
        setDescription(description);
        setEndDate(endDate);
        setStartDate(startDate);
        setPriority(priority);
        setTaskName(taskName);
        setStatus(status);
    }

    /**
     * Instantiates a new Task.
     *
     * @param taskName    the task name
     * @param description the description
     * @param status      the status
     */
//Constructor if there is no priority, start or end date to task.
    public Task(String taskName, String description, String status){
        setDescription(description);
        setTaskName(taskName);
        setStatus(status);
        this.startDate = LocalDate.now();
        this.endDate = null;
    }

    /**
     * Get task name string.
     *
     * @return the string
     */
    public String getTaskName(){
        return taskName;
    }

    /**
     * Sets task name.
     *
     * @param taskName the task name
     */
    public void setTaskName(String taskName) {
        checkIfNull(taskName);
        checkIfEmpty(taskName);
        this.taskName = taskName;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        checkIfEmpty(description);
        checkIfNull(description);
        this.description = description;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        if(validateStartDate(startDate)){
            this.startDate = startDate;
        }
    }

    private boolean validateStartDate(LocalDate startDate) {
        if(startDate == null){
            throw new NullPointerException("Startdate cant be null");
        }
        return true;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {
        if(validateEndDate(endDate)){
            this.endDate = endDate;
        }
    }

    private boolean validateEndDate(LocalDate endDate) {
        if(endDate == null){
            throw new NullPointerException("Endate cant be null");
        }
        else if(endDate.isBefore(this.getStartDate())){
            throw new IllegalArgumentException("End date cant be before start date");
        }
        return true;
    }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(String priority) {
        checkIfEmpty(priority);
        checkIfNull(priority);
        this.priority = priority;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        checkIfNull(status);
        checkIfEmpty(status);
        this.status = status;
    }

    private void checkIfEmpty(String s){
       if(s.isEmpty()){
           throw new IllegalArgumentException("The String cant be empty");
       }
    }
    private void checkIfNull(String s){
        if(s == null){
            throw new NullPointerException("The String cant be null");
        }
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
