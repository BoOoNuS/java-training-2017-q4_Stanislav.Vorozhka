package ua.nure.vorozhka.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.vorozhka.SummaryTask4.web.command.admin.*;
import ua.nure.vorozhka.SummaryTask4.web.command.common.*;
import ua.nure.vorozhka.SummaryTask4.web.command.manager.*;
import ua.nure.vorozhka.SummaryTask4.web.command.user.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stanislav on 03.01.2017.
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);
    private static Map<String, Command> commands = new HashMap<>();

    static {

        // Authenticating command
        commands.put("authenticate", new AuthenticateCommand());
        commands.put("reg", new RegCommand());
        commands.put("logout", new LogoutCommand());

        // Common command
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("searchCars", new SearchCarsCommand());
        commands.put("toMainPage", new ToMainPage());

        // Client command
        commands.put("toPersonalPage", new ToPersonalPageCommand());
        commands.put("setPassport", new SetPassportCommand());
        commands.put("toEditUser", new ToEditUserCommand());
        commands.put("editUser", new EditUserCommand());
        commands.put("toClientOrders", new ToClientOrders());
        commands.put("payPenalty", new PayPenaltyCommand());
        commands.put("payOrder", new PayOrderCommand());
        commands.put("generateCheck", new GenerateCheckCommand());
        commands.put("toOrder", new ToOrderCommand());
        commands.put("order", new OrderCommand());

        // Manager command
        commands.put("toManagerOrders", new ToManagerOrdersCommand());
        commands.put("acceptCar", new AcceptCarCommand());
        commands.put("toSetPenalty", new ToSetPenaltyCommand());
        commands.put("setPenalty", new SetPenaltyCommand());
        commands.put("confirmOrder", new ConfirmOrderCommand());
        commands.put("toRejectOrder", new ToRejectOrderCommand());
        commands.put("rejectOrder", new RejectOrderCommand());
        commands.put("toStateCount", new ToStateCountCommand());

        // Admin command
        commands.put("toCars", new ToCarsCommand());
        commands.put("toPermission", new ToPermissionCommand());
        commands.put("findUser", new FindUserCommand());
        commands.put("block", new BlockCommand());
        commands.put("unlock", new UnlockCommand());
        commands.put("toAddCar", new ToAddCarCommand());
        commands.put("addCar", new AddCarCommand());
        commands.put("toEditCat", new ToEditCarCommand());
        commands.put("editCar", new EditCarCommand());
        commands.put("toDeleteConfirm", new ToDeleteConfirmCommand());
        commands.put("deleteCar", new DeleteCarCommand());
        commands.put("setPermission", new SetPermissionCommand());

        LOG.debug(String.format("All commands --> %s", commands.keySet()));
    }

    public static Command getCommand(String commandName) {
        if (commands.containsKey(commandName)) {
            return commands.get(commandName);
        }
        LOG.trace(String.format("Command not found, name --> %s", commandName));
        return commands.get("noCommand");
    }
}
