package co.edu.uptc.models.graphs.Graphs202113214;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
public class Persistence202113214 {


    private Properties properties;

    public Persistence202113214() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Arvhico no encontrado o inexistente");
        }
    }


    private Map<Integer, MapElement> fillGraph(MapElement[] mapElements, FileReader reader) throws IOException {
        Map<Integer, MapElement> elements = new HashMap<>();
        if (mapElements != null) {
            for (MapElement mapElement : mapElements) {
                elements.put(mapElement.getIdElement(), mapElement);
            }
        }
        reader.close();
        return elements;
    }
    public Map<Integer, MapElement> getGraphs() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = null;
        try {
            reader = new FileReader(properties.getProperty("DATA_FILE_202113214"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arvhico no encontrado o inexistente" + properties.getProperty("DATA_FILE_202113214") + ", se creara uno nuevo");
        }
        return fillGraph(gson.fromJson(reader, MapElement[].class), reader);
    }


    public void keepGraph(Map<Integer, MapElement> elements) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(properties.getProperty("DATA_FILE_202113214"));
        writer.write(gson.toJson(elements.values()));
        writer.close();
    }
}


