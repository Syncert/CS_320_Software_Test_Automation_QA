package contactservice;

import java.util.HashMap;
import java.util.Map;

/*
The task service shall be able to add tasks with a unique ID.
The task service shall be able to delete tasks per task ID.
The task service shall be able to update task fields per task ID. The following fields are updatable:
Name
Description
 */

public class TaskService {
    private final Map<String, Task> tasks;

    public TaskService() {
        this.tasks = new HashMap<>();
    }

    // Adds a new task with a unique ID generated by TaskIDGenerator
    public String addTask(String name, String description) {
        String id = TaskIDGenerator.generateNextID();
        tasks.put(id, new Task(name, description));
        return id; // Returns the unique task ID
    }

    // Deletes a task by its ID
    public boolean deleteTask(String id) {
        if (tasks.containsKey(id)) {
            tasks.remove(id);
            return true;
        }
        return false;
    }

    // Updates the task name and description
    public boolean updateTask(String id, String newName, String newDescription) {
        if (tasks.containsKey(id)) {
            Task task = tasks.get(id);
            task.setName(newName);
            task.setDescription(newDescription);
            return true;
        }
        return false;
    }

    // Method to retrieve a task
    public Task getTask(String id) {
        return tasks.getOrDefault(id, null);
    }
}