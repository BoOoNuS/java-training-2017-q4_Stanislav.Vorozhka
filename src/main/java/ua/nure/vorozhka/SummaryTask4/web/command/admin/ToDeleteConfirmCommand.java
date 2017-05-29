package ua.nure.vorozhka.SummaryTask4.web.command.admin;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.exception.AppException;
import ua.nure.vorozhka.SummaryTask4.web.Paths;
import ua.nure.vorozhka.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Stanislav on 28.05.2017.
 */
public class ToDeleteConfirmCommand extends Command {

    private static final Logger LOG = Logger.getLogger(ToDeleteConfirmCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        LOG.debug("Command starts");

        String carId = req.getParameter("carId");
        LOG.trace(String.format("Request parameter: carId --> %s", carId));
        req.setAttribute("carId", carId);

        LOG.debug("Command finished");
        return Paths.CONFIRM_PAGE;
    }
}
