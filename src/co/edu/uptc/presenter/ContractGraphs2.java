package co.edu.uptc.presenter;

import co.edu.uptc.views.maps.MapElementGraph;
import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;

import java.util.Set;

public interface ContractGraphs2 {
    public interface View{
        void setPresenter(ContractGraphs2.Presenter presenter);
        Set<MapElementGraph> calculateShortestDistanceRoute(MapPointGraph point1, MapPointGraph point2);
        Set<MapElementGraph> calculateShortestTimeRoute(MapPointGraph point1, MapPointGraph point2);
        void setArcType(int elementID, TypeRoute typeRoute);
        void setArcSpeed(int elementID, double speed);
        void setArcsOrientation(OrientationRoutes orientation);
        OrientationRoutes getOrientation();
        void deletePoint(int idPoint);
        void addElement(MapElementGraph element);
        Set<MapElementGraph> getElements();
        void updateGraph();
        public void notifyError(String value);
        void setUser(String user);

    }
    public interface Presenter{
        void setView(ContractGraphs2.View view);
        void setModel(ContractGraphs2.Model model);
        ContractGraphs2.Model getModel();
        Set<MapElementGraph> calculateShortestDistanceRoute(MapPointGraph point1, MapPointGraph point2);
        Set<MapElementGraph> calculateShortestTimeRoute(MapPointGraph point1, MapPointGraph point2);
        void setArcType(int elementID, TypeRoute typeRoute);
        void setArcSpeed(int elementID, double speed);
        void setArcsOrientation(OrientationRoutes orientation);
        OrientationRoutes getOrientation();
        void deletePoint(int idPoint);
        void addElement(MapElementGraph element);
        Set<MapElementGraph> getElements();
        void updateGraph();
        String getUser();
    }
    public interface Model{
        void setPresenter(ContractGraphs2.Presenter presenter);
        Set<MapElementGraph> calculateShortestDistanceRoute(MapPointGraph point1, MapPointGraph point2);
        Set<MapElementGraph> calculateShortestTimeRoute(MapPointGraph point1, MapPointGraph point2);
        void setArcType(int elementID, TypeRoute typeRoute);
        void setArcSpeed(int elementID, double speed);
        void setArcsOrientation(OrientationRoutes orientation);
        OrientationRoutes getOrientation();
        void deletePoint(int idPoint);
        void addElement(MapElementGraph element);
        Set<MapElementGraph> getElements();
        void updateGraph();
        String getUser();
        void loadGraphs();
    }
}
