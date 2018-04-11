package org.apache.ws.axis2;
/**
 * OrquestradorSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */



import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;

import javax.xml.ws.Endpoint;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.client.async.AxisCallback;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.api_v3.Publisher;
import org.apache.juddi.api_v3.SavePublisher;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.apache.juddi.v3_service.JUDDIApiPortType;
import org.uddi.api_v3.AccessPoint;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BindingTemplates;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.OverviewDoc;
import org.uddi.api_v3.OverviewURL;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveService;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.TModel;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class OrquestradorSkeleton implements ServiceLifeCycle{
    
    public static OMElement respostaAsincrona = null;
    private static String serviceKey;
    private Buscar buscar = null;
    /**
     * Auto generated method signature
     *
     * @param distanciaAMonequiland
     * @return distanciaAMonequilandResponse
     * @throws TransportException 
     * @throws ConfigurationException 
     * @throws RemoteException 
     */
    public org.apache.ws.axis2.DistanciaAMonequilandResponse distanciaAMonequiland(
        org.apache.ws.axis2.DistanciaAMonequiland distanciaAMonequiland) throws ConfigurationException, TransportException, RemoteException {
        
        buscar = new Buscar();
        
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
     * @throws TransportException 
     * @throws ConfigurationException 
     * @throws RemoteException 
     */
    public org.apache.ws.axis2.ViaxeAMonequilandResponse viaxeAMonequiland(
        org.apache.ws.axis2.ViaxeAMonequiland viaxeAMonequiland) throws ConfigurationException, TransportException, RemoteException {
        
        buscar = new Buscar();
        
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
    
    public String TesAmiñaConsulta(String corpoCeleste, String unidades)
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
            opcions.setTo(new EndpointReference(buscar.obterDireccionWSDL("Cache")));
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
            opcions.setTo(new EndpointReference(buscar.obterDireccionWSDL("DistanciaCorpo")));
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
            /**********************************************************************/
            
            /********************CREAMOS FIOS PARA CADA PETICION***************************/
            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
            HttpClient client = new HttpClient(conmgr);
            /******************************************************************************/
            
            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
            Options opcions = new Options();
            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
            opcions.setTo(new EndpointReference(buscar.obterDireccionWSDL("TempoViaxe")));
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
    
    public void GardameIsto(String corpoCeleste, String segundoParametro, String respostaAgardar) throws RemoteException, ConfigurationException, TransportException
    {
        /*
         * Establecer conexion coa cache*/      
         /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
        OMElement elementoAgardar = null;
        OMElement contido1 = null;
        OMElement contido2 = null;
        OMElement contido3 = null;
        OMFactory factory = OMAbstractFactory.getOMFactory();
        OMNamespace nameSpace = factory.createOMNamespace("http://ws.apache.org/axis2", "ns");//Non estou seguro de que sexa asi
        ServiceClient serviceClient = new ServiceClient();
        ChamadaAsincrona chamada = new ChamadaAsincrona();
        
        /********************CREAMOS FIOS PARA CADA PETICION***************************/
        MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
        conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
        HttpClient client = new HttpClient(conmgr);
        /******************************************************************************/
        
        /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
        Options opcions = new Options();
        opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
        opcions.setTo(new EndpointReference(buscar.obterDireccionWSDL("Cache")));
        serviceClient.setOptions(opcions);
        /*****************************************************************************/
    
        /***********USAMOS A FUNCION GARDAR NA CACHE DO SERVIZO CACHE******************/
        elementoAgardar = factory.createOMElement("gardar", nameSpace);
        contido1 = factory.createOMElement("args0", nameSpace);
        contido1.setText(corpoCeleste);
        elementoAgardar.addChild(contido1);
        contido2 = factory.createOMElement("args1", nameSpace);
        contido2.setText(segundoParametro);
        elementoAgardar.addChild(contido2);
        contido3 = factory.createOMElement("args3", nameSpace);
        contido3.setText(respostaAgardar);
        elementoAgardar.addChild(contido3);
        /******************************************************************************/
    
        serviceClient.sendReceiveNonBlocking(elementoAgardar, chamada);
    }

    /*Neste metodo desrexitramos o servizo de UDDI*/
    @Override
    public void shutDown(ConfigurationContext arg0, AxisService arg1) {
        try {
            //Creamos o noso cliente JUDDI a traves da informacion gardada no arquivo uddi.xml
            //O cal garda a auntenticacions (usuario e contrasinal) do servizo
            UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");    
            UDDIClerk clerk = uddiClient.getClerk("joe");
            
            //Desrexistramos o servizo
            clerk.unRegisterWsdls();
            clerk.unRegisterBusiness(serviceKey);
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public void startUp(ConfigurationContext arg0, AxisService arg1) {
        
        try {
            UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
            Transport transport = uddiClient.getTransport("default");
            UDDISecurityPortType security = transport.getUDDISecurityService();
            UDDIPublicationPortType publish = transport.getUDDIPublishService();

            GetAuthToken getAuthTokenMyPub = new GetAuthToken();
            getAuthTokenMyPub.setUserID("AntonSergioTomas");
            getAuthTokenMyPub.setCred("AST");
            AuthToken myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);

            BusinessEntity myBusEntity = new BusinessEntity();
            Name myBusName = new Name();
            myBusName.setValue("Orquestrador");
            myBusEntity.getName().add(myBusName);

            SaveBusiness sb = new SaveBusiness();
            sb.getBusinessEntity().add(myBusEntity);
            sb.setAuthInfo(myPubAuthToken.getAuthInfo());
            BusinessDetail bd = publish.saveBusiness(sb);
            String BusKey = bd.getBusinessEntity().get(0).getBusinessKey();

            BusinessService myService = new BusinessService();
            myService.setBusinessKey(BusKey);
            Name myServName = new Name();
            myServName.setValue("Orquestrador");
            myService.getName().add(myServName);

            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue("http://localhost:8080/axis2/services/Orquestrador");
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);
            myService.setBindingTemplates(myBindingTemplates);

            SaveService ss = new SaveService();
            ss.getBusinessService().add(myService);
            ss.setAuthInfo(myPubAuthToken.getAuthInfo());
            ServiceDetail sd = publish.saveService(ss);
            this.serviceKey = sd.getBusinessService().get(0).getServiceKey();
            
        } catch (ConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransportException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DispositionReportFaultMessage e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   

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
        Iterator respuestas = arg0.getEnvelope().getBody()
                .getFirstChildWithName(new javax.xml.namespace.QName("http://cache", "cacheResponse", "ns"))
                .getChildElements();
        while (respuestas.hasNext()) {
        //  OrquestradorSkeleton.respostaAsincrona = ((OMElement) respuestas.next()).getText();
        }
        
    }
    }
