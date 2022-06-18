package dataComponent;

public class task implements Comparable<task>{
	
	private int ID;
	private String Name;
	private String Location;
	private int TaskPriority;
	private String Status;
	private int CategoryID;
	private String CategoryDesc;	
	private String Routine;
	private int Duration;
	private String DueDate;
	private String CreationDate;
	private String Comments;
	private String CompletionDate;
	private int AssignedEmp;
	private String AssignedEmpFName;
	private String AssignedEmpSName;
	
	
	public task(){
		ID = 0;
		Name = "Not set";
		Location = "-";
		CategoryID = 0;
		CategoryDesc = "-"; 
		Status = "Unknown";
		DueDate = "-"; 
		Duration = 0;
		Routine = "-";
		TaskPriority = 0;
		CreationDate = "-"; 
		Comments  = "-"; 
		CompletionDate  = "-"; 
		AssignedEmp = 0;
		AssignedEmpFName = "-";
		AssignedEmpSName = "-";

}

	public void setTaskID(int taskID) {
		ID = taskID;
	}

	public void setName(String taskName) {
		Name = taskName;
	}
	
	public void setLocation(String taskLocation) {
		Location = taskLocation;
	}
	
	public void setCategoryID(int taskCategoryID) {
		CategoryID = taskCategoryID;
	}

	public void setCategoryDesc(String taskCategory) {
		CategoryDesc = taskCategory;
	}

	public void setStatus(String taskStatus) {
		Status = taskStatus;
	}
	
	public void setDueDate(String taskDueDate) {
		DueDate = taskDueDate;
	}
	
	public void setDuration(int taskLength) {
		Duration = taskLength;
	}
	
	public void setRoutine(String taskRoutine) {
		Routine = taskRoutine;
	}
	
	public void setTaskPriority(int taskPriority) {
		TaskPriority = taskPriority ;
	}

	public void setCreationDate(String taskCreation) {
		CreationDate = taskCreation;
	}

	public void setComments(String taskComments) {
		Comments = taskComments;
	}

	public void setCompletionDate(String taskCompletion) {
		CompletionDate = taskCompletion;
	}

	public void setAssignedEmp(int assignedEmp){
		AssignedEmp = assignedEmp;
	}

	public void setAssignedEmpFName(String assignedEmpF){
		AssignedEmpFName = assignedEmpF;
	}

	public void setAssignedEmpSName(String assignedEmpS){
		AssignedEmpSName = assignedEmpS;
	}

	public int getID(){
		return ID;
	}
	
	public String getName() {
		return Name;
	}
	
	public String getLocation() {
		return Location;
	}
	
	public int getCategoryID() {
		return CategoryID;
	}

	public String getCategoryDesc() {
		return CategoryDesc;
	}
	
	public String getStatus() {
		return Status;
	}
	
	public String getDueDate() {
		return DueDate;
	}
	
	public int getTaskLength() {
		return Duration;
	}
	
	public String getRoutine() {
		return Routine;
	}
	
	public int getTaskPriority() {
		return TaskPriority;
	}

	public String getCreationDate() {
		return CreationDate;
	}

	public String getComments() {
		return Comments;
	}

	public String getCompletionDate() {
		return CompletionDate;
	}

	public int getAssignedEmp(){
		return AssignedEmp;
	}

	public String getAssignedEmpFName(){
		return AssignedEmpFName;
	}

	public String getAssignedEmpSName(){
		return AssignedEmpSName;
	}
	
	//TEMP PLACEHOLDER
	
	//public task(String newName, String newPrio, int newDur) {
		//Name = newName;
		//TaskPriority = newPrio;
		//Duration = newDur;
	//}
	
	@Override
	public int compareTo(task o) {
		return o.ID-this.ID;
	}
}