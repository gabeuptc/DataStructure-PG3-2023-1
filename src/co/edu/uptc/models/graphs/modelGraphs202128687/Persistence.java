package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Persistence {
    public Map<Integer, MapElement> loadGraph() {
        MapElement[] elements = new MapElement[0];
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader fileReader = new FileReader("data/202128687Graph.json");
            elements = gson.fromJson(fileReader, MapElement[].class);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        return getGraphList(elements);
    }

    private Map<Integer, MapElement> getGraphList(MapElement[] elements) {
        Map<Integer, MapElement> graph = new HashMap<>();
        for (MapElement element : elements) {
            graph.put(element.getIdElement(), element);
        }
        return graph;
    }

    public void saveGraph(Map<Integer, MapElement> graph) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter("data/202128687Graph.json");
            fileWriter.write(gson.toJson(graph.values()));
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
