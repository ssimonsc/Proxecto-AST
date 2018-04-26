/**
 * TempoViaxeSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package org.apache.ws.axis2;


import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;


/**
 *  TempoViaxeSkeleton java skeleton for the axisService
 */
public class TempoViaxeSkeleton implements ServiceLifeCycle{
	
    
	 private static String bus_serv = null;
	private static String bus_key;
	private static String serv_key;
	private static String miIP;
	private static String endpoint;
	private static UDDI uddi;
	
	static final long VITRASA = 60;
	static final long COHETE = 400000;
	static final long EstrelaDaMorte =  (long)5.32*(long)Math.pow(10,17);
	static final long FalconMilenario = (long)1.33 * (long)Math.pow(10,18);
	static final long CarreroBlanco = 36;
	static final long JACKSON = 2;
	static final BigDecimal DIA = new BigDecimal(24);
	static final BigDecimal SEMANA = new BigDecimal(168);
	static final BigDecimal MES = new BigDecimal(720);
	static final BigDecimal ANO = new BigDecimal(8640);
	static final BigDecimal DECADA = new BigDecimal(86400);
	static final BigDecimal SECULO = new BigDecimal(864000);
	static final BigDecimal MILENIO = new BigDecimal(8640000);
	static final BigDecimal EON = new BigDecimal("8640000000000");
	
    /**
     * Auto generated method signature
     *
     * @param getTempo
     * @return getTempoResponse
     */
    public org.apache.ws.axis2.GetTempoResponse getTempo(
        org.apache.ws.axis2.GetTempo datos) {
        //TODO : fill this with the necessary business logic
    	BigDecimal velocidade = null;
    	BigDecimal distancia = null;
    	BigDecimal zero = new BigDecimal((long)0+"");

    	distancia = new BigDecimal(datos.getArgs0());
    	String vehiculo = datos.getArgs1();
    	
    	GetTempoResponse resposta = new GetTempoResponse();
   
    	if(distancia.compareTo(zero) != 1)
    	{
    		resposta.set_return("erro");
        	return resposta;
    	}
    	
    	switch(vehiculo){

    	case "Vitrasa":
    	    velocidade = new BigDecimal(VITRASA+"");
    	    break;
    	case "Cohete Espacial":
    	    velocidade = new BigDecimal(COHETE+"");
    	    break;
    	case "Estrela da Morte":
    	    velocidade = new BigDecimal(EstrelaDaMorte+"");
    	    break;
    	case "Falcon Milenario":
    	    velocidade = new BigDecimal(FalconMilenario+"");
    	    break;
    	case "Carrero Blanco":
    	    velocidade = new BigDecimal(CarreroBlanco+"");
    	    break;
    	case "Michael Jackson facendo o Moonwalk":
    	    velocidade = new BigDecimal(JACKSON+"");
    	    break;

    	default:
    		resposta.set_return("Ese vehiculo non se atopa na Base de datos");
        	return resposta;
    	}
    	

    	BigDecimal tempo = distancia.divide(velocidade, 4, RoundingMode.CEILING);
    	String tempoS = null;
    	if(tempo.compareTo(DIA) == -1)
    	{    		
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Horas");
        	return resposta;
    	}
    	if(tempo.compareTo(SEMANA) == -1)
    	{
    		tempo = tempo.divide(DIA, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Dias");
        	return resposta;
    	}
    	if(tempo.compareTo(MES) == -1)
    	{
    		tempo = tempo.divide(SEMANA, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Semanas");
        	return resposta;
    	}
    	if(tempo.compareTo(ANO) == -1)
    	{
    		tempo = tempo.divide(MES, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Meses");
        	return resposta;
    	}
    	if(tempo.compareTo(DECADA) == -1)
    	{
    		tempo = tempo.divide(ANO, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Anos");
        	return resposta;
    	}
    	if(tempo.compareTo(SECULO) == -1)
    	{
    		tempo = tempo.divide(DECADA, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Decadas");
        	return resposta;
    	}
    	if(tempo.compareTo(MILENIO) == -1)
    	{
    		tempo = tempo.divide(SECULO, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Seculos");
        	return resposta;
    	}
    	if(tempo.compareTo(EON) == -1)
    	{
    		tempo = tempo.divide(MILENIO, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Milenios");
        	return resposta;
    	}
    	
		tempo = tempo.divide(EON, 4, RoundingMode.CEILING);
		tempoS = String.valueOf(tempo);
    	resposta.set_return(tempoS + " Eones");
    	return resposta;
    }

	@Override
	public void shutDown(ConfigurationContext arg0, AxisService arg1) {
		System.out.println(bus_key);
		bus_key=bus_key.trim();
		serv_key=serv_key.trim();
		uddi.unPublish(serv_key,bus_key,"TempoViaxe","AST");
		
	}

	@Override
	public void startUp(ConfigurationContext arg0, AxisService arg1) 
	{
		 //System.out.println("hola");
        UDDI sp = new UDDI();
        String serv = "TempoViaxe";
        String endpoint = "http://localhost:8080/axis2/services/TempoViaxe";
        
       bus_serv =sp.publish(serv, endpoint);
        
		if(bus_serv!=null){
			String cad[] = bus_serv.split(",");
			serv_key = cad[0];
			bus_key = cad[1];
		}
      //  System.out.println(serv_key+" "+bus_key);
	}
}
		
