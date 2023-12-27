package util;

import jakarta.ejb.Asynchronous;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
@Asynchronous
public class MailSender {
    public static String sendEmail(String to, String body) {
        final String username = "hmsemsi@gmail.com";
        final String password = "cnmy kdni yeeh ujcu";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setText(body);
            message.setSubject("Facture Reservation");


            Transport.send(message);

            return "E-mail envoyé avec succès.";

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
