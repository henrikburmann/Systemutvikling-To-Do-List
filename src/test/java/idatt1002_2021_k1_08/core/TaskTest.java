package idatt1002_2021_k1_08.core;

import idatt1002_2021_k1_08.datamodel.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskTest {

    static Task task;

    @BeforeAll
    static void setup() {
        task = new Task("Taskname", "cat", LocalDate.of(2022,10,10),"high");
    }

    @Test
    @DisplayName("Check if Task Name is null and throws exception")
    public void checkIfNull_null_ShouldThrowException(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            task.setTaskName(null);
        });
    }
    @Test
    @DisplayName("Check if empty string in name and throws exception")
    public void checkIfEmpty_emptyString_ShouldThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            task.setTaskName("");
        });
    }
    @Test
    @DisplayName("Check if you can set a new name")
    public void checkIfNull_simpleString_changesName(){
        Assertions.assertDoesNotThrow(() -> task.setTaskName("newName"));
    }
    @Test
    @DisplayName("Check if you can set a new name")
    public void checkIfEmpty_simpleString_changesName(){
        Assertions.assertDoesNotThrow(() -> task.setTaskName("newName"));
    }


}
