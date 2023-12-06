package contactservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
The task service shall be able to add tasks with a unique ID.
The task service shall be able to delete tasks per task ID.
The task service shall be able to update task fields per task ID. The following fields are updatable:
Name
Description
 */

class TaskServiceTest {
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }
    //test ability to add task
    @Test
    void testAddTask() {
        String id = taskService.addTask("Task 1", "Description 1");
        assertNotNull(id);
        assertNotNull(taskService.getTask(id));
    }
    //test ability to delete a task
    @Test
    void testDeleteTask() {
        String id = taskService.addTask("Task 2", "Description 2");
        assertTrue(taskService.deleteTask(id));
        assertNull(taskService.getTask(id));
    }
    //test updating of task
    @Test
    void testUpdateTask() {
        String id = taskService.addTask("Task 3", "Description 3");
        taskService.updateTask(id, "Updated Task 3", "Updated Description 3");
        Task updatedTask = taskService.getTask(id);
        assertNotNull(updatedTask);
        assertEquals("Updated Task 3", updatedTask.getName());
        assertEquals("Updated Description 3", updatedTask.getDescription());
    }
}