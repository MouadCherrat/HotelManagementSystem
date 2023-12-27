package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatePdf {
    public void generatePdf(String filePath, int roomNumber, int numberOfBeds, Date checkInDate, Date checkOutDate, String totalAmount) throws IOException {
        try (PDDocument pdfDoc = new PDDocument()) {
            PDPage page = new PDPage();
            pdfDoc.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 30);
                contentStream.setNonStrokingColor(Color.BLUE);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 750);
                contentStream.showText("Récapitulatif de Réservation");
                contentStream.endText();
                contentStream.setFont(PDType1Font.HELVETICA, 16);
                contentStream.setNonStrokingColor(Color.BLACK);
                drawText(contentStream, "-Numéro de la Chambre:", " " + roomNumber, 100, 700);
                drawText(contentStream, "-Nombre de Lits:", " " + numberOfBeds, 100, 670);
                drawText(contentStream, "-Date d'Arrivée:", " " + formatDate(checkInDate), 100, 640);
                drawText(contentStream, "-Date de Départ:", " " + formatDate(checkOutDate), 100, 610);
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
                contentStream.setNonStrokingColor(Color.BLUE);
                drawText(contentStream, "Montant Total:", " $" + totalAmount, 100, 550);
            }
            pdfDoc.save(filePath);
            System.out.println("PDF cree avec succes.");
        }
    }

    private void drawText(PDPageContentStream contentStream, String label, String value, float x, float y) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(label);
        contentStream.showText(value);
        contentStream.endText();
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
