package org.BasketSplitter;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
public class Tests {

    //For testing
    JsonFolderReader reader = new JsonFolderReader();
    JsonObject config = reader.readJsonObject("src/main/resources/config.json");
    ShoppingList sh = new ShoppingList(config);

    Map<String, List<String>> deliveryMap = new HashMap<>();

    @Test
    public void validTest(){
        deliveryMap.put("Pick-up point", List.of("Fond - Chocolate"));
        deliveryMap.put("Parcel locker", new ArrayList<>());
        deliveryMap.put("In-store pick-up", new ArrayList<>());
        deliveryMap.put("Same day delivery", new ArrayList<>());
        deliveryMap.put("Next day shipping", new ArrayList<>());
        deliveryMap.put("Mailbox delivery", new ArrayList<>());
        deliveryMap.put("Courier", Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Cookies - Englishbay Wht"));
        deliveryMap.put("Express Collection", new ArrayList<>());

        JsonArray basketTest = reader.readJsonArray("src/main/resources/basket-1.json");
        assertEquals(deliveryMap,sh.split(basketTest));
    }
    @Test
    public void testBlankBasket(){
        JsonArray basketTest = reader.readJsonArray("src/main/resources/basket-test.json");
        assertNull(sh.split(basketTest));
    }
    @Test
    public void testInvalidFormatBasket(){
        //Testing JsonReader
        assertNull(reader.readJsonArray("src/main/resources/basket-invalid-form.json"));
    }
    @Test
    public void testInvalidFormatConfig(){
        //Testing JsonReader
        assertNull(reader.readJsonObject("src/main/resources/config-invalid-form.json"));
    }

}
