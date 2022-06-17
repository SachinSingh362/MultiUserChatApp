package com.ss.chatapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ss.chatapp.network.Client;
import com.ss.chatapp.utils.UserInfo;

//import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClientChatScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private Client client;
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		
					ClientChatScreen frame = new ClientChatScreen();
					
	}
	private void sendIt(){
		String message=textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME+"-"+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea=new JTextArea();
		 client=new Client(textArea);

		setTitle("Chit-Chat"); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 995, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 22, 926, 433);
		contentPane.add(scrollPane);
		
		
		
		
		textArea.setFont(new Font("Microsoft YaHei UI Light", Font.PLAIN, 16));
		textArea.setBounds(46, 32, 892, 423);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
		textField.setBounds(41, 480, 765, 47);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendit = new JButton("Send Message");
		sendit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
		
			}
		});
		sendit.setBounds(816, 480, 136, 43);
		contentPane.add(sendit);
		setVisible(true);
	}
}
