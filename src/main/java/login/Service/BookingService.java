package login.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import login.Model.Booking;
import login.Model.Room;
import login.Model.RoomStatus;




import java.util.Date;
import java.util.List;
import java.util.Optional;

import static login.Model.RoomStatus.DISPONIBLE;

public class BookingService {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public BookingService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("HMS");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public Optional<Booking> save(Booking booking) {
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

    public Room checkReservation(int nombreLit, Date checkin, Date checkout) {
        List<Room> availableRooms = entityManager.createQuery(
                        "SELECT r FROM Room r " +
                                "WHERE r.nombre_lits = :nombreLit " +
                                "AND r.status = :status",
                        Room.class)
                .setParameter("nombreLit", nombreLit)
                .setParameter("status", RoomStatus.DISPONIBLE)
                .getResultList();

        System.out.println("Available rooms: " + availableRooms);

        if (availableRooms == null || availableRooms.isEmpty()) {
            return null;
        }

        for (Room room : availableRooms) {

            Long overlappingCount = entityManager.createQuery(
                            "SELECT COUNT(b) FROM Booking b " +
                                    "WHERE b.room = :room " +
                                    "AND :checkout > b.checkInDate AND :checkin < b.checkOutDate",
                            Long.class)
                    .setParameter("room", room)
                    .setParameter("checkin", checkin)
                    .setParameter("checkout", checkout)
                    .getSingleResult();
            if (overlappingCount == 0) {
                return room;
            }
        }

        return null;
    }






        }








