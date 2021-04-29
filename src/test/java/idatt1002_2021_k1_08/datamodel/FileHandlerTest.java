package idatt1002_2021_k1_08.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileHandlerTest {

    static FileHandler fileHandler;
    static Task task1;
    static Task task2;
    static Task task3;
    static ObservableList<Task> tasks;
    static ObservableList<String> categoriesTestList;

    static File FILE_PATH_CATEGORY;
    static File FILE_PATH_TASKS;

    @BeforeAll
    static void setUp() throws IOException{
        FILE_PATH_CATEGORY = new File("src/test/resources/DataStorageTest/CategoryStringsTest.ser");
        FILE_PATH_TASKS = new File("src/test/resources/DataStorageTest/TaskData.ser");

        fileHandler = new FileHandler();
        tasks = FXCollections.observableArrayList();
        categoriesTestList = FXCollections.observableArrayList();
        //FILE_PATH_TASKS.createNewFile();
        //FILE_PATH_CATEGORY.createNewFile();

        task1 = new Task("Test1","Category1",
                LocalDate.now().plusDays(1), "high");

        task2 = new Task("Test2","Category2",
                LocalDate.now().plusDays(1), "medium");

        task3 = new Task("Test3","Category3",
                LocalDate.now().plusDays(1), "medium");

        fileHandler.addTask(task1);
        fileHandler.addTask(task2);
        fileHandler.addTask(task3);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
    }


    @Test
    @DisplayName("Check if Filehandler instance is returned")
    public void filehandlerClass_standardClassSetup_ExpectedCorrectInstance() {
        FileHandler instance = new FileHandler();
        MatcherAssert.assertThat(instance, instanceOf(FileHandler.class));
    }

    @Test
    @DisplayName("Check getTasks() method")
    void getTasks_Task_ExpectedContainsTask() {
        assertTrue(fileHandler.getTasks().contains(task1));
        assertTrue(fileHandler.getTasks().contains(task2));
        assertTrue(fileHandler.getTasks().contains(task3));
    }

    @Test
    @DisplayName("Check if getCategory() method works")
    void getCategory_Category_ReturnedCorrect() {
        Assertions.assertEquals(task1.getCategory(), "Category1");
        Assertions.assertEquals(task2.getCategory(), "Category2");
        Assertions.assertEquals(task3.getCategory(), "Category3");
    }

    @Test
    @DisplayName("Check is addTask() methods works")
    void AddTask_Task_TaskAdded() {
        Task task4 = new Task("Test4", "Category4", LocalDate.now().plusDays(1), "high");
        fileHandler.addTask(task4);
        assertTrue(fileHandler.getTasks().contains(task4));
    }

    @Test
    @DisplayName("Check if serializeTask do not throw exception")
    void serializeTask_tasksFilepath_NoException() {

        try{
            fileHandler.serializeTask(tasks, FILE_PATH_TASKS);
        } catch (Exception e){
            e.printStackTrace();
            Assertions.fail("Should not throw");
        }

    }

    @Test
    @DisplayName("Check if serialzeTask() returns right elements from file")
    void SerializeTask_Tasks_CorrectElements() {
        try{
            fileHandler.serializeTask(tasks, FILE_PATH_TASKS);
            ArrayList<Task> arrayTasks = fileHandler.deserializeTask(FILE_PATH_TASKS);
            assertTrue(arrayTasks.get(0).getTaskName().equals(tasks.get(0).getTaskName()));
            assertTrue(arrayTasks.get(1).getTaskName().equals(tasks.get(1).getTaskName()));
            assertTrue(arrayTasks.get(2).getTaskName().equals(tasks.get(2).getTaskName()));
        }catch (IOException e){
            Assertions.fail("Should not enter this");
        }
    }

    @Test
    @DisplayName("Check if deleteTask methods deletes from list")
    void deleteTask_Task_taskDeleted() {
        Task task5 = new Task("Task5", "Category5",
                LocalDate.now().plusDays(1), "high");
        fileHandler.addTask(task5);
        System.out.println(fileHandler.getTasks());
        fileHandler.deleteTask(task5);
        System.out.println(fileHandler.getTasks());
        Assertions.assertFalse(fileHandler.getTasks().contains(task5));
    }

    @Test
    @DisplayName("Check if task have it's category removed")
    void deleteCategory_category_deletedCategory() {
        fileHandler.deleteCategory("Category1");
        //Add method by @Maiken where delete is checked inside categorylist AND on the taskView. ?????
        Assertions.assertFalse(FileHandler.categories.contains("Category1"));
    }
}