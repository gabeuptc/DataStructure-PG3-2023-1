package co.edu.uptc.models.graphs.modelGraphs202128710;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Persistence {

    public void store(Graph graph) throws FileNotFoundException {
        PrintWriter print = new PrintWriter("data/graphsData202128710.json");
        String store = new Gson().toJson(graph);
        print.write(store);
        print.close();
    }

    public JsonReader load() throws FileNotFoundException {
        return new JsonReader(new FileReader("data/graphsData202128710.json"));
    }
}
