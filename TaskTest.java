package contactservice;

import java.util.Collections;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
The task object shall have a required unique task ID String that cannot be longer than 10 characters. The task ID shall not be null and shall not be updatable.
The task object shall have a required name String field that cannot be longer than 20 characters. The name field shall not be null.
The task object shall have a required description String field that cannot be longer than 50 characters. The description field shall not be null. 
*/


class TaskTest {

    //test Task creation, this also tests if the generated ID is null or <= 10 from the ContactIDGenerator 
    @Test
    public void testValidTask() {
        Task task = new Task("Task1","This is a task.");
        assertNotNull(task);
        //test null ID
        assertNotNull(task.getName());
        assertTrue(task.getTaskID().length() <= 10);
    }

    //name shall not be null and name shall not exceed 20 characters
    @Test
    void testNameNotNullAndLength() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "Sample Description"));
        assertThrows(IllegalArgumentException.class, () -> new Task("This name is way too long to be acceptable", "Sample Description"));
    }
    //test description shall not be null and test description shall not exceed 50 characters
    @Test
    void testDescriptionNotNullAndLength() {
        assertThrows(IllegalArgumentException.class, () -> new Task("Sample Name", null));
        assertThrows(IllegalArgumentException.class, () -> new Task("Sample Name", String.join("", Collections.nCopies(51, "a"))));
    }
    //test if the task can be created and that it matches the construction details
    @Test
    void testValidTaskCreation() {
        Task task = new Task("Sample Name", "Sample Description");
        assertNotNull(task);
        assertEquals("Sample Name", task.getName());
        assertEquals("Sample Description", task.getDescription());
    }
}