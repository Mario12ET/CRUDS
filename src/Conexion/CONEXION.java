package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CONEXION {
	Connection cx = null;

	public Connection conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			cx = DriverManager.getConnection("jdbc:sqlite:ALUMNO.db");
			System.out.println("Conexion Exitosa");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
         return cx;
	}
	public void desconectar() {
		try {
			cx.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]args) {
		CONEXION cx=new CONEXION();
		cx.conectar();
	}
	
}
