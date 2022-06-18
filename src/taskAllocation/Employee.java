package taskAllocation;
import java.util.ArrayList;

import dataComponent.*;

public class Employee implements Comparable<Employee>{
	
	private int empID;
	private String empFName;
	private String empSName;
	private String empUsername;
	private String empPassword;
	private String empStatus;
	private int empTaskPriority;
	private boolean empPriv;
	private boolean empManagement;
	private ArrayList<task> empTasks;
	private int empTaskTotDur;
	private String empActive;

	
	public Employee() {
		empFName = "Unset";
		empSName = "Unset";
		empUsername = "Unset";
		empPassword = "Unset";
		empStatus = "Unset";
		empTaskPriority = 0;
		empTasks = new ArrayList<task>();
		empTaskTotDur = 0;
		empManagement = false;
		empPriv = false;
		empActive = "ACTIVE";
	}
	
// Setters
	
	public void setEmpID(int newID) {
		empID = newID;
	}
	
	public void setEmpFName(String newFName) {
		empFName = newFName;
	}
	
	public void setEmpSName(String newSName) {
		empSName = newSName;
	}
	
	public void setEmpUsername(String newUsername) {
		empUsername = newUsername;
	}
	
	public void setEmpPassword(String newPassword) {
		empPassword = newPassword;
	}

	public void setEmpStatus(String newStatus) {
		empStatus = newStatus;
	}
	
	public void setEmpTaskPriority(int newtaskPriority) {
		empTaskPriority = newtaskPriority;
	}
	
	public void setEmpPriv(boolean newPriv) {
		empPriv = newPriv;
	}
	
	public void setEmpManagement(boolean newManagement) {
		empManagement = newManagement;
	}
	
	public void setEmpActive(String newActive) {
		empActive = newActive;
	}
	public void addEmpTask(task newTask) {
		empTasks.add(newTask);
	}
	
// Getters
	
	public int getEmpID() {
		return empID;
	}
	
	public String getEmpFName() {
		return empFName;
	}
	
	public String getEmpSName() {
		return empSName;
	}
	
	public String getEmpUsername() {
		return empUsername;
	}
	
	public String getEmpPassword() {
		return empPassword;
	}
	
	public String getEmpStatus() {
		return empStatus;
	}
	
	public int getEmpTaskPriority() {
		return empTaskPriority;
	}
	
	public boolean getEmpPriv() {
		return empPriv;
	}
	
	public boolean getEmpManagement() {
		return empManagement;
	}
	
	public String getEmpActive() {	
		return empActive;
	}
	
	public ArrayList<task> getEmpTask() {
		return empTasks;
	}
	
	
	public String getEmpTaskName(int i) {
		task newTask = empTasks.get(i);
		return newTask.getName();
	}
	
	public int getEmpTaskPrio(int i) {
		task newTask = empTasks.get(i);
		return newTask.getTaskPriority();
	}
	
	public int getEmpTaskDur(int i) {
		task newTask = empTasks.get(i);
		return newTask.getTaskLength();
	}
	
	public int getEmpTaskTotDur() {
		empTaskTotDur = 0;
		for(int count = 0; count < empTasks.size();count++) {
			empTaskTotDur = empTaskTotDur + getEmpTaskDur(count);
		}
		return empTaskTotDur;
	}
	
// Display Employee Info and All Tasks
	
	public void displayInfo() {
		System.out.println("Employee Name: " + this.getEmpFName() + " " + this.getEmpSName());
		
		for (int count = 0; count < this.empTasks.size(); count++){
			System.out.println("	Task Name: " + this.getEmpTaskName(count) + "(" + this.getEmpTaskPrio(count) + ")");
		}
			System.out.println("	Total Duration: " + this.getEmpTaskTotDur());
	}
	
	public void displayStatus() {
		System.out.println("ID: " + this.getEmpID());
		System.out.println("Employee Name: " + this.getEmpFName() + " " + this.getEmpSName());
		System.out.println("Username | Password: "+ this.getEmpUsername() + " " + this.getEmpPassword());
		System.out.println("Task Priority : " + this.getEmpTaskPriority());
		System.out.println("Admin: " + this.getEmpPriv());
		System.out.println("Management: " + this.getEmpManagement());
	}
	
	@Override
	public int compareTo(Employee o) {
		return this.getEmpTaskTotDur()-o.getEmpTaskTotDur();
	}
}
