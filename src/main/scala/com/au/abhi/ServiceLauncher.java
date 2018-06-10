package com.au.abhi;

public class ServiceLauncher implements Runnable{
	
	private WebURLInformation  webURLInformation;
	
	public ServiceLauncher( WebURLInformation webURLInformation) {
		this.webURLInformation = webURLInformation;
	   }
	
    @Override
    public void run() {
    	FindAgentsService findAgentsService = new FindAgentsService();
    	findAgentsService.getPageLinks(webURLInformation);
    }
}
