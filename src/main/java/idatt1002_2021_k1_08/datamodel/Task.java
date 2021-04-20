package idatt1002_2021_k1_08.datamodel;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type Task.
 */
public class Task implements Serializable {

    private String taskName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String priority;
    private static final long SerialVersionUID = 1L;
    private String category;
    private boolean completed;


    /**
     * Instantiates a new Task.
     *
     * @param taskName    the task name
     * @param endDate     the end date
     * @param priority    the priority
     */

//Constructor if all parameters are good and written in their respective FXML textfields
    public Task (String taskName, String category, LocalDate endDate, String priority){
        this.startDate = LocalDate.now();
        setEndDate(endDate);
        setTaskName(taskName);
        setPriority(priority);
        setCompleted(false);
        setCategory(category);
        if (category == null){
            setCategory("");
        }

    }

    /**
     * gets the value of completed
     *
     * @return true or false
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the value of completed
     *
     * @param completed sets the value of completed, true or false
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
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
     * Get category
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets task name.
     *
     * @param taskName the task name
     */
    public void setTaskName(String taskName) {
        checkifNull(taskName);
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

        this.description = description;
    }

    /**
     * Sets startDate, this is defined as LocalDate.now();
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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
     * Gets end date.
     *
     * @return the end date
     */
    public LocalDate getEndDate() {
        return endDate;
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
     * Sets the priority on input
     *
     * @param priority the priority
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Sets the category on input
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Helper method to simplify sorting
     *
     * @return 3, 2 or 1 for the priorty. Each string represents an integer and the integer returns.
     */
    public int setPriorityNumber(){
        if (priority.equals("High")){
            return 3;
        }
        else if (priority.equals("Medium")){
            return 2;
        }
        else{
            return 1;
        }
    }

    /**
     * Checks if String is null
     *
     * @param string every possible string
     */
    private void checkifNull(String string){
        if(string == (null)){
            throw new NullPointerException("String cant be null");
        }
    }

    /**
     * Checks if String is empty
     *
     * @param string every possible string
     */
    private void checkIfEmpty(String string){
        if(string.isEmpty()){
            throw new IllegalArgumentException("String cant be empty");
        }
    }

    /**
     * sets the endDate.
     * @throws IllegalArgumentException will happen if endDate is Earlier that startDate.
     * This will only display in terminal. In app, this will be an alertbox for the user.
     *
     * @param endDate LocalDate endDate will be read from input in datepicker
     */
    public void setEndDate(LocalDate endDate) {
        if(endDate.isBefore(startDate)){
            throw new IllegalArgumentException("End date cant be before start date - " + startDate);
        }
        this.endDate = endDate;
    }

    /**
     * Simple toString for task readout.
     * @return String taskname, endDate, priority
     */
    @Override
    public String toString() {
        return taskName + "   Deadline: " + endDate + "   Priority " + priority + completed;
    }
}
