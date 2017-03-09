package ua.peresvit.entity;

/**
 * Created by maximmaximov_2 on 3/9/17.
 */
public enum EventType {

    COMPETITION("Змагання"),
    CELEBRATION("Святкування"),
    DEMONSTARTION("Показові виступи"),
    APPRAISAL("Атестації"),
    WORKSHOP("Семінари"),
    TEASCHOOL("Чайні школи"),
    HIKECAMPMOVIE("Походи, табори, перегляд кінофільмів");

    private final String pres;

    EventType(String pres) {
        this.pres = pres;
    }

    public String getPres() {
        return pres;
    }

    public static EventType defaultType() {
        return COMPETITION;
    }
}
