package pe.edu.cibertec.aw1.farmacia;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;

    String email;

    LocalDateTime registrationDate;
}
