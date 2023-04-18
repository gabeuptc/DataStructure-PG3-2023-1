package co.edu.uptc.models.graphs.modelGraphs202022012;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Persistence {

    private static Persistence persistence;

    public static Persistence getInstance(){
        return persistence == null? persistence = new Persistence(): persistence;
    }

    public void saveData(Graph graph) {
        try {
            String json = new Gson().toJson(graph);
            PrintWriter print = new PrintWriter("data/graphsData.json");
            print.write(json);
            print.close();
        }catch (FileNotFoundException e){
            System.err.println("No se pudo encontrar el archivo data/graphsData.json");
        }
    }

    public Graph loadData() {
        Graph graph = null;
        try {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader("data/graphsData202022012.json"));
            Type type = new TypeToken<Graph>(){}.getType();
            graph = gson.fromJson(br, type);
        }catch (FileNotFoundException e){
            System.err.println("No se pudo encontrar el archivo data/graphsData.json");
        }
        return graph;
    }

}
