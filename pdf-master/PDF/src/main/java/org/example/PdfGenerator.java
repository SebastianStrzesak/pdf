package org.example;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.time.LocalDate;
import java.util.ArrayList;

public class PdfGenerator {

    // Ścieżka do zapisu pliku PDF
    String dest = "zamowienie.pdf";
    LocalDate currentDate = LocalDate.now();
    String companyDetails = "XYZ Company\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Kraków: " +
            currentDate + "\nNIP: 111-222-33-44\n12-345 Krakow\nul. Testowa123\n\n";

    public void generatePdf(ArrayList<OneRow> list) {
        try {
            PdfWriter writer = new PdfWriter(dest);
            PdfDocument pdfDoc = new PdfDocument(writer);

            // Dodanie zawartości do dokumentu
            Document document = new Document(pdfDoc);
            LocalDate currentDate = LocalDate.now();
            document.add(new Paragraph(companyDetails));
            document.add(new Paragraph("...........................::::::::::::::::::::::::::::::::::::::ZAMÓWIENIE::::::::::::::::::::::::::::::::::::::...........................\n\n"));
            // Tworzenie tabeli
            Table table = new Table(new float[]{3, 4, 4});

            // Dodanie nagłówków tabeli
            table.addHeaderCell(new Cell().add(new Paragraph("Usluga")));
            table.addHeaderCell(new Cell().add(new Paragraph("Liczba h")));
            table.addHeaderCell(new Cell().add(new Paragraph("PLN/h")));

            // Dodanie wierszy do tabeli
            for (OneRow row : list) {
                table.addCell(new Cell().add(new Paragraph(row.name)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(row.hours))));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(row.price))));
            }

            table.setWidth(520);
            document.add(table);

            document.add(new Paragraph("Suma: " + OrderWindow.getSuma() + "PLN\nZaliczka: " +
                    OrderWindow.getZaliczka()+ "PLN\nPozostalo do zaplaty: " +
                    (OrderWindow.getSuma()-OrderWindow.getZaliczka())+"PLN"));
            document.add(new Paragraph("Metoda platności: " + OrderWindow.getMetodaPlatnosci() ));

            Paragraph podpisLine = new Paragraph("................................");
            podpisLine.setTextAlignment(TextAlignment.RIGHT);
            document.add(podpisLine);
            Paragraph podpisPracownika = new Paragraph("Podpis pracownika   ");
            podpisPracownika.setTextAlignment(TextAlignment.RIGHT);
            document.add(podpisPracownika);




            document.close();
            System.out.println("PDF został wygenerowany!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
