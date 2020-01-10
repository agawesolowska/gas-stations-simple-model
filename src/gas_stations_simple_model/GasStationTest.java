package gas_stations_simple_model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class GasStationTest {
	// unit tests of class representing a simple model of gas stations

	private GasStation gasStation;

	@Before
	public void setUp() {
		gasStation = new GasStation("BP_ID_255478", "4.98", "5.17", "5.19");
	}

	@Test
	public void testConstructor() {
		assertEquals("BP_ID_255478", gasStation.getName());
		assertEquals(new BigDecimal("4.98"), gasStation.getPriceOfDiesel());
		assertEquals(new BigDecimal("5.17"), gasStation.getPriceOfPetrol95());
		assertEquals(new BigDecimal("5.19"), gasStation.getPriceOfPetrol98());
		assertEquals(0.0, gasStation.getAmountOfDiesel(), 0.0);
		assertEquals(0.0, gasStation.getAmountOfPetrol95(), 0.0);
		assertEquals(0.0, gasStation.getAmountOfPetrol98(), 0.0);
	}

	@Test
	public void testGetPriceOfFuelIfDefault() {
		assertEquals(null, gasStation.getPriceOfFuel(""));
	}

	@Test
	public void testSetPriceOfFuelInCaseOfDiesel() {
		gasStation.setPriceOfFuel("diesel", "5.12");
		assertEquals(new BigDecimal("5.12"), gasStation.getPriceOfDiesel());
		assertEquals(new BigDecimal("5.17"), gasStation.getPriceOfPetrol95());
		assertEquals(new BigDecimal("5.19"), gasStation.getPriceOfPetrol98());
	}

	@Test
	public void testGetAmountOfFuelIfDefault() {
		assertEquals(0.0, gasStation.getAmountOfFuel(""), 0.0);
	}

	@Test
	public void testSetAmountOfFuelInCaseOfBenzyna95() {
		gasStation.setAmountOfFuel("benzyna-95", 87.16);
		assertEquals(0.0, gasStation.getAmountOfDiesel(), 0.0);
		assertEquals(87.16, gasStation.getAmountOfPetrol95(), 0.01);
		assertEquals(0.0, gasStation.getAmountOfPetrol98(), 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFillUpTankWithIllegalArgument() {
		gasStation.fillUpTank("diesel", -14.12);
	}

	@Test
	public void testFillUpAllTanks() {
		gasStation.setAmountOfFuel("diesel", 1014.12);
		gasStation.setAmountOfFuel("benzyna-95", 801.03);
		gasStation.setAmountOfFuel("benzyna-98", 1204.75);
		gasStation.setAmountOfFuel("diesel", -14.12);

		assertEquals(1000.00, gasStation.getAmountOfDiesel(), 0.01);
		assertEquals(801.03, gasStation.getAmountOfPetrol95(), 0.01);
		assertEquals(1204.75, gasStation.getAmountOfPetrol98(), 0.01);
	}

	@Test(expected = DeficiencyOfFuelException.class)
	public void testFillUpCarInCaseOfDeficiencyOfFuel() throws DeficiencyOfFuelException {
		gasStation.fillUpTank("benzyna-98", 14.67);
		gasStation.fillUpCar("benzyna-98", 62.47);
	}

	@Test
	public void testFillUpCarAndCheckPayment() throws DeficiencyOfFuelException {
		gasStation.fillUpTank("diesel", 659.14);
		assertEquals(new BigDecimal("126.99"), gasStation.fillUpCar("diesel", 25.5));
	}

	@Test
	public void testFillUpCarAndCheckAmountOfFuel() throws DeficiencyOfFuelException {
		gasStation.fillUpTank("benzyna-95", 412.16);
		gasStation.fillUpCar("benzyna-95", 16.31);
		assertEquals(395.85, gasStation.getAmountOfPetrol95(), 0.01);
	}

}
