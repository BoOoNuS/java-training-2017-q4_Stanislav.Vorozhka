package ua.nure.vorozhka.SummaryTask4.web.servlet;

import ua.nure.vorozhka.SummaryTask4.web.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Stanislav on 29.05.2017.
 */

@WebServlet("/permission")
public class PermissionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Paths.PERMISSION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(Paths.PERMISSION_SERVLET);
    }
}
