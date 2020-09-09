package assignment02;

/**
 * The Seat class contains relevant information about the seat. It stores the
 * position using the seat position class (which contains rows and columns). It
 * stores the seat type using the seat type enumeration class (whether it is
 * window, aisle or middle). Also contains whether it is reserved and whether it
 * is first class.
 * 
 * @author Charlie Cho 18010426
 *
 */

public class Seat {
	private SeatType seatType;
	private boolean isFirstClass;
	private boolean isReserved;
	private SeatPosition seatPosition;

	/**
	 * Basics constructor that takes in seat position, type and whether it is first
	 * class.
	 * 
	 * @param seatPosition - position of seat (row, column)
	 * @param seatType     - type of seat (window, aisle, middle)
	 * @param isFirstClass - whether it is first class (true, false)
	 * 
	 * @author 18010426
	 */
	public Seat(SeatPosition seatPosition, SeatType seatType, boolean isFirstClass) {
		this.seatPosition = seatPosition;
		this.seatType = seatType;
		this.isFirstClass = isFirstClass;
	}

	public Seat(SeatPosition seatPosition) {
		this.seatPosition = seatPosition;
	}

	/**
	 * Get method for the seat type (middle, aisle or window).
	 * 
	 * @return seatType - type of seat (window, aisle, middle)
	 * @author 18010426
	 */
	public SeatType getSeatType() {
		return seatType;
	}

	/**
	 * Get method for whether it is first class (true or false).
	 * 
	 * @return isFirstClass - whether it is first class (true, false)
	 * @author 18010426
	 */
	public boolean isFirstClass() {
		return isFirstClass;
	}

	/**
	 * Get method for whether it is reserved (true or false).
	 * 
	 * @return isReserved - whether it is reserved (true, false)
	 * @author 18010426
	 */
	public boolean isReserved() {
		return isReserved;
	}

	/**
	 * Set method for whether it is reserved (true or false).
	 * 
	 * @param isReserved - whether it is reserved (true, false)
	 * @author 18010426
	 */
	public void setReserved(boolean reserved) {
		this.isReserved = reserved;
	}

	/**
	 * Get method for the seat position (row and column).
	 * 
	 * @return seatPosition - position of seat (row, column)
	 * @author 18010426
	 */
	public SeatPosition getSeatPosition() {
		return seatPosition;
	}

	/**
	 * Returns a longer text description of the seat. In the instructions this
	 * method is called getSeatDescription(). However it is named toDescription() in
	 * the UML diagram.
	 * 
	 * First if statement checks whether it is first class. The next checks whether
	 * it is reserved. The nested if statements determines and outputs the correct
	 * description.
	 * 
	 * @return String - description of seat
	 * @author 18010426
	 */

	public String toDescription() {
		if (isFirstClass) {
			// if first class and reserved
			if (isReserved)
				return "The first class " + seatType + " seat at: " + seatPosition + " is reserved.";
			// if first class and not reserved
			else
				return "The economy class " + seatType + " seat at: " + seatPosition + " is not reserved.";
		} else {
			// if not first class and reserved
			if (isReserved)
				return "The first class " + seatType + " seat at: " + seatPosition + " is reserved.";
			// if not first class and not reserved
			else
				return "The economy class " + seatType + " seat at: " + seatPosition + " is not reserved.";
		}
	}

	/**
	 * set method for seat type
	 * 
	 * @param seatType
	 * @author 18010426
	 */
	public void setSeatType(SeatType seatType) {
		this.seatType = seatType;
	}

	/**
	 * set method for first class
	 * 
	 * @param isFirstClass
	 * @author 18010426
	 */
	public void setFirstClass(boolean isFirstClass) {
		this.isFirstClass = isFirstClass;
	}

	/**
	 * toString method for the Seat class. The first character of type is obtained
	 * (W, A, M) which indicate the seat type. This is set to lower case for non
	 * first class bookings. A X is placed to represent reserved bookings and _ for
	 * non reserved.
	 * 
	 * @return String return description of seat in specific format (i.e. [w X])
	 * @author 18014026
	 */
	public String toString() {
		if (isFirstClass) {
			// if first class and reserved
			if (isReserved)
				return ("[" + seatType.toString().charAt(0) + "X]");
			// if first class and not reserved
			else
				return ("[" + seatType.toString().charAt(0) + "_]");
		} else {
			// if not first class and reserved
			if (isReserved)
				return ("[" + Character.toLowerCase(seatType.toString().charAt(0)) + "X]");
			// if not first class and not reserved
			else
				return ("[" + Character.toLowerCase(seatType.toString().charAt(0)) + "_]");
		}
	}
}
