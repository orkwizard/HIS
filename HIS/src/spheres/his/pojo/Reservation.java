package spheres.his.pojo;

import java.util.ArrayList;

import core.FSAirlines;

public class Reservation {
	String confirmation;
	int pax;
	String branch;
	String agent;
	String NUC;
	String request_code; //NEWBOOK,CHANGE,FINAL,CHECK,PUSH,CANCEL
	String japan_code;
	String final_destination;
	String date_final_destination;
	ArrayList<Traveler> travelers;
	ArrayList<Flight> flights;
	String tours;
	
	public ArrayList<Flight> getFlights() {
		return flights;
	}
	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}
	public ArrayList<Traveler> getTravelers() {
		return travelers;
	}
	public void setTravelers(ArrayList<Traveler> travelers) {
		this.travelers = travelers;
	}
	public boolean isErrors() {
		return errors;
	}
	public void setErrors(boolean errors) {
		this.errors = errors;
	}
	Itinerary itinerary;
	String raw;
	boolean parsed;
	boolean errors;
	
	
	public String getRaw() {
		return raw;
	}
	public void setRaw(String raw) {
		this.raw = raw;
	}
	public boolean isParsed() {
		return parsed;
	}
	public void setParsed(boolean parsed) {
		this.parsed = parsed;
	}
	public String getConfirmation() {
		return confirmation;
	}
	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}
	public int getPax() {
		return pax;
	}
	public void setPax(int pax) {
		this.pax = pax;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getNUC() {
		return NUC;
	}
	public void setNUC(String nUC) {
		NUC = nUC;
	}
	public String getRequest_code() {
		return request_code;
	}
	public void setRequest_code(String request_code) {
		this.request_code = request_code;
	}
	public String getJapan_code() {
		return japan_code;
	}
	public void setJapan_code(String japan_code) {
		this.japan_code = japan_code;
	}
	public String getFinal_destination() {
		return final_destination;
	}
	public void setFinal_destination(String final_destination) {
		this.final_destination = final_destination;
	}
	public String getDate_final_destination() {
		return date_final_destination;
	}
	public void setDate_final_destination(String date_final_destination) {
		this.date_final_destination = date_final_destination;
	}
	public Itinerary getItinerary() {
		return itinerary;
	}
	public void setItinerary(Itinerary itinerary) {
		this.itinerary = itinerary;
	}
	
	public String getTours() {
		return tours;
	}
	public void setTours(String tours) {
		this.tours = tours;
	}
	@Override
	public String toString() {
		return "Reservation [confirmation=" + confirmation + ", pax=" + pax + ", branch=" + branch + ", agent=" + agent
				+ ", NUC=" + NUC + ", request_code=" + request_code + ", japan_code=" + japan_code
				+ ", final_destination=" + final_destination + ", date_final_destination=" + date_final_destination
				+ ", travelers=" + travelers + ", flights=" + flights + ", tours=" + tours + ", itinerary=" + itinerary
				+ ", parsed=" + parsed + ", errors=" + errors + "]";
	}
	
	
	
}
