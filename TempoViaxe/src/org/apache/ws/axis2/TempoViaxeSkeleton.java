/**
 * TempoViaxeSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package org.apache.ws.axis2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 *  TempoViaxeSkeleton java skeleton for the axisService
 */
public class TempoViaxeSkeleton{
	
	static final long VITRASA = 60;
	static final long EstrelaDaMorte =  (long)5.32*(long)Math.pow(10,17);
	static final long FalconMilenario = (long)1.33 * (long)Math.pow(10,18);
	static final long CarreroBlanco = 36;
	static final long JACKSON = 2;
	static final BigDecimal DIA = new BigDecimal(24);
	static final BigDecimal SEMANA = new BigDecimal(168);
	static final BigDecimal MES = new BigDecimal(720);
	static final BigDecimal ANO = new BigDecimal(8640);
	static final BigDecimal LUSTRO = new BigDecimal(43200);
	static final BigDecimal DECADA = new BigDecimal(86400);
	static final BigDecimal SECULO = new BigDecimal(864000);
	static final BigDecimal MILENIO = new BigDecimal(864000);
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
    	case "Estrela da morte":
    	    velocidade = new BigDecimal(EstrelaDaMorte+"");
    	    break;
    	case "Falcon Milenario":
    	    velocidade = new BigDecimal(FalconMilenario+"");
    	    break;
    	case "Carrero blanco":
    	    velocidade = new BigDecimal(CarreroBlanco+"");
    	    break;
    	case "Michael Jackson facendo o Moonwalk":
    	    velocidade = new BigDecimal(JACKSON+"");
    	    break;

    	default:
    		resposta.set_return("erro");
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
    	if(tempo.compareTo(LUSTRO) == -1)
    	{
    		tempo = tempo.divide(ANO, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Anos");
        	return resposta;
    	}
    	if(tempo.compareTo(DECADA) == -1)
    	{
    		tempo = tempo.divide(LUSTRO, 4, RoundingMode.CEILING);
    		tempoS = String.valueOf(tempo);
        	resposta.set_return(tempoS + " Lustros");
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
    


}

