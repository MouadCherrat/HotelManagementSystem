package login.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import login.Model.Booking;



import java.time.temporal.ChronoUnit;

import java.util.Date;
import java.util.Optional;

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

    public double calculateAmount(Booking booking) {
        double pricePerNight = 100.0;
        long numberOfNights = ChronoUnit.DAYS.between(booking.getCheckInDate().toInstant(), booking.getCheckOutDate().toInstant());
        double  totalAmount = pricePerNight *numberOfNights * booking.getNombre_beds();
        booking.setAmount(totalAmount);
        return totalAmount;
    }

    public boolean checkReservation(int room_num, Date checkin, Date checkout){

        Long overlappingCount = entityManager.createQuery(
                        "SELECT COUNT(b) FROM Booking b " +
                                "WHERE b.room_number = :room_num " +
                                "AND :checkout > b.checkInDate AND :checkin < b.checkOutDate",
                        Long.class)
                .setParameter("room_num", room_num)
                .setParameter("checkin", checkin)
                .setParameter("checkout", checkout)
                .getSingleResult();


        return overlappingCount == 0;

        }




    }






