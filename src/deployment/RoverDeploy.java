package deployment;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import rover.Rover;

/**
 * A simple sequential main class to test the rover in its environment.
 * 
 * @author Antoine Stevan
 *
 */
public class RoverDeploy {

	/**
	 * Main method to test the rover in its environment.
	 * 
	 * @param args java arguments for main methods.
	 */
	public static void main(String[] args) {
		Sound.setVolume(1);
		//###################################################################################################################
		//### full mission simulation #######################################################################################
		//###################################################################################################################
		Rover rover = Rover.build(SensorPort.S4, SensorPort.S1,
				                  MotorPort.A, MotorPort.B, MotorPort.C);
		rover.land();
		rover.checkBattery();
		rover.connect_peripherals();
		rover.compute_path();  
//		rover.calibrate_origin();

		rover.test_grabber_antoine();
		Button.waitForAnyPress();
		
		int nb_missions = 3;
		for (int i = 0; i < nb_missions; i++) {
			rover.init_obstacle_detection();
			while (!rover.mission_done()) {
				rover.harvest(rover.explore());
			}
			rover.checkBattery();
			rover.await();
		}
		
		//###################################################################################################################
		//### several tests #################################################################################################
		//###################################################################################################################
//		rover.test_ultrasonic_sensor();
//		rover.test_color_sensor();
//		rover.test_motors();
//		rover.test_navigator();
//		rover.test_navigator_square_antoine();
//		rover.test_navigator_sweep_antoine();
		
//		rover.test_travel_antoine();
//		rover.test_rotate_antoine();
	}
}