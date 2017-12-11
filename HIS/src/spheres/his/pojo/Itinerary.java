package spheres.his.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

import core.FSAirlines;
import core.FSAirports;
import pojo.Airport;

public class Itinerary {
	ArrayList<Traveler> travelers;
	ArrayList<Flight> flights;
	ArrayList<Tour> tours;
	
	
	public static ArrayList<Traveler> parseTravelers(String raw){
		ArrayList<Traveler> t = new ArrayList<Traveler>();
		BufferedReader reader = new BufferedReader(new StringReader(raw));
		String line;
		try {
			while((line=reader.readLine())!=null) {
				boolean traveler = line.contains("(AGE:");
				if(traveler) {
					StringTokenizer tokens = new StringTokenizer(line);
					//while(tokens.hasMoreTokens())
					//	System.out.println(tokens.nextToken());
					Traveler trav = new Traveler();
					trav.setIndex(Integer.parseInt(tokens.nextToken()));
					StringTokenizer name = new StringTokenizer(tokens.nextToken(),"/");
					trav.setLastname(name.nextToken());
					trav.setName(name.nextToken());
					trav.setSalutation(tokens.nextToken());
					tokens.nextToken(); //(AGE:
					StringTokenizer composed = new StringTokenizer(tokens.nextToken("|"));
					String age = StringUtils.strip(composed.nextToken(),")").trim();
					if(StringUtils.isNumeric(age))
						trav.setAge(Integer.parseInt(age));
					else
						trav.setAge(-1);
					
					t.add(trav);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
		
	}
	

	public static ArrayList<Flight> parseFlights(BufferedReader itinerary_record, FSAirlines airlines,HashMap<String,Airport> map) {
		// TODO Auto-generated method stub
		String line;
		ArrayList<Flight> flights=new ArrayList<Flight>();
		FSAirports air = new FSAirports();
		try {
			while((line=itinerary_record.readLine())!=null) {
				//System.out.println(line);
				StringTokenizer tline = new StringTokenizer(line);
				if(tline.countTokens()>0){
					Flight f= new Flight();
					//Get Airline
					String flight= tline.nextToken();
					String airlinecode = StringUtils.substring(flight, 0, 2) ;
					String flightnumber = StringUtils.substring(flight, 2);
					String category = tline.nextToken();
					String airports = tline.nextToken();
					String date = tline.nextToken();
					
					f.setFlight_code(airlinecode);
					f.setFlight_number(flightnumber);
					f.setAirline(airlines.getAirlineByIata(airlinecode));
					
					//f.setDeparture_airport(air.getAirportByFS(airports.substring(0,3)));
					//f.setArrival_airport(air.getAirportByFS(airports.substring(3)));
					String dep_airport = airports.substring(0,3);
					String arr_airport = airports.substring(3);
					
					Airport da = map.get(dep_airport);
					Airport aa  =map.get(arr_airport);
					if(da==null) {
						da = new Airport();
						da.setName("Invalid Airport");
					}
					if(aa==null) {
						aa = new Airport();
						aa.setName("Invalid Airport");
					}
					f.setDeparture_airport(da);
					f.setArrival_airport(aa);
					
				
					f.setCategory(category);
					f.setDate(date);
					
					
					flights.add(f);
					//airlines.getAirlineByIata(airlinecode).getName() +  " - " + flightnumber + " Origin :" + origin.getName() + " Departure: " + departure.getName());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flights;
	}
	
}

