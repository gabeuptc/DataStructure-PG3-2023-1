package co.edu.uptc.models.graphs.modelGraphs202113049;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Persistence {

    public void store(GraphsManager graphsManager) throws FileNotFoundException {
        PrintWriter printer = new PrintWriter("data/graphsData202113049");
        String graphManagerText = new Gson().toJson(graphsManager);
        printer.write(graphManagerText);
        printer.close();
    }

    public JsonReader load() throws FileNotFoundException {
        return new JsonReader(new FileReader("data/graphsData202113049"));
    }

}
