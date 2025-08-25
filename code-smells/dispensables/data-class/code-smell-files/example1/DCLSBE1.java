import java.util.ArrayList;
import java.util.List;

class Task {
    public String name;
    public String description;
    public boolean completed;
}

class TaskList {
    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void getTaskList() {
        for (Task task : tasks) {
            System.out.println(" - " + task.name + " : " + task.description + " :: " + task.completed);
        }
    }

    public void addTask(String name, String description) {
        Task newTask = new Task();
        newTask.name = name;
        newTask.description = description;
        newTask.completed = false;

        tasks.add(newTask);
    }

    public void markTaskAsCompleted(String name) {
        for (Task task : tasks) {
            if (task.name.equals(name)) {
                task.completed = true;
            }
        }
    }

}

public class DCLSBE1 {

    public static void main(String[] args) {

        TaskList tl = new TaskList();
        tl.addTask("Groceries", "Get groceries from trader joe's");
        tl.addTask("Test", "Do testing for app");
        tl.markTaskAsCompleted("Groceries");
        tl.getTaskList();

    }

}