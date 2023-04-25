package co.edu.uptc.models.graphs.modelGraphs202127343.persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import co.edu.uptc.pojos.MapElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GsonMapper {

    private String font;

    public GsonMapper(String font) {
        this.font = font;
    }

    public void writeInformation(ArrayList<MapElement> list) {
        PrintWriter print;
        try {
            print = new PrintWriter(font);
            print.write(new Gson().toJson(list));
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<MapElement> getInformation() throws IOException {
        ArrayList<MapElement> information = new ArrayList<MapElement>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonReader reader = new JsonReader(new FileReader(font));
        reader.beginArray();
        while (reader.hasNext()) {
            information.add(gson.fromJson(reader, MapElement.class));
        }
        return information;
    }
}
