package login.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import login.Model.Booking;
import login.Model.Room;

import java.util.Optional;

public class RoomService {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public RoomService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HMS");
        this.entityManager = entityManagerFactory.createEntityManager();
    }
    public Optional<Room> save(Room room) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(room);
            entityManager.getTransaction().commit();
            return Optional.of(room);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
