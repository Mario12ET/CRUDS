package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import Conexion.CONEXION;
import Modelo.Comentario;

public class daoComentario {
	CONEXION cx = null;

	public daoComentario() {
		cx = new CONEXION();
	}

	public boolean insertaComentario(Comentario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO comentario VALUES(null,?,?,)");
			ps.setString(1,(user.getTexto()));
			ps.setString(2, user.getUsuario());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Comentario> fetchComentarios() {
		ArrayList<Comentario> lista = new ArrayList<Comentario>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM comentario");
			rs = ps.executeQuery();
			while (rs.next()) {
				Comentario u = new Comentario();
				u.setId(rs.getInt("Id"));
				u.setTexto(null);
				u.setUsuario(rs.getString("Nombre"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean eliminarComentario(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM comentario WHERE id=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editarComentario(Comentario user) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE comentario SET texto=?,usuarios=? WHERE id=?");
			ps.setString(1, user.getUsuario());
			ps.setString(2,user.getTexto());
			ps.setInt(3, user.getId());

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	
	}

}