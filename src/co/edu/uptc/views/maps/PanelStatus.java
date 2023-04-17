package co.edu.uptc.views.maps;

import co.edu.uptc.views.Globals.ValuesGlobals;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;

import javax.swing.*;
import java.awt.*;

public class PanelStatus extends JPanel {

    public static final int NORMAL =0;
    public static final int ORIGEN_ROUTE =1;
    public static final int DESTINATION_ROUTE =2;

    public static final int CREATED_POINT =100;
    public static final int DELETE_POINT =101;
    public static final int SELECTED_POINT =102;
    public static final int CREATED_ROUTED =103;
    public static final int CANCELED_ROUTE =104;
    public static final int SHORTEST_ROUTE_IN_DISTANCE = 105;
    public static final int SHORTEST_ROUTE_IN_TIME = 106;

    public static final int  MODIFIED_ROUTE = 107;


    public static final int TYPE_MAP_PREDETERMINED =300;
    public static final int TYPE_MAP_MAP =301;
    public static final int TYPE_MAP_HYBRID =302;
    public static final int TYPE_MAP_SATELLITE =303;


    private JLabel titulo1;
    private JLabel message1;

    private JLabel titleLastAction;
    private JLabel lastAction;

    private JLabel titleTypeMap;
    private JLabel typeMap;

    private JLabel statusElement;


    String []titles = {"Estado: ","      Ultima acción: ","    Estado elementos: ","   Tipo de Mapa: "};
    String []messages1 = {"Normal","Seleccionar Origen de Ruta","Seleccionar destino de ruta"};
    String [] messagesLastAction = {"Punto Creado","Punto Borrado","Punto Selecciondo","Ruta Creada","Ruta Cancelada","Ruta mas corta en distancia","Ruta mas corta en tiempo","Ruta Modificada"};
    //String [] messagesStatusElement = {"VIsible","Ocultos"};

    String [] messagesTypeMap = {"Predeterminado","Mapa","Hibrido","Satelital"};



    public PanelStatus() {
        setPreferredSize(new Dimension(0,50));
        setBackground(ValuesGlobals.COLOR_BACK_PANEL_N3);
        createComponents();
    }

    private void createComponents(){
        titulo1 = new JLabel(titles[0]);
        titleLastAction = new JLabel(titles[1]);

        titleTypeMap = new JLabel(titles[3]);

        message1 = new JLabel(messages1[0]);
        lastAction = new JLabel();
        statusElement = new JLabel();
        typeMap = new JLabel();


        add(titulo1);
        add(message1);

        add(titleLastAction);
        add(lastAction);


        add(statusElement);

        add(titleTypeMap);
        add(typeMap);
    }


    public void setMessageS(int value){
        switch (value){
            case 0,1,2,3,4,5,6,7,8,9,10 : {
                setMessage1(messages1[value]);
                break;
            }
            case 100,101,102,103,104,105,106,107,108,109,110: {
                setLastAction(messagesLastAction[value-100]);
                break;
            }

            case 300,301,302,303,304,305,306: {
                setStatusTypeMap(messagesTypeMap[value-300]);
                break;
            }
        }

    }


    private void setMessage1(String text){
        message1.setText(text);
    }

    private void setLastAction(String text){
        lastAction.setText(text);
    }
    private void setStatusElement(String text){
        statusElement.setText(text);
    }

    private void setStatusTypeMap(String text){
        typeMap.setText(text);
    }
}
