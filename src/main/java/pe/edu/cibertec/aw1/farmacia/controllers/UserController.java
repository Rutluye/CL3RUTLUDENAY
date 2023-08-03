package pe.edu.cibertec.aw1.farmacia.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pe.edu.cibertec.aw1.farmacia.dtos.RegisterUserDto;
import pe.edu.cibertec.aw1.farmacia.entities.User;
import pe.edu.cibertec.aw1.farmacia.repositories.UserRepository;

@Controller
public class UserController {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("login")
    public String showFormLogin() {
        return "user/login";
    }

    @GetMapping("registrar")
    public String showFormRegister(Model model) {
        model.addAttribute("registerUser", new RegisterUserDto());
        return "user/register";
    }

    @PostMapping("registrar")
    public String register(RegisterUserDto registerUser) {
        User user = new User();
        user.setUsername(registerUser.getUsername());
        user.setEmail(registerUser.getEmail());
        String passwordEncoded = passwordEncoder.encode(registerUser.getPassword());
        user.setPassword(passwordEncoded);

        userRepository.save(user);

        return "redirect:/";
    }
}
