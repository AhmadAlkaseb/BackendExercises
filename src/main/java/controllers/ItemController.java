package controllers;

import daos.ItemDAO;
import daos.UserDAO;
import dtos.ItemDTO;
import exceptions.ApiException;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import persistence.model.Item;
import persistence.model.User;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class ItemController {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String timestamp = dateFormat.format(new Date());

    public static ItemDTO convertToDTO(Item item) {
        return ItemDTO.builder()
                .id(item.getId())
                .title(item.getTitle())
                .description(item.getDescription())
                .price(item.getPrice())
                .fullName(item.getFullName())
                .address(item.getAddress())
                .phoneNr(item.getPhoneNumber())
                .postalCode(item.getZipCode())
                .status(item.isStatus())
                .user(item.getUser())
                .build();
    }

    public static Item convertToItem(ItemDTO dto) {

        return Item.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .fullName(dto.getFullName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNr())
                .zipCode(dto.getPostalCode())
                .status(dto.isStatus())
                .user(dto.getUser())
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
                throw new ApiException(HttpStatus.NOT_FOUND.getCode(), "No items were found.", timestamp);
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
                throw new ApiException(HttpStatus.NOT_FOUND.getCode(), "Item was not found: " + id, timestamp);
            }
        };
    }


    public static Handler getById(ItemDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            try {
                Item foundItem = dao.getById(id);
                if (foundItem != null) {
                    ItemDTO dto = convertToDTO(foundItem);
                    if (dto != null) {
                        ctx.status(HttpStatus.OK).json(dto);
                    }
                } else {
                    throw new ApiException(HttpStatus.NOT_FOUND.getCode(), "Item was not found: " + id, timestamp);
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format for id
                ctx.status(HttpStatus.BAD_REQUEST.getCode()).json("Invalid id format: " + e.getMessage());
            } catch (ApiException e) {
                throw new ApiException(HttpStatus.NOT_FOUND.getCode(), "Item was not found: " + id, timestamp);
            }
        };
    }

    public static Handler create(ItemDAO dao) {
        return ctx -> {
            ItemDTO dto = ctx.bodyAsClass(ItemDTO.class);
            Item createdItem = dao.create(convertToItem(dto));
            if (createdItem != null) {
                ctx.status(HttpStatus.OK).json(dto);
            } else {
                throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.getCode(), "Couldn't create item.", timestamp);
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
                    throw new ApiException(HttpStatus.NOT_FOUND.getCode(), "Item not found for id: " + item.getId(), timestamp);
                }
            } else {
                throw new ApiException(HttpStatus.NOT_FOUND.getCode(), "User not found for email: " + user.getEmail(), timestamp);
            }
        };
    }
}

