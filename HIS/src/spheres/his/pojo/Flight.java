package spheres.his.pojo;

import pojo.Airline;
import pojo.Airport;

public class Flight {

	String flight_code;
	Airline airline;
	String flight_number;
	String category;
	Airport departure_airport;
	Airport arrival_airport;
	String date;
	String departure_time;
	String arrival_time;
	
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public void setDeparture_airport(Airport departure_airport) {
		this.departure_airport = departure_airport;
	}
	public void setArrival_airport(Airport arrival_airport) {
		this.arrival_airport = arrival_airport;
	}
	
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
	
	public Airport getDeparture_airport() {
		return departure_airport;
	}
	public Airport getArrival_airport() {
		return arrival_airport;
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
		return "Flight [flight_code=" + flight_code + ", airline=" + airline.getName() + ", category=" + category
				+ ", departure_airport=" + departure_airport.getName() + ", arrival_airport=" + arrival_airport.getName() + ", date=" + date
				+ ", departure_time=" + departure_time + ", arrival_time=" + arrival_time + "]";
	}
	
	
	
}
