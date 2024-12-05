package org.bedu.books.repository;

import org.bedu.books.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Aquí puedes agregar consultas personalizadas si es necesario
    // Ejemplo: List<Libro> findByAutor(String autor);
}

