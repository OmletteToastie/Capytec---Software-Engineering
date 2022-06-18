package dataComponent;
import taskAllocation.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class TaskSystemDatabase {
	private DBConnection database;

    	//establish database class and connect to the built database
	    public TaskSystemDatabase() {
		database = new DBConnection();
        // relative path connection to the database
		database.Connect("../SoftwareEngineeringPractice/capytecDataBase.db");
	}   

	//function for adding a new task to the database with the passed variables
	public void AddTask( String taskName, String taskLocation, int taskPriority, int taskCategoryID, String taskRoutine, int taskLength, String taskDueDate ) {
		String sqlString = new String("INSERT INTO tasks (task_name, task_location, task_priority, task_cat, task_routine, task_length, task_dueDate) VALUES  ('"+taskName+"','"+taskLocation+"','"+taskPriority+"','"+taskCategoryID+"','"+taskRoutine+"','"+taskLength+"','"+taskDueDate+"');");

        //runs the query and then checks if it was successful
		boolean success = database.RunSQL(sqlString);

		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}


    //function to remove a task from the database based on the given ID
	public void DeleteTask(int taskID ) {
		String sqlString = new String("DELETE FROM tasks WHERE task_id = '"+taskID+"';");
        
        //runs the query and then checks if it was successful
		boolean success = database.RunSQL(sqlString);
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}
    
    //function to update a task with the new given variables passed on the passed task ID
    public void UpdateTask (String taskName, String taskLocation, int taskPriority, int taskCategoryID, String taskRoutine, int taskLength, String taskDueDate, int taskID) {
        String sqlString = new String("UPDATE tasks set task_name = '"+taskName+"', task_location = '"+taskLocation+"', task_priority = '"+taskPriority+"', task_cat = '"+taskCategoryID+"', task_routine = '"+taskRoutine+"', task_length = '"+taskLength+"', task_dueDate = '"+taskDueDate+"' WHERE task_id = '"+taskID+"';");

        //runs the query and then checks if it was successful
        boolean success = database.RunSQL(sqlString);

		if(!success) {
		System.out.println("Failed to run query: "+sqlString);
		}
    }

    //function to update a task as complete from a passed ID with the option for aditional comments
	public void UpdateTaskComplete ( int taskID, String taskComments ) {
		String sqlString = new String("UPDATE tasks set task_status = 'Complete', task_completion = CURRENT_TIMESTAMP, task_comment = '"+taskComments+"'  WHERE task_id = '"+taskID+"';");
        
        //runs the query and then checks if it was successful
		boolean success = database.RunSQL(sqlString);

		if(!success) {
		System.out.println("Failed to run query: "+sqlString);
		}
    }

    //function to add an employee into the database with the passed variables
    public void AddEmployee( String empFName, String empSName, String empUsername, String empPassword, int taskPriority, Boolean empAdmin, Boolean empManagment ) {
		String sqlString = new String("INSERT INTO userEmployee (emp_fname, emp_sname, emp_login, emp_password, emp_taskPriority, emp_adminPriv, emp_management) VALUES  ('"+empFName+"','"+empSName+"','"+empUsername+"','"+empPassword+"','"+taskPriority+"','"+empAdmin+"','"+empManagment+"');");
       
        //runs the query and then checks if it was successful
		boolean success = database.RunSQL(sqlString);

		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}

    //function to remove an employee from the database based on the given ID
	public void DeleteEmployee(int empID ) {
		String sqlString = new String("DELETE FROM userEmployee WHERE emp_id = '"+empID+"';");
		
        //runs the query and then checks if it was successful
        boolean success = database.RunSQL(sqlString);
		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}

    //function to update an employee row based on the based ID with all of the variables being the changes
    public void UpdateEmployee (int empID, String empFName, String empSName, String empUsername, String empPassword, String empStatus, int taskPriority, Boolean empAdmin, Boolean empManagment) {
        String sqlString = new String("UPDATE userEmployee set emp_fname = '"+empFName+"', emp_sname = '"+empSName+"', emp_login = '"+empUsername+"', emp_password = '"+empPassword+"', emp_status = '"+empStatus+"', emp_taskPriority = '"+taskPriority+"', emp_adminPriv = '"+empAdmin+"', emp_management = '"+empManagment+"' WHERE emp_id = '"+empID+"';");
        
        //runs the query and then checks if it was successful
        boolean success = database.RunSQL(sqlString);

		if(!success) {
		System.out.println("Failed to run query: "+sqlString);
		}
    }

    //fucntion to add a user Report to the database with all the relevant information as passed variables
    public void AddUserReport( int ReportSubmitter, String ReportTitle, String ReportDesc, int ReportUserID ) {
		String sqlString = new String("INSERT INTO userReport (userReport_submiter, userReport_title, userReport_desc, userReport_userID) VALUES  ('"+ReportSubmitter+"','"+ReportTitle+"','"+ReportDesc+"','"+ReportUserID+"');");
        
        //runs the query and then checks if it was successful
		boolean success = database.RunSQL(sqlString);

		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}

    //fucntion to add a task Report to the database with all the relevant information as passed variables
    public void AddTaskReport( int ReportSubmitter, String ReportTitle, String ReportDesc, int ReportTaskID ) {
		String sqlString = new String("INSERT INTO taskReport (taskReport_submiter, taskReport_title, taskReport_desc, taskReport_userID) VALUES  ('"+ReportDesc+"','"+ReportTitle+"','"+ReportDesc+"','"+ReportTaskID+"');");

        //runs the query and then checks if it was successful
		boolean success = database.RunSQL(sqlString);

		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}

    //function to get all of the task reports within the database and place them into an arraylist of reports
    public ArrayList<report> getTaskReports(){
		String sqlString = new String("SELECT emp_fname, emp_sname, taskReport_title, task_name, taskReport_date, taskReport_desc FROM taskReport LEFT JOIN userEmployee on taskReport_submiter = emp_id LEFT JOIN tasks on taskReport_taskID = task_id;");
		ResultSet reportList = database.RunSQLQuery(sqlString);
		ArrayList<report> answer = new ArrayList<report>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the reports arraylist
		try {
		while(reportList.next()) {
			task newReport = new report();
			newReport.setSFName(reportList.getString(1));
			newReport.setSSname(reportList.getString(2));
			newReport.setReportTitle(reportList.getString(3));
			newReport.setReportTaskName(reportList.getString(4));
			newReport.setReportDate(reportList.getInt(5));
			newReport.setReportDesc(reportList.getString(6));
			answer.add(newReport);
			}
            // to catch any errors from the sql statment and print them
		} catch (SQLException e) {
			System.out.println("Failed to process query in GetAssignedTasks()");
			System.out.println("SQL attempted" + sqlString);
			System.out.println("Error: " +e.getErrorCode());
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
        //returns the report arraylist
		return answer;
	}

    //function to get all of the user reports within the database and place them into an arraylist of reports
    public ArrayList<report> getUserReports(){
		String sqlString = new String("SELECT T.emp_fname, T.emp_sname, userReport_title, I.emp_fname, I.emp_sname, userReport_date, userReport_desc FROM userReport LEFT JOIN userEmployee AS T on userReport_submiter = T.emp_id LEFT JOIN userEmployee AS I on userReport_userID = emp_id;");
		ResultSet reportList = database.RunSQLQuery(sqlString);
		ArrayList<report> answer = new ArrayList<report>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the reports arraylist
		try {
		while(reportList.next()) {
			task newReport = new report();
			newReport.setSFName(reportList.getString(1));
			newReport.setSSname(reportList.getString(2));
			newReport.setReportTitle(reportList.getString(3));
			newReport.setReportUserFName(reportList.getString(4));
            newReport.setReportUserSName(reportList.getString(5));
			newReport.setReportDate(reportList.getInt(6));
			newReport.setReportDesc(reportList.getString(7));
			answer.add(newReport);
			}
            // to catch any errors from the sql statment and print them
		} catch (SQLException e) {
			System.out.println("Failed to process query in GetAssignedTasks()");
			System.out.println("SQL attempted" + sqlString);
			System.out.println("Error: " +e.getErrorCode());
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
        //returns the report arraylist
		return answer;
	}


    //function to get all the assigned tasks from the database based on an employee ID linked to them and place them into an arraylist of tasks
	public ArrayList<task> getAssignedTasks(int employeeID){
		String sqlString = new String("SELECT task_id, task_name, task_location, cat_desc, task_status, task_dueDate, task_priority, task_length FROM tasks join taskCategory on task_cat = cat_id WHERE assigned_emp = '"+employeeID+"';");
		ResultSet taskList = database.RunSQLQuery(sqlString);
		ArrayList<task> answer = new ArrayList<task>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the task arraylist
		try {
		while(taskList.next()) {
			task newTask = new task();
            newTask.setTaskID(taskList.getInt(1));
			newTask.setName(taskList.getString(2));
			newTask.setLocation(taskList.getString(3));
			newTask.setCategoryDesc(taskList.getString(4));
			newTask.setStatus(taskList.getString(5));
			newTask.setDueDate(taskList.getString(6));
			newTask.setTaskPriority(taskList.getInt(7));
			newTask.setDuration(taskList.getInt(8));
			answer.add(newTask);
			}
            // to catch any errors from the sql statment and print them
		} catch (SQLException e) {
			System.out.println("Failed to process query in GetAssignedTasks()");
			System.out.println("SQL attempted" + sqlString);
			System.out.println("Error: " +e.getErrorCode());
			System.out.println("Message: " +e.getMessage());
			e.printStackTrace();
		}
        //returns the task arraylist
		return answer;
	}

    //function to get all tasks that need to be complete and add them to an arraylist of tasks
	public ArrayList<task> getAllTasks(){
        String sqlString = new String("SELECT task_id,task_name, task_location, cat_desc, task_Routine, task_length, task_priority,task_dueDate, assigned_emp FROM tasks JOIN taskCategory on task_cat = cat_id where task_status = 'To be complete';");
        ResultSet taskList = database.RunSQLQuery(sqlString);
        ArrayList<task> answer = new ArrayList<task>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the task arraylist
        try {
        while(taskList.next()) {
            task newTask = new task();
            newTask.setTaskID(taskList.getInt(1));
            newTask.setName(taskList.getString(2));
            newTask.setLocation(taskList.getString(3));
            newTask.setCategoryDesc(taskList.getString(4));
            newTask.setRoutine(taskList.getString(5));
            newTask.setDuration(taskList.getInt(6));
            newTask.setTaskPriority(taskList.getInt(7));
            newTask.setDueDate(taskList.getString(8));
            newTask.setAssignedEmp(taskList.getInt(9));
            answer.add(newTask);
            }
            // to catch any errors from the sql statment and print them
        } catch (SQLException e) {
            System.out.println("Failed to process query in GetAllTasks()");
            System.out.println("SQL attempted" + sqlString);
            System.out.println("Error: " +e.getErrorCode());
            System.out.println("Message: " +e.getMessage());
            e.printStackTrace();
        }
        //returns the task arraylist
        return answer;
    }

    //function to get all tasks for the admin while displaying the name of who is assigned and placing them into an arraylist of tasks
    public ArrayList<task> getAllTasksAdmin(){
        String sqlString = new String("SELECT task_id, emp_fname, emp_sname, task_name, task_location, cat_desc, task_status, task_Routine, task_length, task_priority, task_dueDate, task_creation FROM tasks LEFT JOIN taskCategory on task_cat = cat_id LEFT JOIN userEmployee on assigned_emp = emp_id where task_status = 'To be complete';");
        ResultSet taskList = database.RunSQLQuery(sqlString);
        ArrayList<task> answer = new ArrayList<task>();
        
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the task arraylist
        try {
        while(taskList.next()) {
            task newTask = new task();
            newTask.setTaskID(taskList.getInt(1));
            newTask.setAssignedEmpFName(taskList.getString(2));
            newTask.setAssignedEmpSName(taskList.getString(3));
            newTask.setName(taskList.getString(4));
            newTask.setLocation(taskList.getString(5));
            newTask.setCategoryDesc(taskList.getString(6));
            newTask.setStatus(taskList.getString(7));
            newTask.setRoutine(taskList.getString(8));
            newTask.setDuration(taskList.getInt(9));
            newTask.setTaskPriority(taskList.getInt(10));
            newTask.setDueDate(taskList.getString(11));
            newTask.setCreationDate(taskList.getString(12));
            answer.add(newTask);
            }
            // to catch any errors from the sql statment and print them
        } catch (SQLException e) {
            System.out.println("Failed to process query in GetAllTasks()");
            System.out.println("SQL attempted" + sqlString);
            System.out.println("Error: " +e.getErrorCode());
            System.out.println("Message: " +e.getMessage());
            e.printStackTrace();
        }
        //returns the task arraylist
        return answer;
    }

   
    //function to get all the logged task in database and them to an arraylist of tasks to be returned
    public ArrayList<task> getLoggedTasks(){
        String sqlString = new String("SELECT emp_fname, emp_sname, task_name, task_location, cat_desc, task_dueDate, task_completion, task_comments FROM tasks LEFT JOIN taskCategory on task_cat = cat_id LEFT JOIN userEmployee on assigned_emp = emp_id where task_status = 'Complete';");
        ResultSet taskList = database.RunSQLQuery(sqlString);
        ArrayList<task> answer = new ArrayList<task>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the task arraylist
        try {
        while(taskList.next()) {
            task newTask = new task();
            newTask.setAssignedEmpFName(taskList.getString(1));
            newTask.setAssignedEmpSName(taskList.getString(2));
            newTask.setName(taskList.getString(3));
            newTask.setLocation(taskList.getString(4));
            newTask.setCategoryDesc(taskList.getString(5));
            newTask.setDueDate(taskList.getString(6));
            newTask.setCompletionDate(taskList.getString(7));
            newTask.setComments(taskList.getString(8));
            answer.add(newTask);
            }
            // to catch any errors from the sql statment and print them
        } catch (SQLException e) {
            System.out.println("Failed to process query in GetAllTasks()");
            System.out.println("SQL attempted" + sqlString);
            System.out.println("Error: " +e.getErrorCode());
            System.out.println("Message: " +e.getMessage());
            e.printStackTrace();
        }
        //returns the task arraylist
        return answer;
    } 
    
    //function to get all the logged task assigned to a specific employee in database and them to an arraylist of tasks to be returned
    public ArrayList<task> getLoggedTasksUnique (int AssignedEmp){
        String sqlString = new String("SELECT emp_fname, emp_sname, task_name, task_location, cat_desc, task_dueDate, task_completion, task_comments FROM tasks LEFT JOIN taskCategory on task_cat = cat_id LEFT JOIN userEmployee on assigned_emp = emp_id where task_status = 'Complete' and assigned_emp = '"+AssignedEmp+"';");
        ResultSet taskList = database.RunSQLQuery(sqlString);
        ArrayList<task> answer = new ArrayList<task>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the task arraylist
        try {
        while(taskList.next()) {
            task newTask = new task();
            newTask.setAssignedEmpFName(taskList.getString(1));
            newTask.setAssignedEmpSName(taskList.getString(2));
            newTask.setName(taskList.getString(3));
            newTask.setLocation(taskList.getString(4));
            newTask.setCategoryDesc(taskList.getString(5));
            newTask.setDueDate(taskList.getString(6));
            newTask.setCompletionDate(taskList.getString(7));
            newTask.setComments(taskList.getString(8));
            answer.add(newTask);
            }
            // to catch any errors from the sql statment and print them
        } catch (SQLException e) {
            System.out.println("Failed to process query in GetAllTasks()");
            System.out.println("SQL attempted" + sqlString);
            System.out.println("Error: " +e.getErrorCode());
            System.out.println("Message: " +e.getMessage());
            e.printStackTrace();
        }
        //returns the task arraylist
        return answer;
    }

    //function to add a new logged task to the database using the given variables 
    public void AddLoggedTask( String taskName, String taskLocation, int taskPriority, int taskCategoryID, String taskRoutine, int taskLength, String Comments, int CompletionDate, int AssignedEmp ) {
		String sqlString = new String("INSERT INTO tasks (task_name, task_location, task_priority, task_status, task_cat, task_routine, task_length, task_comments, task_completion, assigned_emp) VALUES  ('"+taskName+"','"+taskLocation+"','"+taskPriority+"','Complete','"+taskCategoryID+"','"+taskRoutine+"','"+taskLength+"','"+Comments+"','"+CompletionDate+"','"+AssignedEmp+"');");
       
        //runs the query and then checks if it was successful
		boolean success = database.RunSQL(sqlString);

		if(!success) {
			System.out.println("Failed to run query: "+sqlString);
		}
	}


	//function to get all the employees from the database and place them into an arraylist of employees to be returned
	public ArrayList<Employee> getAllEmployees(){
        String sqlString = new String("SELECT emp_id, emp_fname, emp_sname, emp_status, emp_taskPriority, emp_login, emp_password, emp_adminPriv, emp_management, emp_status FROM userEmployee;");
        ResultSet empList = database.RunSQLQuery(sqlString);
        ArrayList<Employee> answer = new ArrayList<Employee>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the task arraylist
        try {
        while(empList.next()) {
            Employee newEmp = new Employee();
            newEmp.setEmpID(empList.getInt(1));
            newEmp.setEmpFName(empList.getString(2));
            newEmp.setEmpSName(empList.getString(3));
            newEmp.setEmpStatus(empList.getString(4));
            newEmp.setEmpTaskPriority(empList.getInt(5));
            newEmp.setEmpUsername(empList.getString(6));
            newEmp.setEmpPassword(empList.getString(7));
            boolean adminBool = false;
            if(empList.getString(8).equals("TRUE") || empList.getString(8).equals("true"))  {
            	adminBool = true;
            }
            newEmp.setEmpPriv(adminBool);
            boolean managementBool = false;
            if(empList.getString(9).equals("TRUE") || empList.getString(9).equals("true")){
            	managementBool = true;
            }
            newEmp.setEmpManagement(managementBool);
            newEmp.setEmpActive(empList.getString(10));

            answer.add(newEmp);
            }
            // to catch any errors from the sql statment and print them
        } catch (SQLException e) {
            System.out.println("Failed to process query in GetAllEmployees()");
            System.out.println("SQL attempted" + sqlString);
            System.out.println("Error: " +e.getErrorCode());
            System.out.println("Message: " +e.getMessage());
            e.printStackTrace();
        }
        //returns the task arraylist
        return answer;
    }

	
    // function to return all task that are not currently assigned to an employee and then place them in arraylist of tasks
	public ArrayList<task> getAllUnassignedTasks(){
        String sqlString = new String("SELECT task_id, task_name, task_location, task_cat, task_Routine, task_length, task_priority,task_dueDate FROM tasks where assigned_emp = 'NULL';");
        ResultSet taskList = database.RunSQLQuery(sqlString);
        ArrayList<task> answer = new ArrayList<task>();
        //runs the sql statement and creates the arraylist as answer 

        //loops through the retunred query and places each atritbute into the task arraylist
        try {
        while(taskList.next()) {
            task newTask = new task();
            newTask.setTaskID(taskList.getInt(1));
            newTask.setName(taskList.getString(2));
            newTask.setLocation(taskList.getString(3));
            newTask.setCategoryID(taskList.getInt(4));
            newTask.setRoutine(taskList.getString(5));
            newTask.setDuration(taskList.getInt(6));
            newTask.setTaskPriority(taskList.getInt(7));
            newTask.setDueDate(taskList.getString(8));
            answer.add(newTask);
            }
            // to catch any errors from the sql statment and print them
        } catch (SQLException e) {
            System.out.println("Failed to process query in GetAllTasks()");
            System.out.println("SQL attempted" + sqlString);
            System.out.println("Error: " +e.getErrorCode());
            System.out.println("Message: " +e.getMessage());
            e.printStackTrace();
        }
        //returns the task arraylist
        return answer;
    }
	
	
	//function to update a given task with an employee ID
    public void AssignEmployee ( int taskID, int empID ) {
        String sqlString = new String("UPDATE tasks set assigned_emp = '"+empID+"' WHERE task_id = '"+taskID+"';");

        //runs the statemnt and returns an error if it fails
        boolean success = database.RunSQL(sqlString);

        if(!success) {
        System.out.println("Failed to run query: "+sqlString);
        }
    }
}
