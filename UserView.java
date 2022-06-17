package com.ss.chatapp.views;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	public UserView(){
		setVisible(true);	
		setSize(700,500);
//		setLocation(440,150);
		setResizable(false);
		setTitle("Login");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel welcome=new JLabel("Login");
		welcome.setFont(new Font("Arial",Font.BOLD,50));
		Container container=this.getContentPane();
		container.setLayout(null);
		welcome.setBounds(270, 100, 400, 70);
		container.add(welcome);
		

	}
	public static void main(String[] args) {
		UserView userview=new UserView();
		
			
		}
	}


