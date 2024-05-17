package routes;

import controllers.ItemController;
import logger.CustomLogger;
import persistence.HibernateConfig;
import controllers.SecurityController;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.security.RouteRole;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RouteUser {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static SecurityController securityController = new SecurityController(emf);
    private static CustomLogger customLogger = new CustomLogger();

    public EndpointGroup securityRoutes() {
        return () -> {
            path("/auth", () -> {
                before(securityController.authenticate());
                post("/login", customLogger.handleExceptions(securityController.login()), Role.ANYONE);
                post("/register", customLogger.handleExceptions(securityController.register()), Role.ANYONE);
            });
        };
    }
}

