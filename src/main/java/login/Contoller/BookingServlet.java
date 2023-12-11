package login.Contoller;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Model.Booking;
import login.Model.User;
import login.Service.BookingService;

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

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                checkInDate = dateFormat.parse(checkIn);
                checkOutDate = dateFormat.parse(checkOut);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();
        if (checkInDate != null && checkInDate.after(currentDate)) {

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
            resp.sendRedirect(req.getContextPath() + "/Summary.jsp");

        } else {
            req.setAttribute("erreurMessage", "La date de réservation est invalide.");
            req.getRequestDispatcher("Index.jsp").forward(req, resp);
        }
    }

    public void destroy() {
    }
}