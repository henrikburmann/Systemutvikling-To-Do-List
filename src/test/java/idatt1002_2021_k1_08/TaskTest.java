package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskTest {

    static Task task;

    @BeforeAll
    static void setup() {
        task = new Task("Taskname", "cat", LocalDate.of(2022,10,10),"high");
    }

    @Test
    public void checkIfNull_null_ShouldThrowException(){
        Assertions.assertThrows(NullPointerException.class, () -> {
            task.setTaskName(null);
        });
    }
    @Test
    public void checkIfEmpty_emptyString_ShouldThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            task.setTaskName("");
        });
    }
    @Test
    public void checkIfNull_simpleString_changesName(){
        Assertions.assertDoesNotThrow(() -> task.setTaskName("newName"));
    }
    @Test
    public void checkIfEmpty_simpleString_changesName(){
        Assertions.assertDoesNotThrow(() -> task.setTaskName("newName"));
    }


}
