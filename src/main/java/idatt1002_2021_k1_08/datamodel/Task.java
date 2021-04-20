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
     * @param startDate   the start date
     * @param endDate     the end date
     * @param priority    the priority
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

    public boolean isCompleted() {
        return completed;
    }

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


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
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

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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



    @Override
    public String toString() {
        return taskName + "   Deadline: " + endDate + "   Priority " + priority;
    }
}
