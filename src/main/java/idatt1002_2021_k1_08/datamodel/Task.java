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
     * @param taskName  the task name
     * @param category  the category
     * @param startDate the start date
     * @param endDate   the end date
     * @param priority  the priority
     */
//Constructor if all parameters are good and written in their respective FXML textfields
    public Task (String taskName, String category,LocalDate startDate, LocalDate endDate, String priority){
        setEndDate(endDate);
        setStartDate(LocalDate.now());
        setEndDate(endDate);
        setTaskName(taskName);
        setPriority(priority);
        setCompleted(false);
        setCategory(category);

    }

    /**
     * Is completed boolean.
     *
     * @return the boolean
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets completed.
     *
     * @param completed the completed
     */
    public void setCompleted(boolean completed) {
        if(this.completed == completed){
            throw new IllegalArgumentException("Completed value cant be the same");
        }
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
     * @return category category
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
        checkIfEmpty(taskName);
        checkifNull(taskName);
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
        checkifNull(description);
        checkIfEmpty(description);
        this.description = description;
    }


    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(LocalDate endDate) {
        if(endDate.isBefore(this.startDate)){
            throw new IllegalArgumentException("End date cant be before start date - " + this.startDate);
        }
        this.endDate = endDate;
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
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(String priority) {
        checkIfEmpty(priority);
        checkifNull(priority);
        this.priority = priority;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(String category) {
        checkifNull(category);
        checkIfEmpty(category);
        this.category = category;
    }

    /**
     * Set priority number int.
     *
     * @return the int
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

    private void checkifNull(String string){
        if(string == (null)){
            throw new NullPointerException("String cant be null");
        }
    }

    private void checkIfEmpty(String string){
        if(string.isEmpty()){
            throw new IllegalArgumentException("String cant be empty");
        }
    }

    @Override
    public String toString() {
        return taskName + "   Deadline: " + endDate + "   Priority " + priority;
    }
}
