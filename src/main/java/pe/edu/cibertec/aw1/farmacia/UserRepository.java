package pe.edu.cibertec.aw1.farmacia;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    // select * from user where username = ?
    Optional<User> findByUsername(String username);

     // select * from user where username = ?
    Optional<User> getByUsername(String username);
    Optional<User> queryByUsername(String username);


    // select * from user where username = ? and registrationDate = ?
    Optional<User> findByUsernameAndRegistrationDate(String username, LocalDateTime registrationDate);

    // select * from user where username like %?%
    Optional<User> findByUsernameContains(String username);
    

    // metodos personalizados (custom methods) basados en una nomenclatura
    @Query("select u from User u where u.username = :username")
    Optional<User> buscarPorNombre(String username);

    @Query(value = "select * from user where username = :username", nativeQuery = true)
    Optional<User> buscarPorEmail(String username);

    // @Query("""
    //     select count(u) as conteo, u.registrationDate as fecha 
    //     from User u 
    //     group by u.registrationDate
    // """)
    // List<ContarUsuariosPorDia> contarUsuariosRegistradosPorDia();
}


// class ContarUsuariosPorDia {
//     Long conteo;
//     LocalDate fecha;
// }