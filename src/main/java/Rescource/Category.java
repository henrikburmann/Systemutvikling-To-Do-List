package Rescource;

import java.util.ArrayList;

public class Category {
    private String categoryName;
    private ArrayList<Task> tasks;

    public String getCategoryName(){
        return categoryName;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public boolean addTask(Task task){
        for (Task t: tasks){
            if (task.equals(t)){
                return false;
            }
        }
        tasks.add(task);
        return true;
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }
}
