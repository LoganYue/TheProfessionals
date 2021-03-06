package professional.team17.com.professional;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Arrays;

import professional.team17.com.professional.Entity.Task;
import professional.team17.com.professional.Entity.TaskList;

/**
 * Created by Hailan on 2018-02-20.
 */

public class TaskListTest extends ActivityInstrumentationTestCase2 {
    public TaskListTest(){
        super(TaskList.class);
    }

    public void testAddTask(){ //same as the test for hasTask
        TaskList tasks = new TaskList();
        Task task = new Task("TaskRequester1", "Title1", "Description1");
        tasks.addTask(task);
        assertTrue(tasks.hasTask(task));
    }

    public void testDeleteTask(){
        TaskList tasks = new TaskList();
        Task task = new Task("TaskRequester2", "Title2", "Description2");
        tasks.addTask(task);
        tasks.deleteTask(task);
        assertFalse(tasks.hasTask(task));
    }

    public void testGetTasks(){
        TaskList tasks = new TaskList();

        Task task1 = new Task("TaskRequester1", "Title1", "Description1");
        Task task2 = new Task("TaskRequester2", "Title2", "Description2" );
        Task task3 = new Task("TaskRequester3", "Title3", "Description3");

        tasks.addTask(task1);
        tasks.addTask(task2);
        tasks.addTask(task3);

        ArrayList<Task> taskList = new ArrayList<>(Arrays.asList(task1, task2, task3));
        assertEquals(tasks.getTasks(), taskList);
    }
}
