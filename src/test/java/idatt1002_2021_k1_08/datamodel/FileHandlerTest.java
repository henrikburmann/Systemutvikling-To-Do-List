package idatt1002_2021_k1_08.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

class FileHandlerTest {

    static FileHandler fileHandler;
    static Task task1;
    static Task task2;
    static Task task3;
    static ObservableList<Task> tasks;

    static File FILE_PATH_CATEGORY;
    static File FILE_PATH_TASKS;

    @BeforeAll
    static void setUp() throws IOException{
        FILE_PATH_CATEGORY = new File("src/test/resources/DataStorageTest/CategoryStringsTest.ser");
        FILE_PATH_TASKS = new File("src/test/resources/DataStorageTest/TaskData.ser");

        fileHandler = new FileHandler();

        //FILE_PATH_TASKS.createNewFile();
        //FILE_PATH_CATEGORY.createNewFile();

        task1 = new Task("Test1","Category1",
                LocalDate.now().plusDays(1), "high");

        task2 = new Task("Test2","Category2",
                LocalDate.now().plusDays(1), "medium");

        task3 = new Task("Test3","Category3",
                LocalDate.now().plusDays(1), "medium");

        tasks = FXCollections.observableArrayList();

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }


    @Test
    public void check_return_of_filehandler_instance() {
        FileHandler instance = new FileHandler();
        Assertions.assertInstanceOf(FileHandler.getInstance().getClass(), instance);
    }

    @Test
    void check_if_observablelist_contains_task() {
        Assertions.assertEquals(tasks.get(0), task1);
        Assertions.assertEquals(tasks.get(1), task2);
        Assertions.assertEquals(tasks.get(2), task3);
    }

    @Test
    void check_if_categories_is_cool() {
        Assertions.assertEquals(task1.getCategory(), "Category1");
        Assertions.assertEquals(task2.getCategory(), "Category2");
        Assertions.assertEquals(task3.getCategory(), "Category3");
    }

    @Test
    void check_if_task_is_added() {
        Task task4 = new Task("Test4", "Category4", LocalDate.now().plusDays(1), "high");
        tasks.add(task4);
        Assertions.assertTrue(tasks.contains(task4));
    }

    @Test
    void check_store_task_by_not_throwing_exception() {

        try{
            fileHandler.serializeTask(tasks, FILE_PATH_TASKS);
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Should not throw");
        }

    }

    @Test
    void check_if_serialized_tasks_contains_deserialized_tasks() {
        try{
            fileHandler.serializeTask(tasks, FILE_PATH_TASKS);
            ArrayList<Task> arrayTasks = fileHandler.deserializeTask(FILE_PATH_TASKS);
            Assertions.assertTrue(arrayTasks.get(0).getTaskName().equals(tasks.get(0).getTaskName()));
            Assertions.assertTrue(arrayTasks.get(1).getTaskName().equals(tasks.get(1).getTaskName()));
            Assertions.assertTrue(arrayTasks.get(2).getTaskName().equals(tasks.get(2).getTaskName()));
        }catch (IOException e){
            Assertions.fail("Should not enter this");
        }
    }

    @Test
    void deleteTask() {
        Task task5 = new Task("Task5", "Category5",
                LocalDate.now().plusDays(1), "high");
        tasks.add(task5);
        Assertions.assertTrue(tasks.contains(task5));

    }

    @Test
    void deleteCategory() {

    }
}