package com.satfnRegBot.frames;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class HomePage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static String actualUserId;
	protected static String excelPath;
	public static String actualPassword;

	public static TestNG testNG;
	public static TestListenerAdapter tla;
	private JTextField userID;
	private JLabel message;
	private JTextField password;
	private JFileChooser fileChooser;
	private JButton selectFileBtn;
	private JLabel selectedFilePath;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
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
				System.out.println(actualUserId);
				actualPassword = password.getText();
				System.out.println(actualPassword);
				if (actualUserId != null && actualUserId != "") {
					if (actualPassword != null && actualPassword != "") {

						tla = new TestListenerAdapter();
						testNG = new TestNG();
						testNG.addListener(tla);
						// path of the XML file
						String path = System.getProperty("user.dir") + File.separator + "testng.xml";

						// by using the suiteXML file
						List<String> suiteXMLs = new ArrayList<String>();
						suiteXMLs.add(path);
						testNG.setTestSuites(suiteXMLs);

//						testNG.setTestClasses(new Class[] {});
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

	}

}
