package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pojo.Edge;
import pojo.Node;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Persistence {

    public void loadData(List<Graph> graphs) throws FileNotFoundException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(graphs);
        System.out.println(json);
        PrintWriter print = new PrintWriter("data/graphsData.json");
        print.write(json);
        print.close();
    }

    public void loadData(ArrayList<Edge> graphs) throws FileNotFoundException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(graphs);
        System.out.println(json);
        PrintWriter print = new PrintWriter("data/graphsData.json");
        print.write(json);
        print.close();
    }
}
