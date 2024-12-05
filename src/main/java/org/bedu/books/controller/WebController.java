package org.bedu.books.controller;

import org.bedu.books.model.Libro;
import org.bedu.books.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/") // Añadido para establecer la base común de las rutas
public class WebController {

    private final LibroService libroService;

    @Autowired
    public WebController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public String mostrarLibros(Model model) {
        model.addAttribute("libros", libroService.obtenerTodos());
        return "index";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("libro", new Libro());
        return "agregarLibro";
    }

    @PostMapping("/agregar")
    public String agregarLibro(@ModelAttribute Libro libro, RedirectAttributes redirectAttributes) {
        libroService.agregarLibro(libro);
        redirectAttributes.addFlashAttribute("mensaje", "Libro agregado exitosamente.");
        return "redirect:/";
    }

    @GetMapping("/buscar/{id}")
    public String buscarLibro(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Libro> libroOpt = libroService.buscarLibro(id);
        if (libroOpt.isPresent()) {
            model.addAttribute("libro", libroOpt.get());
            return "detalleLibro";
        } else {
            redirectAttributes.addFlashAttribute("error", "Libro no encontrado.");
            return "redirect:/";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        if (libroService.eliminarLibro(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "Libro eliminado exitosamente.");
        } else {
            redirectAttributes.addFlashAttribute("error", "No se pudo eliminar el libro.");
        }
        return "redirect:/";
    }
}
