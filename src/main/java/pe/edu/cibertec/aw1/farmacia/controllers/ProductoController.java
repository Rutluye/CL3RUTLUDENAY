package pe.edu.cibertec.aw1.farmacia.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.data.DataSourceCollection;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.cibertec.aw1.farmacia.dtos.ProductoDto;
import pe.edu.cibertec.aw1.farmacia.entities.Producto;
import pe.edu.cibertec.aw1.farmacia.repositories.ProductoRepository;

@Controller
@RequestMapping("productos")
public class ProductoController {

    ProductoRepository productoRepository;
    DataSource dataSource;

    public ProductoController(ProductoRepository productoRepository, DataSource dataSource) {
        this.productoRepository = productoRepository;
        this.dataSource = dataSource;
    }

    @GetMapping
    public String list(Model modelo) {
        List<Producto> productos = productoRepository.findAll();
        modelo.addAttribute("listaProductos", productos);

        return "productos/list";
    }

    @GetMapping("create")
    public String showCreateForm(Model model) {
        ProductoDto productoDto = new ProductoDto();
        model.addAttribute("productoForm", productoDto);
        return "productos/create";
    }

    @PostMapping
    public String create(ProductoDto productoDto) {
        // Producto producto = new Producto();
        // producto.nombre = productoDto.getNombre();
        // producto.stock = productoDto.getStock();
        // producto.precio = productoDto.getPrecio();

        Producto producto = new Producto(productoDto.getNombre(), productoDto.getStock(), productoDto.getPrecio());
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isEmpty()) {
            return "404";
        }

        Producto producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "productos/detail";
    }

    @GetMapping("{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isEmpty()) {
            return "404";
        }

        Producto producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable Long id, Producto productoDataForm) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if(productoOptional.isEmpty()) {
            return "404";
        }

        Producto producto = productoOptional.get();
        producto.setNombre(productoDataForm.getNombre());
        producto.setPrecio(productoDataForm.getPrecio());
        producto.setStock(productoDataForm.getStock());
        productoRepository.save(producto);

        return "redirect:/productos";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        productoRepository.deleteById(id);
        return "redirect:/productos";
    }

    // Servlet, java web
    // request => servidor (aplicacion) hace un procesamiento => response
    // modelo de computacion cliente servidor

    @GetMapping("report")
    public void downloadReport(HttpServletResponse response) throws SQLException {
        try {
            InputStream inputStream = new ClassPathResource("reports/listado_productos.jasper").getInputStream();
            JasperReport report = (JasperReport) JRLoader.loadObject(inputStream);

            // JRDataSource dataSource = new JREmptyDataSource();
            Connection connection = dataSource.getConnection();


            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombreEmpresa", "InkaFarma");
            parameters.put("descargadoPor", "Arthur");

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
            connection.close();

            // OutputStream outputStream = new FileOutputStream("hola.pdf");
            response.setContentType("application/pdf");
            OutputStream outputStream =  response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        } catch (IOException | JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
