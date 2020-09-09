package assignment02;

/**
 * The SeatPosition class returns the row and column of the seat.
 * 
 * @author Charlie Cho 18010426
 *
 */
public class SeatPosition {
	private int row;
	private char column;

	/**
	 * Constructor that takes in the row and column.
	 * 
	 * @param column - column of seat
	 * @param row    - row of seat
	 * @author 18010426
	 */
	public SeatPosition(int row, char column) {
		this.column = column;
		this.row = row;
	}

	/**
	 * Get method for row.
	 * 
	 * @return row - row of seat
	 * @author 18010426
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get method for column.
	 * 
	 * @return column - column of seat
	 */
	public char getColumn() {
		return column;
	}

	/**
	 * toString method for SeatPosition class. Not stated in assignment details but
	 * is used for toDescription/getSeatDescription in Seat Class.
	 * 
	 * @return String containing row and column position of seat.
	 * @author 18010426
	 */
	@Override
	public String toString() {
		return Integer.toString(getRow() + 1) + "_" + Character.toString(getColumn()).toUpperCase();
	}
}