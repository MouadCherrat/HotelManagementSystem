package login.Contoller;

import java.io.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Model.Booking;
import login.Model.Facture;
import login.Model.Room;
import login.Model.User;
import login.Service.BookingService;
import login.Service.FactureService;
import util.CreatePdf;

@WebServlet(name = "BookingServletServlet", value = "/BookingServlet-servlet")
public class BookingServlet extends HttpServlet {
    private BookingService bookingService;
    private FactureService factureservice;

    private Facture facture;
    private Room room;
    public void init() {
        bookingService = new BookingService();
        factureservice = new FactureService();
        facture = new Facture();
        room = new Room();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Booking booking = new Booking();
        Integer nombreLits =Integer.parseInt(req.getParameter("nombre_lits")) ;
        String checkIn = req.getParameter("checkInDate");
        String checkOut = req.getParameter("checkOutDate");
        Date checkInDate = new Date();
        Date checkOutDate = new Date();

        try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                checkInDate = dateFormat.parse(checkIn);
                checkOutDate = dateFormat.parse(checkOut);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date currentDate = new Date();
        if (checkInDate != null && checkInDate.after(currentDate) && checkOutDate != null && checkOutDate.after(currentDate)) {
          Room room =  bookingService.checkReservation(nombreLits,checkInDate,checkOutDate);
          if (room != null){

              HttpSession session = req.getSession() ;
              User user = (User)session.getAttribute("user");
              booking.setRoom(room);
              booking.setCheckInDate(checkInDate);
              booking.setCheckOutDate(checkOutDate);
              booking.setUser(user);
              bookingService.save(booking);

              long nights = ChronoUnit.DAYS.between(booking.getCheckInDate().toInstant(), booking.getCheckOutDate().toInstant());
              BigDecimal roomPrice = room.getPrice();
              BigDecimal totalAmount = roomPrice.multiply(BigDecimal.valueOf(nights));
              facture.setAmount(totalAmount);
              facture.setBooking(booking);
              facture.setUser(user);

              session.setAttribute("facture",facture);
              factureservice.save(facture);

              CreatePdf createPdf = new CreatePdf();
              String filePath = "/Users/mouaad/IdeaProjects/HotelManagement/pdfSample.pdf";
              createPdf.generatePdf(filePath, room.getRoom_number(), room.getNombre_lits(), checkInDate, checkOutDate, String.valueOf(totalAmount));
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