package co.edu.uptc.models.graphs.modelGraphs202128710;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.List;

public class Graph {

    private Persistence persistence;
    private ManagerModelGraphs202128710 managerModelGraphs202128710;
    private List<MapElement> element;

    public List<MapElement> getElement() {
        return element;
    }

    public void setElement(List<MapElement> element) {
        this.element = element;
    }

    public void storeGraphs() throws FileNotFoundException {
        String store = new Gson().toJson(managerModelGraphs202128710);
        persistence.store(store);
    }

    public void loadGraphs() throws FileNotFoundException {
        managerModelGraphs202128710 = new Gson().fromJson(persistence.load(),ManagerModelGraphs202128710.class);
    }


}
