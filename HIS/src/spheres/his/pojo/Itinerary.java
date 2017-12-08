package spheres.his.pojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;

import core.FSAirlines;

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
	

	public static ArrayList<Flight> parseFlights(BufferedReader itinerary_record, FSAirlines airlines) {
		// TODO Auto-generated method stub
		String line;
		ArrayList<Flight> flights=null;
		try {
			while((line=itinerary_record.readLine())!=null) {
				System.out.println(line);
				StringTokenizer tline = new StringTokenizer(line);
				if(tline.countTokens()>0){
					//Get Airline
					String flight= tline.nextToken();
					String airlinecode = StringUtils.substring(flight, 0, 2) ;
					String flightnumber = StringUtils.substring(flight, 2);
					String rate = tline.nextToken();
					String airports = tline.nextToken();
					String originairport = airports.substring(0,2);
					String departureairport = airports.substring(2);
					
					System.out.println(airlines.getAirlineByIata(airlinecode).getName() +  " - " + flightnumber + "Origin :" +originairport + " Departure " + departureairport);
					
				}
				
			}
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flights;
	}
	
}

