package co.edu.uptc.models.graphs.modelGraphs202022012;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;

public class Persistence {

    private static Persistence persistence;

    public static Persistence getInstance(){
        return persistence == null? persistence = new Persistence(): persistence;
    }

    public void saveData(Graph graph) {
        try {
            String json = new Gson().toJson(graph);
            PrintWriter print = new PrintWriter("data/graphsData202022012.json");
            print.write(json);
            print.close();
        }catch (FileNotFoundException e){
        }
    }

    public Graph loadData() {
        Graph graph = null;
        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader("data/graphsData202022012.json");
            BufferedReader br = new BufferedReader(fileReader);
            Type type = new TypeToken<Graph>(){}.getType();
            graph = gson.fromJson(br, type);
        }catch (FileNotFoundException e){
        }
        return graph;
    }

}
