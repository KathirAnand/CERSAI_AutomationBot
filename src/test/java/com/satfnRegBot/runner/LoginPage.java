package com.satfnRegBot.runner;

import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class LoginPage extends JPanel {
	private JTextField userID;
	private JTextField password;
	private static final long serialVersionUID = 1L;
	protected static String actualUserId;
	protected static String excelPath;
	protected static String cofigPath;
	public static String actualPassword;
	
	public static TestNG testNG;
	public static TestListenerAdapter tla;
	private JLabel message;
	private JFileChooser fileChooser;
	private JLabel selectedFilePath;
	private JLabel selectedConfigPath;
	

	/**
	 * Create the panel.
	 */
	public LoginPage() {
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(255, 255, 255));
		layeredPane.setBounds(-2, 293, 452, -293);
		add(layeredPane);
		
		JPanel left_panel = new JPanel();
		left_panel.setBackground(new Color(0, 102, 102));
		left_panel.setBounds(0, 0, 400, 400);
		add(left_panel);
		left_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\CERSAI_AutomationBot\\InvBot\\icon\\InvBotIcon.png"));
		lblNewLabel_2.setBounds(118, 49, 219, 186);
		left_panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Copyrights © 2024  All rights reserved");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_3.setBounds(28, 375, 219, 14);
		left_panel.add(lblNewLabel_3);
		
		JPanel right_panel = new JPanel();
		right_panel.setBackground(new Color(255, 255, 255));
		right_panel.setBounds(400, 0, 400, 400);
		add(right_panel);
		right_panel.setLayout(null);
		
		JLabel name = new JLabel("InvBot");
		name.setFont(new Font("Segoe UI", Font.BOLD, 36));
		name.setForeground(new Color(0, 102, 102));
		name.setBackground(new Color(0, 102, 102));
		name.setBounds(160, 11, 120, 48);
		right_panel.add(name);
		
		JLabel email_label = new JLabel("User ID");
		email_label.setForeground(new Color(0, 102, 102));
		email_label.setFont(new Font("Segoe UI", Font.BOLD, 21));
		email_label.setBounds(10, 75, 83, 29);
		right_panel.add(email_label);
		
		userID = new JTextField();
		userID.setBounds(10, 115, 270, 27);
		right_panel.add(userID);
		userID.setColumns(10);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(new Color(0, 102, 102));
		password_label.setFont(new Font("Segoe UI", Font.BOLD, 21));
		password_label.setBounds(10, 153, 120, 29);
		right_panel.add(password_label);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(10, 193, 270, 27);
		right_panel.add(password);
		
		JButton select_excel = new JButton("Select Excel");
		select_excel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				int response = fileChooser.showOpenDialog(null);// select file to open
				if (response == JFileChooser.APPROVE_OPTION) {

					excelPath = fileChooser.getSelectedFile().getAbsolutePath();
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					excelPath = file.getPath();
					selectedFilePath.setText(excelPath);
				}
			}
		});
		select_excel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		select_excel.setBackground(new Color(0, 102, 102));
		select_excel.setBounds(10, 240, 100, 30);
		right_panel.add(select_excel);
		
		selectedFilePath = new JLabel("");
		selectedFilePath.setBounds(10, 280, 359, 27);
		right_panel.add(selectedFilePath);
		
		JButton btnNewButton = new JButton("Start Registration");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualUserId = userID.getText();
				System.out.println(actualUserId);
				actualPassword = password.getText();
				System.out.println(actualPassword);
				if (actualUserId != null && actualUserId != "") {
					if (actualPassword != null && actualPassword != "") {

						tla = new TestListenerAdapter();
						testNG = new TestNG();
						// path of the XML file
//						String path = System.getProperty("user.dir") + File.separator + "testng.xml";

						// by using the suiteXML file
//						List<String> suiteXMLs = new ArrayList<String>();
//						suiteXMLs.add(path);
//						testNG.setTestSuites(suiteXMLs);

						testNG.setTestClasses(new Class[] {com.satfnRegBot.automationScripts.SatisfactionRegisteration.class});
						testNG.run();
					} else {
						message.setText("Please enter Password");
					}
				} else {
					message.setText("Please enter userID");
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(0, 102, 102));
		btnNewButton.setBounds(125, 354, 130, 30);
		right_panel.add(btnNewButton);
		
		JButton select_config = new JButton("Select Config");
		select_config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				int response = fileChooser.showOpenDialog(null);// select file to open
				if (response == JFileChooser.APPROVE_OPTION) {

					excelPath = fileChooser.getSelectedFile().getAbsolutePath();
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					cofigPath = file.getPath();
					selectedConfigPath.setText(excelPath);
				}
			}
		});
		select_config.setBackground(new Color(0, 102, 102));
		select_config.setFont(new Font("Segoe UI", Font.BOLD, 11));
		select_config.setBounds(244, 240, 100, 30);
		right_panel.add(select_config);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(10, 316, 359, 27);
		right_panel.add(lblNewLabel_1);

	}
	
}
