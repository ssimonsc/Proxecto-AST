/**
 * OrquestradorSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package org.apache.ws.axis2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

import com.google.gson.Gson;


/**
 *  OrquestradorSkeleton java skeleton for the axisService
 */
public class OrquestradorSkeleton implements ServiceLifeCycle{
	
	private static String bus_serv = null;
	private static String bus_key;
	private static String serv_key;
	private static UDDI uddi;
	
    /**
     * Auto generated method signature
     *
     * @param viaxeAMonequiland
     * @return viaxeAMonequilandResponse
     * @throws AxisFault 
     */
    public org.apache.ws.axis2.ViaxeAMonequilandResponse viaxeAMonequiland(
        org.apache.ws.axis2.ViaxeAMonequiland viaxeAMonequiland) throws AxisFault {
      
          
        /**********ARGUMENTOS QUE NOS PASA O USUARIO***********/
        String corpoCeleste = viaxeAMonequiland.getArgs0();
        String vehiculo = viaxeAMonequiland.getArgs1();
        /*****************************************************/

        /*************************OBXECTO RESPOSTA DO NOSO SERVIZO*************************/
        ViaxeAMonequilandResponse BoaViaxe = new ViaxeAMonequilandResponse();
        /**********************************************************************************/

        System.out.println("\n\n\n\n\n\n"+corpoCeleste+"\t"+vehiculo+"\n\n\n\n\n\n");
          
        String respostaCache = TesAmiñaConsulta(corpoCeleste, vehiculo);//Creamos unha conexion coa cache e preguntamoslle se ten gardada esta consulta
        System.out.println(respostaCache);
        if(!respostaCache.equalsIgnoreCase("erro na consulta") && !respostaCache.equalsIgnoreCase("nada"))//Se a ten gardada, finalizamos e pasamoslle a resposta ao cliente
        {
        	BoaViaxe.set_return(respostaCache);
            return BoaViaxe;
        }

        String distanciaAtaOcorpoCeleste = IstoOndeQueda(corpoCeleste); //Creamos unha conexion co noso servizo propio DistanciaPlaneta e obtemos a distancia

        String tempoAtaOcorpoCeleste = PeroCantoTardoOh(distanciaAtaOcorpoCeleste, vehiculo); //Creamos unha conexion co noso servizo propio TempoViaxe e obtemos o tempo que se tarda en chegar no vehiculo seleccionado
          
        GardameIsto(corpoCeleste, vehiculo, tempoAtaOcorpoCeleste); //Gardamos a consulta e a resposta na cache para futuras consultas

        BoaViaxe.set_return(tempoAtaOcorpoCeleste); //Enviamos a resposta ao usuario
              
        return BoaViaxe;
    }

    /**
     * Auto generated method signature
     *
     * @param distanciaAMonequiland
     * @return distanciaAMonequilandResponse
     * @throws IOException 
     */
    public org.apache.ws.axis2.DistanciaAMonequilandResponse distanciaAMonequiland(
        org.apache.ws.axis2.DistanciaAMonequiland distanciaAMonequiland) throws IOException {

    	/**********ARGUMENTOS QUE NOS PASA O USUARIO***********/
    	
    	String soles = distanciaAMonequiland.getArgs1();
        /*****************************************************/
        
        /*************************OBXECTO RESPOSTA DO NOSO SERVIZO*************************/
        DistanciaAMonequilandResponse estaEaInformacion = new DistanciaAMonequilandResponse();
        /**********************************************************************************/
        
        System.out.println("Espera un momentiño...\n");
        
        String respostaCache = TesAmiñaConsulta("Marte", soles); //Creamos unha conexion coa cache e preguntamoslle se ten gardada esta consulta
        System.out.println("\n\n\n\n\n\nhola10\n\n\n\n\n\n");
        if(!respostaCache.equalsIgnoreCase("erro na consulta") && !respostaCache.equalsIgnoreCase("nada")) //Se a ten gardad, finalizamos e pasamoslle a resposta ao cliente
        {
        	estaEaInformacion.set_return(respostaCache+":IstoVenDaCache");
            return estaEaInformacion;
        }
        
       String DistanciaAtaOcorpoCeleste = IstoOndeQueda("Marte"); //Creamos unha conexion co noso servizo propio DistanciaPlaneta e obtemos a distancia
       System.out.println("\n\n\n\n\n\nhola11\n\n\n\n\n\n");
       String respostaMarte = QueTemosAla(soles); //Creamos unha conexion co noso servizo externo Conversor e obtemos a distancia nas unidades seleccionadas
       System.out.println("\n\n\n\n\n\nhola12\n\n\n\n\n\n");
       String resposta = respostaMarte;
       System.out.println("\n\n\n\n\n\nhola13\n\n\n\n\n\n");
       GardameIsto("Marte", soles, resposta); //Gardamos a consulta e a resposta na cache para futuras consultas
       System.out.println("\n\n\n\n\n\nhola14\n\n\n\n\n\n");
        estaEaInformacion.set_return(resposta); //Enviamos a resposta ao usuario
       
       return estaEaInformacion;
    }
    
    
    public String IstoOndeQueda(String corpoCeleste)
    {
        /*
         * Establecer conexion co servizo Distancia Planeta*/      
      
            String miñaConsulta = corpoCeleste;
            Browse buscar = new Browse();
            
            /**********ELEMENTOS NESCESARIOS PARA ESTBLCER A CONEXION***********/
            OMElement consulta = null;
            OMElement contido = null;;
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace nameSpace = factory.createOMNamespace("http://ws.apache.org/axis2", "ns");
         
            ServiceClient serviceClient = null;
			try {
				serviceClient = new ServiceClient();
			} catch (AxisFault e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference(buscar.Browse("DistanciaCorpoCeleste")));
            opcions.setAction("urn:getDistancia");
            serviceClient.setOptions(opcions);
            /*****************************************************************************/
        
            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
            consulta = factory.createOMElement("getDistancia", nameSpace);
            contido = factory.createOMElement("args0", nameSpace);
            contido.setText(miñaConsulta);
            consulta.addChild(contido);
            /******************************************************************************/
       
            /**Obtemos a resposta dos servizo***/
            String respostaDistancia = null;
			try {
				respostaDistancia = serviceClient.sendReceive(consulta).getFirstElement().getText();
			} catch (AxisFault e) {
				// TODO Auto-generated catch block
			System.out.println(e);
			}
			
            return respostaDistancia;     
        
    }
    
    public String PeroCantoTardoOh(String DistanciaAtaOcorpoCeleste, String vehiculo)
    {
        /*
         * Establcer conexion co servizo Tempo Viaxe*/
        try
        {
            String miñaConsulta1 = DistanciaAtaOcorpoCeleste;
            String miñaConsulta2 = vehiculo;
            Browse buscar = new Browse();
            
            /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
            OMElement consulta = null;
            OMElement contido1 = null;
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace nameSpace = factory.createOMNamespace("http://ws.apache.org/axis2", "ns");
            
            ServiceClient serviceClient = new ServiceClient();
            /**********************************************************************/
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference(buscar.Browse("TempoViaxe")));
            opcions.setAction("urn:getTempo");
            serviceClient.setOptions(opcions);
            /*****************************************************************************/
        
            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
            consulta = factory.createOMElement("getTempo", nameSpace);
            contido1 = factory.createOMElement("args0", nameSpace);
            contido1.setText(miñaConsulta1);
            consulta.addChild(contido1);
            contido1 = factory.createOMElement("args1", nameSpace);
            contido1.setText(miñaConsulta2);
            consulta.addChild(contido1);
            /******************************************************************************/
        
            /**Obtemos a resposta do servizo***/
            String respostaTempo= serviceClient.sendReceive(consulta).getFirstElement().getText();
            return respostaTempo;
        }
        catch(Exception e) {
            return "erro na consulta";
        }
    }
    
    public String TesAmiñaConsulta(String corpoCeleste, String unidades)
    {
        /*
         * Establecer conexion coa cache*/
        
        try
        {
            String miñaConsulta1 = corpoCeleste+":"+unidades;
            Browse buscar = new Browse();
            
            /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
            OMElement consulta = null;
            OMElement contido1 = null;
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace nameSpace = factory.createOMNamespace("http://ws.apache.org/axis2", "ns");
            
            ServiceClient serviceClient = new ServiceClient();
            /*******************************************************************************/
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference(buscar.Browse("Cache")));
            opcions.setAction("urn:Consultar");
            opcions.setTransportInProtocol(Constants.TRANSPORT_HTTP);
            serviceClient.setOptions(opcions);
            
            /*****************************************************************************/
            
            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
            consulta = factory.createOMElement("Consultar", nameSpace);
            contido1 = factory.createOMElement("args0", nameSpace);
            contido1.setText(miñaConsulta1);
            consulta.addChild(contido1);
            /******************************************************************************/
        
            /**Obtemos a resposta da cache***/
            String respostaCache = serviceClient.sendReceive(consulta).getFirstElement().getText();

            return respostaCache;
        }
        catch(Exception e) {
            return "erro na consulta";
        }
    }
    
    
    public void GardameIsto(String corpoCeleste, String segundoParametro, String respostaAgardar) throws AxisFault 
    {
        /*
         * Establecer conexion coa cache*/    
    	
    	Browse buscar = new Browse();
    	
         /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
        OMElement elementoAgardar = null;
        OMElement contido1 = null;
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace nameSpace = factory.createOMNamespace("http://ws.apache.org/axis2", "ns");//Non estou seguro de que sexa asi
       
        ServiceClient serviceClient = new ServiceClient();
        ChamadaAsincrona chamada = new ChamadaAsincrona();
        /***********************************************************************************/
        
        /********************CREAMOS FIOS PARA CADA PETICION***************************/
        MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
        conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
        HttpClient client = new HttpClient(conmgr);
        /******************************************************************************/
        
        /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
        Options opcions = new Options();
        opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
        opcions.setTo(new EndpointReference(buscar.Browse("Cache")));
        opcions.setAction("urn:Guardar");
        serviceClient.setOptions(opcions);
        /*****************************************************************************/
    
        /***********USAMOS A FUNCION GARDAR NA CACHE DO SERVIZO CACHE******************/
        elementoAgardar = factory.createOMElement("Guardar", nameSpace);
        contido1 = factory.createOMElement("args0", nameSpace);
        String chave = corpoCeleste+":"+segundoParametro;
        contido1.setText(chave);
        elementoAgardar.addChild(contido1);
        contido1 = factory.createOMElement("args1", nameSpace);
        contido1.setText(respostaAgardar);
        elementoAgardar.addChild(contido1);
        /******************************************************************************/

        serviceClient.sendReceiveNonBlocking(elementoAgardar, chamada);
    }

    public String QueTemosAla(String soles) throws IOException
    {
    	String url = "https://api.maas2.jiinxt.com/";
    	
    	URL text = new URL(url+""+soles);
    	System.out.println("\n\n\n\n\n\n"+text+"\n\n\n\n\n\n");
		HttpsURLConnection conection = (HttpsURLConnection) text.openConnection();
		System.out.println("\n\n\n\n\n\nhola1\n\n\n\n\n\n");
		conection.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(conection.getInputStream()));
		System.out.println("\n\n\n\n\n\nhola3\n\n\n\n\n\n");
		String response = in.readLine();
		System.out.println("\n\n\n\n\n\nhola4\n\n\n\n\n\n");
	//	System.out.println(response);
		
		in.close();
		System.out.println("\n\n\n\n\n\nhola5\n\n\n\n\n\n");
		conection.disconnect();
		System.out.println("\n\n\n\n\n\nhola6\n\n\n\n\n\n");
		Gson gson = new Gson();
		System.out.println("\n\n\n\n\n\nhola7\n\n\n\n\n\n");
		Marte marte =gson.fromJson(response, Marte.class);
		String estacion = marte.getSeason();
		String amencer = marte.getSunrise();
		String anoitecer = marte.getSunset();
		System.out.println("\n\n\n\n\n"+estacion+"\n\n\n\n");
		String datos = estacion+":"+amencer+":"+anoitecer;
		
    	return datos;
    }
    
	@Override
	public void shutDown(ConfigurationContext arg0, AxisService arg1) {
		System.out.println(bus_key);
		bus_key=bus_key.trim();
		serv_key=serv_key.trim();
		uddi.unPublish(serv_key,bus_key,"Orquestrador","AST");
	}

	@Override
	public void startUp(ConfigurationContext arg0, AxisService arg1) {
		uddi = new UDDI();

		bus_serv=uddi.publish("Orquestrador","http://localhost:8080/axis2/services/Orquestrador");
		if(bus_serv!=null){
			String cad[] = bus_serv.split(",");
			serv_key = cad[0];
			bus_key = cad[1];
		}
		System.out.println(serv_key+" "+bus_key);
	}
    
}

class ChamadaAsincrona implements AxisCallback
{

    @Override
    public void onComplete() {
        System.out.println("Fin chamada asíncrona\n");
        
    }

    @Override
    public void onError(Exception arg0) {
        System.out.println("Erro na chamada asíncrona \n");
        
    }

    @Override
    public void onFault(MessageContext arg0) {
        System.out.println("Fallou a chamada asíncrona\n");
        
    }

    @Override
    public void onMessage(MessageContext arg0) {
System.out.println("Chamada asíncrona correcta");
    }

}
