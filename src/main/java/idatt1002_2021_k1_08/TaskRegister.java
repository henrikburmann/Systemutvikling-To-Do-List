package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Task;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class TaskRegister {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public TaskRegister(){
    }

    public static ArrayList<Task> getTasks() {
        if(tasks.isEmpty()){
            System.out.println("No tasks inside task list");
        }
        return tasks;
    }

    public static boolean addTask(Task task){
        try{
            if(!(tasks.contains(task))){
                tasks.add(task);
            }else {
                throw new IllegalArgumentException("Task already inn list");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean removeTask(Task task){
        try{
            if(!(tasks.contains(task))){
                throw new IllegalArgumentException("Task not inn list");
            }else{
                tasks.remove(task);
            }
        }catch (Exception e ){
            e.printStackTrace();
        }
        return true;
    }


}
