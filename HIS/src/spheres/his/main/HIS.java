                                                                                                                                                                                                                                                                                                                               package spheres.his.main;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import core.FSAirports;
import pojo.arrays.Airports;
import spheres.his.tools.Reader;

public class HIS {
	
	private static String path = "//Users//edgarosorio//Downloads//NUC20161114.doc";
	private FSAirports fs;
	private Airports airports = new Airports();
	
	
	public static void main(String[] args) {
		
		Reader reader = new Reader(path);
	}
}
