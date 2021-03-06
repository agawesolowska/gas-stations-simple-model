package gas_stations_simple_model;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

/**
 * Simple gas station app.
 * 
 * @author Aga
 *
 */
public class GasStationApp {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Ustaw cennik nowej stacji paliw.\nPodaj nazwe stacji:\n>");
		String nameOfNewGasStation = sc.nextLine();

		System.out.println("Podaj cene diesela, benzyny-95 i benzyny-98:\n>");
		String dieselPriceAtNewGasStation = sc.nextLine();
		String petrol95PriceAtNewGasStation = sc.nextLine();
		String petrol98PriceAtNewGasStation = sc.nextLine();

		GasStation newGasStation = new GasStation(nameOfNewGasStation, dieselPriceAtNewGasStation,
				petrol95PriceAtNewGasStation, petrol98PriceAtNewGasStation);
		System.out.println(newGasStation);

		newGasStation.fillUpTank("diesel", 650.00);
		newGasStation.fillUpTank("benzyna-98", 475.00);

		System.out.println("Dla jakiego rodzaju paliwa obliczyć kwote do zapłaty?\n>");
		String typeOfFuelToFillUpCar = sc.nextLine();
		System.out.println("Ile litrów zatankował klient?\n>");
		double amountOfFuelToFillUpCar = sc.nextDouble();

		try {
			BigDecimal amountToPay = newGasStation.fillUpCar(typeOfFuelToFillUpCar, amountOfFuelToFillUpCar);
			System.out.printf("Przy kasie zapłaci: %.2f zł.", amountToPay);
		} catch (DeficiencyOfFuelException e) {
			e.printStackTrace();
		}

		sc.close();

	}

}
