package login.Contoller;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Model.Booking;
import login.Model.User;
import login.Service.BookingService;
import util.MailSender;

@WebServlet(name = "BookingServletServlet", value = "/BookingServlet-servlet")
public class BookingServlet extends HttpServlet {
    private BookingService bookingService;
    private Booking booking;

    public void init() {
        bookingService = new BookingService();
        booking = new Booking();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = new Booking();
        Integer nombreLits =Integer.parseInt(req.getParameter("nombre_lits")) ;
       // Integer roomnumber=Integer.parseInt(req.getParameter("room_number")) ;
        Integer roomNumber = Integer.parseInt(req.getParameter("room_number"));

        String checkIn = req.getParameter("checkInDate");
        String checkOut = req.getParameter("checkOutDate");
        Date checkInDate = new Date();
        Date checkOutDate = new Date();

        HttpSession session = req.getSession() ;
        User user = (User)session.getAttribute("user");
        session.setAttribute("booking", booking);
        List<Integer> chambresDisponibles = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        session.setAttribute("chambresDisponibles", chambresDisponibles);


        try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                checkInDate = dateFormat.parse(checkIn);
                checkOutDate = dateFormat.parse(checkOut);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();
        if (checkInDate != null && checkInDate.after(currentDate) && checkOutDate != null && checkOutDate.after(currentDate)) {

            booking.setNombre_beds(nombreLits);
            System.out.println("test1");
            booking.setRoom_number(roomNumber);
            System.out.println("test2");
            booking.setCheckInDate(checkInDate);
            booking.setCheckOutDate(checkOutDate);
            booking.setUser(user);

            double totalAmount =  bookingService.calculateAmount(booking);
            session.setAttribute("totalAmount", totalAmount);
            bookingService.save(booking);

            String emailBody = buildEmailBody(booking);

            System.out.println("mail "+ user.getEmail());

            MailSender.sendEmail(user.getEmail(), emailBody);
            System.out.println("body"+emailBody);

            resp.sendRedirect(req.getContextPath() + "/Summary.jsp");

        } else {
            req.setAttribute("erreurMessage", "La date de réservation est invalide.");
            req.getRequestDispatcher("Index.jsp").forward(req, resp);
        }

    }

    private String buildEmailBody(Booking booking) {
        StringBuilder emailBody = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String checkInDateFormatted = dateFormat.format(booking.getCheckInDate());
        String checkOutDateFormatted = dateFormat.format(booking.getCheckOutDate());


        emailBody.append("Booking Details:\n");
        emailBody.append("Room Number: ").append(booking.getRoom_number()).append("\n");
        emailBody.append("Number of Beds: ").append(booking.getNombre_beds()).append("\n");
        emailBody.append("Check-In Date: ").append(checkInDateFormatted).append("\n");
        emailBody.append("Check-Out Date: ").append(checkOutDateFormatted).append("\n");
        emailBody.append("Total Amount: $").append(booking.getAmount()).append("\n");

        return emailBody.toString();
    }


    public void destroy() {
    }
}