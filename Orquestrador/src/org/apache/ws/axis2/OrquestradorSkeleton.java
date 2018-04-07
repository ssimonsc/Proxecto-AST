/**
 * OrquestradorSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package org.apache.ws.axis2;


import java.util.Iterator;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;

public class OrquestradorSkeleton {
    
    public static OMElement respostaAsincrona = null;
    /**
     * Auto generated method signature
     *
     * @param distanciaAMonequiland
     * @return distanciaAMonequilandResponse
     */
    public org.apache.ws.axis2.DistanciaAMonequilandResponse distanciaAMonequiland(
        org.apache.ws.axis2.DistanciaAMonequiland distanciaAMonequiland) {
        /**********ARGUMENTOS QUE NOS PASA O USUARIO***********/
        String corpoCeleste = distanciaAMonequiland.getArgs0();
        String unidades = distanciaAMonequiland.getArgs1();
        /*****************************************************/
        
        /*************************OBXECTO RESPOSTA DO NOSO SERVIZO*************************/
        DistanciaAMonequilandResponse estaEaDistancia = new DistanciaAMonequilandResponse();
        /**********************************************************************************/
        
        System.out.println("Espera un momentiño...\n");
        
        String respostaCache = TesAmiñaConsulta(corpoCeleste, unidades); //Creamos unha conexion coa cache e preguntamoslle se ten gardada esta consulta
        
        if(respostaCache != null) //Se a ten gardad, finalizamos e pasamoslle a resposta ao cliente
        {
            estaEaDistancia.set_return(respostaCache);
            return estaEaDistancia;
        }
        
       String DistanciaAtaOcorpoCeleste = IstoOndeQueda(corpoCeleste); //Creamos unha conexion co noso servizo propio DistanciaPlaneta e obtemos a distancia
       
       String respostaConversor = PasameIsto(DistanciaAtaOcorpoCeleste, unidades); //Creamos unha conexion co noso servizo externo Conversor e obtemos a distancia nas unidades seleccionadas
       
       GardameIsto(corpoCeleste, unidades, DistanciaAtaOcorpoCeleste); //Gardamos a consulta e a resposta na cache para futuras consultas
       
       estaEaDistancia.set_return(DistanciaAtaOcorpoCeleste); //Enviamos a resposta ao usuario
       
       return estaEaDistancia;
    }

    /**
     * Auto generated method signature
     *
     * @param viaxeAMonequiland
     * @return viaxeAMonequilandResponse
     */
    public org.apache.ws.axis2.ViaxeAMonequilandResponse viaxeAMonequiland(
        org.apache.ws.axis2.ViaxeAMonequiland viaxeAMonequiland) {
        /**********ARGUMENTOS QUE NOS PASA O USUARIO***********/
        String corpoCeleste = viaxeAMonequiland.getArgs0();
        String vehiculo = viaxeAMonequiland.getArgs1();
        /*****************************************************/

        /*************************OBXECTO RESPOSTA DO NOSO SERVIZO*************************/
        ViaxeAMonequilandResponse BoaViaxe = new ViaxeAMonequilandResponse();
        /**********************************************************************************/

        System.out.println("Espera un momentiño...\n");
        
        String respostaCache = TesAmiñaConsulta(corpoCeleste, vehiculo);//Creamos unha conexion coa cache e preguntamoslle se ten gardada esta consulta
        
        if(respostaCache != null)//Se a ten gardada, finalizamos e pasamoslle a resposta ao cliente
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
    
    public static String TesAmiñaConsulta(String corpoCeleste, String unidades)
    {
        /*
         * Establecer conexion coa cache*/
        
        try
        {
            String miñaConsulta1 = corpoCeleste;
            String miñaConsulta2 = unidades;
            
            /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
            OMElement consulta = null;
            OMElement contido1 = null;
            OMElement contido2 = null;
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace nameSpace = factory.createOMNamespace("http://Cache", "ns");//Non estou seguro de que sexa asi
            ServiceClient serviceClient = new ServiceClient();
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference("http://localhost:8080/axis2/services/Cache"));
            serviceClient.setOptions(opcions);
            /*****************************************************************************/
        
            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
            consulta = factory.createOMElement("cache", nameSpace);
            contido1 = factory.createOMElement("args0", nameSpace);
            contido1.setText(miñaConsulta1);
            consulta.addChild(contido1);
            contido2 = factory.createOMElement("args1", nameSpace);
            contido2.setText(miñaConsulta2);
            consulta.addChild(contido2);
            /******************************************************************************/
        
            /**Obtemos a resposta da cache***/
            String respostaCache = serviceClient.sendReceive(consulta).getText().toString();
            return respostaCache;
        }
        catch(Exception e) {
            return "erro na consulta";
        }
                
        
    }
    
    public String IstoOndeQueda(String corpoCeleste)
    {
        /*
         * Establecer conexion co servizo Distancia Planeta*/
        
        try
        {
            String miñaConsulta = corpoCeleste;
            
            /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
            OMElement consulta = null;
            OMElement contido = null;;
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace nameSpace = factory.createOMNamespace("http://DistanciaCorpo", "ns");//Non estou seguro de que sexa asi
            ServiceClient serviceClient = new ServiceClient();
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference("http://localhost:8080/axis2/services/DistanciaCorpo"));
            serviceClient.setOptions(opcions);
            /*****************************************************************************/
        
            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
            consulta = factory.createOMElement("distanciaCorpo", nameSpace);
            contido = factory.createOMElement("args0", nameSpace);
            contido.setText(miñaConsulta);
            consulta.addChild(contido);
            /******************************************************************************/
        
            /**Obtemos a resposta da cache***/
            String respostaDistancia = serviceClient.sendReceive(consulta).getText().toString();
            return respostaDistancia;
        }
        catch(Exception e) {
            return "erro na consulta";
        }
        
    }
    
    public String PeroCantoTardoOh(String DistanciaAtaOcorpoCeleste, String vehiculo)
    {
        /*
         * Establcer conexion co servizo Tempo Viaxe*/
        try
        {
            String miñaConsulta1 = DistanciaAtaOcorpoCeleste;
            String miñaConsulta2 = vehiculo;
            
            /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
            OMElement consulta = null;
            OMElement contido1 = null;
            OMElement contido2 = null;
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace nameSpace = factory.createOMNamespace("http://Tempo", "ns");//Non estou seguro de que sexa asi
            ServiceClient serviceClient = new ServiceClient();
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference("http://localhost:8080/axis2/services/Tempo"));
            serviceClient.setOptions(opcions);
            /*****************************************************************************/
        
            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
            consulta = factory.createOMElement("tempo", nameSpace);
            contido1 = factory.createOMElement("args0", nameSpace);
            contido1.setText(miñaConsulta1);
            contido2 = factory.createOMElement("args1", nameSpace);
            contido2.setText(miñaConsulta2);
            consulta.addChild(contido1);
            consulta.addChild(contido2);
            /******************************************************************************/
        
            /**Obtemos a resposta da cache***/
            String respostaTempo= serviceClient.sendReceive(consulta).getText().toString();
            return respostaTempo;
        }
        catch(Exception e) {
            return "erro na consulta";
        }
    }
    
    public String PasameIsto(String DistanciaAtaOcorpoCeleste, String unidades)
    {
        /*
         * Establecer conexion co servizo externo conversor*/
        
        try
        {
            String miñaConsulta1 = DistanciaAtaOcorpoCeleste;
            String miñaConsulta2 = unidades;
            
            /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
            OMElement consulta = null;
            OMElement contido1 = null;
            OMElement contido2 = null;
            OMFactory factory = OMAbstractFactory.getOMFactory();
            OMNamespace nameSpace = factory.createOMNamespace("http://www.webservicex.net/", "ns");//Non estou seguro de que sexa asi
            ServiceClient serviceClient = new ServiceClient();
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference("http://www.webservicex.net/Astronomical.asmx"));
            serviceClient.setOptions(opcions);
            /*****************************************************************************/
        
            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
            consulta = factory.createOMElement("distanciaCorpo", nameSpace);
            contido1 = factory.createOMElement("args0", nameSpace);
            contido1.setText(miñaConsulta1);
            consulta.addChild(contido1);
            contido2 = factory.createOMElement("args1", nameSpace);
            contido2.setText(miñaConsulta2);
            consulta.addChild(contido2);
            /******************************************************************************/
        
            /**Obtemos a resposta da cache***/
            String respostaConversor = serviceClient.sendReceive(consulta).getText().toString();
            return respostaConversor;
        }
        catch(Exception e) {
            return "erro na consulta";
        }
    }
    
    public void GardameIsto(String corpoCeleste, String unidades, String DistanciaAtaOcorpoCeleste)
    {
        /*
         * Establecer conexion coa cache*/      
    }
    
    
}

class ChamadaAsincrona implements AxisCallback
{

    @Override
    public void onComplete() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onError(Exception arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onFault(MessageContext arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void onMessage(MessageContext arg0) {
        Iterator respuestas = arg0.getEnvelope().getBody()
                .getFirstChildWithName(new javax.xml.namespace.QName("http://registrador", "registrarResponse", "ns1"))
                .getChildElements();
        while (respuestas.hasNext()) {
        //  OrquestradorSkeleton.respostaAsincrona = ((OMElement) respuestas.next()).getText();
        }
        
    }
    }
