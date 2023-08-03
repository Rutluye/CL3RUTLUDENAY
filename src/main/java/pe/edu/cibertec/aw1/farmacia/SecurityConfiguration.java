package pe.edu.cibertec.aw1.farmacia;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import pe.edu.cibertec.aw1.farmacia.repositories.UserRepository;
import pe.edu.cibertec.aw1.farmacia.services.MyUserDetailsService;

@Configuration
public class SecurityConfiguration {
    // UserDetailsService => en base al nombre de usuario obtener toda la informacion del usuario
    // spring tiene por defecto algunas implementaciones de user detail service
    // => en memoria

    // PasswordEncoder => codifica el password se encripte

    // Bean <= 
    // @Bean
    // public UserDetailsService getUserDetailsService() {
    //     User arthur = new User(
    //         "arthur", 
    //         getPasswordEncoder().encode("123456"), 
    //         Arrays.asList(
    //             new SimpleGrantedAuthority("ROLE_USER"), 
    //             new SimpleGrantedAuthority("ELIMINAR_PRODUCTOS")));
        
    //     User diana = new User(
    //         "diana", 
    //         getPasswordEncoder().encode("123456"), 
    //         Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

    //     return new InMemoryUserDetailsManager(arthur, diana);
    // }

    @Bean
    public UserDetailsService getUserDetailsService(UserRepository userRepository) {
        // return new JdbcUserDetailsManager(null)
        return new MyUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // return new BCryptPasswordEncoder();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // authority: permisos y roles

        // /about, / => public
        // /marcas => ROLE_USER

        // tenemos opciones para: 
        // authenticated(), hasAuthority("ROLE_USER"), hasRole("ADMIN"), permitAll()

        return httpSecurity
            .authorizeHttpRequests(authorizeHttpRequests -> 
                authorizeHttpRequests
                    .requestMatchers("/nosotros", "/").permitAll()
                    .requestMatchers("/**").authenticated()
                    // .requestMatchers("/marcas").hasAuthority("ROLE_USER")
                )
            .formLogin(formLogin -> 
                formLogin.loginPage("/login")
                    // .usernameParameter("email")
                    // .passwordParameter("pwd")
                    .permitAll())
            .build();
    }
}
