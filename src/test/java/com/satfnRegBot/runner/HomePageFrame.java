package com.satfnRegBot.runner;

import java.awt.EventQueue;

//import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

public class HomePageFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField loginID_input;
	private JTextField password_input;
	private JTextField pin_input;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePageFrame frame = new HomePageFrame();
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
	public HomePageFrame() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 102));
		panel.setBounds(0, 0, 584, 42);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("CERSAI Satisfaction Registration");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lblNewLabel_1.setBounds(196, 0, 365, 42);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("InvBot");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 21));
		lblNewLabel.setBounds(110, 11, 76, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\Veritas\\SatfnReg.InvBot\\icon\\InvBotFavIcon.png"));
		lblNewLabel_2.setBounds(-11, -22, 149, 155);
		panel.add(lblNewLabel_2);
		
		JPanel right_panel = new JPanel();
		right_panel.setBackground(new Color(0, 102, 102));
		right_panel.setBounds(0, 42, 292, 419);
		contentPane.add(right_panel);
		right_panel.setLayout(null);
		
		JLabel companyNameLogo = new JLabel("");
		companyNameLogo.setIcon(new ImageIcon("D:\\Veritas\\SatfnReg.InvBot\\icon\\InvBotIcon.png"));
		companyNameLogo.setBounds(51, 82, 166, 134);
		right_panel.add(companyNameLogo);
		
		JLabel lblNewLabel_4 = new JLabel("Copyrights © 2024  All rights reserved");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_4.setBounds(10, 388, 272, 20);
		right_panel.add(lblNewLabel_4);
		
		JPanel left_panel = new JPanel();
		left_panel.setBackground(new Color(255, 255, 255));
		left_panel.setBounds(292, 42, 292, 419);
		contentPane.add(left_panel);
		left_panel.setLayout(null);
		
		JLabel login_label = new JLabel("Login ID");
		login_label.setForeground(new Color(0, 102, 102));
		login_label.setFont(new Font("Segoe UI", Font.BOLD, 19));
		login_label.setBounds(10, 11, 105, 26);
		left_panel.add(login_label);
		
		loginID_input = new JTextField();
		login_label.setLabelFor(loginID_input);
		loginID_input.setBounds(10, 42, 233, 26);
		left_panel.add(loginID_input);
		loginID_input.setColumns(10);
		
		JButton select_excelBtn = new JButton("Select Excel");
		select_excelBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		select_excelBtn.setBackground(new Color(0, 102, 102));
		select_excelBtn.setBounds(10, 196, 133, 31);
		left_panel.add(select_excelBtn);
		
		JButton select_configBtn = new JButton("Select Config");
		select_configBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
		select_configBtn.setBackground(new Color(0, 102, 102));
		select_configBtn.setBounds(10, 255, 133, 31);
		left_panel.add(select_configBtn);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(10, 230, 282, 23);
		left_panel.add(lblNewLabel_3);
		
		JLabel password_label = new JLabel("Password");
		password_label.setForeground(new Color(0, 102, 102));
		password_label.setFont(new Font("Segoe UI", Font.BOLD, 19));
		password_label.setBounds(10, 71, 105, 26);
		left_panel.add(password_label);
		
		password_input = new JTextField();
		password_label.setLabelFor(password_input);
		password_input.setColumns(10);
		password_input.setBounds(10, 103, 233, 26);
		left_panel.add(password_input);
		
		pin_input = new JTextField();
		pin_input.setColumns(10);
		pin_input.setBounds(10, 159, 233, 26);
		left_panel.add(pin_input);
		
		JLabel pin_label = new JLabel("PIN");
		pin_label.setLabelFor(pin_input);
		pin_label.setForeground(new Color(0, 102, 102));
		pin_label.setFont(new Font("Segoe UI", Font.BOLD, 19));
		pin_label.setBounds(10, 132, 105, 26);
		left_panel.add(pin_label);
		
		JButton btnNewButton_2 = new JButton("Start");
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_2.setBackground(new Color(0, 102, 102));
		btnNewButton_2.setBounds(91, 361, 89, 23);
		left_panel.add(btnNewButton_2);
	}
}
