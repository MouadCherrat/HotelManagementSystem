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
import util.CreatePdf;


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
          boolean isAvailable = bookingService.checkReservation(roomNumber,checkInDate,checkOutDate);
          if (isAvailable){
              booking.setNombre_beds(nombreLits);
              booking.setRoom_number(roomNumber);
              booking.setCheckInDate(checkInDate);
              booking.setCheckOutDate(checkOutDate);
              booking.setUser(user);

              double totalAmount =  bookingService.calculateAmount(booking);
              session.setAttribute("totalAmount", totalAmount);
              bookingService.save(booking);

              CreatePdf createPdf = new CreatePdf();
              String filePath = "/Users/mouaad/IdeaProjects/HotelManagement/pdfSample.pdf";
              createPdf.generatePdf(filePath, roomNumber, nombreLits, checkInDate, checkOutDate, String.valueOf(totalAmount));


              req.setAttribute("reservationMessage", "Réservez dès maintenant pour 100 $ par nuit!");

              resp.sendRedirect(req.getContextPath() + "/Summary.jsp");
          }
          else {
              req.setAttribute("erreurMessage", "La chambre n'est pas disponible pour cet intervalle de dates.");
              req.getRequestDispatcher("Index.jsp").forward(req, resp);
          }

        } else {
            req.setAttribute("erreurMessage", "La date de réservation est invalide.");
            req.getRequestDispatcher("Index.jsp").forward(req, resp);
        }
    }
    public void destroy() {
    }

}