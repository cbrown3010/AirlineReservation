package assignment02;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The AirlineReservaionApplication class tests the functionality of the
 * reserving system. It also includes two static methods setup() and update().
 * The setup function reads data from a text file and populates the available
 * flights from the data. The update class updates the information with the
 * booking done in the main method.
 * 
 * @author Charlie Cho 18010426
 *
 */

public class AirlineReservationApplication {
	// flights and airline keep track of available flights and airlines read from
	// text
	private static ArrayList<Flight> flights;
	private static ArrayList<Airline> airline;
	// the seats array list keeps track of current bookings for update
	private static ArrayList<String> seats;

	// static int of chosen flight used in several methods
	private static int chosenFlight = 1;

	/**
	 * the setup method reads the flight information from the text file
	 * "CUSTOMflights" and populates the array list flights.
	 * 
	 * @throws NumberFormatException - integer parsing problem
	 * @throws IOException           - input/output problem
	 * @author 18010426
	 */
	public static void setup() throws NumberFormatException, IOException {
		flights = new ArrayList<Flight>();
		airline = new ArrayList<Airline>();
		seats = new ArrayList<String>();
		// read flights text file using file reader and scanner
		Scanner scan = new Scanner(new FileReader("CUSTOMflights.txt"));
		// the first line indicates the number of flights
		int numOfFlights = Integer.parseInt(scan.nextLine());
		/*
		 * the scanner reads until the end of the text file line by line. It makes sure
		 * that is is relevant flight information by checking the first two lines which
		 * should be @Flight and @F_info. Then it determines whether it is an airbus or
		 * boeing and populates the flights array list with correct information.
		 * 
		 */
		while (scan.hasNextLine()) {
			for (int i = 0; i < numOfFlights; i++) {
				if (scan.nextLine().equals("@Flight")) {
					if (scan.nextLine().equals("@F_info")) {
						List<String> flightInfo = Arrays.asList(scan.nextLine().split(","));
						if (flightInfo.get(4).equals("Airbus"))
							flights.add(new Flight(flightInfo.get(0), flightInfo.get(1), flightInfo.get(2),
									flightInfo.get(3), new AirBusSeatMap()));
						if (flightInfo.get(4).equals("Boeing"))
							flights.add(new Flight(flightInfo.get(0), flightInfo.get(1), flightInfo.get(2),
									flightInfo.get(3), new BoeingSeatMap()));
						// preload the pre existing reservations
						if (scan.nextLine().equals("@F_R_Seats")) {
							String string;
							if (!(string = scan.nextLine()).isEmpty()) {
								List<String> reservationInfo = Arrays.asList(string.split(","));
								for (String resv : reservationInfo) {
									seats.add(resv);
									int row = resv.charAt(0) - 49;
									int column = resv.charAt(2) - 65;
									flights.get(0).getSeating().getSeatsArray()[row][column].setReserved(true);
								}
							}
						}
					}
				}
			}
		}
		airline.add(new SimpleWay("Simpleway"));
		airline.add(new SmartLine("SmartLine"));
		scan.close();

	}

	/**
	 * the update method updates the text file. It updates the new reservations.
	 * 
	 * @throws IOException - input/output problem
	 * @author 18010426
	 */
	public static void update() throws IOException {
		// use scanner to read the original flight information
		Scanner s = new Scanner(new File("CUSTOMflights.txt"));
		ArrayList<String> temporaryList = new ArrayList<String>();
		// read through original file and add to temporary list
		while (s.hasNext()) {
			temporaryList.add(s.nextLine());
		}
		s.close();
		// create a primitive string array and populate with the seats array
		String[] seatArray = seats.toArray(new String[0]);
		String reservedSeat = "";
		// iterate through the array list and add to the reservedSeat string
		for (int i = 0; i < seatArray.length; i++) {
			reservedSeat += seatArray[i];
			// this if statement is to prevent adding a comma on the last reservation
			if (i < seatArray.length - 1)
				reservedSeat += ",";
		}
		// writing the reservations for the flight used
		temporaryList.set(5 + ((chosenFlight - 1) * 6), reservedSeat);
		// create new file writer to update updated informations to the text file
		FileWriter writer = new FileWriter("CUSTOMflights.txt");
		for (String str : temporaryList) {
			// iterate through the list and write line by line
			writer.write(str + System.lineSeparator());
		}
		writer.close();
	}

	/**
	 * The main method that tests the functionalities of the program. It utilizes
	 * the static methods setup and update to read the textfile for flight
	 * information and store it in another.
	 * 
	 * @param args
	 * @author 18010426
	 */
	public static void main(String[] args) {

		// standard error handling, catch the errors setup and update throw
		try {
			setup();
		} catch (NumberFormatException e) {
			System.err.println("Problem with the number formats " + e);
		} catch (IOException e) {
			System.err.println("Problem with input outputs " + e);
		}

		Scanner scan = new Scanner(System.in);
		// objects used to determine the selected airline and flight
		Airline selectedAirline = null;
		Flight selectedFlight = null;

		// booleans used to control the menu loops
		boolean airlineMenu = true;
		boolean flightMenu = true;
		boolean bookingMenu = true;
		boolean classMenu = true;
		boolean seatMenu = true;

		// variables determining the user choice for first class and seat type
		SeatType seatType = null;
		boolean isFirstClass = true;

		// indexes used to determine the user input within the menu
		int chosenAirline = 0;
		int chosenClass = 0;
		int chosenSeatType = 0;

		System.out.println("---Welcome to Airline Reservation Application---");
		System.out.println("Which airline would you like to travel with?");
		for (int i = 0; i < airline.size(); i++)
			System.out.println(i + 1 + ": " + airline.get(i));

		// airline menu loop allows user to input desired airline
		while (airlineMenu) {
			try {
				chosenAirline = scan.nextInt();
			} catch (InputMismatchException inputMismatchException) {
				System.out.println("Please choose 1 or 2");
				System.err.println("Exceptional event: " + inputMismatchException);
				scan.next();
			}

			if (chosenAirline == 1) {
				selectedAirline = airline.get(0);
				airlineMenu = false;
			} else if (chosenAirline == 2) {
				selectedAirline = airline.get(1);
				airlineMenu = false;
			} else {
				System.out.println("Please choose 1 or 2");
			}
		}
		System.out.println("Welcome to the " + selectedAirline.getAirlineName() + " Airline reservation system");
		System.out.println("Which flight would you like to reserve a seat on?(choose a number)");
		for (int i = 0; i < flights.size(); i++) {
			System.out.println((i + 1) + ": " + flights.get(i));
		}

		// flight menu allows the user to select desired flight
		while (flightMenu) {
			try {
				chosenFlight = scan.nextInt();
			} catch (InputMismatchException inputMismatchException) {
				System.out.println("Please choose a valid number");
				System.err.println("Exceptional event: " + inputMismatchException);
				scan.next();
			}
			selectedFlight = flights.get(chosenFlight - 1);
			flightMenu = false;
		}
		// the booking menu is a loop that will allow continuous reservations until
		// exited.
		while (bookingMenu) {
			// the class loop will allow user to input desired class
			while (classMenu) {
				try {
					System.out.println("Booking seats for " + selectedFlight);
					System.out.println(selectedFlight.getSeating());
					System.out.println("1. Reserve First Class");
					System.out.println("2. Reserve Economy Class");
					System.out.println("3. Show Seating Map");
					System.out.println("4. Quit");
					chosenClass = scan.nextInt();
				} catch (InputMismatchException inputMismatchException) {
					System.out.println("Please choose 1 or 2");
					System.err.println("Exceptional event: " + inputMismatchException);
					scan.next();
				}

				if (chosenClass == 1) {
					isFirstClass = true;
					classMenu = false;
				} else if (chosenClass == 2) {
					isFirstClass = false;
					classMenu = false;
				} else if (chosenClass == 3) {
					System.out.println(selectedFlight.getSeating());
				} else if (chosenClass == 4) {
					// if quit is chosen, set all loops to false and exit
					classMenu = false;
					seatMenu = false;
					bookingMenu = false;
					System.exit(0);
					break;
				} else {
					System.out.println("Please choose 1, 2, 3 or 4");
				}
			}
			// the seat loop allows the user to choose desired seat type
			while (seatMenu) {
				try {
					System.out.println("Which seat type?");
					System.out.println("1. WINDOW");
					System.out.println("2. AISLE");
					System.out.println("3. MIDDLE");
					chosenSeatType = scan.nextInt();
				} catch (InputMismatchException inputMismatchException) {
					System.out.println("Please choose 1 or 2");
					System.err.println("Exceptional event: " + inputMismatchException);
					scan.next();
				}

				if (chosenSeatType == 1) {
					seatType = SeatType.WINDOW;
					seatMenu = false;
				} else if (chosenSeatType == 2) {
					seatType = SeatType.AISLE;
					seatMenu = false;
				} else if (chosenSeatType == 3) {
					seatType = SeatType.MIDDLE;
					seatMenu = false;
				} else {
					System.out.println("Please choose 1, 2, or 3");
				}
			}
			Seat chosenSeat;
			if (bookingMenu = true) {
				// depending on desired class, execute relevant reservation method
				if (isFirstClass) {
					chosenSeat = selectedAirline.reserveFirstClass(selectedFlight, seatType);
				} else {
					chosenSeat = selectedAirline.reserveEconomy(selectedFlight, seatType);
				}

				if (chosenSeat == null) {
					System.out.println("Booking was unavailable");
				} else {
					System.out.println("Seat reservation:" + chosenSeat.toString());
				}
				// add reserved seat to static array for updating
				seats.add(chosenSeat.getSeatPosition().toString());
				// re enable menu booleans to restart loop
				classMenu = true;
				seatMenu = true;
				// update
				try {
					update();
				} catch (IOException e) {
					System.err.println("Problem with input outputs " + e);
				}
			}
		}
		scan.close();
	}
}
