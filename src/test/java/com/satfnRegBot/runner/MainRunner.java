package com.satfnRegBot.runner;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

public class MainRunner extends JFrame {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	protected static String actualUserId;
	protected static String excelPath;
	public static String actualPassword;
	protected static String configPath;

	public static TestNG testNG;
	public static TestListenerAdapter tla;
	private JTextField userID;
	private JLabel message;
	private JTextField password;
	private JFileChooser fileChooser;
	private JButton selectFileBtn;
	private JLabel selectedFilePath;
	private JLabel lblNewLabel_2;
	private JButton select_config;

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

	/**
	 * Create the frame.
	 */
	public MainRunner() {
		setTitle("Automation InvBot");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("User ID");
		lblNewLabel.setBounds(10, 54, 83, 19);
		contentPane.add(lblNewLabel);

		userID = new JTextField();
		userID.setBounds(103, 53, 230, 20);
		contentPane.add(userID);
		userID.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setBounds(10, 91, 83, 14);
		contentPane.add(lblNewLabel_1);

		password = new JTextField();
		password.setBounds(103, 88, 230, 20);
		contentPane.add(password);
		password.setColumns(10);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualUserId = userID.getText();
				actualPassword = password.getText();
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
		btnNewButton.setBounds(157, 207, 89, 23);
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
		selectFileBtn.setBounds(10, 128, 108, 23);
		contentPane.add(selectFileBtn);

		selectedFilePath = new JLabel("");
		selectedFilePath.setBounds(10, 182, 414, 14);
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
		select_config.setBounds(210, 128, 108, 23);
		contentPane.add(select_config);

	}
}
