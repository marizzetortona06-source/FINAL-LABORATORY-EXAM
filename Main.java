package ui;

import controller.TaskManager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTable displayTable;
    private DefaultTableModel dataModel;
    private TaskManager manager;
    
    public Main() {
        this.manager = new TaskManager();
        
        this.setTitle("To-Do List Viewer");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        initializeWindow();
    }
    
    private void initializeWindow() {
        String[] columnHeaders = {"Task Name", "Task Description", "Status"};
        dataModel = new DefaultTableModel(columnHeaders, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        displayTable = new JTable(dataModel);
        JScrollPane tableScrollPane = new JScrollPane(displayTable);
        
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFormWindow();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        
        this.setLayout(new BorderLayout());
        this.add(tableScrollPane, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.NORTH);
    }
    
    private void openFormWindow() {
        ToDoListForm taskForm = new ToDoListForm(manager, displayTable);
        taskForm.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main mainWindow = new Main();
                mainWindow.setVisible(true);
            }
        });
    }
}