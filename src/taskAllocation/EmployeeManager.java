package taskAllocation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dataComponent.TaskSystemDatabase;
import dataComponent.task;
import taskentrycomponent.edittask;

import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmployeeManager extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public static int selectedUserID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeManager frame = new EmployeeManager();
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
	public EmployeeManager() {
		//Connects to the database.
		TaskSystemDatabase data = new TaskSystemDatabase();
		//Creates arraylists of data containing all employees and tasks respectively.
		ArrayList<task> taskList = data.getAllTasks();
		ArrayList<Employee> empList = data.getAllEmployees();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		JTable table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);
		
		//Set column headers.
		String header[] = new String[] { "ID", "First Name", "Surname", "Username", "Preferred Task", "Admin",
				"Management", "Active" };

		tableModel.setColumnIdentifiers(header);
		table.setModel(tableModel);
		table.setAutoCreateRowSorter(true);
		
		//Searches employee class for data on the employee.
		for (int i = 0; i < empList.size(); i++) {
			Employee currentEmp = empList.get(i);
			String preferredTask = null;
			for (int j = 0; j < taskList.size(); j++) {
				//Ensure table does not fail if tasks cannot be found.
				task currentTask = taskList.get(j);

				if (currentTask.getID() == currentEmp.getEmpTaskPriority()) {
					preferredTask = currentTask.getName();
					break;
				} else {
					preferredTask = "Task Not Found";
				}
			}
			tableModel.addRow(new Object[] { currentEmp.getEmpID(), currentEmp.getEmpFName(), currentEmp.getEmpSName(),
					currentEmp.getEmpUsername(), preferredTask, currentEmp.getEmpPriv(),
					currentEmp.getEmpManagement(), currentEmp.getEmpActive() });
		}
		JScrollPane scrollPane_1 = new JScrollPane(table);
		getContentPane().add(scrollPane_1);

		JPanel ButtonPanel = new JPanel();
		contentPane.add(ButtonPanel, BorderLayout.SOUTH);
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton ButtonAdd = new JButton("Add");
		ButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Forwards to the create account form.
				CreateAccount createAccount = new CreateAccount();
				createAccount.setVisible(true);
			}
		});
		ButtonPanel.add(ButtonAdd);

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Gets the selected row and removes the employee from the selected row.
				int row = table.getSelectedRow();
				if (row >= 0) {
					selectedUserID = (int) table.getValueAt(row, 0);
					data.DeleteEmployee(selectedUserID);
					JOptionPane.showMessageDialog(contentPane, "User successfully removed.");
				} else {
					JOptionPane.showMessageDialog(contentPane, "Please select a row.");
				}
			}
		});
		ButtonPanel.add(btnRemove);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Gets the selected row and goes to the edit form.
				int row = table.getSelectedRow();
				selectedUserID = table.getSelectedRow();
				if (row >= 0) { 
					//Forwards all data from the selected row to the edit account form.
					selectedUserID = (int) table.getValueAt(row, 0);
					EditAccount editAccount = new EditAccount();

					editAccount.fieldUsername.setText(table.getValueAt(row, 3).toString());
					editAccount.fieldFName.setText(table.getValueAt(row, 1).toString());
					editAccount.fieldSName.setText(table.getValueAt(row, 2).toString());
					editAccount.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(contentPane, "Please select a row.");
				}
			}
		});
		ButtonPanel.add(btnEdit);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Closes the current window.
				dispose();
			}
		});
		ButtonPanel.add(btnClose);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Opens and closes window to refresh table data.
				EmployeeManager refresh = new EmployeeManager();
				dispose();
				refresh.setVisible(true);
			}
		});
		ButtonPanel.add(btnRefresh);
	}

}
