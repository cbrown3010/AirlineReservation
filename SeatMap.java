package assignment02;

/**
 * The seatmap abstract class contains the structure for seat maps, which is
 * implemented by the airline and boeing classes. The toString method draws the
 * seat map in a string format. The class also contains methods used to check
 * whether a specific seat is available.
 * 
 * @author Charlie Cho 18010426
 *
 */
public abstract class SeatMap {
	protected Seat[][] seats;
	protected int nRows;
	protected int nColumns;
	protected int nFirstClassRows;

	/**
	 * abstract method used in the children classes
	 * 
	 * @author 18010426
	 */
	abstract protected void initializeSeatMap();

	/**
	 * returns the last column by adding 65 (as A is 65 in ASCII table) then casting
	 * as a character type.
	 * 
	 * @return char - the last column
	 * @author 18010426
	 */
	public char lastSeatColumn() { // last column
		return (char) (nColumns + 65);
	}

	/**
	 * returns last row.
	 * 
	 * @return int - the last row
	 * @author 18010426
	 */
	public int lastSeatRow() { // last row
		return nRows;
	}

	/**
	 * returns the seat in the specified row and position. As the column is a
	 * character type is is casted as an integer (after subtracting 65 as per the
	 * ASCII table)
	 * 
	 * @param seatRow
	 * @param seatPosition
	 * @return Seat - the seat in the row and position
	 * @author 18010426
	 */
	public Seat getSeat(int seatRow, char seatColumn) {
		return seats[seatRow][(int) seatColumn - 65];
	}

	/**
	 * returns the seat left of the seat given. The row and columns are obtained by
	 * using the get methods in relevant classes. Returns null of the column is less
	 * than 1, i.e. the seat does not exist.
	 * 
	 * @param seat - the original seat we are examining
	 * @return seat - the seat left of the original seat
	 * @author 18010426
	 */
	public Seat getLeft(Seat seat) {
		int row = seat.getSeatPosition().getRow();
		// must cast column to integer from character
		int col = (int) seat.getSeatPosition().getColumn() - 65;
		// check if seat exists
		if (col <= 0)
			return null;
		else
			// -1 as it is one seat left
			return seats[row][col - 1];
	}

	/**
	 * returns the seat right of the seat given. The row and columns are obtained by
	 * using the get methods in relevant classes. Returns null of the column is less
	 * than 1, i.e. the seat does not exist.
	 * 
	 * @param seat - the original seat we are examining
	 * @return seat - the seat right of the original seat
	 * @author 18010426
	 */
	public Seat getRight(Seat seat) {
		int row = seat.getSeatPosition().getRow();
		// must cast column to integer from character
		int col = (int) seat.getSeatPosition().getColumn() - 65;
		// check if seat exists
		if (col >= nColumns - 1)
			return null;
		else
			// +1 as it is one seat right
			return seats[row][col + 1];
	}

	/**
	 * toString method that represents the seat map in text format. The labels A~END
	 * is created by using a loop through the number of columns and incrementing the
	 * ASCII character. These are inserted into an empty string. Then the seats and
	 * inserted by first looping through the number of rows and then the number of
	 * columns to populate the data.
	 * 
	 * @author 18010426
	 */
	public String toString() {
		// empty String that will be returned
		String textMap = "";
		// column labels starting from A
		char columnLabel = 'A';
		// loop through the available columns and append column labels with spaces in
		// between
		for (int i = 0; i < nColumns; i++) {
			textMap += "   " + columnLabel++;
		}
		// next line
		textMap += "\n";
		// loop through the available rows
		for (int j = 0; j < nRows; ++j) {
			// append row labels (which is the index j)
			textMap += j + 1;
			// loop through available columns
			for (int k = 0; k < nColumns; k++) {
				// populate data with seats
				textMap += seats[j][k];
			}
			textMap += "\n";
		}
		return textMap;
	}

	/**
	 * method used to check whether a specific seat is available in the economy
	 * class. two loops iterate through the rows and columns for the relevant
	 * economy class seats. A seat is only returned if it is not reserved, otherwise
	 * null is returned. If the specific seat type is available, that is returned.
	 * 
	 * @param requestedSeat - seat type that is requested
	 * @return outputSeat - seat that is returned. Null if not available. A
	 *         matchedSeat is returned if the specifically requested type is found
	 * @author 18010426
	 */
	public Seat queryAvailableEconomySeat(SeatType requestedSeat) {
		// initialize to null as if matching seat does not exist, null will be returned
		Seat outputSeat = null;
		// loop through rows and columns and find matching seat type and check whether
		// it is reserved, skipping the first class rows.
		for (int i = nFirstClassRows; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				// any seat within the economy class
				Seat matchedSeat = seats[i][j];
				// return only if the seat is not reserved
				if (!matchedSeat.isReserved()) {
					outputSeat = matchedSeat;
					// within the loop if the requested type of seat is found,
					// return that specific matching seat.
					if (matchedSeat.getSeatType() == requestedSeat) {
						return matchedSeat;
					}
				}
			}
		}

		return outputSeat;
	}

	/**
	 * method used to check whether a specific seat is available in the business
	 * class. two loops iterate through the rows and columns for the relevant
	 * business class seats. A seat is only returned if it is not reserved,
	 * otherwise null is returned. If the specific seat type is available, that is
	 * returned.
	 * 
	 * @param requestedSeat - seat type that is requested
	 * @return outputSeat - seat that is returned. Null if not available. A
	 *         matchedSeat is returned if the specifically requested type is found
	 * @author 18010426
	 */
	public Seat queryAvailableFirstClassSeat(SeatType requestedSeat) {
		// initialize to null as if matching seat does not exist, null will be returned
		Seat outputSeat = null;
		// loop through rows and columns and find matching seat type and check whether
		// it is reserved, only for first class rows.
		for (int i = 0; i < nFirstClassRows; i++) {
			for (int j = 0; j < nColumns; j++) {
				// any seat within first class
				Seat matchedSeat = seats[i][j];
				// return only if the seat is not reserved
				if (!matchedSeat.isReserved()) {
					outputSeat = matchedSeat;
					// within the loop if the requested type of seat is found,
					// return that specific matching seat.
					if (matchedSeat.getSeatType() == requestedSeat) {
						return matchedSeat;
					}
				}
			}
		}

		return outputSeat;
	}

	/**
	 * Get method for the seat array
	 * 
	 * @return Seat[][] - seat array
	 * @author 18010426
	 */

	public Seat[][] getSeatsArray() {
		return seats;
	}

	/**
	 * Get method for the number of rows
	 * 
	 * @return nRows - number of rows
	 * @author 18010426
	 */
	public int getNRows() {
		return nRows;
	}

	/**
	 * Get method for the number of columns
	 * 
	 * @return nColumns - number of columns
	 * @author 18010426
	 */
	public int getNColumns() {
		return nColumns;
	}

	/**
	 * Get method for the number of first class rows
	 * 
	 * @return nFirstClassrows - number of first class rows
	 * @author 18010426
	 */
	public int getNFirstClassRows() {
		return nFirstClassRows;
	}
}