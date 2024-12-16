package Hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de ID si es aplicable
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 60, nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY) // LAZY para cargar solo cuando se necesite
    @JoinColumn(name = "codauthor", referencedColumnName = "cod") // Mapea con la clave `cod` en la tabla `authors`
    private Author author;

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
