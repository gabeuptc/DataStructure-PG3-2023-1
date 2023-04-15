package co.edu.uptc.presenter;

import co.edu.uptc.views.maps.MapElement;
import co.edu.uptc.views.maps.MapPoint;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

import java.util.Set;

public interface ContractGraphs {
    public interface View{
        void setPresenter(ContractGraphs.Presenter presenter);
        Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2);
        Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2);
        void setArcType(int elementID, TypeRoute typeRoute);
        void setArcSpeed(int elementID, double speed);
        void setArcsOrientation(OrientationRoutes orientation);
        OrientationRoutes getOrientation();
        void deletePoint(int idPoint);
        void addElement(MapElement element);
        Set<MapElement> getElements();
        void updateGraph();
        public void notifyError(String value);
        void setUser(String user);

    }
    public interface Presenter{
        void setView(ContractGraphs.View view);
        void setModel(ContractGraphs.Model model);
        ContractGraphs.Model getModel();
        Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2);
        Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2);
        void setArcType(int elementID, TypeRoute typeRoute);
        void setArcSpeed(int elementID, double speed);
        void setArcsOrientation(OrientationRoutes orientation);
        OrientationRoutes getOrientation();
        void deletePoint(int idPoint);
        void addElement(MapElement element);
        Set<MapElement> getElements();
        void updateGraph();
        String getUser();
    }
    public interface Model{
        void setPresenter(ContractGraphs.Presenter presenter);
        Set<MapElement> calculateShortestDistanceRoute(MapPoint point1, MapPoint point2);
        Set<MapElement> calculateShortestTimeRoute(MapPoint point1, MapPoint point2);
        void setArcType(int elementID, TypeRoute typeRoute);
        void setArcSpeed(int elementID, double speed);
        void setArcsOrientation(OrientationRoutes orientation);
        OrientationRoutes getOrientation();
        void deletePoint(int idPoint);
        void addElement(MapElement element);
        Set<MapElement> getElements();
        void updateGraph();
        String getUser();
    }
}
