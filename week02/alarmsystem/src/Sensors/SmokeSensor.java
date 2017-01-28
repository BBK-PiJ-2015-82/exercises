package Sensors;

public class SmokeSensor implements BatterySensor, Sensor {

  private Double batteryPercentage = 100.0;
  private String location;
  private final String sensorType = "Smoke";
  private final String sensorCategory = "Battery";

  @Override
  public boolean isTriggered() {

    batteryPercentage = batteryPercentage * 0.8;
    double random = Math.random();
    return (random < 0.1);
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
  public double getBatteryPercentage() {
    return batteryPercentage;
  }

  @Override
  public String getSensorCategory() {return sensorCategory;}
}
