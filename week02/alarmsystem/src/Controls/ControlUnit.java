package Controls;

import Announcement.*;
import Sensors.FireSensor;
import Sensors.Sensor;
import Announcement.*;
import java.util.List;

public class ControlUnit {

  private List<Sensor> sensors = null;
  //private List<Sensors.Sensor> triggeredSensor = null;


  public ControlUnit() {}
  public ControlUnit(List<Sensor> sensors) {
    this.sensors = sensors;
  }
  private AnnouncementContext announcementContext = null;


  public void pollSensors() {

    for (Sensor sensor : sensors) {
      if (sensor.isTriggered()) {
        triggered(sensor);
        System.out.println("A " + sensor.getSensorType() + " sensor was triggered at " + sensor.getLocation());
      } else {
        System.out.println("Polled " + sensor.getSensorType() + " at " + sensor.getLocation() + " successfully");
      }
    }
  }

  public void triggered(Sensor sensor) {

    String sensorType = sensor.getSensorType();

    switch (sensorType) {
      case "Fire": new AnnouncementContext(new FireAnnouncement()).doAnnouncement(); break;
      case "Smoke": new AnnouncementContext(new SmokeAnnouncement()).doAnnouncement(); break;
      case "Motion": new AnnouncementContext(new MotionAnnouncement()).doAnnouncement(); break;

    }

  }
}
