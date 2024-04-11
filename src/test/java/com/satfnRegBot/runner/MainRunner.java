package com.satfnRegBot.runner;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//import java.util.ArrayList;
//import java.util.List;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import com.satfnRegBot.pageActions.ProjectSpecificMethods;
import com.satfnRegBot.testBase.FilePaths;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class MainRunner extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	protected static String actualUserId;
	protected static String excelPath;
	public static String actualPassword;
	protected static String configPath;
	protected static String actualUserPIN;
	protected static String processLogsPath;
	protected Properties emailProps;
	protected Properties propsForLogID;

	public static TestNG testNG;
	public static TestListenerAdapter tla;
	private JTextField userID;
	private JLabel message;
	private JFileChooser fileChooser;
	private JButton selectFileBtn;
	private JLabel selectedFilePath;
	private JLabel lblNewLabel_2;
	private JButton select_config;
	private JPasswordField password_inputField;
	private JTextField userPIN_inputField;
	private JCheckBox showPassword_checkbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainRunner frame = new MainRunner();
					frame.setVisible(true);
					ImageIcon imgicon = new ImageIcon("D:\\Veritas\\SatfnReg.InvBot\\icon\\InvBotFavIcon.png");
					frame.setIconImage(imgicon.getImage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void intializeProperties() throws IOException {
		propsForLogID = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream(FilePaths.PROPERTIES_HOME + "config.properties");
			propsForLogID.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Create the frame.
	 */
	public MainRunner() {

		try {
			intializeProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTitle("Automation InvBot");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel userId_label = new JLabel("User ID");
		userId_label.setBounds(10, 54, 83, 19);
		contentPane.add(userId_label);

		userID = new JTextField();
		userID.setText(propsForLogID.getProperty("LOGIN_ID"));
		userId_label.setLabelFor(userID);
		userID.setBounds(103, 53, 230, 20);
		contentPane.add(userID);
		userID.setColumns(10);

		JLabel password_label = new JLabel("Password");
		password_label.setBounds(10, 91, 83, 14);
		contentPane.add(password_label);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualUserId = userID.getText();
				actualPassword = String.valueOf(password_inputField.getPassword());
				actualUserPIN = userPIN_inputField.getText();
				if (actualUserId != null && actualUserId != "") {
					if (actualPassword != null && actualPassword != "") {

						processLogsPath = FilePaths.PROCESS_LOGFILE
								+ ProjectSpecificMethods.getProcessLogFilenameWithMinutes();
						tla = new TestListenerAdapter();
						testNG = new TestNG();
						// path of the XML file
//						String path = System.getProperty("user.dir") + File.separator + "testng.xml";

						// by using the suiteXML file
//						List<String> suiteXMLs = new ArrayList<String>();
//						suiteXMLs.add(path);
//						testNG.setTestSuites(suiteXMLs);

						testNG.setTestClasses(
								new Class[] { com.satfnRegBot.automationScripts.SatisfactionRegisteration.class });
						testNG.run();
					} else {
						message.setText("Please enter Password");
					}
				} else {
					message.setText("Please enter userID");
				}

			}
		});
		btnNewButton.setBounds(155, 260, 89, 23);
		contentPane.add(btnNewButton);

		selectFileBtn = new JButton("Select File");
		selectFileBtn.addActionListener(new ActionListener() {
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
		selectFileBtn.setBounds(10, 187, 108, 23);
		contentPane.add(selectFileBtn);

		selectedFilePath = new JLabel("");
		selectedFilePath.setBounds(10, 235, 414, 14);
		contentPane.add(selectedFilePath);

		lblNewLabel_2 = new JLabel("CERSAI Satisfaction RegBot");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 17));
		lblNewLabel_2.setBounds(10, 11, 306, 32);
		contentPane.add(lblNewLabel_2);

		select_config = new JButton("Select Config");
		select_config.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				int response = fileChooser.showOpenDialog(null);// select file to open
				if (response == JFileChooser.APPROVE_OPTION) {

					configPath = fileChooser.getSelectedFile().getAbsolutePath();
					File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
					configPath = file.getPath();
				}
			}
		});
		select_config.setBounds(208, 187, 108, 23);
		contentPane.add(select_config);

		password_inputField = new JPasswordField();
		password_inputField.setText(propsForLogID.getProperty("PASSWORD"));
		password_label.setLabelFor(password_inputField);
		password_inputField.setBounds(103, 88, 230, 20);
		contentPane.add(password_inputField);

		JLabel userPIN_label = new JLabel("User PIN");
		userPIN_label.setBounds(10, 148, 83, 14);
		contentPane.add(userPIN_label);

		userPIN_inputField = new JTextField();
		userPIN_inputField.setText("ccc12345");
		userPIN_label.setLabelFor(userPIN_inputField);
		userPIN_inputField.setColumns(10);
		userPIN_inputField.setBounds(103, 148, 230, 20);
		contentPane.add(userPIN_inputField);

		showPassword_checkbox = new JCheckBox("Show Password");
		showPassword_checkbox.setBounds(103, 115, 129, 23);
		contentPane.add(showPassword_checkbox);
		showPassword_checkbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showPassword_checkbox.isSelected()) {
					password_inputField.setEchoChar((char) 0);
				} else {
					password_inputField.setEchoChar('*');
				}
			}
		});

	}
}
