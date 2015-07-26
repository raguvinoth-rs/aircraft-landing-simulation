package inputs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ComputeSimulationState {

	private static int timeUntilLanding;
	private static int speed;
	private static int speedIncrement;
	private static int altitude;
	private static int altitudeIncrement;
	private static Position gearPosition;
	private static Position selectedGearPosition;
	private static String throttleCmd;
	private static String elevonCmd;
	private static boolean airBrakeWarningOn; 
	private static boolean gearOverrideWarningOn; 
	private static boolean gearNotDownAlarmOn;
	private static boolean gearAirSpeedAlarmOn;
	private static boolean gearUpCommand;
	private static boolean silenceAlarms;
	private static int pass=0;
	private static int fail=0;
	private static int i=1;

	public static void computeSimulationState(int altitude,int speed,int timeUntilLanding,Position currentGearPosition,
			String throttleCmd,String elevonCmd){
		if (throttleCmd == "+")
			ComputeSimulationState.speed=speed+10;
		else
		{
			if (throttleCmd== "-")
				ComputeSimulationState.speed=speed-10;
			else
				ComputeSimulationState.speed = speed;
		}
		if (elevonCmd== "+")
			ComputeSimulationState.altitude=altitude+20;
		else
		{
			if (elevonCmd =="-")
				ComputeSimulationState.altitude=altitude-20;
			else
				ComputeSimulationState.altitude=altitude;
		}
		ComputeSimulationState.airBrakeWarningOn = (ComputeSimulationState.speed >= 250) && (timeUntilLanding < 60);
		ComputeSimulationState.gearOverrideWarningOn = (currentGearPosition == Position.Down) && (ComputeSimulationState.speed>400);
		ComputeSimulationState.gearNotDownAlarmOn = (currentGearPosition == Position.Up) && ((timeUntilLanding <=120) || (ComputeSimulationState.altitude <1000));
		ComputeSimulationState.gearAirSpeedAlarmOn = (currentGearPosition== Position.Down ) && (ComputeSimulationState.speed>300);
		if (!gearOverrideWarningOn)
			currentGearPosition = selectedGearPosition;
	}

/**	public static void main(String[] args) {
		String csvFile = "1.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] inputs = line.split(cvsSplitBy);

				speed = Integer.parseInt(inputs[0]);
				if(inputs[1].equals("Y"))
					inputs[1] = "Down";
				else
					inputs[1] = "Up";
				gearPosition = Position.valueOf(inputs[1]);
				altitude = Integer.parseInt(inputs[2]);
				timeUntilLanding = Integer.parseInt(inputs[3]); 
				ComputeSimulationState.computeSimulationState(altitude, speed, timeUntilLanding, gearPosition,null,null);
				airBrakeWarningOn = ComputeSimulationState.isAirBrakeWarningOn(); 
				gearOverrideWarningOn = ComputeSimulationState.isGearOverrideWarningOn();
				gearNotDownAlarmOn = ComputeSimulationState.isGearNotDownAlarmOn();
				gearAirSpeedAlarmOn = ComputeSimulationState.isGearAirSpeedAlarmOn();
				gearUpCommand = ComputeSimulationState.isGearOverrideWarningOn();
				if(ComputeSimulationState.gearAirSpeedAlarmOn || ComputeSimulationState.gearNotDownAlarmOn)
					silenceAlarms = true;
				else
					silenceAlarms = false;
				if(gearNotDownAlarmOn == Boolean.valueOf(inputs[4]) && gearAirSpeedAlarmOn == Boolean.valueOf(inputs[5]) && 
						airBrakeWarningOn == Boolean.valueOf(inputs[6]) && gearOverrideWarningOn == Boolean.valueOf(inputs[7])
						&& gearUpCommand == Boolean.valueOf(inputs[8])&& silenceAlarms == Boolean.valueOf(inputs[9]))
				{
					pass++;
					System.out.println("Test Case No: "+i+" passed.");
				}
				else
				{
					fail++;
					System.out.println("Test Case No: "+i+" failed.");
				}
				i++;
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		System.out.println("Total No. of Test Cases passed: "+pass+" and failed: "+fail+".");

		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	**/
	public static int getTimeUntilLanding() {
		return timeUntilLanding;
	}

	public static void setTimeUntilLanding(int timeUntilLanding) {
		ComputeSimulationState.timeUntilLanding = timeUntilLanding;
	}

	public static int getSpeed() {
		return speed;
	}

	public static void setSpeed(int speed) {
		ComputeSimulationState.speed = speed;
	}

	public static int getSpeedIncrement() {
		return speedIncrement;
	}

	public static void setSpeedIncrement(int speedIncrement) {
		ComputeSimulationState.speedIncrement = speedIncrement;
	}

	public static int getAltitude() {
		return altitude;
	}

	public static void setAltitude(int altitude) {
		ComputeSimulationState.altitude = altitude;
	}

	public static int getAltitudeIncrement() {
		return altitudeIncrement;
	}

	public static void setAltitudeIncrement(int altitudeIncrement) {
		ComputeSimulationState.altitudeIncrement = altitudeIncrement;
	}

	public static Position getGearPosition() {
		return gearPosition;
	}

	public static void setGearPosition(Position gearPosition) {
		ComputeSimulationState.gearPosition = gearPosition;
	}

	public static Position getSelectedGearPosition() {
		return selectedGearPosition;
	}

	public static void setSelectedGearPosition(Position selectedGearPosition) {
		ComputeSimulationState.selectedGearPosition = selectedGearPosition;
	}

	public static String getThrottleCmd() {
		return throttleCmd;
	}

	public static void setThrottleCmd(String throttleCmd) {
		ComputeSimulationState.throttleCmd = throttleCmd;
	}

	public static String getElevonCmd() {
		return elevonCmd;
	}

	public static void setElevonCmd(String elevonCmd) {
		ComputeSimulationState.elevonCmd = elevonCmd;
	}

	public static boolean isAirBrakeWarningOn() {
		return airBrakeWarningOn;
	}

	public static void setAirBrakeWarningOn(boolean airBrakeWarningOn) {
		ComputeSimulationState.airBrakeWarningOn = airBrakeWarningOn;
	}

	public static boolean isGearOverrideWarningOn() {
		return gearOverrideWarningOn;
	}

	public static void setGearOverrideWarningOn(boolean gearOverrideWarningOn) {
		ComputeSimulationState.gearOverrideWarningOn = gearOverrideWarningOn;
	}

	public static boolean isGearNotDownAlarmOn() {
		return gearNotDownAlarmOn;
	}

	public static void setGearNotDownAlarmOn(boolean gearNotDownAlarmOn) {
		ComputeSimulationState.gearNotDownAlarmOn = gearNotDownAlarmOn;
	}

	public static boolean isGearAirSpeedAlarmOn() {
		return gearAirSpeedAlarmOn;
	}

	public static void setGearAirSpeedAlarmOn(boolean gearAirSpeedAlarmOn) {
		ComputeSimulationState.gearAirSpeedAlarmOn = gearAirSpeedAlarmOn;
	}
}