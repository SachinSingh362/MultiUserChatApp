package com.ss.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ss.chatapp.dto.UserDTO;
import com.ss.chatapp.utils.Encryption;

public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	final String SQL="select username from users where username=? and password=?";
			try {
				con=CommonDAO.createConnection();
				pstmt=con.prepareStatement(SQL);
				pstmt.setString(1, userDTO.getUsername());
				String encryptedPwd=Encryption.passwordEncrypt(new String (userDTO.getPassword()));
				pstmt.setString(2,encryptedPwd );
				rs=pstmt.executeQuery();
				return rs.next();
			}
	finally {
		if(rs!=null) {
			rs.close();
		
		}
		if(pstmt!=null) {
			pstmt.close();
		}
		if(con!=null) {
			con.close();
		}
	}
	
}
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, Exception {
		System.out.println("Rec "+userDTO.getUsername()+userDTO.getPassword());
		Connection  connection=null;
		Statement stmt=null; //used to write query
		try {
		connection=CommonDAO.createConnection(); //step 1
		stmt=connection.createStatement(); 
		int record=stmt.executeUpdate("insert into users(username,password)values('"+userDTO.getUsername()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");//insert,update,delete query
		return record;
		}
		
		finally{
			if (stmt!=null) {
				stmt.close();
				}	
		if(connection!=null) {
		connection.close();
		}
		}

		
	
		
	}

}
