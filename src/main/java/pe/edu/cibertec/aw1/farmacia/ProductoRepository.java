package pe.edu.cibertec.aw1.farmacia;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

// Repository, JpaRepository, PageAndSortRepository, CrudRepository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    List<Producto> findAll();
}
