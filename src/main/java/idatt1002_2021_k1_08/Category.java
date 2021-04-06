package idatt1002_2021_k1_08;

import java.util.ArrayList;


// Need to find a better way of checking if not null or empty, also
// consider making separate methods for this, possible validation for other checks than not null or empty
// nullpointexception for empty object

public class Category {
    private String name;
    private ArrayList<Task> tasks;

    /**
     *
     * @param name
     */
    public Category(String name){
        if (name == null || name.equals("")){
            throw new IllegalArgumentException("Category must have a name");
        }
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public String getName(){
        return name;
    }
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void setName(String name){
        this.name = name;
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
        text.append(name + ":\n");
        tasks.forEach(e -> text.append(e.toString()));
        text.append("\n");
        return text.toString();
    }
}
