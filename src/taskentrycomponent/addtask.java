package taskentrycomponent;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
public class addtask extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel tasknamedescpanel = new JPanel();
	public JTextField taskName;
	public JTextField taskLocation;
	private final JLabel LabelCategory = new JLabel("Category");
	public JTextField taskLengthField;
	public JComboBox<?> taskCategoryField;
	public JComboBox<?> taskPriorityField;
	public JTextField taskDueDateField;
	public JComboBox<?> comboBoxRoutine;
	/**
	 * Create the dialog.
	 */
	public addtask() {
		setTitle("Add Task Form");
		setModal(true);
		setBounds(100, 100, 850, 385);
		getContentPane().setLayout(new BorderLayout());
		tasknamedescpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(tasknamedescpanel, BorderLayout.CENTER);
		GridBagLayout gbl_tasknamedescpanel = new GridBagLayout();
		gbl_tasknamedescpanel.columnWidths = new int[]{143, 143, 143, 143, 0, 0};
		gbl_tasknamedescpanel.rowHeights = new int[]{20, 20, 20, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_tasknamedescpanel.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_tasknamedescpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		tasknamedescpanel.setLayout(gbl_tasknamedescpanel);
		//TASK NAME FORM AREA
		{
			JLabel TaskNameLabel = new JLabel("Task Name:");
			GridBagConstraints gbc_TaskNameLabel = new GridBagConstraints();
			gbc_TaskNameLabel.fill = GridBagConstraints.BOTH;
			gbc_TaskNameLabel.insets = new Insets(0, 0, 5, 5);
			gbc_TaskNameLabel.gridx = 0;
			gbc_TaskNameLabel.gridy = 0;
			tasknamedescpanel.add(TaskNameLabel, gbc_TaskNameLabel);
		}
		{
			taskName = new JTextField();
			taskName.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_taskName = new GridBagConstraints();
			gbc_taskName.fill = GridBagConstraints.BOTH;
			gbc_taskName.insets = new Insets(0, 0, 5, 5);
			gbc_taskName.gridx = 1;
			gbc_taskName.gridy = 0;
			tasknamedescpanel.add(taskName, gbc_taskName);
			taskName.setText("Input task name here.");
			taskName.setColumns(10);
		}
		//END OF TASK FORM AREA.
		//TASK DESCRIPTION FORM AREA.
		{
			JLabel TaskDescriptionLabel = new JLabel("Task Description:");
			GridBagConstraints gbc_TaskDescriptionLabel = new GridBagConstraints();
			gbc_TaskDescriptionLabel.fill = GridBagConstraints.BOTH;
			gbc_TaskDescriptionLabel.insets = new Insets(0, 0, 5, 5);
			gbc_TaskDescriptionLabel.gridx = 0;
			gbc_TaskDescriptionLabel.gridy = 1;
			tasknamedescpanel.add(TaskDescriptionLabel, gbc_TaskDescriptionLabel);
		}
		{
			taskLocation = new JTextField();
			taskLocation.setHorizontalAlignment(SwingConstants.CENTER);
			taskLocation.setText("Task description here.");
			GridBagConstraints gbc_taskLocation = new GridBagConstraints();
			gbc_taskLocation.fill = GridBagConstraints.BOTH;
			gbc_taskLocation.insets = new Insets(0, 0, 5, 5);
			gbc_taskLocation.gridx = 1;
			gbc_taskLocation.gridy = 1;
			tasknamedescpanel.add(taskLocation, gbc_taskLocation);
			taskLocation.setColumns(10);
		}
		//END OF TASK DESCRIPTION CODE.
		
		//LABELS AND VALUES FOR TABLE.
		{
			JLabel LabelTaskDuration = new JLabel("Task Duration");
			GridBagConstraints gbc_LabelTaskDuration = new GridBagConstraints();
			gbc_LabelTaskDuration.insets = new Insets(0, 0, 5, 5);
			gbc_LabelTaskDuration.gridx = 0;
			gbc_LabelTaskDuration.gridy = 2;
			tasknamedescpanel.add(LabelTaskDuration, gbc_LabelTaskDuration);
		}
		
					
		{
			JLabel LabelFrequency = new JLabel("Frequency of Task");
			GridBagConstraints gbc_LabelFrequency = new GridBagConstraints();
			gbc_LabelFrequency.insets = new Insets(0, 0, 5, 5);
			gbc_LabelFrequency.gridx = 1;
			gbc_LabelFrequency.gridy = 2;
			tasknamedescpanel.add(LabelFrequency, gbc_LabelFrequency);
		}
		
		GridBagConstraints gbc_LabelCategory = new GridBagConstraints();
		gbc_LabelCategory.insets = new Insets(0, 0, 5, 5);
		gbc_LabelCategory.gridx = 2;
		gbc_LabelCategory.gridy = 2;
		tasknamedescpanel.add(LabelCategory, gbc_LabelCategory);
		
		{
			JLabel LabelImportance = new JLabel("Importance");
			GridBagConstraints gbc_LabelImportance = new GridBagConstraints();
			gbc_LabelImportance.insets = new Insets(0, 0, 5, 5);
			gbc_LabelImportance.gridx = 3;
			gbc_LabelImportance.gridy = 2;
			tasknamedescpanel.add(LabelImportance, gbc_LabelImportance);
		}
		{
			JLabel LabelDueDate = new JLabel("Due date for task");
			GridBagConstraints gbc_LabelDueDate = new GridBagConstraints();
			gbc_LabelDueDate.insets = new Insets(0, 0, 5, 0);
			gbc_LabelDueDate.gridx = 4;
			gbc_LabelDueDate.gridy = 2;
			tasknamedescpanel.add(LabelDueDate, gbc_LabelDueDate);
		}
		{
			JLabel LengthDesc = new JLabel("Please enter the task's length");
			GridBagConstraints gbc_LengthDesc = new GridBagConstraints();
			gbc_LengthDesc.insets = new Insets(0, 0, 5, 5);
			gbc_LengthDesc.gridx = 0;
			gbc_LengthDesc.gridy = 4;
			tasknamedescpanel.add(LengthDesc, gbc_LengthDesc);
		}
		{
			JLabel FrequencyDesc = new JLabel("Please choose if your task");
			GridBagConstraints gbc_FrequencyDesc = new GridBagConstraints();
			gbc_FrequencyDesc.insets = new Insets(0, 0, 5, 5);
			gbc_FrequencyDesc.gridx = 1;
			gbc_FrequencyDesc.gridy = 4;
			tasknamedescpanel.add(FrequencyDesc, gbc_FrequencyDesc);
		}
		{
			JLabel CategoryDesc = new JLabel("Please set a Category Value");
			GridBagConstraints gbc_CategoryDesc = new GridBagConstraints();
			gbc_CategoryDesc.insets = new Insets(0, 0, 5, 5);
			gbc_CategoryDesc.gridx = 2;
			gbc_CategoryDesc.gridy = 4;
			tasknamedescpanel.add(CategoryDesc, gbc_CategoryDesc);
		}
		{
			JLabel PriorityDesc = new JLabel("Please set a Priority Value");
			GridBagConstraints gbc_PriorityDesc = new GridBagConstraints();
			gbc_PriorityDesc.insets = new Insets(0, 0, 5, 5);
			gbc_PriorityDesc.gridx = 3;
			gbc_PriorityDesc.gridy = 4;
			tasknamedescpanel.add(PriorityDesc, gbc_PriorityDesc);
		}
		{
			JLabel DueDateDesc = new JLabel("Enter the date due for task");
			GridBagConstraints gbc_DueDateDesc = new GridBagConstraints();
			gbc_DueDateDesc.insets = new Insets(0, 0, 5, 0);
			gbc_DueDateDesc.gridx = 4;
			gbc_DueDateDesc.gridy = 4;
			tasknamedescpanel.add(DueDateDesc, gbc_DueDateDesc);
		}
		{
			JLabel MinutesDesc = new JLabel("(In Minutes.)");
			GridBagConstraints gbc_MinutesDesc = new GridBagConstraints();
			gbc_MinutesDesc.insets = new Insets(0, 0, 5, 5);
			gbc_MinutesDesc.gridx = 0;
			gbc_MinutesDesc.gridy = 5;
			tasknamedescpanel.add(MinutesDesc, gbc_MinutesDesc);
		}
		{
			JLabel OneoffDesc = new JLabel("is a recurring task.");
			GridBagConstraints gbc_OneoffDesc = new GridBagConstraints();
			gbc_OneoffDesc.insets = new Insets(0, 0, 5, 5);
			gbc_OneoffDesc.gridx = 1;
			gbc_OneoffDesc.gridy = 5;
			tasknamedescpanel.add(OneoffDesc, gbc_OneoffDesc);
		}
		{
			JLabel CleaningDesc = new JLabel("Cleaning = 1");
			GridBagConstraints gbc_CleaningDesc = new GridBagConstraints();
			gbc_CleaningDesc.insets = new Insets(0, 0, 5, 5);
			gbc_CleaningDesc.gridx = 2;
			gbc_CleaningDesc.gridy = 5;
			tasknamedescpanel.add(CleaningDesc, gbc_CleaningDesc);
		}
		{
			JLabel MenialDesc = new JLabel("Menial = 1");
			GridBagConstraints gbc_MenialDesc = new GridBagConstraints();
			gbc_MenialDesc.insets = new Insets(0, 0, 5, 5);
			gbc_MenialDesc.gridx = 3;
			gbc_MenialDesc.gridy = 5;
			tasknamedescpanel.add(MenialDesc, gbc_MenialDesc);
		}
		{
			JLabel FormatLabel = new JLabel("YYYY-DD-MM");
			GridBagConstraints gbc_FormatLabel = new GridBagConstraints();
			gbc_FormatLabel.insets = new Insets(0, 0, 5, 0);
			gbc_FormatLabel.gridx = 4;
			gbc_FormatLabel.gridy = 5;
			tasknamedescpanel.add(FormatLabel, gbc_FormatLabel);
		}
		{
			JLabel MaintenanceDesc = new JLabel("Maintenance = 2");
			GridBagConstraints gbc_MaintenanceDesc = new GridBagConstraints();
			gbc_MaintenanceDesc.insets = new Insets(0, 0, 5, 5);
			gbc_MaintenanceDesc.gridx = 2;
			gbc_MaintenanceDesc.gridy = 6;
			tasknamedescpanel.add(MaintenanceDesc, gbc_MaintenanceDesc);
		}
		{
			JLabel StandardDesc = new JLabel("Standard = 2");
			GridBagConstraints gbc_StandardDesc = new GridBagConstraints();
			gbc_StandardDesc.insets = new Insets(0, 0, 5, 5);
			gbc_StandardDesc.gridx = 3;
			gbc_StandardDesc.gridy = 6;
			tasknamedescpanel.add(StandardDesc, gbc_StandardDesc);
		}
		{
			JLabel OutdoorsDesc = new JLabel("Outdoors = 3");
			GridBagConstraints gbc_OutdoorsDesc = new GridBagConstraints();
			gbc_OutdoorsDesc.insets = new Insets(0, 0, 5, 5);
			gbc_OutdoorsDesc.gridx = 2;
			gbc_OutdoorsDesc.gridy = 7;
			tasknamedescpanel.add(OutdoorsDesc, gbc_OutdoorsDesc);
		}
		{
			JLabel SignificantDesc = new JLabel("Significant = 3");
			GridBagConstraints gbc_SignificantDesc = new GridBagConstraints();
			gbc_SignificantDesc.insets = new Insets(0, 0, 5, 5);
			gbc_SignificantDesc.gridx = 3;
			gbc_SignificantDesc.gridy = 7;
			tasknamedescpanel.add(SignificantDesc, gbc_SignificantDesc);
		}
		{
			JLabel Otherdesc = new JLabel("Other = 4");
			GridBagConstraints gbc_Otherdesc = new GridBagConstraints();
			gbc_Otherdesc.insets = new Insets(0, 0, 5, 5);
			gbc_Otherdesc.gridx = 2;
			gbc_Otherdesc.gridy = 8;
			tasknamedescpanel.add(Otherdesc, gbc_Otherdesc);
		}
		//END OF LABELS AND TABLE VALUES
		
		//TASK LENGTH FORM AREA
		{
			taskLengthField = new JTextField(3);
			
			taskLengthField.addKeyListener(new KeyAdapter() {
		         public void keyPressed(KeyEvent ke) {
		        	 String taskLength = taskLengthField.getText();
			            int length = taskLength.length();
		            
			            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || (ke.getKeyChar()== KeyEvent.VK_BACK_SPACE)) && length<3) {
		            	taskLengthField.setEditable(true);
		            } else {
		            	taskLengthField.setEditable(true);
		            	 JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed"); //WARNING MESSAGE
		            }
		         }
			});
			
			taskLengthField.setText(" ");
			taskLengthField.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_taskLengthField = new GridBagConstraints();
			gbc_taskLengthField.insets = new Insets(0, 0, 5, 5);
			gbc_taskLengthField.fill = GridBagConstraints.HORIZONTAL;
			gbc_taskLengthField.gridx = 0;
			gbc_taskLengthField.gridy = 10;
			tasknamedescpanel.add(taskLengthField, gbc_taskLengthField);
			taskLengthField.setColumns(10);
			taskLengthField.setDocument(new Jtextfieldcharlimit(3));
		}
		//END OF TASK LENGTH AREA.
		
		//COMBO BOX FOR WHETHER TASKS ARE ROUTINE OR NOT
		{
			comboBoxRoutine = new JComboBox();
			comboBoxRoutine.setModel(new DefaultComboBoxModel(new String[] {"Select True or False", "TRUE", "FALSE"}));
			GridBagConstraints gbc_comboBoxRoutine = new GridBagConstraints();
			gbc_comboBoxRoutine.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxRoutine.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxRoutine.gridx = 1;
			gbc_comboBoxRoutine.gridy = 10;
			tasknamedescpanel.add(comboBoxRoutine, gbc_comboBoxRoutine);
		}
		//END OF TASK ROUTINE CODE
		
		//TASK CATEGORY COMBOBOX
		{
			taskCategoryField = new JComboBox();
			taskCategoryField.setModel(new DefaultComboBoxModel(new String[] {"Select Category", "Cleaning", "Maintenance", "Outdoor", "Other"}));
			GridBagConstraints gbc_taskCategoryField = new GridBagConstraints();
			gbc_taskCategoryField.insets = new Insets(0, 0, 5, 5);
			gbc_taskCategoryField.fill = GridBagConstraints.HORIZONTAL;
			gbc_taskCategoryField.gridx = 2;
			gbc_taskCategoryField.gridy = 10;
			tasknamedescpanel.add(taskCategoryField, gbc_taskCategoryField);
		}
		//END OF TASK CATEGORY CODE
		
		// TASK PRIORITY COMBOBOX
		{
			taskPriorityField = new JComboBox();
			taskPriorityField.setModel(new DefaultComboBoxModel(new String[] {"Select Priority Level", "1", "2", "3"}));
			GridBagConstraints gbc_taskPriorityField = new GridBagConstraints();
			gbc_taskPriorityField.insets = new Insets(0, 0, 5, 5);
			gbc_taskPriorityField.fill = GridBagConstraints.HORIZONTAL;
			gbc_taskPriorityField.gridx = 3;
			gbc_taskPriorityField.gridy = 10;
			tasknamedescpanel.add(taskPriorityField, gbc_taskPriorityField);
		}
		//END OF TASK PRIORITY CODE
		
		//TASK DUE DATE FIELD
		{
			taskDueDateField = new JTextField();
			
			taskDueDateField.addKeyListener(new KeyAdapter() {
		         public void keyPressed(KeyEvent ke) {
		            String taskDueDate = taskDueDateField.getText();
		            int length = taskDueDate.length();
		            if ((ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() <= '-' || (ke.getKeyChar()== KeyEvent.VK_BACK_SPACE)) && length<10) {
		            	
		            	taskDueDateField.setEditable(true);
		            	
		            } else {
		            	taskDueDateField.setEditable(true);
		            	 JOptionPane.showMessageDialog(rootPane, "Only Numbers Allowed"); //WARNING MESSAGE
		            	 
		            }
		            
		       
		         }
			});
			
			GridBagConstraints gbc_taskDueDateField = new GridBagConstraints();
			gbc_taskDueDateField.insets = new Insets(0, 0, 5, 0);
			gbc_taskDueDateField.fill = GridBagConstraints.HORIZONTAL;
			gbc_taskDueDateField.gridx = 4;
			gbc_taskDueDateField.gridy = 10;
			tasknamedescpanel.add(taskDueDateField, gbc_taskDueDateField);
			taskDueDateField.setColumns(10);
		}
		//END OF TASK DUE DATE FIELD
		//BUTTON PANE AREA
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			//OKAY BUTTON CODE
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 setModal(false);
			         dispose();
				}
				
			});
				buttonPane.add(okButton);
			}
			
			//CANCEL BUTTON CODE
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						taskName.setText("Input task name here.");
						taskLocation.setText("Input task description here.");
						 setModal(false);
				         dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

}
