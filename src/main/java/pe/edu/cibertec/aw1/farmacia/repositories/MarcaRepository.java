package pe.edu.cibertec.aw1.farmacia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pe.edu.cibertec.aw1.farmacia.entities.Marca;

public interface MarcaRepository extends CrudRepository<Marca, Integer> {
	List<Marca> findAll();

	// select m from Marca m where nombre = ?
	List<Marca> findByNombre(String nombre);

}



// spring data
// Repository es un repository sin nada.
// CrudRepository te permite hacer un Crud (save, delete, findAll)
// PageAndSortRepository ordenar (80%)

// spring data jpa
// JpaRepository ()