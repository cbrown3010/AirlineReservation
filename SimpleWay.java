package assignment02;

/**
 * The SimpleWay class inherits the Airline class and overrides the reserving
 * methods to implement the airline's rules.
 * 
 * @author Charlie Cho 18010426
 *
 */

public class SimpleWay extends Airline {

	/**
	 * constructor that takes in the airline name that uses smartline and sets it up
	 * using the super's constructor (airline).
	 * 
	 * @param name
	 * @author 18010426
	 */
	public SimpleWay(String name) {
		super.setAirlineName(name);
	}

	/**
	 * overrides super method to implement simple way rules for first class. It is
	 * used to reserve a first class seat.
	 * 
	 * @param flight   - information about the flight (origin/dest city, dept time,
	 *                 seat plan, flight identifier)
	 * @param seatType - the type of seat (window, aisle, middle)
	 * @return Seat - information about the seat (type, position, whether it is
	 *         first class and whether it is reserved)
	 * @author 18010426
	 */
	@Override
	public Seat reserveFirstClass(Flight flight, SeatType seatType) {
		// obtain seating map from the flight class using get method
		SeatMap seatMap = flight.getSeating();
		/*
		 * check for available seats in the seat map using query method as per the
		 * preset method it will search for the requested seat type first but if it does
		 * not exist will return another seat type.
		 */
		Seat availableSeat = seatMap.queryAvailableFirstClassSeat(seatType);
		// if there are no available seats in first class
		if (availableSeat == null) {
			// look for available seats in economy that is a window seat
			availableSeat = seatMap.queryAvailableEconomySeat(SeatType.WINDOW);
			// if there are no more window seats check for middle seats
			if (availableSeat.getSeatType() == SeatType.AISLE)
				availableSeat = seatMap.queryAvailableEconomySeat(SeatType.MIDDLE);
			if (availableSeat.getSeatType() == SeatType.WINDOW || availableSeat.getSeatType() == SeatType.MIDDLE) {
				// if it is a window or middle. the left OR right seat must be reserved.
				Seat rightSeat = seatMap.getRight(availableSeat);
				Seat leftSeat = seatMap.getLeft(availableSeat);
				if (rightSeat != null && rightSeat.isReserved() == false)
					// if right exists set the right seat to reserved and exit nested if statement
					seatMap.getRight(availableSeat).setReserved(true);
				else if (leftSeat != null && leftSeat.isReserved() == false)
					// if left exists set the left seat to reserved and exit nested if statement
					seatMap.getLeft(availableSeat).setReserved(true);
				else
					return null;
			} else {
				return null;
			}
		}
		// if reservation is success i.e. did not return null, set reserved to true
		availableSeat.setReserved(true);
		return availableSeat;
	}

	/**
	 * overrides super method to implement simple way rules for economy class. It is
	 * used to reserve a economy class seat.
	 * 
	 * @param flight   - information about the flight (origin/dest city, dept time,
	 *                 seat plan, flight identifier)
	 * @param seatType - the type of seat (window, aisle, middle)
	 * @return Seat - information about the seat (type, position, whether it is
	 *         first class and whether it is reserved)
	 * @author 18010426
	 */
	@Override
	public Seat reserveEconomy(Flight flight, SeatType seatType) {
		SeatMap seating = flight.getSeating();
		/*
		 * check for available seats in the seat map using query method. As per the
		 * preset method it will search for the requested seat type first but if it does
		 * not exist will return another seat type.
		 */
		Seat availableSeat = seating.queryAvailableEconomySeat(seatType);
		if (availableSeat == null)
			return null;
		availableSeat.setReserved(true);
		return availableSeat;
	}
}