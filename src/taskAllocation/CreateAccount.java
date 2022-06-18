package taskAllocation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataComponent.TaskSystemDatabase;
import dataComponent.task;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField fieldUsername;
	private JTextField fieldFName;
	private JTextField fieldSName;
	private JPasswordField fieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
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
	public CreateAccount() {

		TaskSystemDatabase data = new TaskSystemDatabase();

		ArrayList<task> taskList = new ArrayList<task>();
		taskList = data.getAllTasks();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Create Account");
		lblTitle.setBounds(5, 5, 424, 22);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblTitle);

		JLabel lblNewUsername = new JLabel("Username");
		lblNewUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewUsername.setBounds(33, 58, 93, 14);
		contentPane.add(lblNewUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(33, 86, 93, 14);
		contentPane.add(lblPassword);

		fieldUsername = new JTextField();
		fieldUsername.setBounds(148, 58, 164, 20);
		contentPane.add(fieldUsername);
		fieldUsername.setColumns(10);

		fieldFName = new JTextField();
		fieldFName.setColumns(10);
		fieldFName.setBounds(148, 114, 164, 20);
		contentPane.add(fieldFName);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds(33, 114, 93, 14);
		contentPane.add(lblFirstName);

		fieldSName = new JTextField();
		fieldSName.setColumns(10);
		fieldSName.setBounds(148, 145, 164, 20);
		contentPane.add(fieldSName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurname.setBounds(33, 145, 93, 14);
		contentPane.add(lblSurname);

		JLabel lblPreferredTask = new JLabel("Preferred Task");
		lblPreferredTask.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreferredTask.setBounds(33, 207, 93, 14);
		contentPane.add(lblPreferredTask);

		JCheckBox fieldAdmin = new JCheckBox("");
		fieldAdmin.setBounds(215, 236, 27, 23);
		contentPane.add(fieldAdmin);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdmin.setBounds(33, 238, 93, 14);
		contentPane.add(lblAdmin);

		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(148, 86, 164, 20);
		contentPane.add(fieldPassword);
		
		
		JCheckBox fieldManagement = new JCheckBox("");
		fieldManagement.setBounds(215, 174, 27, 23);
		contentPane.add(fieldManagement);
		
		
		JLabel lblManagement = new JLabel("Management");
		lblManagement.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManagement.setBounds(33, 176, 93, 14);
		contentPane.add(lblManagement);

		String[] taskArray = new String[taskList.size()];

		for (int i = 0; taskList.size() > i; i++) {
			taskArray[i] = taskList.get(i).getName();
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox fieldTask = new JComboBox(taskArray);
		fieldTask.setBounds(148, 207, 164, 20);
		contentPane.add(fieldTask);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<task> taskList = data.getAllTasks();
				
				//Collects data from all fields.
				String username = fieldUsername.getText();
				String password = new String(fieldPassword.getPassword());
				String fname = fieldFName.getText();
				String sname = fieldSName.getText();
				String preferredTaskString = (String) fieldTask.getSelectedItem();
				int preferredTask = -1;
				//Finds task ID using taskList by searching for the task name.
				for (int i = 0; taskList.size() > i; i++) {
					task currentTask = taskList.get(i);
					if(currentTask.getName().equals(preferredTaskString)) {
						preferredTask = currentTask.getID();
					}
				}
				//Sets booleans to true if checkbox is selected
				boolean admin = false;
				if (fieldAdmin.isSelected()) {
					admin = true;
				}
				boolean management = false;
				if (fieldManagement.isSelected()) {
					management = true;
				}
				//Checks that all fields are completed.
				if(username.equals("") || password.equals("") || fname.equals("") || sname.equals("")) {

					JOptionPane.showMessageDialog(contentPane, "Please ensure all fields are completed.");
				} else {
					//Updates employee based on supplied data
					data.AddEmployee(fname, sname, username, password, preferredTask, admin, management);
					JOptionPane.showMessageDialog(contentPane, "Account Creation Successful");
					dispose();
				}
			}
		});
		
		btnSubmit.setBounds(240, 266, 90, 23);
		contentPane.add(btnSubmit);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(125, 266, 90, 23);
		contentPane.add(btnCancel);
	}
}
