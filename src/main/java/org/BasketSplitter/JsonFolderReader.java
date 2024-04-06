package org.BasketSplitter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonFolderReader {
    //Class made to read JSON files and manipulate them
    public JsonArray readJsonArray(String url){
        File file = new File(url);

        try (FileReader reader = new FileReader(file)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonArray()) {
                JsonArray x = jsonElement.getAsJsonArray();
                return x;
            }

        } catch (IOException e) {
            return null;
        }

        return null;
    }

    public JsonObject readJsonObject(String url){
        File file = new File(url);

        try (FileReader reader = new FileReader(file)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject()) {
                JsonObject x = jsonElement.getAsJsonObject();
                return x;
            }

        } catch (IOException e) {
            return null;
        }

        return null;
    }
}


