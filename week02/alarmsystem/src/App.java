import Controls.ControlUnit;
import Controls.SecurityControlUnit;
import Sensors.FireSensor;
import Sensors.MotionSensor;
import Sensors.Sensor;
import Sensors.SmokeSensor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
  private static final String EXIT = "exit";
  private static final String POLL = "poll";
  
  public static void main(String[] args) throws IOException {

    List<Sensor> sensors = new ArrayList<Sensor>();
    sensors.add(new FireSensor());
    sensors.add(new SmokeSensor());
    List<Sensor> mainsSensors = new ArrayList<Sensor>();
    mainsSensors.add(new MotionSensor());
    mainsSensors.add(new MotionSensor());

    ControlUnit controlUnit = new ControlUnit(sensors);
    SecurityControlUnit securityControlUnit = new SecurityControlUnit(mainsSensors);

    Scanner scanner = new Scanner(System.in);
    String input = "";
    while (!input.equals(EXIT)) {
      System.out.println("Type \"poll\" to poll all sensors once or \"exit\" to exit");
      input = scanner.nextLine();
      if (input.equals(POLL)) {
        controlUnit.pollSensors();
        securityControlUnit.pollSensors();

      }
    }
  }
}
