package taskAllocation;

import taskentrycomponent.*;
import LogTask.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dataComponent.TaskSystemApp;
import dataComponent.TaskSystemDatabase;
import dataComponent.task;

import javax.swing.JScrollPane;
import javax.swing.JButton;

public class UserArea extends JFrame {

	private JPanel contentPane;
	private Employee user = LoginArea.currentUser;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserArea frame = new UserArea();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserArea() {
		
		TaskSystemDatabase data = new TaskSystemDatabase();
		
		ArrayList<Employee> empList = data.getAllEmployees();
		
		ArrayList<task> taskList = data.getAllTasksAdmin();
		ArrayList<task> userTasks = data.getAssignedTasks(user.getEmpID());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblHeader = new JLabel("Welcome, " + user.getEmpFName());
		contentPane.add(lblHeader, BorderLayout.NORTH);
		
		
		
		JTable table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);

		
		if(user.getEmpPriv() == true) {
			for (int i = 0; i < taskList.size(); i++) {
			//Create column headers.
			String header[] = new String[] {
					"Name", "Location", "Priority", "Length", "Due Date", "Assigned Employee"
				};
			
			tableModel.setColumnIdentifiers(header);
			table.setModel(tableModel);
			table.setAutoCreateRowSorter(true);
			task currentTask = taskList.get(i);
			String assignedEmpName = currentTask.getAssignedEmpFName() + " " + currentTask.getAssignedEmpSName();
//			String assignedEmpName = "Unset";
//			for(int j = 0; j < empList.size(); j++) {
//				if(empList.get(j).getEmpID() == currentTask.getAssignedEmp());
//			}
			tableModel.addRow(new Object[] { currentTask.getName(), currentTask.getLocation(), currentTask.getTaskPriority(),
					currentTask.getTaskLength(), currentTask.getDueDate(), assignedEmpName});
			}
		} else {
		//Create a table of the currently logged in users tasks.
		for (int i = 0; i < userTasks.size(); i++) {
			userTasks = data.getAssignedTasks(user.getEmpID());
			//Create column headers.
			String header[] = new String[] {
					"Name", "Location", "Priority", "Length", "Due Date"
				};
			
			tableModel.setColumnIdentifiers(header);
			table.setModel(tableModel);
			
			
			task currentTask = userTasks.get(i);
			tableModel.addRow(new Object[] { currentTask.getName(), currentTask.getLocation(), currentTask.getTaskPriority(),
					currentTask.getTaskLength(), currentTask.getDueDate()});
		}
	 }
		JScrollPane scrollPane_1 = new JScrollPane(table);
		getContentPane().add(scrollPane_1);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close the window.
				dispose();
			}
		});
		
		JButton btnTaskManager = new JButton("Task Manager");
		btnTaskManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Take the user into the task manager.
				TaskSystemApp mh = new TaskSystemApp();
			}
		});
		
		JButton btnAllocate = new JButton("Allocate Tasks");
		btnAllocate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Allocate the tasks.
				TaskAllocator taskAllocator = new TaskAllocator();
				taskAllocator.allocate();
				JOptionPane.showMessageDialog(contentPane, "Tasks Allocated.");
			}
		});
		panel.add(btnAllocate);
		panel.add(btnTaskManager);
		panel.add(btnLogout);
		
		JButton btnEmpManager = new JButton("Employee Manager");
		btnEmpManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Take the user into the employee manager.
				EmployeeManager employeeManager = new EmployeeManager();
				employeeManager.setVisible(true);
			}
		});
		panel.add(btnEmpManager);
		
		JButton btnTaskLogging = new JButton("Task Logging");
		btnTaskLogging.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Take the user into task logging area.
				maintasklogging taskLogging = new maintasklogging();
				taskLogging.setVisible(true);
			}
		});
		panel.add(btnTaskLogging);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserArea userArea = new UserArea();
				userArea.setVisible(true);
			}
		});
		panel.add(btnRefresh);
		
		//Hide the admin buttons if the user is not an admin.
		
		if(user.getEmpPriv() != true) {
			btnEmpManager.setVisible(false);
			btnTaskManager.setVisible(false);
			btnAllocate.setVisible(false);
		}
		
	}
}
