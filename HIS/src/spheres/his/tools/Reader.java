package spheres.his.tools;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import core.FSAirlines;
import core.FSAirports;
import core.FlightStats;
import pojo.Airport;
import pojo.arrays.Airports;
import spheres.his.pojo.Itinerary;
import spheres.his.pojo.Reservation;
import spheres.his.pojo.Traveler;

public class Reader {
	private HWPFDocument doc;
	private WordExtractor extractor;
	private String path;
	private ArrayList<String> raw_records;
	private ArrayList<Reservation> reservations;
	private FSAirlines airlines;
	private FSAirports airports;
	
	private HashMap<String,Airport> map;
	
	public Reader(String p) {
		super();
		path = p;
		map = new HashMap<String,Airport>();
		try {
			FileInputStream input = new FileInputStream(path);
			HWPFDocument doc = new HWPFDocument(input);
			WordExtractor extract = new WordExtractor(doc);
			raw_records = getRecords(extract.getText());
			System.out.println("La cantidad de registros es -> " + raw_records.size());
			airlines = new FSAirlines();
			airports = new FSAirports();		
			Iterator<Airport> iterator = airports.getAllAirports().getAirports().iterator();
			while(iterator.hasNext()) {
				Airport a = iterator.next();
				map.put(a.getIata(),a);
			}			
			//System.out.println("Aeropuertos :" + airports.getAirports().size());
			//System.out.println("Aerolineas :" +  airlines.size());	
			parse(raw_records);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Reservation> parse(ArrayList<String> raw_records) throws IOException {
		// TODO Auto-generated method stub
		
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		
		Iterator<String> iterator = raw_records.iterator();
		while(iterator.hasNext())
		{
			Reservation reservation = new Reservation();
			String raw = iterator.next();
			BufferedReader record = new BufferedReader(new StringReader(raw));
			reservation.setRaw(raw);
			//System.out.println(reservation.getRaw());
			String line = record.readLine();
			reservation.setConfirmation(getConfirmation(line));
			reservation.setPax(getPax(line));
			reservation.setBranch(getBranch(line)+" || " + record.readLine().trim());
			reservation.setAgent(getAgent(line));
			reservation.setNUC(getNuc(line));
			reservation.setRequest_code(record.readLine().trim());
			//Header Intineraty 
			//NO NAME                ROOMING(BED INFO):CUN
			record.readLine();
			String itinerary = getItinerary(record);
			String tours = getTours(raw);
			
			//System.out.println(itinerary);
			
			reservation.setTravelers(Itinerary.parseTravelers(itinerary));
			
			if(!reservation.getRequest_code().contains("CANCEL")) {
				BufferedReader itinerary_record = new BufferedReader(new StringReader(itinerary));
				String aline="";
				boolean found = false;
				while((aline=itinerary_record.readLine())!=null && !found) {
					if(aline.contains("TC-")) {
						found=true;
						StringTokenizer atokens = new StringTokenizer(aline);
						reservation.setJapan_code(atokens.nextToken());
						reservation.setFinal_destination(atokens.nextToken());
						reservation.setDate_final_destination(atokens.nextToken());
					}
				}
				reservation.setFlights(Itinerary.parseFlights(itinerary_record,airlines,map));
				
			}
				
			reservation.setTours(tours);
			reservation.setParsed(true);
			//System.out.println(tours);
			reservations.add(reservation);
			System.out.println(reservation.toString());
			
		
		}
		return reservations;
	}

	private String getTours(String record) {
		// TODO Auto-generated method stub
		String tours;
		int index = StringUtils.indexOf(record,"==>");
		tours = record.substring(index);
		return tours;
		
	}

	private String getItinerary(BufferedReader record) {
		String travelers="";
		boolean eoi = false;
		try {
			while(record.ready() && !eoi) {
				record.mark(100);
				String line = record.readLine();
				if(!line.contains("==>"))
					travelers+=line+"\n";
				else {
					eoi=true;
					record.reset();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return travelers;
	}

	private String getNuc(String line) {
		// TODO Auto-generated method stub
		return StringUtils.substring(line,StringUtils.indexOf(line, "NUC"));
	}

	private String getAgent(String line) {
		int initindex = StringUtils.indexOf(line, "PAX-")+4;
		int endindex = StringUtils.indexOf(line,"NUC");
		String str= StringUtils.substring(line, initindex,endindex);
		StringTokenizer tokens = new StringTokenizer(str);
		int ntokens = tokens.countTokens();
		if(ntokens==3){
			tokens.nextToken();
			return tokens.nextToken()+" "+tokens.nextToken();
		}
			
		return "Incorrect data";
	}

	private String getBranch(String line) {
		int initindex = StringUtils.indexOf(line, "PAX-")+4;
		int endindex = StringUtils.indexOf(line,"NUC");
		String str= StringUtils.substring(line, initindex,endindex);
		//System.out.println(str);
		StringTokenizer tokens = new StringTokenizer(str);
		int ntokens = tokens.countTokens();
		if(ntokens > 1)
			return tokens.nextToken();
		
		return "Incorrect Data";
	}

	private int getPax(String line) {
		int initindex = StringUtils.indexOf(line,"-TTL:");
		initindex+=5;
		int endindex = StringUtils.indexOf(line, "PAX-");
		return Integer.parseInt(StringUtils.substring(line, initindex,endindex).trim());
	}

	private String getConfirmation(String line) {
		StringTokenizer token = new StringTokenizer(line);
		return token.nextToken();
	}

	private ArrayList<String> getRecords(String raw) throws IOException {
		ArrayList<String> records= new ArrayList<String>();
		String line;
		BufferedReader buffer = new BufferedReader(new StringReader(raw));
		
		while(buffer.ready() &&(line = buffer.readLine())!=null) {
			if(line.contains("NUC"))
			{
				String strbuffer = new String();
				strbuffer += line+"\n";
				boolean eor = false; //end of record
				while(buffer.ready() && !eor) {
					String inline = buffer.readLine();
					if(!inline.contains("----------------")) 
						strbuffer += inline+"\n";
					else
						eor=true;
				}
				//System.out.println(strbuffer.toString());
				records.add(strbuffer.toString());
				//Lee lineas hasta encontrar ----------
			}
		}
		
		System.out.println("RECORDS: ->" + records.size());
		return records;
	}
	
	
	
	
}
