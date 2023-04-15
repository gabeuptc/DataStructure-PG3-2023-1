package co.edu.uptc.presenter;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.views.maps.MapElementGraph;
import co.edu.uptc.views.maps.MapPointGraph;
import co.edu.uptc.views.maps.OrientationRoutes;
import co.edu.uptc.views.maps.TypeRoute;
import org.jxmapviewer.viewer.GeoPosition;

import java.util.Map;
import java.util.Set;

public interface ContractGraphs {
    public interface View{
        void setPresenter(ContractGraphs.Presenter presenter);
        void updateGraph();
        public void notifyError(String value);
        void setUser(String user);

    }
    public interface Presenter{
        void setView(ContractGraphs.View view);
        void setModel(ContractGraphs.Model model);

        ContractGraphs.Model getModel();


        void updateGraph();
        String getUser();


        void addElement(MapElement mapElement);
        Set<MapElement> getElements();
        MapElement getElement(int id);
    }
    public interface Model{
        void setPresenter(ContractGraphs.Presenter presenter);

        void addElement(MapElement mapElement);

        Set<MapElement>  getElements();
        MapElement getElement(int id);
        void updateGraph();
        String getUser();
        void loadGraphs();
    }
}
