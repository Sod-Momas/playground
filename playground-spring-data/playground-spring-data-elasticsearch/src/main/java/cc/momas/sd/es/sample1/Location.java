package cc.momas.sd.es.sample1;

import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author Sod-Momas
 */
public class Location {

    @Field
    private int lon;
    @Field
    private int lat;

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

}