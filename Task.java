package contactservice;

/*
The task object shall have a required unique task ID String that cannot be longer than 10 characters. The task ID shall not be null and shall not be updatable.
The task object shall have a required name String field that cannot be longer than 20 characters. The name field shall not be null.
The task object shall have a required description String field that cannot be longer than 50 characters. The description field shall not be null. 
*/

public class Task {
    private final String taskID;
    private String name;
    private String description;

    public Task(String name, String description) {
        //null and < 10 character responsibilities delegated to taskIDGenerator
        this.taskID = TaskIDGenerator.generateNextID();

        setName(name);
        setDescription(description);

    }

    /*
    * SETTERS
    */
    public void setName(String name) {
        //name shall not be null
        if (name == null) {
            throw new IllegalArgumentException("Name is null.");
        }
        //name shall not exceed 20 characters
        else if (name.length() > 20) {
            throw new IllegalArgumentException("Name greater than 20 characters.");
        }
        this.name = name;
        }

    public void setDescription(String description) {
        //description shall not be null
        if (description == null) {
            throw new IllegalArgumentException("Description is null.");
        }
        //description shall not exceed 50 characters
        else if (description.length() > 50) {
            throw new IllegalArgumentException("Description greater than 50 characters.");
        }
        this.description = description;
        }

    /*
    * GETTERS
    */
    public String getTaskID() {
        return taskID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}
