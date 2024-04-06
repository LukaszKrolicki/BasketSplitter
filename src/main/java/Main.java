import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.BasketSplitter.JsonFolderReader;
import org.BasketSplitter.ShoppingList;


public class Main {
    public static void main(String[] args) {
        JsonFolderReader reader = new JsonFolderReader();

        //basket is the file with products, you can change basket file to test other combinations
        JsonArray basket = reader.readJsonArray("src/main/resources/basket-2.json");
        //JsonArray basket = reader.readJsonArray("src/main/resources/basket-2.json");

        JsonObject config = reader.readJsonObject("src/main/resources/config.json");

        ShoppingList sh = new ShoppingList(config);

        if(config!=null || basket!=null){
            System.out.println(sh.split(basket));
        }
        else{
            System.out.println("Invalid JSON format");
        }
    }


}

