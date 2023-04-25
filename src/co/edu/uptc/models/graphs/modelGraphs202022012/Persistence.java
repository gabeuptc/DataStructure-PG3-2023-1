package co.edu.uptc.models.graphs.modelGraphs202022012;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Properties;

public class Persistence {

    private static Persistence persistence;
    private Properties properties;

    public static Persistence getInstance(){
        return persistence == null? persistence = new Persistence(): persistence;
    }

    public void loadProperties(){
        properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/config.properties"));
        }catch (Exception e){
            throw new RuntimeException("No se encontro el archivo");
        }
    }

    public void saveData(Graph graph) {
        //loadProperties();
        try {
            String json = new Gson().toJson(graph);
            PrintWriter print = new PrintWriter("data/graphsData202022012.json");
            print.write(json);
            print.close();
        }catch (FileNotFoundException e){
        }
    }

    public Graph loadData() {
        //loadProperties();
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
