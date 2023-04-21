package co.edu.uptc.models.graphs.modelGraphs202113049;

import co.edu.uptc.pojos.MapElement;
import co.edu.uptc.presenter.ContractGraphs;
import co.edu.uptc.views.maps.types.ElementType;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class ManagerModelGraphs202113049 implements ContractGraphs.Model {

    private ContractGraphs.Presenter presenter;
    private Persistence persistence;
    private GraphsManager graphsManager;

    public ManagerModelGraphs202113049(){
        graphsManager = new GraphsManager();
        persistence = new Persistence();
    }

    @Override
    public void setPresenter(ContractGraphs.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addElement(MapElement mapElement){
        graphsManager.addElement(mapElement);
        try {
            persistence.store(this.graphsManager);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        presenter.updateGraph();
    }

    @Override
    public Set<MapElement> getElements() {
        return graphsManager.getElements();
    }

    @Override
    public MapElement getElement(int id) {
        return graphsManager.getElement(id);
    }

    @Override
    public MapElement getElement(int idElementPoint1, int idElementPoint2) {
        return null;
    }

    @Override
    public void updateGraph() {

    }

    @Override
    public String getUser() {
        return "DEYVID FERNANDO CRUZ MOLANO";
    }

    @Override
    public void loadGraphs() {
        try {
            graphsManager = new Gson().fromJson( persistence.load(),GraphsManager.class);
            if(graphsManager!=null){
                presenter.updateGraph();
            }else{
                graphsManager = new GraphsManager();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePoint(int idElement) {
        if (!isRelation(idElement)) {
            graphsManager.getElements1().remove(idElement);
            presenter.updateGraph();
            try {
                persistence.store(this.graphsManager);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            presenter.notifyWarning("El punto esta relacionado, por lo tanto no se puede borrar");
        }

    }

    private boolean isRelation(int id){
        boolean isRelation = false;

        for (int i = 0; i < graphsManager.getElements().size(); i++) {
            MapElement element = graphsManager.getElement(i);
            if(element!=null){
                if(graphsManager.getElement(i).getElementType() == ElementType.ROUTE){

                    if (graphsManager.getElement(i).getMapRoute().getPoint1().getIdElement() == id) {
                        isRelation =true;
                    }
                    if (graphsManager.getElement(i).getMapRoute().getPoint2().getIdElement() == id) {
                        isRelation = true;
                    }
                }
            }else{
                i++;
            }
        }
        return isRelation;
    }

    @Override
    public void findSortestRouteINDisntance(int idElementPoint1, int idElementPoint2) {

        graphsManager.searchRoutes(idElementPoint1,idElementPoint2);
    }

    @Override
    public void findShortestRouteInTime(int idElementPoint1, int idElementPoint2) {

    }

    @Override
    public Set<MapElement> getResultElements() {
        return graphsManager.getElementsResult();
    }

    @Override
    public void modifyElement(MapElement mapElementModify) {

    }
}
