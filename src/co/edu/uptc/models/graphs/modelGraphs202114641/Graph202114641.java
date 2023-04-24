package co.edu.uptc.models.graphs.modelGraphs202114641;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.*;

public class Graph202114641 {
        private List<MapElement> points;
        private Arch202114641[][] matriz;
        private List<MapElement> elements;
        public Graph202114641(List<MapElement> mapElements) {
            this.elements= mapElements;
            readPoints();
            generateMatriz();
        }
        private void readPoints() {
            points=new ArrayList<>();
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getElementType() == ElementType.POINT) {
                    points.add(elements.get(i));
                }
            }


        }
        private void generateMatriz(){
            if (points.size()>0){
                matriz= new Arch202114641[points.size()][points.size()];
                for (int i = 0; i < elements.size(); i++) {
                    MapElement mapElement= elements.get(i);
                    if (mapElement.getElementType() == ElementType.ROUTE) {
                        MapRoute rute= mapElement.getMapRoute();
                        if (rute.getOrientationRoutes().value==0){
                            matriz[searchPointForIndex(rute.getPoint1())][searchPointForIndex(rute.getPoint2())]=
                                    new Arch202114641(mapElement);
                        } else if (rute.getOrientationRoutes().value==1) {
                            matriz[searchPointForIndex(rute.getPoint2())][searchPointForIndex(rute.getPoint1())]=
                                    new Arch202114641(mapElement);
                        }else {
                            matriz[searchPointForIndex(rute.getPoint2())][searchPointForIndex(rute.getPoint1())]=
                                    new Arch202114641(mapElement);
                            matriz[searchPointForIndex(rute.getPoint1())][searchPointForIndex(rute.getPoint2())]=
                                    new Arch202114641(mapElement);
                        }
                    }
                }

            }


        }
        public int searchPointForIndex(MapElement point){
            for (int i = 0; i < points.size(); i++) {
                if (point.equals(points.get(i))){
                    return i;
                }
            }
            return -1;
        }

        public boolean isRelacionaded(MapElement point){
            generateMatriz();
            int id=searchPointForIndex(point);
            for (int i = 0; i < matriz.length; i++) {
                if (matriz[i][id]!=null){
                    return true;
                }
            }
            for (int i = 0; i < matriz[id].length; i++) {
                if (matriz[id][i]!=null){
                    return true;
                }
            }
            return false;
        }
    private List<MapElement> findShortestPathDistance(MapElement start, MapElement end) {
        int startIdx = searchPointForIndex(start);
        int endIdx = searchPointForIndex(end);

        double[] dist = new double[points.size()];
        int[] prev = new int[points.size()];
        boolean[] visited = new boolean[points.size()];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Double.MAX_VALUE;
        }
        dist[startIdx] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Double.compare(dist[a], dist[b]));
        pq.offer(startIdx);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (int v = 0; v < points.size(); v++) {
                if (matriz[u][v] != null) {
                    double alt = dist[u] + matriz[u][v].getDistance();
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        prev[v] = u;
                        pq.offer(v);
                    }
                }
            }
        }

        LinkedList<MapElement> path = new LinkedList<>();
        int curr = endIdx;
        while (curr != startIdx) {
            path.addFirst(points.get(curr));
            curr = prev[curr];
        }
        path.addFirst(points.get(startIdx));
        return path;
    }
    private List<MapElement> findShortestPathTime(MapElement start, MapElement end) {
        int startIdx = searchPointForIndex(start);
        int endIdx = searchPointForIndex(end);

        double[] dist = new double[points.size()];
        int[] prev = new int[points.size()];
        boolean[] visited = new boolean[points.size()];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Double.MAX_VALUE;
        }
        dist[startIdx] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Double.compare(dist[a], dist[b]));
        pq.offer(startIdx);

        while (!pq.isEmpty()) {
            int u = pq.poll();
            if (visited[u]) {
                continue;
            }
            visited[u] = true;

            for (int v = 0; v < points.size(); v++) {
                if (matriz[u][v] != null) {
                    double alt = dist[u] + matriz[u][v].getTime();
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        prev[v] = u;
                        pq.offer(v);
                    }
                }
            }
        }

        LinkedList<MapElement> path = new LinkedList<>();
        int curr = endIdx;
        while (curr != startIdx) {
            path.addFirst(points.get(curr));
            curr = prev[curr];
        }
        path.addFirst(points.get(startIdx));
        return path;
    }
    public List<MapElement> getShortestForNodes(MapElement start, MapElement end){
            List<MapElement> pointsElements= findShortestPathDistance(start,end);
            List<MapElement> mapElements= new ArrayList<>(pointsElements);

        for (int i = 0; i < pointsElements.size()-1; i++) {
            int first=searchPointForIndex(pointsElements.get(i));
            int second=searchPointForIndex(pointsElements.get(i+1));
            if (matriz[first][second]!=null){
                MapElement mp=(matriz[first][second].getMapRouteElement());
                mapElements.add(mp);
            }

        }

        return mapElements;

    }
    public List<MapElement> getShortestForTime(MapElement start, MapElement end){
        List<MapElement> pointsElements= findShortestPathTime(start,end);
        List<MapElement> mapElements= new ArrayList<>(pointsElements);

        for (int i = 0; i < pointsElements.size()-1; i++) {
            int first=searchPointForIndex(pointsElements.get(i));
            int second=searchPointForIndex(pointsElements.get(i+1));
            if (matriz[first][second]!=null){
                MapElement mp= matriz[first][second].getMapRouteElement();
                mapElements.add(mp);
            }

        }

        return mapElements;


    }
    public void deletePoint(MapElement point){


    }

}
