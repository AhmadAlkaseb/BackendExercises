package ThirdSemesterExercises.Backend.Week10Year2024.Day2.Routes;

import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controllers.HotelController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Controllers.SecurityController;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.HotelDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.DAOs.RoomDAO;
import ThirdSemesterExercises.Backend.Week10Year2024.Day2.Persistence.HibernateConfig;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class RouteHotel {

    private static HotelDAO hotelDAO;
    private static RoomDAO roomDAO;
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static SecurityController securityController = new SecurityController(emf);

    public RouteHotel(HotelDAO hotelDAO, RoomDAO roomDAO) {
        this.hotelDAO = hotelDAO;
        this.roomDAO = roomDAO;
    }

    public EndpointGroup hotelRoutes() {
        return () -> {
            before(securityController.authenticate());
            get("/hotels", HotelController.getAll(hotelDAO), Role.USER);
            get("/hotels/{id}", HotelController.getHotelById(hotelDAO), Role.ADMIN);
            get("/hotels/{id}/rooms", HotelController.getHotelWithRooms(hotelDAO, roomDAO), Role.ANYONE);
            post("/hotels", HotelController.create(hotelDAO), Role.ANYONE);
            put("/hotels/{id}", HotelController.update(hotelDAO), Role.ANYONE);
            delete("/hotels/{id}", HotelController.delete(hotelDAO), Role.ANYONE);
        };
    }
}