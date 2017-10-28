package ua.nure.vorozhka.SummaryTask4.web;

/**
 * Created by Stanislav on 19.05.2017.
 */
public final class Paths {

    private Paths() {}

    // Common pages
    public static final String ERROR_PAGE = "WEB-INF/view/jsp/common/error_page.jsp";
    public static final String AUTHENTICATE_PAGE = "html/authenticate.html";
    public static final String MAIN_PAGE = "WEB-INF/view/jsp/common/main_page.jsp";
    public static final String ORDER_PAGE = "WEB-INF/view/jsp/registered/common/order_page.jsp";

    // Command
    public static final String TO_MANAGER_ORDERS_COMMAND = "/controller?command=toManagerOrders";
    public static final String TO_CARS_COMMAND = "/controller?command=toCars";

    // Servlets
    public static final String MAIN_SERVLET = "main";
    public static final String AUTHENTICATE_SERVLET = "authenticate";
    public static final String ERROR_SERVLET = "error";
    public static final String PERSONAL_SERVLET = "personal";
    public static final String MANAGER_ORDERS_SERVLET = "managerOrders";
    public static final String TICKET_SERVLET = "ticket";
    public static final String CARS_SERVLET = "cars";
    public static final String PERMISSION_SERVLET = "permission";

    // Client
    public static final String PERSONAL_PAGE = "WEB-INF/view/jsp/registered/common/personal_page.jsp";
    public static final String EDIT_USER_PAGE = "WEB-INF/view/jsp/registered/common/edit_user_page.jsp";
    public static final String ORDERS_CLIENT_PAGE = "WEB-INF/view/jsp/registered/common/orders_page.jsp";

    // Manager
    public static final String ORDERS_MANAGER_PAGE = "WEB-INF/view/jsp/registered/manager/orders_page.jsp";
    public static final String PENALTY_PAGE = "WEB-INF/view/jsp/registered/manager/penalty_page.jsp";
    public static final String REJECT_PAGE = "WEB-INF/view/jsp/registered/manager/reject_page.jsp";
    public static final String STATE_STATS_PAGE = "WEB-INF/view/jsp/registered/manager/state_stats_page.jsp";

    // Admin
    public static final String CARS_PAGE = "WEB-INF/view/jsp/registered/admin/cars_page.jsp";
    public static final String PERMISSION_PAGE = "WEB-INF/view/jsp/registered/admin/permission_page.jsp";
    public static final String ADD_CAR_PAGE = "WEB-INF/view/jsp/registered/admin/add_car_page.jsp";
    public static final String EDIT_CAR_PAGE = "WEB-INF/view/jsp/registered/admin/edit_car_page.jsp";
    public static final String CONFIRM_PAGE = "WEB-INF/view/jsp/registered/admin/confirm_page.jsp";

    // PDF reports
    public static final String PDF_REPORTS_PATH = "WEB-INF/resources/";
}
