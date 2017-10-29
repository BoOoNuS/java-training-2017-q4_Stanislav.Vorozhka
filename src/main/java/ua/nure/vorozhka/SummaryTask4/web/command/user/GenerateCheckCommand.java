package ua.nure.vorozhka.SummaryTask4.web.command.user;

import com.itextpdf.text.DocumentException;
import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.exception.AppException;
import ua.nure.vorozhka.SummaryTask4.exception.ExceptionMessages;
import ua.nure.vorozhka.SummaryTask4.model.entyty.Car;
import ua.nure.vorozhka.SummaryTask4.model.entyty.User;
import ua.nure.vorozhka.SummaryTask4.report.TicketGenerator;
import ua.nure.vorozhka.SummaryTask4.web.Paths;
import ua.nure.vorozhka.SummaryTask4.web.command.Command;
import ua.nure.vorozhka.SummaryTask4.web.command.util.CommandUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on 26.05.2017.
 * @author Stanislav Vorozhka
 */
public class GenerateCheckCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GenerateCheckCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        Car car = CommandUtils.getCarFromRequest(req);
        LOG.trace(String.format("Request parameter: car --> %s", car.toString()));
        User user = (User) req.getSession().getAttribute("user");
        LOG.trace(String.format("Session attribute: user --> %s", user.toString()));
        String realPath = req.getServletContext().getRealPath(Paths.PDF_REPORTS_PATH);
        LOG.trace(String.format("Context path --> %s", realPath));

        try {
            TicketGenerator.createTicket(realPath, user, car);
        } catch (IOException | DocumentException e) {
            throw new AppException(ExceptionMessages.COULD_NOT_CREATE_CHECK_MESSAGE);
        }

        LOG.debug("Command finished");
        return Paths.TICKET_SERVLET;
    }
}
