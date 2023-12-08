package login.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import login.Model.Booking;


import java.util.Optional;

public class BookingService {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public BookingService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HMS");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public Optional<Booking> save (Booking booking) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(booking);
            entityManager.getTransaction().commit();
            return Optional.of(booking);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
