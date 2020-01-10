package gas_stations_simple_model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GasStation {
	// class representing a simple model of gas stations

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

	public void fillUpTank(String typeOfFuel, double quantity) {
		if (quantity < 0) {
			throw new IllegalArgumentException();
		}
		setAmountOfFuel(typeOfFuel, quantity);
	}
	
	public BigDecimal fillUpCar(String typeOfFuel, double quantity) throws DeficiencyOfFuelException {
		if (this.getAmountOfFuel(typeOfFuel) < quantity) {
			throw new DeficiencyOfFuelException("Niewystarczająca ilość żądanego paliwa.");
		}
		setAmountOfFuel(typeOfFuel, -quantity);
		return this.getPriceOfFuel(typeOfFuel).multiply(BigDecimal.valueOf(quantity).setScale(2, RoundingMode.HALF_UP));
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPriceOfDiesel() {
		return priceOfDiesel;
	}

	public BigDecimal getPriceOfPetrol95() {
		return priceOfPetrol95;
	}

	public BigDecimal getPriceOfPetrol98() {
		return priceOfPetrol98;
	}

	public double getAmountOfDiesel() {
		return amountOfDiesel;
	}

	public double getAmountOfPetrol95() {
		return amountOfPetrol95;
	}

	public double getAmountOfPetrol98() {
		return amountOfPetrol98;
	}

	@Override
	public String toString() {
		return "Stacja paliw - " + name + " - sprzedaje paliwo typu diesel w cenie: " + priceOfDiesel
				+ "zł, benzyne-95: " + priceOfPetrol95 + "zł, oraz benzyne-98: " + priceOfPetrol98 + "zł.";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amountOfDiesel);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(amountOfPetrol95);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(amountOfPetrol98);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priceOfDiesel == null) ? 0 : priceOfDiesel.hashCode());
		result = prime * result + ((priceOfPetrol95 == null) ? 0 : priceOfPetrol95.hashCode());
		result = prime * result + ((priceOfPetrol98 == null) ? 0 : priceOfPetrol98.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GasStation other = (GasStation) obj;
		if (Double.doubleToLongBits(amountOfDiesel) != Double.doubleToLongBits(other.amountOfDiesel))
			return false;
		if (Double.doubleToLongBits(amountOfPetrol95) != Double.doubleToLongBits(other.amountOfPetrol95))
			return false;
		if (Double.doubleToLongBits(amountOfPetrol98) != Double.doubleToLongBits(other.amountOfPetrol98))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priceOfDiesel == null) {
			if (other.priceOfDiesel != null)
				return false;
		} else if (!priceOfDiesel.equals(other.priceOfDiesel))
			return false;
		if (priceOfPetrol95 == null) {
			if (other.priceOfPetrol95 != null)
				return false;
		} else if (!priceOfPetrol95.equals(other.priceOfPetrol95))
			return false;
		if (priceOfPetrol98 == null) {
			if (other.priceOfPetrol98 != null)
				return false;
		} else if (!priceOfPetrol98.equals(other.priceOfPetrol98))
			return false;
		return true;
	}

}
