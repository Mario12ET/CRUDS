package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import Conexion.CONEXION;
import Modelo.USUARIO;

public class daoUSUARIO {
	CONEXION cx = null;

	public daoUSUARIO() {
		cx = new CONEXION();
	}

	public boolean insertaUsuario(USUARIO user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO USUARIO VALUES(null,?,?,?)");
			ps.setString(1, user.getUser());
			ps.setString(2,convertirSHA256( user.getNombre()));
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
    
	public ArrayList<USUARIO> fetchUsuarios() {
		ArrayList<USUARIO> lista = new ArrayList<USUARIO>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM USUARIO");
			rs = ps.executeQuery();
			while (rs.next()) {
				USUARIO u = new USUARIO();
				u.setId(rs.getInt("Id"));
				u.setUser(rs.getString("User"));
				u.setPassword(rs.getString("Nombre"));
				u.setNombre(rs.getString("Password"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public boolean eliminarUsuario(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM USUARIO WHERE id=?");
			ps.setInt(1,Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
    
	public boolean editarUsuario(USUARIO user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE USUARIO SET user=?,password=?,nombre=? WHERE id=?");
			ps.setString(1, user.getUser());
			ps.setString(2, convertirSHA256( user.getNombre()));
			ps.setString(3, user.getPassword());
			ps.setInt(4,user.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public String convertirSHA256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
}

