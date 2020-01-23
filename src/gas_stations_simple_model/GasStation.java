package gas_stations_simple_model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Class representing a simple model of gas stations.
 * 
 * @author Aga
 * 
 */
@Getter
@EqualsAndHashCode
public class GasStation {

	private String name;
	private BigDecimal priceOfDiesel, priceOfPetrol95, priceOfPetrol98;
	private double amountOfDiesel, amountOfPetrol95, amountOfPetrol98;

	public GasStation(String name, BigDecimal priceOfDiesel, BigDecimal priceOfPetrol95, BigDecimal priceOfPetrol98) {
		this.name = name;
		this.priceOfDiesel = priceOfDiesel;
		this.priceOfPetrol95 = priceOfPetrol95;
		this.priceOfPetrol98 = priceOfPetrol98;
		this.amountOfDiesel = 0.0;
		this.amountOfPetrol95 = 0.0;
		this.amountOfPetrol98 = 0.0;
	}

	/**
	 * This constructor creates an object based on parameters given by user.
	 */
	public GasStation(String name, String priceOfDiesel, String priceOfPetrol95, String priceOfPetrol98) {
		this(name, new BigDecimal(priceOfDiesel), new BigDecimal(priceOfPetrol95), new BigDecimal(priceOfPetrol98));
		this.amountOfDiesel = 0.0;
		this.amountOfPetrol95 = 0.0;
		this.amountOfPetrol98 = 0.0;
	}

	public BigDecimal getPriceOfFuel(String typeOfFuel) {
		switch (typeOfFuel) {
		case "diesel":
			return getPriceOfDiesel();
		case "benzyna-95":
			return getPriceOfPetrol95();
		case "benzyna-98":
			return getPriceOfPetrol98();
		default:
			return null;
		}
	}

	public void setPriceOfFuel(String typeOfFuel, String newPrice) {
		switch (typeOfFuel) {
		case "diesel":
			this.priceOfDiesel = new BigDecimal(newPrice);
			break;
		case "benzyna-95":
			this.priceOfPetrol95 = new BigDecimal(newPrice);
			break;
		case "benzyna-98":
			this.priceOfPetrol98 = new BigDecimal(newPrice);
			break;
		}
	}

	public double getAmountOfFuel(String typeOfFuel) {
		switch (typeOfFuel) {
		case "diesel":
			return getAmountOfDiesel();
		case "benzyna-95":
			return getAmountOfPetrol95();
		case "benzyna-98":
			return getAmountOfPetrol98();
		default:
			return 0.0;
		}
	}

	public void setAmountOfFuel(String typeOfFuel, double quantity) {
		switch (typeOfFuel) {
		case "diesel":
			this.amountOfDiesel += quantity;
			break;
		case "benzyna-95":
			this.amountOfPetrol95 += quantity;
			break;
		case "benzyna-98":
			this.amountOfPetrol98 += quantity;
			break;
		}
	}

	/**
	 * This method fills up fuel in a tank.
	 * 
	 * @param typeOfFuel A type of fuel to fill the tank with.
	 * @param quantity   An amount of fuel to fill the tank with.
	 */
	public void fillUpTank(String typeOfFuel, double quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException();
		}
		setAmountOfFuel(typeOfFuel, quantity);
	}

	/**
	 * This method refuels a car.
	 * 
	 * @param typeOfFuel A type of fuel to fill up a car.
	 * @param quantity   An amount of fuel to fill up a car.
	 * @return An amount to be paid for the refueled car.
	 * @throws DeficiencyOfFuelException Appears in case of deficiency of fuel in a tank.
	 */
	public BigDecimal fillUpCar(String typeOfFuel, double quantity) throws DeficiencyOfFuelException {
		if (this.getAmountOfFuel(typeOfFuel) < quantity) {
			throw new DeficiencyOfFuelException("Niewystarczająca ilość żądanego paliwa.");
		}
		setAmountOfFuel(typeOfFuel, -quantity);
		return this.getPriceOfFuel(typeOfFuel).multiply(BigDecimal.valueOf(quantity).setScale(2, RoundingMode.HALF_UP));
	}

	@Override
	public String toString() {
		return "Stacja paliw - " + name + " - sprzedaje paliwo typu diesel w cenie: " + priceOfDiesel
				+ "zł, benzyne-95: " + priceOfPetrol95 + "zł, oraz benzyne-98: " + priceOfPetrol98 + "zł.";
	}

}
