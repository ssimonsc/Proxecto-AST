package clienteAPI;

import org.uddi.api_v3.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.axis2.transport.udp.Endpoint;
import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.commons.configuration.ConfigurationException;
import org.uddi.api_v3.*;
import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.v3_service.UDDISecurityPortType;
import org.uddi.v3_service.UDDIPublicationPortType;


public class UDDI {
	private static String serv_key ;
	private static String bus_key;
	private static String bus_serv;
	private static UDDIClerk clerk = null;
	private static String miIP;
	private static UDDISecurityPortType security;
	private static UDDIPublicationPortType publish;

	public UDDI(){
		try {
            // create a client and read the config in the archive; 
            // you can use your config file name
			UDDIClient uddiClient = new UDDIClient("/home/ssmonsc/suniversidade/ast/uddi.xml"); 
            clerk = uddiClient.getClerk("default");
            System.out.println(clerk);
            if (clerk==null)
                    throw new Exception("the clerk wasn't found, check the config file!");
    } catch (Exception e) {
            e.printStackTrace();
    }
	}
	public static void unPublish(String servKey,String busKey,String Serv,String Bus){ try {
        System.out.println("Inicializando eliminación de servicio..");
        // create a client and read the config in the archive;
        // you can use your config file name
       UDDIClient uddiClient = new UDDIClient("/home/ssmonsc/suniversidade/ast/uddi.xml"); 
        // a UddiClient can be a client to multiple UDDI nodes, so
        // supply the nodeName (defined in your uddi.xml.
        // The transport can be WS, inVM, RMI etc which is defined in the uddi.xml
       Transport transport = uddiClient.getTransport("default");
        // Now you create a reference to the UDDI API
       security = transport.getUDDISecurityService();
       publish = transport.getUDDIPublishService();


       GetAuthToken getAuthTokenMyPub = new GetAuthToken();
       getAuthTokenMyPub.setUserID("uddi");                    //your username
       getAuthTokenMyPub.setCred("uddi");                          //your password
       AuthToken myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);
       System.out.println(getAuthTokenMyPub.getUserID() + "'s AUTHTOKEN = " + "******* never log auth tokens!");
       BusinessService myService = new BusinessService();
       myService.setBusinessKey(busKey);
       Name myServName = new Name();
       myServName.setValue(Serv);
       myService.getName().add(myServName);
       DeleteService ds = new DeleteService();
       ds.setAuthInfo(myPubAuthToken.getAuthInfo());
       ds.getServiceKey().add(servKey);
       publish.deleteService(ds);
       // Crear un negocio que contendrá el servicio
       BusinessEntity myBusEntity = new BusinessEntity();
       Name myBusName = new Name();
       myBusName.setValue(Bus);
       myBusEntity.getName().add(myBusName);
     
       // Borrando entidad de negocio...
       DeleteBusiness db = new DeleteBusiness();
       db.setAuthInfo(myPubAuthToken.getAuthInfo());
       db.getBusinessKey().add(busKey);
       publish.deleteBusiness(db);

       String address = null;
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
   
       // Añadir binding templates, etc...
       BindingTemplate myBindingTemplate = new BindingTemplate();
       AccessPoint accessPoint = new AccessPoint();
       accessPoint.setUseType(AccessPointType.END_POINT.toString());
       accessPoint.setValue("http://" + miIP + ":8080/juddiv3/"); //poner http://IP:8080/juddiv3/
       myBindingTemplate.setAccessPoint(accessPoint);
       BindingTemplates myBindingTemplates = new BindingTemplates();
       //SOAP binding...
       myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
       myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
       myService.setBindingTemplates(myBindingTemplates);
       security.discardAuthToken(new DiscardAuthToken(myPubAuthToken.getAuthInfo()));
       System.out.println("Servicio eliminado.");
   } 
   catch (Exception e) {
       System.out.println("Se ha producido un error. Revise si el UDDI está activado o si ese servicio existe.");
       e.printStackTrace();
   }
			
	}
	public String publish(String servicio, String endpoint) {
				try {
        	
        		// Creating the parent business entity that will contain our service.
                BusinessEntity myBusEntity = new BusinessEntity();
                Name myBusName = new Name();
                myBusName.setValue("AST");
                
                myBusEntity.getName().add(myBusName);
                //myBusEntity.setBusinessKey("uddi:uddi.lorena.com:business-for-wsdl");
                // Adding the business entity to the "save" structure, using our publisher's authentication info and saving away.
                BusinessEntity register = clerk.register(myBusEntity);
                if (register == null) {
                        System.out.println("Save failed!");
                        return null;
                }                
                
                String myBusKey = register.getBusinessKey();
                System.out.println("myBusiness key:  " + myBusKey);
                bus_key = myBusKey;
                // Creating a service to save.  Only adding the minimum data: the parent business key retrieved from saving the business 
                // above and a single name.
                BusinessService myService = new BusinessService();
                myService.setBusinessKey(myBusKey);
                Name myServName = new Name();
                myServName.setValue(servicio);
                myService.getName().add(myServName);

                // Add binding templates, etc...
                BindingTemplate myBindingTemplate = new BindingTemplate();
                AccessPoint accessPoint = new AccessPoint();
                accessPoint.setUseType(AccessPointType.END_POINT.toString());
                accessPoint.setValue(endpoint);
                myBindingTemplate.setAccessPoint(accessPoint);
                BindingTemplates myBindingTemplates = new BindingTemplates();
                //optional but recommended step, this annotations our binding with all the standard SOAP tModel instance infos
                myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
                myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
                myService.setBindingTemplates(myBindingTemplates);
         
                // Adding the service to the "save" structure, using our publisher's authentication info and saving away.
                BusinessService svc = clerk.register(myService);
                if (svc==null){
                        System.out.println("Save failed!");
                        System.exit(1);
                }
                
                String myServKey = svc.getServiceKey();
                System.out.println("myService key:  " + myServKey);
                bus_key = myServKey;
                clerk.discardAuthToken();
                // Now you have a business and service via 
                // the jUDDI API!
                System.out.println("Success!");
                return myServKey+","+myBusKey;

        } catch (Exception e) {
                e.printStackTrace();
        }
				return null;
}
	 public static void main(String args[]) {
		 //System.out.println("hola");
         UDDI sp = new UDDI();
         String serv = "Orquestrador";
         String endpoint = "http://localhost:8080/axis2/services/Orquestrador";
         
        bus_serv =sp.publish(serv, endpoint);
         
         String cad[] = bus_serv.split(",");
         serv_key = cad[0];
         bus_key = cad[1];
         InputStreamReader isr = new InputStreamReader(System.in);
         BufferedReader br = new BufferedReader (isr);
         String cadena;
		try {
			cadena = br.readLine();
			while(cadena.equals("q")){
	        	 cadena = br.readLine();
	         }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         System.out.println(serv_key+" "+bus_key);

     //sp.unPublish(serv_key,bus_key,serv,"AST");
 }

}
