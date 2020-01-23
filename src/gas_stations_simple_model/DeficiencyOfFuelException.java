package gas_stations_simple_model;

/**
 * Exception class in case of deficiency of fuel in a tank.
 * 
 * @author Aga
 *
 */
public class DeficiencyOfFuelException extends Exception {

	public DeficiencyOfFuelException() {

	}

	public DeficiencyOfFuelException(String message) {
		super(message);
	}

}
