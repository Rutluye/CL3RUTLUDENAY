package pe.edu.cibertec.aw1.farmacia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pe.edu.cibertec.aw1.farmacia.entities.Producto;

// Repository, JpaRepository, PageAndSortRepository, CrudRepository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    List<Producto> findAll();
}
