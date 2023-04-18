package co.edu.uptc.models.graphs.modelGraphs202115100.persistence202115100;

import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Persistence {

    private Properties prop;

    public Persistence() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream("resources/config.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveGraph(Map<Integer, MapElement> elements) {
    }

    public Map<Integer, MapElement> getGraphs() throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(prop.getProperty("DATA_FILE_202115100"));
        System.out.println("Ruta: " + prop.getProperty("DATA_FILE_202115100"));
        for (MapElement mapElement : gson.fromJson(reader, MapElement[].class)) {

        }
        return null;
    }
}
