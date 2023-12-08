package login.Contoller;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Model.Booking;
import login.Model.User;
import login.Service.BookingService;
import login.Service.LoginService;

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
        Integer numeroChambre =Integer.parseInt(req.getParameter("roomNumber")) ;
        String checkIn = req.getParameter("checkInDate");
        String checkOut = req.getParameter("checkOutDate");
        Date checkInDate = new Date();
        Date checkOutDate = new Date();

        HttpSession session = req.getSession() ;
        User user = (User)session.getAttribute("user");

        try {
            checkInDate = DateFormat.getInstance().parse(checkIn);
            checkOutDate = DateFormat.getInstance().parse(checkOut);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        booking.setRoomNumber(numeroChambre);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);
        booking.setUser(user);

        bookingService.save(booking);







    }

    public void destroy() {
    }
}