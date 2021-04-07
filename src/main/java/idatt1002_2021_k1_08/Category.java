package idatt1002_2021_k1_08;

import java.util.ArrayList;


/**
 * The type Category.
 */
public class Category {
    private String name;
    private ArrayList<Task> tasks;

    /**
     * Instantiates a new Category.
     *
     * @param name the name
     */
    public Category(String name){
       setName(name);
       this.tasks = new ArrayList<>();
    }

    /**
     * Add task.
     *
     * @param task the task
     */
    public void addTask(Task task){
        tasks.add(task);
    }

    /**
     * Remove task.
     *
     * @param task the task
     */
    public void removeTask(Task task){
        tasks.remove(task);
    }

    /**
     * Get name string.
     *
     * @return the string
     */
    public String getName(){
        return name;
    }

    /**
     * Get tasks array list.
     *
     * @return the array list
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * Set name.
     *
     * @param name the name
     */
    public void setName(String name){
       if(name.equals(this.name)){
           throw new IllegalArgumentException("Cant be the same name as before");
       }
       else if(name.isEmpty()){
           throw new IllegalArgumentException("Name string cant be empty");
       }
       else {
           this.name = name;
       }
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        else if (!(o instanceof Category)){
            return false;
        }
        Category category = (Category) o;
        return category.getName().equals(this.getName());
    }

    public String toString(){
        StringBuilder text = new StringBuilder();
        text.append(name).append(":\n");
        tasks.forEach(e -> text.append(e.toString()));
        text.append("\n");
        return text.toString();
    }
}
