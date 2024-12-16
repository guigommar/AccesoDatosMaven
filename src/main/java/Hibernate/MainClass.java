package Hibernate;

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        if (session != null) {
            System.out.println("Session opened");
        } else {
            System.out.println("Error opening session");
        }

        try {
            session.beginTransaction();
            showBooks(session);
            showAuthors(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }

        System.out.println("END");
    }

    public static void showBooks(Session session) {
        try {
            Query<Book> query = session.createQuery("FROM Book", Book.class);
            List<Book> results = query.getResultList();

            System.out.println("Showing books data: ");
            for (Book book : results) {
                System.out.println(book.getId() + ": " + book.getTitle() +
                        ", by " + (book.getAuthor() != null ? book.getAuthor().getName() : "Anonymous"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAuthors(Session session) {
        try {
            Query<Author> query = session.createQuery("FROM Author", Author.class);
            List<Author> results = query.getResultList();

            System.out.println("Showing authors data: ");
            for (Author author : results) {
                System.out.println("Author " + author.getCod() + " with name " + author.getName() + " has written:");
                author.getBooks().forEach(book -> System.out.println("\t* " + book.getTitle()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
