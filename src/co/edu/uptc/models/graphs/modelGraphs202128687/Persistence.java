package co.edu.uptc.models.graphs.modelGraphs202128687;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Persistence {
    public Map<Integer, MapElement> loadGraph() {
        Map<Integer, MapElement> graphList = new HashMap<>();
        MapElement[] elements = new MapElement[0];
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader("data/202128687Graph.json");
            BufferedReader bReader = new BufferedReader(fileReader);
            elements = gson.fromJson(bReader, MapElement[].class);
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
            String json = new Gson().toJson(graph);
            PrintWriter print = new PrintWriter("data/202128687Graph.json");
            print.write(json);
            print.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}
