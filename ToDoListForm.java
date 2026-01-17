package ui;

import controller.TaskManager;
import model.Task;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListForm extends JFrame {
    private JTextField nameInput;
    private JTextArea descInput;
    private JComboBox<String> statusDropdown;
    private TaskManager manager;
    private JTable table;
    
    public ToDoListForm(TaskManager taskManager, JTable dataTable) {
        manager = taskManager;
        table = dataTable;
        
        this.setTitle("Add New Task");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        buildForm();
    }
    
    private void buildForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel nameLabel = new JLabel("Task Name:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        formPanel.add(nameLabel, constraints);
        
        nameInput = new JTextField(20);
        constraints.gridx = 1;
        formPanel.add(nameInput, constraints);
        
        JLabel descLabel = new JLabel("Task Description:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTH;
        formPanel.add(descLabel, constraints);
        
        descInput = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(descInput);
        constraints.gridx = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        formPanel.add(scrollPane, constraints);
        
        JLabel statusLabel = new JLabel("Status:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0;
        constraints.weighty = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        formPanel.add(statusLabel, constraints);
        
        String[] statusOptions = {"Not Started", "Ongoing", "Completed"};
        statusDropdown = new JComboBox<>(statusOptions);
        constraints.gridx = 1;
        formPanel.add(statusDropdown, constraints);
        
        JButton saveButton = new JButton("Save Task");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        formPanel.add(saveButton, constraints);
        
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSave();
            }
        });
        
        this.add(formPanel);
    }
    
    private void handleSave() {
        String taskName = nameInput.getText().trim();
        String taskDescription = descInput.getText().trim();
        String taskStatus = (String) statusDropdown.getSelectedItem();
        
        if (taskName.isEmpty() || taskDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Please fill in Task Name and Status.", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int newTaskId = manager.generateNewId();
        Task newTask = new Task(newTaskId, taskName, taskDescription, taskStatus);
        manager.createTask(newTask);
        
        refreshTableData();
        this.dispose();
    }
    
    private void refreshTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        
        ArrayList<Task> allTasks = manager.viewAllTasks();
        for (int i = 0; i < allTasks.size(); i++) {
            Task currentTask = allTasks.get(i);
            Object[] row = {
                currentTask.getTaskName(),
                currentTask.getTaskDescription(),
                currentTask.getStatus()
            };
            tableModel.addRow(row);
        }
    }
}