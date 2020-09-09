package assignment02;

/**
 * The AirBusSeatMap class inherits the abstract class SeatMap. A specific set
 * number of rows (12), columns (9) and first class rows(6) are set. Then class
 * then instantiates the seat map according to those values.
 * 
 * @author Charlie Cho 18010426
 *
 */

public class AirBusSeatMap extends SeatMap {

	/**
	 * default constructor that initializes row to 12, columns to 9 and the number
	 * of first class rows to 6. Also initializes the seat map.
	 * 
	 * @author 18010426
	 */
	public AirBusSeatMap() {
		super.nRows = 12;
		super.nColumns = 9;
		super.nFirstClassRows = 6;
		initializeSeatMap();
	}

	/**
	 * method inherited by SeatMap abstract class and overridden. Initializes the
	 * seat map according to the number of columns, rows and first class rows.
	 * Iterate through rows and columns set by constructor and instantiate seat
	 * position, type and whether it is first class through if statements.
	 * 
	 * @author 18010426
	 */
	@Override
	protected void initializeSeatMap() {
		// set seat array to match AirBus seat map
		seats = new Seat[nRows][nColumns];
		for (int i = 0; i < nRows; ++i) {
			for (int j = 0; j < nColumns; ++j) {
				// verify whether the seat is first class by checking row
				boolean isFirstClass = false;
				if (i < nFirstClassRows)
					isFirstClass = true;

				SeatType type = null;
				// if column is 3 (middle seat)
				if (j == 1 || j == 4 || j == 7)
					type = SeatType.MIDDLE;
				// else if column is 0 or 6 (window seats)
				else if (j == 0 || j == 8)
					type = SeatType.WINDOW;
				// else (rest are aisle seats)
				else
					type = SeatType.AISLE;

				// instantiate each seat object with correct attributes (seat position, seat
				// type and whether it is first class)
				seats[i][j] = new Seat(new SeatPosition(i, (char) (j + 65)), type, isFirstClass);
			}
		}
	}
}