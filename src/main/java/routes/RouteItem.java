package routes;

import Controllers.ItemController;
import Controllers.SecurityController;
import daos.ItemDAO;
import persistence.HibernateConfig;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RouteItem {

    private static ItemDAO itemDAO;
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static SecurityController securityController = new SecurityController(emf);

    public RouteItem(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public EndpointGroup itemRoutes() {
        return () -> {
            path("/items", () -> {
                        before(securityController.authenticate());
                        get("/", ItemController.getAll(itemDAO), Role.ANYONE);
                        get("/{id}", ItemController.getById(itemDAO), Role.ANYONE);
                        post("/", ItemController.create(itemDAO), Role.USER, Role.ADMIN);
                        put("/{user_id}", ItemController.update(itemDAO), Role.USER, Role.ADMIN);
                        delete("/{user_id}", ItemController.delete(itemDAO), Role.USER, Role.ADMIN);
            });
        };
    }
}