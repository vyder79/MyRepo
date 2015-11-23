import java.util.LinkedList;


public class Task {
	
	String name;
	double weight;
	LinkedList<Task> previousTasks;
	
	public Task(String name, double weight) {
		super();
		this.name = name;
		this.weight = weight;
		this.previousTasks = new LinkedList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public LinkedList<Task> getPreviousTasks() {
		return previousTasks;
	}

	public void setPreviousTasks(LinkedList<Task> previousTasks) {
		this.previousTasks = previousTasks;
	}
	
	public void addTaskToDoBefore(Task previousTask) {
		this.previousTasks.add(previousTask);
	}
	
	

}
