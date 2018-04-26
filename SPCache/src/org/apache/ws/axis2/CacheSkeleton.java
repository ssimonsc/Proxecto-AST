/**
 * CacheSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package org.apache.ws.axis2;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BindingTemplates;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.Name;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import javax.xml.ws.soap.Addressing;

/**
 *  CacheSkeleton java skeleton for the axisService
 */
public class CacheSkeleton implements ServiceLifeCycle{
   
	private static String bus_serv = null;
	private static String bus_key;
	private static String serv_key;
	private static String miIP;
	private static String endpoint;
	private static UDDI uddi;
	private static String serv_name = "Cache";
	private static String key = null;
	
	static Map<String, String> cache = new HashMap<String, String>();
	
    public org.apache.ws.axis2.ConsultarResponse consultar(
        org.apache.ws.axis2.Consultar consultar) {
    	
    	String clave = consultar.getArgs0();
    	
        ConsultarResponse response = new ConsultarResponse();
        
        if(cache.containsKey(clave))
        	response.set_return(cache.get(clave)+":IstoVenDaCache");   
        else 
        	response.set_return("nada");
       
        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param guardar
     * @return guardarResponse
     */
    public org.apache.ws.axis2.GuardarResponse guardar(org.apache.ws.axis2.Guardar guardar) {
       String clave = guardar.getArgs0();
       String Otro = guardar.getArgs1();
       
       if(!cache.containsKey(clave))
    	   cache.put(clave, Otro);
       
       return null;
       
     
    	
    }

	@Override
	public void shutDown(ConfigurationContext arg0, AxisService arg1) {
		System.out.println(bus_key);
		bus_key=bus_key.trim();
		serv_key=serv_key.trim();
		uddi.unPublish(serv_key,bus_key,"Cache","AST");
	}

	@Override
	public void startUp(ConfigurationContext arg0, AxisService arg1) {

		System.out.println("ServiceLifeCycle:::startUp() Holaaa"); 
		try {
			Enumeration e = NetworkInterface.getNetworkInterfaces();
		while(e.hasMoreElements()){
			NetworkInterface n = (NetworkInterface) e.nextElement();
			//System.out.println(n.getName());
			if(!(n.getName().equals("wlan1"))) continue;
			Enumeration ee = n.getInetAddresses();
			while (ee.hasMoreElements())
			{
				InetAddress i = (InetAddress) ee.nextElement();
				if(i.getHostAddress().contains("fe80")) continue;
				miIP=i.getHostAddress();
				System.out.println(i.getHostAddress());
			}
		}
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		miIP="10.100.39.99";
		System.out.println(miIP);
		uddi = new UDDI();
		endpoint = "http://"+miIP+":8080/axis2/services/"+serv_name;
		System.out.println(endpoint);
		bus_serv=uddi.publish(serv_name,endpoint);
		if(bus_serv!=null){
			String cad[] = bus_serv.split(",");
			serv_key = cad[0];
			bus_key = cad[1];
		}
		System.out.println(serv_key+" "+bus_key);
	}
		
}

