package co.edu.uptc.presenter;

import co.edu.uptc.pojos.MapElement;

import java.util.Set;

public interface ContractGraphs {
    public interface View{
        void setPresenter(ContractGraphs.Presenter presenter);
        void updateGraph();
        public void notifyError(String value);
        void setUser(String user);

        void notifyWarning(String value);

        void updateResultGraph();
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

        void deletePoint(int idElement);

        void notifyWarning(String s);

        void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2);

        void findShortestRouteInTime(int idElementPoint1, int idElementPoint2);

        void updateResultGraph();

        Set<MapElement> getResultElements();
    }
    public interface Model{
        void setPresenter(ContractGraphs.Presenter presenter);

        void addElement(MapElement mapElement);

        Set<MapElement>  getElements();
        MapElement getElement(int id);
        void updateGraph();
        String getUser();
        void loadGraphs();

        void deletePoint(int idElement);

        void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2);

        void findShortestRouteInTime(int idElementPoint1, int idElementPoint2);

        Set<MapElement> getResultElements();
    }
}
