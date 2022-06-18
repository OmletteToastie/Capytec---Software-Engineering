package taskAllocation;

import  java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dataComponent.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginArea {
	
	public static Employee currentUser = new Employee();
	
	private JFrame frame;
	private JTextField fieldUsername;
	private JPasswordField fieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginArea window = new LoginArea();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void checkDetails(String username, String password) {
		TaskSystemDatabase data = new TaskSystemDatabase();
		ArrayList<Employee> empList = data.getAllEmployees();

		String correctPassword = null;
		//Search the arraylist of employees for the input username and compare this with the corresponding 
		for(int i = 0; i < empList.size(); i++) {
			Employee currentEmp = empList.get(i);
			String currentEmpUsername = currentEmp.getEmpUsername();
			if(currentEmpUsername.equals(username)) {
				currentUser = currentEmp;
				correctPassword = currentEmp.getEmpPassword();
				break;
			}
		}
			
		if(password.equals(correctPassword)) {
			//Display logged in message and move the user into the userarea.
			JOptionPane.showMessageDialog(frame, "Logged In");
			
			UserArea userArea = new UserArea();
			userArea.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(frame, "Login Failed");
		}
	}
	/**
	 * Create the application.
	 */
	public LoginArea() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(31, 52, 68, 24);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(31, 100, 68, 24);
		frame.getContentPane().add(lblPassword);
		
		fieldUsername = new JTextField();
		fieldUsername.setBounds(109, 56, 268, 20);
		frame.getContentPane().add(fieldUsername);
		fieldUsername.setColumns(10);
		
		JButton Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Extract input username and password from the window and compare it with data in the database.
				String username = fieldUsername.getText();
				String password = new String(fieldPassword.getPassword());
				
				checkDetails(username, password);	
			}
		});
		Login.setBounds(177, 156, 107, 23);
		frame.getContentPane().add(Login);
		
		fieldPassword = new JPasswordField();
		fieldPassword.setBounds(109, 102, 268, 20);
		frame.getContentPane().add(fieldPassword);
	}
}
