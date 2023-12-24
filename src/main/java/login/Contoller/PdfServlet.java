package login.Contoller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import login.Model.Facture;
import login.Model.User;

import login.Service.FactureService;
import util.MailSender;

@WebServlet(name = "PdfServletServlet", value = "/PdfServlet-servlet")
public class PdfServlet extends HttpServlet {
   // private FactureService factureservice;
    public void init() {
        //factureservice = new FactureService();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
      Facture facture  = (Facture) session.getAttribute("facture");
        String emailBody = buildEmailBody(facture);

        System.out.println("mail " + user.getEmail());

        MailSender.sendEmail(user.getEmail(), emailBody);
        System.out.println("body" + emailBody);
      //  factureservice.save(facture);


        resp.sendRedirect(req.getContextPath() + "/Index.jsp");

    }

    public void destroy() {
    }

    private String buildEmailBody(Facture facture) throws IOException {
          StringBuilder emailBody = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String checkInDateFormatted = dateFormat.format(facture.getBooking().getCheckInDate());
        String checkOutDateFormatted = dateFormat.format(facture.getBooking().getCheckOutDate());

        emailBody.append("Booking Details:\n");
        emailBody.append("Room Number: ").append(facture.getBooking().getRoom().getRoom_number()).append("\n");
        emailBody.append("Number of Beds: ").append(facture.getBooking().getRoom().getNombre_lits()).append("\n");
        emailBody.append("Check-In Date: ").append(checkInDateFormatted).append("\n");
        emailBody.append("Check-Out Date: ").append(checkOutDateFormatted).append("\n");
        emailBody.append("Total Amount: $").append(facture.getAmount()).append("\n");


        return emailBody.toString();

    }

}



