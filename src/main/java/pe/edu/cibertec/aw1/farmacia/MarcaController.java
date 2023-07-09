package pe.edu.cibertec.aw1.farmacia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    List<Marca> marcas = new ArrayList<>();

    public MarcaController() {
        marcas.add(new Marca(1, "Baby Jhonson"));
        marcas.add(new Marca(2, "Neko"));
        marcas.add(new Marca(3, "Oral B"));
    }

    @GetMapping
    public String list(Model model) {
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
        marca.id = marcas.size() + 10; // genero el id
        marcas.add(marca);
        return "redirect:/marcas";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Marca> marcaOptional = marcas.stream().filter(marca -> marca.getId().equals(id)).findFirst();
        if(marcaOptional.isEmpty()) {
            return "404";
        }

        model.addAttribute("marca", marcaOptional.get());
        return "marcas/detail";
    }
}
