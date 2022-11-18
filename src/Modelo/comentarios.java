package Modelo;

public class comentarios {
	int id;
	String texto, usuario;
	
	public comentarios(){
		
	}
	
	public comentarios(int id, String texto, String usuario) {
		super();
		this.id = id;
		this.texto = texto;
		this.usuario = usuario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}