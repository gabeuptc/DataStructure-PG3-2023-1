package co.edu.uptc.models.graphs.modelGraphs202114641;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.pojos.MapRoute;
import co.edu.uptc.views.maps.types.ElementType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Graph202114641 {
        private List<MapElement> points;
        private Arch202114641[][] matriz;
        private Map<Integer, MapElement> elements;
        public Graph202114641(HashMap mapElements) {
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
            if (points.size()<0){
                matriz= new Arch202114641[points.size()][points.size()];
                for (int i = 0; i < elements.size(); i++) {
                    MapElement mapElement= elements.get(i);
                    if (mapElement.getElementType() == ElementType.ROUTE) {
                        MapRoute rute= mapElement.getMapRoute();
                        if (rute.getOrientationRoutes().value==0){
                            matriz[searchPoirforIndex(rute.getPoint1())][searchPoirforIndex(rute.getPoint2())]=
                                    new Arch202114641(rute);
                        } else if (rute.getOrientationRoutes().value==1) {
                            matriz[searchPoirforIndex(rute.getPoint2())][searchPoirforIndex(rute.getPoint1())]=
                                    new Arch202114641(rute);
                        }else {
                            matriz[searchPoirforIndex(rute.getPoint2())][searchPoirforIndex(rute.getPoint1())]=
                                    new Arch202114641(rute);
                            matriz[searchPoirforIndex(rute.getPoint1())][searchPoirforIndex(rute.getPoint2())]=
                                    new Arch202114641(rute);
                        }
                    }
                }

            }


        }
        public int searchPoirforIndex(MapElement point){
            for (int i = 0; i < points.size(); i++) {
                if (point.equals(points.get(i))){
                    return i;
                }
            }
            return 0;
        }



}
