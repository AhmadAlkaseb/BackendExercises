package Controllers;

import daos.ItemDAO;
import dtos.HotelDTO;
import persistence.Model.Item;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

public class ItemController {
    public static Handler getAll(ItemDAO dao) {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND).result("No hotels were found.");
            } else {
                ctx.status(HttpStatus.OK).json(dao.getAll());
            }
        };
    }

    public static Handler delete(ItemDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("user_id"));
            Item foundItem = dao.getById(id);
            if (foundItem != null) {
                HotelDTO hotelDTO = HotelDTO.builder()
                        .id(foundItem.getId())
                        .name(foundItem.getTitle())
                        .address(foundItem.getAddress())
                        .rooms(foundItem.getRooms())
                        .build();

                dao.delete(foundItem.getId());

                ctx.status(HttpStatus.OK).json(hotelDTO);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Item was not found.");
            }
        };
    }


    public static Handler getById(ItemDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (dao.getById(id) != null) {
                Item foundItem = dao.getById(id);
                HotelDTO hotelDTO = HotelDTO.builder()
                        .id(foundItem.getId())
                        .name(foundItem.getTitle())
                        .address(foundItem.getAddress())
                        .rooms(foundItem.getRooms())
                        .build();
                ctx.status(HttpStatus.OK).json(hotelDTO);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("The hotel id you are looking for does not exist.");
            }
        };
    }

    public static Handler create(ItemDAO dao) {
        return ctx -> {
            Item item = ctx.bodyAsClass(Item.class);
            if (item != null) {
                dao.create(item);
                ctx.status(HttpStatus.OK).json(item);
            } else {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Couldn't create the item with the given data.");
            }
        };
    }

    public static Handler update(ItemDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("user_id"));
            HotelDTO updatedHotelDTO = ctx.bodyAsClass(HotelDTO.class);

            // Fetch the hotel from the database
            Item foundItem = dao.getById(id);
            updatedHotelDTO.setRooms(foundItem.getRooms());

            if (foundItem != null) {
                foundItem.setTitle(updatedHotelDTO.getName());
                foundItem.setAddress(updatedHotelDTO.getAddress());

                // Save the updated hotel to the database
                dao.update(foundItem);
                updatedHotelDTO.setId(id);

                // Return the updated hotelDTO object as JSON response
                ctx.json(updatedHotelDTO);
            } else {
                // Item with the provided ID not found
                ctx.status(HttpStatus.NOT_FOUND).result("Item not found.");
            }
        };
    }
}

