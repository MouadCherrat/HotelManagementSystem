package login.Model;

import jakarta.persistence.*;

import java.util.Date;

    @Entity
    @Table(name = "reservations")
    public class Booking {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
        @Column(name = "nombre_lits")
        private int nombre_beds;
        @Column(name = "check_in_date")
        private Date checkInDate;
        @Column(name = "check_out_date")
        private Date checkOutDate;
        private double amount;
        private int room_number;

        public int getRoom_number() {
            return room_number;
        }

        public void setRoom_number(int room_number) {
            this.room_number = room_number;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getNombre_beds() {
            return nombre_beds;
        }

        public void setNombre_beds(int nombre_beds) {
            this.nombre_beds = nombre_beds;
        }

        public Date getCheckInDate() {
            return checkInDate;
        }

        public void setCheckInDate(Date checkInDate) {
            this.checkInDate = checkInDate;
        }

        public Date getCheckOutDate() {
            return checkOutDate;
        }

        public void setCheckOutDate(Date checkOutDate) {
            this.checkOutDate = checkOutDate;
        }

    }


