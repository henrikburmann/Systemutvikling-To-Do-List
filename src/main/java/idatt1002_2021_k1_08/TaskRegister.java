package idatt1002_2021_k1_08;

import java.util.ArrayList;

public class TaskRegister {

    private ArrayList<Task> tasks;

    public TaskRegister(ArrayList<Task> tasks){
        if(tasks.isEmpty()){
            System.out.println("No tasks inside task list");
        }
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        if(tasks.isEmpty()){
            System.out.println("No tasks inside task list");
        }
        return tasks;
    }

    public boolean addTask(Task task){
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

    public boolean removeTask(Task task){
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
