package ua.nure.vorozhka.SummaryTask4.web.servlet;

import ua.nure.vorozhka.SummaryTask4.model.entyty.User;
import ua.nure.vorozhka.SummaryTask4.web.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Stanislav on 27.05.2017.
 */

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String ticketPath = String.format("%s%s.pdf", Paths.PDF_REPORTS_PATH, user.getLogin());
        req.getRequestDispatcher(ticketPath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(Paths.TICKET_SERVLET);
    }
}
