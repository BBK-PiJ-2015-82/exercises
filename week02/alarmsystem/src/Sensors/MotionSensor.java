package Sensors;

/**
 * Created by dannymadell on 21/01/2017.
 */
public class MotionSensor implements Sensor, MainsSensor {

    private String location;
    private final String sensorType = "Motion";
    private final String sensorCategory = "Mains";

    @Override
    public boolean isTriggered() {
        return false;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public String getSensorType() {
        return sensorType;
    }

    @Override
    public String getSensorCategory() {
        return sensorCategory;
    }
}
