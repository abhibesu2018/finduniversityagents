package com.au.abhi;

import java.io.IOException;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;


public class FindAgents{
	public static void main(String[] args) throws Exception {
		//Load the yaml configuration files
		WebInformations webInformations =  loadFromFile("/findAgentsConfiguration.yml");
		
		
//		FindAgentsService findAgentsService = new FindAgentsService();
		
		for(WebURLInformation webURLInformation:webInformations.getWebURLInformations()) {
			System.err.println("Page URL: "+webURLInformation.getURL());
			Runnable r = new ServiceLauncher(webURLInformation);
			new Thread(r).start();
			}
		}
	
	private static WebInformations loadFromFile(String path) throws IOException {
		Yaml yaml = new Yaml();
        try (InputStream in = FindAgents.class.getResourceAsStream(path)) {
        	return yaml.loadAs(in, WebInformations.class);
        }
	  }
}
