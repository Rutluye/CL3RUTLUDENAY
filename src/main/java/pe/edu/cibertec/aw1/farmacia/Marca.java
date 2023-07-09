package pe.edu.cibertec.aw1.farmacia;

public class Marca {
    Integer id;
	String nombre;
	String logo;

	Marca(Integer id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	Marca() {}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	// var marca = new Marca("Pantene");
	// marca.nombre
	// marca.getNombre()


}
