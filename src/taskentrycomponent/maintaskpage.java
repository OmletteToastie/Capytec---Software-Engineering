package taskentrycomponent;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dataComponent.TaskSystemApp;
import dataComponent.task;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
//All Import Packages
public class maintaskpage extends JFrame { //Start of the code
	 static TaskSystemApp thetaskHandler; //connects the Taskhandler method  from TaskSystem App to the Maintaskpage.//
	 static Jtextfieldcharlimit insertString; //Connects to the CharacterLimit File.
	 /**  
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tasktable;



	/**
	 * Creates the frame.
	 */
	public maintaskpage(TaskSystemApp taskHandler) {
		thetaskHandler = taskHandler; //Sets new variable taskHandler to be the same variable used to connect thetaskHandler. 
		setTitle("Task Entry Table"); //Sets the Window Title.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 450); //Dimensions of Window.
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel ButtonPanel = new JPanel(); //CREATES A BUTTON PANEL.
		contentPane.add(ButtonPanel, BorderLayout.SOUTH);
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//FUNCTIONALITY FOR THE ADD BUTTON.
		JButton ButtonAdd = new JButton("Add"); //Creates the new button and titles it 'Add'.
		ButtonAdd.addActionListener(new ActionListener() { //Creates an actionlistener for the button, for the ADD task functionality.
			public void actionPerformed(ActionEvent e) {
			addtask dialog = new addtask(); //Dialog becomes a variable for Add task, so when Add Button is pressed, addtask.java is opened.
			dialog.setVisible(true);

			String taskName = dialog.taskName.getText(); //gets the string from the TaskName Field in addtask.
			String taskLocation = dialog.taskLocation.getText(); //Gets the string from the taskLocation Field in addtask.
			String taskDueDate = dialog.taskDueDateField.getText();
			int taskCategory = dialog.taskCategoryField.getSelectedIndex(); //Retrieves the selected drop down option from the category combobox.
			int taskPriority = dialog.taskPriorityField.getSelectedIndex(); // //Retrieves the selected drop down option from the priority combobox.
			String taskRoutine = (String) dialog.comboBoxRoutine.getSelectedItem(); //gets the true or false value from the Routine combobox.
			
			
				//GETS THE TASK DURATION STRING AND CONVERTS IT TO INT
				int taskLength = 0;
				try {
					taskLength = Integer.parseInt(dialog.taskLengthField.getText());
				} catch (NumberFormatException ex) {
					taskLength =0;
				}

				if // If any of the fields are empty when 'ok' is pressed, a warning dialog box will appear.
				(taskName.isEmpty()
					||taskLocation.isEmpty()
					||taskRoutine.isEmpty()
					||dialog.taskLengthField.getText().equals("")
					||dialog.taskDueDateField.getText().equals("")
					||dialog.comboBoxRoutine.getSelectedItem().equals("Select Recurring or One Off")
					||dialog.taskCategoryField.getSelectedItem().equals("Select Category")
					||dialog.taskPriorityField.getSelectedItem().equals("Select Priority Level")	){
					JOptionPane.showMessageDialog(rootPane, "Please completely fill in the form. ");
				}
				
				else if //otherwise if none of the fields are empty, the task will be successfully added to the database.
				(
					!dialog.taskName.getText().equals("")
					||!dialog.taskLocation.getText().equals("")
					||!dialog.taskLengthField.getText().equals("")
					||!dialog.taskDueDateField.getText().equals("")
					||!dialog.comboBoxRoutine.getSelectedItem().equals("Select Recurring or One Off")
					||!dialog.taskCategoryField.getSelectedItem().equals("Select Category")
					||!dialog.taskPriorityField.getSelectedItem().equals("Select Priority Level")	){
				thetaskHandler.AddTask(taskName,taskLocation,taskPriority,taskCategory,taskRoutine,taskLength,taskDueDate);
				JOptionPane.showMessageDialog(rootPane, "Task Successfully Added");
				}
			
		}
	});
		ButtonPanel.add(ButtonAdd); //Adds the Add button to the button panel.
		
		

		// REMOVE BUTTON AND REMOVE TASK FUNCTIONALITY.
		JButton ButtonRemove = new JButton("Remove"); // Creates the remove button.
		ButtonRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			//Selects the row to delete from the Table.
			int DeleteRow = tasktable.getSelectedRow();
			
			//If a row is selected, Delete its contents based off the task ID
			if(DeleteRow>=-0) {
				int RowtoDelete = (int) tasktable.getValueAt(DeleteRow, 0); //Selects the task ID in the 0 row
				thetaskHandler.DeleteTask(RowtoDelete); // calls the Delete task 
				JOptionPane.showMessageDialog(rootPane, "Task successfully deleted."); //If a task is successfully removed from the db, this is displayed
			}
			else{
				JOptionPane.showMessageDialog(rootPane, "Please select a Row first."); //If no row is selected, display this.
			}
			}
		});
		ButtonPanel.add(ButtonRemove); // Adds the remove button to the Button Panel.

		//EDIT BUTTON AND EDIT TASK FUNCTIONALITY
		JButton ButtonEdit = new JButton("Edit"); //Creates a new Button and titles it Edit.
		ButtonEdit.addActionListener(new ActionListener() { //Creates actionlistener for Edit button.
			public void actionPerformed(ActionEvent e) {
				int i = tasktable.getSelectedRow(); //Sets I to be the selected row
				if(i>=0) { //If I is greater than or Equal to 0, collect all values from the selected row and parse into Edit Form.
					edittask dialog = new edittask();
					dialog.TaskIDField.setText(tasktable.getValueAt(i,0).toString());
					dialog.taskName.setText(tasktable.getValueAt(i,1).toString());
					dialog.taskLocation.setText(tasktable.getValueAt(i,2).toString());
					dialog.setVisible(true);
					String taskName = dialog.taskName.getText();
					String taskLocation = dialog.taskLocation.getText();
					String taskDueDate = dialog.taskDueDateField.getText();
					int taskCategory = dialog.taskCategoryField.getSelectedIndex();
					int taskPriority = dialog.taskPriorityField.getSelectedIndex();
					String taskRoutine = (String) dialog.comboBoxRoutine.getSelectedItem();
	
					int taskID = 1;
					try {
						taskID = Integer.parseInt(dialog.TaskIDField.getText());
					} catch (NumberFormatException ex) {
						taskID = 1;
					}
					
					int taskLength = 0;
					try {
						taskLength = Integer.parseInt(dialog.taskLengthField.getText());
					} catch (NumberFormatException ex) {
						taskLength = 0;
					}

					if ( // If any of the fields are empty when 'ok' is pressed, a warning dialog box will appear.
					  dialog.taskName.getText().equals("")
					||dialog.taskLocation.getText().equals("")
					||dialog.taskLengthField.getText().equals("")
					||dialog.taskDueDateField.getText().equals("")
					||dialog.comboBoxRoutine.getSelectedItem().equals("Select True or False")
					||dialog.taskCategoryField.getSelectedItem().equals("Select Category")
					||dialog.taskPriorityField.getSelectedItem().equals("Select Priority Level")	
					)					
				{
					JOptionPane.showMessageDialog(rootPane, "Please Complete the edit form. ");
				}

					else if(  //otherwise if none of the fields are empty, the task will be successfully added to the database.
					!taskName.isEmpty()
					||!taskLocation.isEmpty()
					||!taskRoutine.isEmpty()
					||!dialog.taskLengthField.getText().equals("")
					||!dialog.taskDueDateField.getText().equals("")
					||!dialog.comboBoxRoutine.getSelectedItem().equals("Select True or False")
					||!dialog.taskCategoryField.getSelectedItem().equals("Select Category")
					||!dialog.taskPriorityField.getSelectedItem().equals("Select Priority Level")	){
				
					thetaskHandler.UpdateTask(taskName, taskLocation,taskPriority,taskCategory,taskRoutine,taskLength,taskDueDate,taskID);
					JOptionPane.showMessageDialog(rootPane, "Task Successfully Updated");
					}
					
					}
		
				
	
			
			}
		});
		ButtonPanel.add(ButtonEdit); //Adds edit button to Button Panel.
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		ButtonPanel.add(btnClose);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane(); //Scroll Pane for the Table
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		//TASK TABLE, SORTING AND SELECTION FUNCTIONALITY
	tasktable = new JTable(); //Implements the table 
	tasktable.setAutoCreateRowSorter(true); // Creates the sorting function
	tasktable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Sets the table to only allow selections of single rows at a time.
	tasktable.setModel(new DefaultTableModel(
		new Object[][] {
		},
		new String[] { //Sets the Titles of the columns
				"Task ID",
				"Task Name", 
				"Description", 
				"Category", 
				"One off / Recurring", 
				"Duration", 
				"Importance",
				"Due Date"
		}
	));
	tasktable.getColumnModel().getColumn(0).setPreferredWidth(50); //Table size width and columns
	scrollPane.setViewportView(tasktable);
}

	public void displayTableData(ArrayList<task> tableData){ //SECTION DISPLAYS THE TABLE DATA
		
		// Empties the existing data should new data be added.
		DefaultTableModel tableModel = (DefaultTableModel) tasktable.getModel();
		tableModel.setRowCount(0);
		
		// collects the data from the database through task (See task.Java if having issues).
		for(task s: tableData) {
			tableModel.addRow(new Object[] {s.getID(),s.getName(), s.getLocation(), s.getCategoryDesc(),s.getRoutine(),s.getTaskLength(),s.getTaskPriority(),s.getDueDate()});
		}
	}
	
	
	
}

