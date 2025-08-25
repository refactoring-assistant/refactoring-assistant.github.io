import java.util.ArrayList;
import java.util.List;

class Task {
    private String name;
    private String description;
    private boolean completed;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

}

class TaskList {
    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void printTaskList() {
        for (Task task : tasks) {
            System.out.println(" - " + task.getName() + " : " + task.getDescription() + " :: " + task.getStatus());
        }
    }

    public void addTask(String name, String description) {
        Task newTask = new Task(name, description);
        tasks.add(newTask);
    }

    public void markTaskAsCompleted(String name) {
        for (Task task : tasks) {
            if (task.getName().equals(name)) {
                task.markAsCompleted();
            }
        }
    }

}

public class DCLSGE1 {

    public static void main(String[] args) {

        TaskList tl = new TaskList();
        tl.addTask("Groceries", "Get groceries from trader joe's");
        tl.addTask("Test", "Do testing for app");
        tl.markTaskAsCompleted("Groceries");
        tl.printTaskList();

    }

}