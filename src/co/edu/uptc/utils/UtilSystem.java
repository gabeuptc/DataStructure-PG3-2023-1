package co.edu.uptc.utils;

public class UtilSystem {

    public static String getOsGenaral(){
        String aux =System.getProperty("os.name");
        aux = aux.startsWith("Windows")?"Windows":aux;
        aux = aux.startsWith("Mac")?"Mac":aux;
        return aux;
    }
}
