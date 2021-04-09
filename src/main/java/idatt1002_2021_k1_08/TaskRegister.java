package idatt1002_2021_k1_08;

import idatt1002_2021_k1_08.datamodel.Task;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

public class TaskRegister {

    private ObservableList<Task> tasks;

    public TaskRegister(ObservableList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks inside task list");
        }

    }

}
