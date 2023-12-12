package app.SARA;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class JsonBuilder {
    public static void writeHashSetToJsonFile(HashSet<String> set, String filePath) {
        // Convert HashSet to JSON array
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ArrayList<String> list = new ArrayList<String>(set);
        JsonArray jsonArray = gson.toJsonTree(list).getAsJsonArray();

        // Ensure the directory exists
        Path directoryPath = Paths.get(filePath).getParent();
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle or log the exception
                return;
            }
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Write JSON array to file
            gson.toJson(jsonArray, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle or log the exception
        }
    }
}
