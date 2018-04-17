/**
 * DistanciaCorpoCelesteSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package org.apache.ws.axis2;

/**
 *  DistanciaCorpoCelesteSkeleton java skeleton for the axisService
 */
public class DistanciaCorpoCelesteSkeleton {
    /**
     * Auto generated method signature
     *
     * @param getDistancia
     * @return getDistanciaResponse
     */
    public org.apache.ws.axis2.GetDistanciaResponse getDistancia(
        org.apache.ws.axis2.GetDistancia getDistancia) {
      	String corpo = getDistancia.getArgs0();
    	GetDistanciaResponse distanciaE1 = new GetDistanciaResponse();
    	distanciaE1.set_return(ConsultaDB(corpo));
    	return distanciaE1;
    }
    
    public String ConsultaDB(String cuerpo) {

    	if(cuerpo.equalsIgnoreCase("Lua") || cuerpo.equalsIgnoreCase("Lúa") ) {
    		
    		return "384400";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Mercurio")) {
    		
    		return "91690000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Venus")) {
    		
    		return "42000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Marte")) {
    		
    		return "69000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Jupiter")) {
    		
    		return "591000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Saturno")) {
    		
    		return "1200000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Urano")) {
    		
    		return "2543164000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Neptuno")) {
    		
    		return "4500000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Pluton") || cuerpo.equalsIgnoreCase("Plutón")) {
    		
    		return "5929000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Sol")) {
    		
    		return "149600000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Io") || cuerpo.equalsIgnoreCase("Ío")) {
    		
    		return "628300000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Europa")) {
    		
    		return "628300000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Calisto")) {
    		
    		return "628300000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Ganimedes") || cuerpo.equalsIgnoreCase("Ganímedes")) {
    		
    		return "628300000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Mimas")) {
    		
    		return "1272000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Tetis")) {
    		
    		return "1200000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Rea")) {
    		
    		return "1200000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Umbriel")) {
    		
    		return "2543164000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Miranda")) {
    		
    		return "2543164000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Ariel")) {
    		
    		return "2543164000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Porteo")) {
    		
    		return "4500000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Triton") || cuerpo.equalsIgnoreCase("Tritón")) {
    		
    		return "4338000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Caronte")) {
    		
    		return "5928000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Eris")) {
    		
    		return "14316000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Fobos")) {
    		
    		return "77790000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Deimos")) {
    		
    		return "77800000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Proxima Centauri") || cuerpo.equalsIgnoreCase("Próxima Centauri")) {
    		
    		return "41343000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Estrela de Barnard")) {
    		
    		return "56670000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Wolf 359")) {
    		
    		return "73746400000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Lalande 21185")) {
    		
    		return "78590300000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Sirio")) {
    		
    		return "81457000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Luyten 726-8")) {
    		
    		return "82687000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Ross 154")) {
    		
    		return "90634000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Ross 248")) {
    		
    		return "97446000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Alfa Centauri")) {
    	
    		return "41343000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Icarus")) {
    
    		return "85149000000000000000000";
    	}
    	
    	if(cuerpo.equalsIgnoreCase("Universo Observabel")) {
 
    		return "1296157000000000000000000";
    	}
    	

    		return "Este corpo celeste non se atopa na base de datos"; // Indica que el cuerpo pasado por parámetro no está en la DB.

    	
    }

}
