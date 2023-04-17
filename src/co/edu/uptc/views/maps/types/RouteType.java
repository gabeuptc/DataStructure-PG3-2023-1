package co.edu.uptc.views.maps.types;

public enum RouteType {
    PAVING(0),
    ROAT_RECEBO(1),
    ADOQUINATE(2),
    TRAIL(3),
    OTHER(4);

    public final int value;

    private RouteType(int value) {
        this.value = value;
    }


}


