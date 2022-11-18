package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Conexion.CONEXION;
import Modelo.Producto;

public class daoProducto {
	CONEXION cx = null;

	public daoProducto() {
		cx = new CONEXION();
	}

	public boolean insertarProducto(Producto Producto) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("INSERT INTO Producto VALUES(null,?,?,?,?)");
			ps.setString(1, Producto.getDescripcion());
			ps.setString(2, Producto.getPrecio());
			ps.setString(3, Producto.getCantidad());
			ps.setString(4, Producto.getProvedor());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Producto> fetchProducto() {
		ArrayList<Producto> lista = new ArrayList<Producto>();
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("SELECT * FROM Producto");
			rs = ps.executeQuery();
			while (rs.next()) {
				Producto u = new Producto();
				u.setIdProducto(rs.getInt("IdProducto"));
				u.setDescripcion(rs.getString("Descripcion"));
				u.setPrecio(rs.getString("Precio"));
				u.setCantidad(rs.getString("Cantidad"));
				u.setProvedor(rs.getString("Provedor"));
				lista.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public boolean eliminarProducto(int Id) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("DELETE FROM Producto WHERE IdProducto=?");
			ps.setInt(1, Id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editarProducto(Producto producto) {
		PreparedStatement ps = null;
		try {
			ps = cx.conectar().prepareStatement("UPDATE Producto SET Descripcion=?,Precio=?,Cantidad=?,Provedor=? WHERE IdProducto=?");
			ps.setString(1, producto.getDescripcion());
			ps.setString(2, producto.getPrecio());
			ps.setString(3, producto.getCantidad());
			ps.setString(4, producto.getProvedor());
			ps.setInt(5, producto.getIdProducto());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
