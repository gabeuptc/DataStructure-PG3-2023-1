package co.edu.uptc.models.graphs.modelGraphs202128687;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

public class Persistence {
    public Graph loadGraph() {
        Graph graph = null;
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader("data/202128687Graph.json");
            BufferedReader bReader = new BufferedReader(fileReader);
            Type type = new TypeToken<Graph>(){}.getType();
            graph = gson.fromJson(bReader, type);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
        return graph;
    }
}
