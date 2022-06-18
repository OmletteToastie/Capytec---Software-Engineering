package dataComponent;
import taskentrycomponent.maintaskpage;

public class TaskSystemApp {

	maintaskpage theMainWindow;
	private TaskSystemDatabase data;
	
	
	public static void main (String [] args) {		
		TaskSystemApp mh = new TaskSystemApp();
	}
	
	public TaskSystemApp() {
		data = new TaskSystemDatabase();
		theMainWindow = new maintaskpage(this);
		theMainWindow.setVisible(true);
		theMainWindow.displayTableData(data.getAllTasks());
	}
	
	public void DeleteTask(int taskID) {
		data.DeleteTask(taskID);
		
		theMainWindow.displayTableData(data.getAllTasks());
	}
	
	public void UpdateTask( String taskName, String taskLocation, int taskPriority,int taskCategoryID, String taskRoutine, int taskLength, String taskDueDate, int taskID) {
		data.UpdateTask(taskName, taskLocation, taskPriority, taskCategoryID, taskRoutine, taskLength,  taskDueDate, taskID);
		
		// Tempted to convert to an Object[][] at this point.
		// But I'll let the GUI do it, since it will be replaced by
		// database ResultSet, anyway.
		theMainWindow.displayTableData(data.getAllTasks());
	}
	
	public void AddTask( String taskName, String taskLocation, int taskPriority,int taskCategoryID,String taskRoutine,int taskLength, String taskDueDate ) {
		task newTask = new task();
		newTask.setName(taskName);
		newTask.setLocation(taskLocation);
		newTask.setTaskPriority(taskPriority);
		newTask.setCategoryID(taskCategoryID);
		newTask.setRoutine(taskRoutine);
		newTask.setDuration(taskLength);
		newTask.setDueDate(taskDueDate);
		
		
		data.AddTask(taskName,taskLocation,taskPriority,taskCategoryID,taskRoutine,taskLength,taskDueDate);
		
		// Tempted to convert to an Object[][] at this point.
		// But I'll let the GUI do it, since it will be replaced by
		// database ResultSet, anyway.
		theMainWindow.displayTableData(data.getAllTasks());
	
	
	
	}
}


