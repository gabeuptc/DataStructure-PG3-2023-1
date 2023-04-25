package co.edu.uptc.models.graphs.modelGraphs202113049;

import java.util.Comparator;

public class ArcComparator implements Comparator<Arc202113049> {
    @Override
    public int compare(Arc202113049 o1, Arc202113049 o2) {
        return String.valueOf(o1.getDistance()).compareTo(String.valueOf(o2.getDistance()));
    }
}
