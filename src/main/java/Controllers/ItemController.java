package Controllers;

import daos.ItemDAO;
import daos.UserDAO;
import dtos.ItemDTO;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import persistence.HibernateConfig;
import persistence.Model.Item;
import persistence.Model.User;

import java.util.ArrayList;
import java.util.List;

public class ItemController {

    public static ItemDTO convertToDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .price(item.getPrice())
                .fullName(item.getFullName())
                .address(item.getAddress())
                .phoneNr(item.getPhoneNumber())
                .postalCode(item.getPostalCode())
                .status(item.isStatus())
                .userEmail(item.getUser().getEmail())
                .build();
    }

    public static Handler getAll(ItemDAO dao) {
        return ctx -> {
            List<Item> itemList = dao.getAll();
            List<ItemDTO> dtoList = new ArrayList<>();
            for (Item i : itemList) {
                dtoList.add(convertToDTO(i));
            }
            if (dtoList.isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND).result("No items were found.");
            } else {
                ctx.status(HttpStatus.OK).json(dtoList);
            }
        };
    }

    public static Handler delete(ItemDAO dao) {
        return ctx -> {
            String email = ctx.pathParam("user_id");
            int id = ctx.bodyAsClass(Integer.class);
            Item foundItem = dao.getById(id);
            if (foundItem != null) {
                ItemDTO dto = convertToDTO(foundItem);
                dao.delete(foundItem.getId());
                ctx.status(HttpStatus.OK).json(dto);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Item was not found.");
            }
        };
    }


    public static Handler getById(ItemDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Item foundItem = dao.getById(id);
            ItemDTO dto = convertToDTO(foundItem);
            if (dto != null) {
                ctx.status(HttpStatus.OK).json(dto);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("The item you are looking for does not exist.");
            }
        };
    }

    public static Handler create(ItemDAO dao) {
        return ctx -> {
            Item item = ctx.bodyAsClass(Item.class);
            Item createdItem = dao.create(item);
            ItemDTO dto = convertToDTO(createdItem);
            if (dto != null) {
                ctx.status(HttpStatus.OK).json(dto);
            } else {
                ctx.status(HttpStatus.INTERNAL_SERVER_ERROR).result("Couldn't create the item with the given data.");
            }
        };
    }

    public static Handler update(ItemDAO itemDAO, UserDAO userDAO) {
        return ctx -> {
            String email = ctx.pathParam("user_id");
            Item item = ctx.bodyAsClass(Item.class);
            User user = userDAO.getByString(email);
            if (user != null) {
                item.setUser(user);
                int i = itemDAO.update(item);
                if (i > 0) {
                    ItemDTO dto = convertToDTO(item);
                    ctx.json(dto);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("Item not found.");
                }
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("User not found.");
            }
        };
    }
}

