package org.apache.ws.axis2;


import java.rmi.RemoteException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.apache.juddi.v3.client.transport.TransportException;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.Name;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;

public class Buscar
{
	private static UDDIInquiryPortType inquiry = null;
	private static UDDISecurityPortType security = null;
	private FindBusiness lista;
	UDDIClerk clerk;
	UDDIClient uddiClient;
	String wsdl;

	public Buscar () throws TransportException, ConfigurationException
	{
		uddiClient = new UDDIClient("META-INF/uddi.xml");
		clerk = uddiClient.getClerk("joe");

		Transport transport = uddiClient.getTransport("default");

		security = transport.getUDDISecurityService();
		inquiry = transport.getUDDIInquiryService();

		String token = GetAuthKey("AntonSergioTomas", "AST");

		lista = new FindBusiness();
		lista.setAuthInfo(token);
	}

	public String obterDireccionWSDL (String busqueda) throws RemoteException, ConfigurationException, TransportException
	{
		FindBusiness fb = new FindBusiness();
        fb.setAuthInfo(lista.getAuthInfo());
        org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
        fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);

        fb.setFindQualifiers(fq);
        Name searchname = new Name();
        searchname.setValue(busqueda);
        fb.getName().add(searchname);
        String key = inquiry.findBusiness(fb).getBusinessInfos().getBusinessInfo().get(0).getBusinessKey();
		
		BusinessEntity businessEntity = null;

		businessEntity = clerk.findBusiness(key);

		if (businessEntity != null)
		{
			for (BusinessService businessService : businessEntity.getBusinessServices().getBusinessService())
				for (int i = 0; i < businessService.getBindingTemplates().getBindingTemplate().size(); i++)
				{
					BindingTemplate bindingTemplate = businessService.getBindingTemplates().getBindingTemplate().get(i);
					wsdl =  bindingTemplate.getAccessPoint().getValue();
				}
		}
		
		return wsdl;
	}

	private String GetAuthKey (String username, String password)
	{
		try
		{
			GetAuthToken getAuthTokenRoot = new GetAuthToken();
			getAuthTokenRoot.setUserID(username);
			getAuthTokenRoot.setCred(password);

			AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);

			return rootAuthToken.getAuthInfo();
		}
		catch (Exception ex)
		{
			System.out.println("Autentificacion fallida " + ex.getMessage());
		}
		return null;
	}

}
