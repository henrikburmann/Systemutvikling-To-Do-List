package idatt1002_2021_k1_08.datamodel;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private String name;
    private ArrayList<Task> tasks;

    public Category(String name){
        if (name == null || name.equals("")){
            throw new IllegalArgumentException("Category must have a name");
        }
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        try{


        }catch (Exception e){
            e.printStackTrace();
        }
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
