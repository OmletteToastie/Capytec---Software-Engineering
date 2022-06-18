package taskAllocation;

import java.util.ArrayList;
import java.util.Collections;

import dataComponent.*;

public class TaskAllocator {
	
	public ArrayList<task> assignToTaskPrioArray(ArrayList<task> allTasks, int prioValue) {
		//Assign tasks to an arraylist based on the priority of the task.
		ArrayList<task> prioArray = new ArrayList<task>();
		for (int i = 0; i < allTasks.size(); i++) {
			task currentTask = allTasks.get(i);
			if (currentTask.getTaskPriority() == prioValue) {
				prioArray.add(currentTask);
			}
		}
		return prioArray;
	}
	//Assign tasks to employees based on who has the lowest duration of tasks.
	public void assignToEmp(ArrayList<task> taskList, ArrayList<Employee> empList) {
		for (int i = 0; i < taskList.size(); i++) {
			task currentTask = taskList.get(i);
			Employee currentEmp = empList.get(0);
			

			currentEmp.addEmpTask(currentTask);
			Collections.sort(empList);
		}
	}

	public void allocate(){

		//Connection to database.
		TaskSystemDatabase data = new TaskSystemDatabase();

		//Create arraylist of all tasks.
		ArrayList<task> taskArray = data.getAllTasks();

		//Create arraylist of all employees and a second arraylist for non-managerial employees.
		ArrayList<Employee> unfilteredEmpArray = data.getAllEmployees();
		ArrayList<Employee> empArray = new ArrayList<Employee>();
		
		
		for(int i = 0; i < unfilteredEmpArray.size(); i++) {
			Employee currentEmp = unfilteredEmpArray.get(i);
			if(currentEmp.getEmpManagement() == false) {
				empArray.add(currentEmp);
			}
		}

		// Attempt To Allocate Preferred Tasks

		Collections.shuffle(empArray);
		for (int i = 0; i < empArray.size(); i++) {
			int searchedTaskID = empArray.get(i).getEmpTaskPriority();
			task searchedTask = null;
			
			//Search array of tasks for employee's preferred task.
			for (int j = 0; j < taskArray.size(); j++) {
				if (taskArray.get(j).getID() == searchedTaskID) {
					searchedTask = taskArray.get(j);
					break;
				}
			}
			//Remove tasks that have been allocated.
			if (taskArray.contains(searchedTask)) {
				empArray.get(i).addEmpTask(searchedTask);
				System.out.println(searchedTask.getName() + " assigned to " + empArray.get(i).getEmpFName());
				taskArray.remove(searchedTask);
			}
		}
		//Sort employee array by total duration of tasks in ascending order.
		Collections.sort(empArray);

		// Create ArrayList for tasks based on taskPriority then assigns these to users
		// based on who has the lowest total task duration.
		ArrayList<task> highTaskArray = new ArrayList<task>();
		highTaskArray = assignToTaskPrioArray(taskArray, 3);
		assignToEmp(highTaskArray, empArray);

		ArrayList<task> medTaskArray = new ArrayList<task>();
		medTaskArray = assignToTaskPrioArray(taskArray, 2);
		assignToEmp(medTaskArray, empArray);

		ArrayList<task> lowTaskArray = new ArrayList<task>();
		lowTaskArray = assignToTaskPrioArray(taskArray, 1);
		assignToEmp(lowTaskArray, empArray);
		
		for(int i = 0; i < empArray.size(); i++) {
			Employee currentEmp = empArray.get(i);
			int currentEmpID = currentEmp.getEmpID();
			ArrayList<task> currentEmpTasks = currentEmp.getEmpTask();
			
			for(int j = 0; j < currentEmpTasks.size(); j++) {
				int currentTaskID = currentEmpTasks.get(j).getID();
				System.out.println(currentTaskID + " assigned to " + currentEmpID);
				data.AssignEmployee(currentTaskID, currentEmpID);
			}
		}
		
	}

}
