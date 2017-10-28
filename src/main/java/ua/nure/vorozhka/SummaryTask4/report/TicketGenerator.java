package ua.nure.vorozhka.SummaryTask4.report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Car;
import ua.nure.vorozhka.SummaryTask4.model.entyty.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Stanislav on 24.05.2017.
 */
public final class TicketGenerator {

    private static final Font BIG_FONT = new Font(Font.FontFamily.HELVETICA,
            18, Font.BOLD);

    private static final Font SMALL_FONT = new Font(Font.FontFamily.HELVETICA,
            9, Font.ITALIC | Font.BOLD);

    private static final int TITLE_TOP_MERGING = 10;

    private static final int PARAGRAPH_BOTTOM_MERGING = 3;

    private static final int PARAGRAPH_TOP_MERGING = 15;

    private static final int BOTTOM_MARGIN = 7;

    private TicketGenerator() {

    }

    public static void createTicket(String path, User user, Car car)
            throws IOException, DocumentException {

        File file = new File(path);
        file.mkdirs();

        // Document creation
        Document ticket = new Document(PageSize.A5);

        PdfWriter.getInstance(ticket,
                new FileOutputStream(String.format("%s%s.pdf", path, user.getLogin())));

        ticket.open();

        // Title
        Paragraph title = getTitle();
        ticket.add(title);

        // Car paragraph
        ticket.add(getParagraph("Information about car:"));

        // Table of car
        ticket.add(getTableOfCar(car));

        // User paragraph
        ticket.add(getParagraph("Information about client:"));

        // Table of user
        ticket.add(getTableOfUser(user));

        // Current date paragraph
        ticket.add(getCurrentDate());

        // Image, logo
        /*Image stamp = getStampImg(file);
        ticket.add(stamp);*/
        ticket.close();
    }

    private static Paragraph getCurrentDate() {
        Paragraph paragraph = new Paragraph(2);
        paragraph.setSpacingBefore(PARAGRAPH_TOP_MERGING);
        paragraph.setFont(SMALL_FONT);
        paragraph.setSpacingAfter(PARAGRAPH_BOTTOM_MERGING);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.add(new Chunk("Current date: "));
        paragraph.add(new Chunk(new Date(System.nanoTime()).toString()));
        return paragraph;
    }

    private static PdfPTable getTableOfUser(User user) {
        PdfPTable tableOfUserInfo = new PdfPTable(2);

        tableOfUserInfo.addCell("User login:");
        tableOfUserInfo.addCell(user.getLogin());

        tableOfUserInfo.addCell("User full name:");
        tableOfUserInfo.addCell(user.getFullName());

        tableOfUserInfo.addCell("User passport:");
        tableOfUserInfo.addCell(user.getPassport());

        return tableOfUserInfo;
    }

    private static Paragraph getParagraph(String chunk) {
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(SMALL_FONT);
        paragraph.setSpacingAfter(PARAGRAPH_BOTTOM_MERGING);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(new Chunk(chunk));
        return paragraph;
    }

    private static PdfPTable getTableOfCar(Car car) {
        PdfPTable tableOfCarInfo = new PdfPTable(2);

        tableOfCarInfo.addCell("Car class:");
        tableOfCarInfo.addCell(car.getClazz().toString());

        tableOfCarInfo.addCell("Car mark:");
        tableOfCarInfo.addCell(car.getMark().toString());

        tableOfCarInfo.addCell("Car name:");
        tableOfCarInfo.addCell(car.getName());

        tableOfCarInfo.addCell("Car cost:");
        tableOfCarInfo.addCell(String.format("%dP", car.getCost()));
        return tableOfCarInfo;
    }

    private static Paragraph getTitle() {
        Paragraph title = new Paragraph("CHECK", BIG_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingBefore(TITLE_TOP_MERGING);
        title.setSpacingAfter(BOTTOM_MARGIN);
        return title;
    }
}
