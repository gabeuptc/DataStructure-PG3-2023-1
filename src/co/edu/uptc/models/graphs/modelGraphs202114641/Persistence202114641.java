package co.edu.uptc.models.graphs.modelGraphs202114641;
import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Persistence202114641 {

    private final String FILE_NAME = "data/graphsData202114641.json";
    private final Gson gson = new Gson();

    public void saveElements(Map<Integer, MapElement> elements) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(elements, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, MapElement> loadElements() {
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type typeOfHashMap = new TypeToken<Map<Integer, MapElement>>(){}.getType();
            return gson.fromJson(reader, typeOfHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}

