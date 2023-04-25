package co.edu.uptc.models.graphs.modelGraphs202127061;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;

public class Persistence {
    private Properties properties;
    public Persistence() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("No se encontra el archivo");
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
    public void save(Map<Integer, MapElement> elements) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(properties.getProperty("DATA_FILE_202127061"));
        writer.write(gson.toJson(elements.values()));
        writer.close();
    }
    public Map<Integer, MapElement> get() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader reader = null;
        try {
            reader = new FileReader(properties.getProperty("DATA_FILE_202127061"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se encontra el archivo " + properties.getProperty("DATA_FILE_202127061") + ", se creara uno nuevo");
        }
        return fillGraph(gson.fromJson(reader, MapElement[].class), reader);
    }
}
