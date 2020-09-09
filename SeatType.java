package assignment02;

/**
 * Enumeration that contains the three different seat types. Also contains a
 * toString method so that relevant information can be retrieved.
 * 
 * @author Charlie Cho 18010426
 *
 */

public enum SeatType {
	WINDOW, AISLE, MIDDLE;

	@Override
	public String toString() {
		return this.name();
	}
}