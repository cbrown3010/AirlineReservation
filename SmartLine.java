package assignment02;

/**
 * The SmartLine class inherits the Airline class and overrides the reserving
 * methods to implement the airline's rules.
 * 
 * @author Charlie Cho 18010426
 *
 */

public class SmartLine extends Airline {

	/**
	 * constructor that takes in the airline name that uses smartline and sets it up
	 * using the super's constructor (airline).
	 * 
	 * @param name
	 * @author 18010426
	 */
	public SmartLine(String name) {
		super.setAirlineName(name);
	}

	/**
	 * overrides super method to implement smartline rules for first class. It is
	 * used to reserve a first class seat.
	 * 
	 * @param flight   - information about the flight (origin/dest city, dept time,
	 *                 seat plan, flight identifier)
	 * @param seatType - the type of seat (window, aisle, middle)
	 * @return Seat - information about the seat (type, position, whether it is
	 *         first class and whether it is reserved)
	 * @author 18010426
	 */
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
			// look for available seats in economy that is a middle seat
			availableSeat = seatMap.queryAvailableEconomySeat(SeatType.MIDDLE);
			// if there are middle seats left
			if (availableSeat.getSeatType() == SeatType.MIDDLE) {
				// the left and right seat is checked
				Seat rightSeat = seatMap.getRight(availableSeat);
				Seat leftSeat = seatMap.getLeft(availableSeat);
				// if both seats exist and is not reserved, set to reserved and return
				if (rightSeat != null && rightSeat.isReserved() == false
						|| leftSeat != null && leftSeat.isReserved() == false) {
					seatMap.getRight(availableSeat).setReserved(true);
					seatMap.getLeft(availableSeat).setReserved(true);
				} else
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
	 * overrides super method to implement smartline rules for economy class. It is
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