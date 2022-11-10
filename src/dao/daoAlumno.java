package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.CONEXION;
import Modelo.ALUMNO;

public class daoAlumno {
	CONEXION cx = null;

	public daoAlumno() {
		cx = new CONEXION();
	}

	public boolean insertarAlumno(ALUMNO alumno) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO ALUMNO VALUES(null,?,?,?,?)");
			ps.setString(1, alumno.getNombre());
			ps.setString(2,alumno.getGrupo());
			ps.setString(3, alumno.getMunicipio());
			ps.setString(4, alumno.getSemestre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
    
	public ArrayList<ALUMNO> fetchALUMNO() {
		ArrayList<ALUMNO> lista = new ArrayList<ALUMNO>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM ALUMNO");
			rs = ps.executeQuery();
			while (rs.next()) {
				ALUMNO u = new ALUMNO();
				u.setId(rs.getInt("Id"));
				u.setNombre(rs.getString("NOMBRE"));
				u.setGrupo(rs.getString("GRUPO"));
				u.setMunicipio(rs.getString("MUNICIPIO"));
				u.setSemestre(rs.getString("SEMESTRE"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	public boolean eliminarAlumno(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM ALUMNO WHERE id=?");
			ps.setInt(1,Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
    
	public boolean editarAlumno(ALUMNO alumno) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE ALUMNO SET nombre=?,grupo=?,municipio=?,semestre=? WHERE id=?");
			ps.setInt(1,alumno.getId());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, ( alumno.getGrupo()));
			ps.setString(4, alumno.getMunicipio());
			ps.setString(5,alumno.getSemestre());			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}


}