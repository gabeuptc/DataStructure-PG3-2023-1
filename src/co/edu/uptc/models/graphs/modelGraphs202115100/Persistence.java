package co.edu.uptc.models.graphs.modelGraphs202115100;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Persistence {
    Properties prop;

    public Persistence() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("No se encontr\u00f3 el archivo");
        }
    }

    public Map<Integer,MapElement> getGraphs() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = new FileReader(prop.getProperty("DATA_FILE_202115100"));
        return fillGraph(gson.fromJson(reader, MapElement[].class), reader);
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

    public void saveGraph(Map<Integer, MapElement> elements) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(prop.getProperty("DATA_FILE_202115100"));
        writer.write(gson.toJson(elements.values()));
        writer.close();
    }
}
