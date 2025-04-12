package org.acme.server;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.Json;
import jakarta.persistence.PersistenceException;
import jakarta.validation.Valid;
import org.acme.enums.ItemsStatus;
import org.acme.exception.ItemsException;
import org.acme.model.Item;
import org.acme.rest.server.ItemRest;
import org.acme.rest.userDTO.ItemDTO;
import org.acme.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ItemRestTest {
    @InjectMock
    private ItemService itemService;

    @Test
    public void updateItemByIdTest() {
        Item item = new Item(); //Item updatedItem
        item.setNameOfItem("Test");
        item.setId(1L);
        Mockito.when(itemService.updateItemById(Mockito.eq(item.getId()), Mockito.any(Item.class))).thenReturn(item);
        given()
                .contentType("application/json")
                .body(item)
                .when().put("/items/"+item.getId())
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
    @Test
    public void updateItemByIdTestBadRequest() {
        Item item = new Item(); //Item updatedItem
        item.setNameOfItem("Test");
        item.setId(1L);
        Mockito.when(itemService.updateItemById(Mockito.eq(item.getId()), Mockito.any(Item.class))).thenReturn(null);
        given()
                .contentType("application/json")
                .body(item)
                .when().put("/items/"+item.getId())
                .then()
                .statusCode(400);
    }

    @Test
    public void deleteItemByIdTestNoContent() {
        Long testId = 1L;
        Mockito.when(itemService.deleteItemById(testId)).thenReturn(null);

        given()
                .when().delete("/items/" + testId)
                .then()
                .statusCode(204)
                .body(isEmptyOrNullString());
    }

    @Test
    public void deleteItemByIdTestOK() {
        Item item = new Item();
        item.setNameOfItem("Test");
        Long testId = 1L;
        Mockito.when(itemService.deleteItemById(testId)).thenReturn(item);

        given()
                .when().delete("/items/" + testId)
                .then()
                .statusCode(200);
    }

    @Test
    public void createItemOK() {
        ItemDTO item = new ItemDTO("Test");


        given()
                .contentType("application/json")
                .body(item)
                .when().post("/items")
                .then()
                .statusCode(201)
                .body("name", equalTo("Test"));
    }
    @Test
    public void createItemInternalServerError() throws ItemsException {
        ItemDTO itemDTO = new ItemDTO("Test");
        Mockito.when(itemService.createItem(Mockito.any(Item.class))).thenThrow(new ItemsException(ItemsStatus.EXISTS.getLabel()));
        given()
                .contentType("application/json")
                .body(itemDTO)
                .when().post("/items")
                .then()
                .statusCode(500)
                .body(containsString(ItemsStatus.EXISTS.getLabel()));;
    }

    @Test
    public void getAllItemsTest() {
        List<Item> list = new ArrayList<>();
        //Создания тестовых объектов
        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();
        // Добавление данных тестовым объектам
        item1.setNameOfItem("item1");
        item2.setNameOfItem("item2");
        item3.setNameOfItem("item3");
        // Добавление всех Item в list
        list.add(item1);
        list.add(item2);
        list.add(item3);

        Mockito.when(itemService.getAllItems()).thenReturn(list);

        given()
                .when().get("/items")
                .then()
                .statusCode(200)
                .body("$.size()", is(3))
                .body("[0].name", equalTo("item1"))
                .body("[1].name", equalTo("item2"))
                .body("[2].name", equalTo("item3"));
    }
}
