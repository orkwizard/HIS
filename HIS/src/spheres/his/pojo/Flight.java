package spheres.his.pojo;

public class Flight {

	String flight_code;
	String category;
	String departure_airport;
	String arrival_airport;
	String date;
	String departure_time;
	String arrival_time;
	public String getFlight_code() {
		return flight_code;
	}
	public void setFlight_code(String flight_code) {
		this.flight_code = flight_code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDeparture_airport() {
		return departure_airport;
	}
	public void setDeparture_airport(String departure_airport) {
		this.departure_airport = departure_airport;
	}
	public String getArrival_airport() {
		return arrival_airport;
	}
	public void setArrival_airport(String arrival_airport) {
		this.arrival_airport = arrival_airport;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	public String getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}
	@Override
	public String toString() {
		return "Flight [flight_code=" + flight_code + ", category=" + category + ", departure_airport="
				+ departure_airport + ", arrival_airport=" + arrival_airport + ", date=" + date + ", departure_time="
				+ departure_time + ", arrival_time=" + arrival_time + "]";
	}
	
	
	
}
