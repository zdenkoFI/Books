package org.bedu.books.services;

import org.bedu.books.model.Libro;
import org.bedu.books.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    /**
     * Agrega un nuevo libro o actualiza uno existente.
     * @param libro el libro a agregar o actualizar
     * @return el libro guardado
     */
    public Libro agregarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    /**
     * Busca un libro por su ID.
     * @param id el ID del libro
     * @return un Optional que contiene el libro si se encuentra, o vacío si no
     */
    public Optional<Libro> buscarLibro(Long id) {
        return libroRepository.findById(id);
    }

    /**
     * Elimina un libro por su ID.
     * @param id el ID del libro a eliminar
     */
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }

    /**
     * Obtiene todos los libros.
     * @return un iterable de todos los libros
     */
    public Iterable<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
}
