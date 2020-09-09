package assignment02;

/**
 * The Flight class stores information about the flight, including the
 * origin/destination city, flight identification number, the seat planning and
 * departure time.
 * 
 * @author Charlie Cho 18010426
 *
 */

public class Flight {
	private String flightIdentifer;
	private String originCity;
	private String destCity;
	private String departureTime;
	private SeatMap seating;

	/**
	 * constructor which takes in the flight identifier, source/destination city,
	 * departure time and seating.
	 * 
	 * @param flightIdentifer - the identification number for the flight
	 * @param originCity      - the city the flight leaves form
	 * @param destCity        - the city the flight goes to
	 * @param departureTime   - the time of departure for the flight
	 * @param seating         - what the seating plan is like (boeing or airbus)
	 * @author 18010426
	 */

	public Flight(String flightIdentifer, String originCity, String destCity, String departureTime, SeatMap seating) {
		this.flightIdentifer = flightIdentifer;
		this.originCity = originCity;
		this.destCity = destCity;
		this.departureTime = departureTime;
		this.seating = seating;
	}

	/**
	 * get method for flight identifier
	 * 
	 * @return flightIdentifer - identification number for flight
	 * @author 18010426
	 */
	public String getFlightNumber() {
		return flightIdentifer;
	}

	/**
	 * get method for origin city
	 * 
	 * @return flightIdentifer - identification number for flight
	 * @author 18010426
	 */
	public String getOriginCity() {
		return originCity;
	}

	/**
	 * set method for origin city
	 * 
	 * @param originCity - the city the flight leaves from
	 * @author 18010426
	 */
	public void setSourceCity(String originCity) {
		this.originCity = originCity;
	}

	/**
	 * get method for destination city
	 * 
	 * @return destCity - the city the flight is going to
	 * @author 18010426
	 */

	public String getDestCity() {
		return destCity;
	}

	/**
	 * set method for destination city
	 * 
	 * @param destCity - the city the flight is going to
	 * @author 18010426
	 */
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}

	/**
	 * get method for seating plan
	 * 
	 * @return seating - the seat map for the flight, boeing or airbus
	 * @author 18010426
	 */
	public SeatMap getSeating() {
		return seating;
	}

	/**
	 * set method for seating plan
	 * 
	 * @param seating - the seat map for the flight, boeing or airbus
	 * @author 18010426
	 */
	public void setSeating(SeatMap seating) {
		this.seating = seating;
	}

	/**
	 * get method for departure time
	 * 
	 * @return departureTime - the time the flight is leaving
	 * @author 18010426
	 */
	public String getTime() {
		return departureTime;
	}

	/**
	 * set method for departure time
	 * 
	 * @param departureTime - the time the flight is leaving
	 * @author 18010426
	 */
	public void setTime(String departureTime) {
		this.departureTime = departureTime;
	}

	/**
	 * toString method for the Flight class. Returns all relevant information
	 * including flight number origin/destination city and departure time.
	 * 
	 * @author 18010426
	 */
	@Override
	public String toString() {
		return "Flight " + getFlightNumber() + " going to " + getDestCity() + " from " + getOriginCity() + " at "
				+ getTime();
	}
}