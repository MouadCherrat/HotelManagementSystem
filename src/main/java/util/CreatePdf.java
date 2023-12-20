package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatePdf {
    public void generatePdf(String filePath, int roomNumber, int numberOfBeds, Date checkInDate, Date checkOutDate, String totalAmount) throws IOException {
        try (PDDocument pdfDoc = new PDDocument()) {
            PDPage page = new PDPage();
            pdfDoc.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Facture de Réservation");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Numéro de chambre: " + roomNumber);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Nombre de lits: " + numberOfBeds);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Date de départ: " + formatDate(checkInDate));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Date de sortie: " + formatDate(checkOutDate));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Montant total: $" + totalAmount);
                contentStream.endText();
            }

            pdfDoc.save(filePath);
            System.out.println("PDF created successfully.");
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
