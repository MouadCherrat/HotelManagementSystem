package login.Contoller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Model.Booking;
import login.Model.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import util.CreatePdf;
import util.MailSender;

@WebServlet(name = "PdfServletServlet", value = "/PdfServlet-servlet")
public class PdfServlet extends HttpServlet {
    private Booking booking;
    public void init() {
        booking = new Booking();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Booking booking = (Booking) session.getAttribute("booking");
        String emailBody = buildEmailBody(booking);

        System.out.println("mail " + user.getEmail());

        MailSender.sendEmail(user.getEmail(), emailBody);
        System.out.println("body" + emailBody);


        resp.sendRedirect(req.getContextPath() + "/Index.jsp");
    }

    public void destroy() {
    }

    private String buildEmailBody(Booking booking) throws IOException {
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

}



