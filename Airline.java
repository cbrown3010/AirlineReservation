package assignment02;

/**
 * The Airline class is an abstract class that contains the airline name as well
 * as methods needed to reserve flights. These methods will be inherited by the
 * SimpleWay and SmartLine classes.
 * 
 * @author Charlie Cho 18010426
 *
 */

public abstract class Airline {

	private String airlineName;

	/**
	 * abstract class that must be used and overridden by the classes that inherit.
	 * It is used to reserve a first class seat.
	 * 
	 * @param flight   - information about the flight (origin/dest city, dept time,
	 *                 seat plan, flight identifier)
	 * @param seatType - the type of seat (window, aisle, middle)
	 * @return Seat - information about the seat (type, position, whether it is
	 *         first class and whether it is reserved)
	 * @author 18010426
	 */
	abstract public Seat reserveFirstClass(Flight flight, SeatType seatType);

	/**
	 * abstract class that must be used and overridden by the classes that inherit.
	 * It is used to reserve an economy seat.
	 * 
	 * @param flight   - information about the flight (origin/dest city, dept time,
	 *                 seat plan, flight identifier)
	 * @param seatType - the type of seat (window, aisle, middle)
	 * @return Seat - information about the seat (type, position, whether it is
	 *         first class and whether it is reserved)
	 * @author 18010426
	 */

	abstract public Seat reserveEconomy(Flight flight, SeatType seatType);

	/**
	 * get method for airline name
	 * 
	 * @return airlineName - name of the airline
	 * @author 18010426
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * set method for airline name
	 * 
	 * @param airlineName - name of the airline
	 * @author 18010426
	 */
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	/**
	 * toString method that details the name of the airline
	 */
	public String toString() {
		return airlineName;
	}
}