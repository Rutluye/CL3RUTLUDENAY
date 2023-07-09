package pe.edu.cibertec.aw1.farmacia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FarmaciaEnSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmaciaEnSpringApplication.class, args);


		// MarcaRepository marcaRepository = new MarcaRepositoryImplementation();

		// el patron de diseño: inyeccion de dependencias, dependency injection
		// consiste en llenar una dependencia a tu componente
		// el framework, se encarga de eso, se encarga de inyectar.
		// es decir, el componente se olvida de como es que se creo su dependencia

		// 1. inyeccion por constructor
		// MarcaController marcaController = new MarcaController(marcaRepository);
		// 2. inyeccion por propiedad (o setter) <=== evite
		// MarcaController marcaController = new MarcaController();
		// marcaController.setMarcaRepository(marcaRepository);
		// 3. inyeccion por atributo  (use @Autowired)
		// (usando java reflection) <=== evitela completo
		// MarcaController marcaController = new MarcaController();
		// marcaController.marcaRepository = marcaRepository;


	}

}
