package routes;

import daos.ItemDAO;
import daos.UserDAO;
import persistence.HibernateConfig;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

public class Route {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static ItemDAO itemDAO = ItemDAO.getInstance(emf);
    private static UserDAO userDAO = UserDAO.getInstance(emf);
    private static RouteItem routeItem = new RouteItem(itemDAO, userDAO);
    private static RouteUser routeUser = new RouteUser();

    public static EndpointGroup addRoutes() {
        return combineRoutes(routeItem.itemRoutes(), routeUser.securityRoutes());
    }

    private static EndpointGroup combineRoutes(EndpointGroup... endpointGroups) {
        return () -> {
            for (EndpointGroup group : endpointGroups) {
                group.addEndpoints();
            }
        };
    }
}
