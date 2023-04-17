package co.edu.uptc.views.maps;

public enum OrientationRoutes {
    ORIGIN_DESTIN(0),DESTIN_ORIGIN(1),BOTH(2);

    public final int value;

    private OrientationRoutes(int value) {
        this.value = value;
    }

}
