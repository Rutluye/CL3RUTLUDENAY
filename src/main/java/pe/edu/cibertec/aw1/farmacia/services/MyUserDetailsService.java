package pe.edu.cibertec.aw1.farmacia.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pe.edu.cibertec.aw1.farmacia.entities.User;
import pe.edu.cibertec.aw1.farmacia.repositories.UserRepository;

public class MyUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }

}
