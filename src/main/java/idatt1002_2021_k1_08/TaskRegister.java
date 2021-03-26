package idatt1002_2021_k1_08;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class TaskRegister {

    private ObservableList<Task> tasks;

    public TaskRegister(ObservableList<Task> tasks){
        if(tasks.isEmpty()){
            System.out.println("No tasks inside task list");
        }



        this.tasks = new ObservableList<>() {

            @Override
            public void addListener(ListChangeListener<? super Task> listChangeListener) {

            }

            @Override
            public void removeListener(ListChangeListener<? super Task> listChangeListener) {

            }

            @Override
            public boolean addAll(Task... tasks) {
                return false;
            }

            @Override
            public boolean setAll(Task... tasks) {
                return false;
            }

            @Override
            public boolean setAll(Collection<? extends Task> collection) {
                return false;
            }

            @Override
            public boolean removeAll(Task... tasks) {
                return false;
            }

            @Override
            public boolean retainAll(Task... tasks) {
                return false;
            }

            @Override
            public void remove(int i, int i1) {

            }

            @Override
            public int size() {
                return tasks.size();
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Task> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Task task) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Task> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Task> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Task get(int index) {
                return null;
            }

            @Override
            public Task set(int index, Task element) {
                return null;
            }

            @Override
            public void add(int index, Task element) {

            }

            @Override
            public Task remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Task> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Task> listIterator(int index) {
                return null;
            }

            @Override
            public List<Task> subList(int fromIndex, int toIndex) {
                return null;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }
        };
    }

    public ObservableList<Task> getTasks() {
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
