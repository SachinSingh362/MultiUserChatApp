package com.ss.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.ss.chatapp.dao.UserDAO;
import com.ss.chatapp.dto.UserDTO;
import com.ss.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {
	private JTextField usernametxt;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
				
	}
	UserDAO userDAO=new UserDAO();
	public void doLogin() {
		String username=usernametxt.getText();
		char[] password=passwordField.getPassword();
		System.out.println("Username "+username+"Password "+password.toString());
	
		UserDTO userDTO=new UserDTO(username,password);
		try {
			String message="";
			if(userDAO.isLogin(userDTO)) {
				message="Welcome "+username;
				UserInfo.USER_NAME=username;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard=new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
			      message="Invalid username or password";
			      JOptionPane.showMessageDialog(this, message);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void register() {
		String username=usernametxt.getText();
		char[] password=passwordField.getPassword();
		System.out.println("Username "+username+"Password "+password.toString());
//		UserDAO userDAO=new UserDAO();
		UserDTO userDTO=new UserDTO(username,password);
		try {
		int result=userDAO.add(userDTO);
		if(result>0) {
			//System.out.println("Record Added...");
			JOptionPane.showMessageDialog(this, "Registered Sucessfully");
		}
		else {
//			System.out.println("Record Not Added...");
			JOptionPane.showMessageDialog(this, "Register Fail");
			
		}
		}
		catch(ClassNotFoundException| SQLException ex) {
			System.out.println("DB Issues...");
		}
		catch(Exception ex ) {
			System.out.println("Some Generic Exception Raised... ");
		}
	}


	public UserScreen() {
		getContentPane().setFont(new Font("Microsoft Himalaya", Font.BOLD, 32));
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(233, 35, 131, 47);
		getContentPane().add(lblNewLabel);
		
		JLabel usernamelbl = new JLabel("Username");
		usernamelbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		usernamelbl.setBounds(55, 130, 168, 60);
		getContentPane().add(usernamelbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 30));
		pwdlbl.setBounds(55, 204, 186, 47);
		getContentPane().add(pwdlbl);
		
		usernametxt = new JTextField();
		usernametxt.setBounds(233, 148, 152, 39);
		getContentPane().add(usernametxt);
		usernametxt.setColumns(10);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.BOLD, 17));
		loginbt.setBounds(177, 312, 97, 21);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.BOLD, 17));
		registerbt.setBounds(297, 312, 110, 21);
		getContentPane().add(registerbt);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(234, 204, 151, 41);
		getContentPane().add(passwordField);
		setBackground(Color.WHITE);
		setBounds(100, 100, 577, 498);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
