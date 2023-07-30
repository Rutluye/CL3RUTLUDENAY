package pe.edu.cibertec.aw1.farmacia.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.cibertec.aw1.farmacia.entities.Marca;
import pe.edu.cibertec.aw1.farmacia.repositories.MarcaRepository;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    private MarcaRepository marcaRepository;

    MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }
    
    @GetMapping
    public String list(Model model) {
        // select m from Marca m;
        // sql: select * from marca;
        List<Marca> marcas = marcaRepository.findAll();
        // List<Persona> personas = personaRepositroy.findByNombre("Juan");
        
        model.addAttribute("marcas", marcas);
        return "marcas/listar";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("marca", new Marca());
        return "marcas/create";
    }

    @PostMapping
    public String store(Marca marca) {
        // INSERT INTO marca (nombre, logo) VALUES (?, ?)
        marcaRepository.save(marca);
        return "redirect:/marcas";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        // SELECT * FROM marca where id = ?
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if(marcaOptional.isEmpty()) {
            return "404";
        }

        model.addAttribute("marca", marcaOptional.get());
        return "marcas/detail";
    }

    @GetMapping("/{id}/edit")
    public String showFormEdit(@PathVariable Integer id, Model model) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if(marcaOptional.isEmpty()) {
            return "404";
        }

        model.addAttribute("marca", marcaOptional.get());
        return "marcas/edit";
    }

    @PostMapping("/{id}")
    public String edit(@PathVariable Integer id, Marca dataFormulario) {
        Optional<Marca> marcaOptional = marcaRepository.findById(id);
        if(marcaOptional.isEmpty()) {
            return "404";
        }

        Marca marca = marcaOptional.get();
        marca.setNombre(dataFormulario.getNombre());
        marca.setDescripcion(dataFormulario.getDescripcion());
        marcaRepository.save(marca); // UPDATE 

        return "redirect:/marcas";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        // Optional<Marca> marcaOptional = marcaRepository.findById(id);
        // if(marcaOptional.isEmpty()) {
        //     return "404";
        // }
        // Marca marca = marcaOptional.get();
        // marcaRepository.delete(marca);
        marcaRepository.deleteById(id);
        return "redirect:/marcas";
    }

}
