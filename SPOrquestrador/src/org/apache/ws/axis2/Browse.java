

package org.apache.ws.axis2;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.axis2.transport.udp.Endpoint;
import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BindingTemplates;
import org.uddi.api_v3.BusinessDetail;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessInfos;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Contacts;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.DiscardAuthToken;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.GetBusinessDetail;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfos;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDISecurityPortType;


public class Browse {

	private static UDDISecurityPortType security = null;
	private static UDDIInquiryPortType inquiry = null;
	private static String servicio ="";

	public Browse() {
		try {
			// create a manager and read the config in the archive; 
			// you can use your config file name
			UDDIClient client = new UDDIClient("/home/ssmonsc/suniversidade/ast/uddi.xml"); 
			// a UDDIClient can be a client to multiple UDDI nodes, so 
			// supply the nodeName (defined in your uddi.xml.
			// The transport can be WS, inVM, RMI etc which is defined in the uddi.xml
			Transport transport = client.getTransport("default");
			// Now you create a reference to the UDDI API
			security = transport.getUDDISecurityService();
			inquiry = transport.getUDDIInquiryService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public String Browse(String serv) {
		String endpoint="";
		try {
			String token = GetAuthKey("uddi", "uddi");
			BusinessList findBusiness = GetBusinessList(token);
			endpoint = busqueda(findBusiness.getBusinessInfos(),token,serv);
			security.discardAuthToken(new DiscardAuthToken(token));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return endpoint;
	}

	private BusinessList GetBusinessList(String token) throws Exception {
		FindBusiness fb = new FindBusiness();
		fb.setAuthInfo(token);
		org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
		fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
		fb.setFindQualifiers(fq);
		Name searchname = new Name();
		searchname.setValue(UDDIConstants.WILDCARD);
		fb.getName().add(searchname);
		BusinessList findBusiness = inquiry.findBusiness(fb);
		return findBusiness;

	}

	/**
	 * Converts category bags of tmodels to a readable string
	 *
	 * @param categoryBag
	 * @return
	 */
	private String CatBagToString(CategoryBag categoryBag) {
		StringBuilder sb = new StringBuilder();
		if (categoryBag == null) {
			return "no data";
		}
		for (int i = 0; i < categoryBag.getKeyedReference().size(); i++) {
			sb.append(KeyedReferenceToString(categoryBag.getKeyedReference().get(i)));
		}
		for (int i = 0; i < categoryBag.getKeyedReferenceGroup().size(); i++) {
			sb.append("Key Ref Grp: TModelKey=");
			for (int k = 0; k < categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().size(); k++) {
				sb.append(KeyedReferenceToString(categoryBag.getKeyedReferenceGroup().get(i).getKeyedReference().get(k)));
			}
		}
		return sb.toString();
	}

	private String KeyedReferenceToString(KeyedReference item) {
		StringBuilder sb = new StringBuilder();
		sb.append("Key Ref: Name=").
		append(item.getKeyName()).
		append(" Value=").
		append(item.getKeyValue()).
		append(" tModel=").
		append(item.getTModelKey()).
		append(System.getProperty("line.separator"));
		return sb.toString();
	}

	private enum AuthStyle {

		HTTP_BASIC,
		HTTP_DIGEST,
		HTTP_NTLM,
		UDDI_AUTH,
		HTTP_CLIENT_CERT
	}

	private String GetAuthKey(String username, String password) {
		try {

			GetAuthToken getAuthTokenRoot = new GetAuthToken();
			getAuthTokenRoot.setUserID(username);
			getAuthTokenRoot.setCred(password);

			// Making API call that retrieves the authentication token for the user.
			AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
			System.out.println(username + " AUTHTOKEN = (don't log auth tokens!");
			return rootAuthToken.getAuthInfo();
		} catch (Exception ex) {
			System.out.println("Could not authenticate with the provided credentials " + ex.getMessage());
		}
		return null;
	}

	private String busqueda(BusinessInfos businessInfos,String token,String serv) throws DispositionReportFaultMessage, RemoteException {
		String endpoint = "";
		if (businessInfos == null) {
			System.out.println("No data returned");
		} else {
			for (int i = 0; i < businessInfos.getBusinessInfo().size(); i++) {
				BusinessInfo business = businessInfos.getBusinessInfo().get(i);
				System.out.println(ListToString(business.getName()).trim());
				if(ListToString(business.getName()).trim().equalsIgnoreCase("AST")){
					GetServiceDetail gsd = new GetServiceDetail();
					for (int k = 0; k < business.getServiceInfos().getServiceInfo().size(); k++) {
						gsd.getServiceKey().add(business.getServiceInfos().getServiceInfo().get(k).getServiceKey());
					}
					gsd.setAuthInfo(token);
					ServiceDetail serviceDetail = inquiry.getServiceDetail(gsd);
					for (int k = 0; k < serviceDetail.getBusinessService().size(); k++) {
						BusinessService servicio = serviceDetail.getBusinessService().get(k);
						if(ListToString(servicio.getName()).trim().equalsIgnoreCase(serv)){
							BindingTemplates bindingTemplates = servicio.getBindingTemplates();
							if (bindingTemplates != null) {
								for (int j = 0; j < bindingTemplates.getBindingTemplate().size(); j++) {
									if (bindingTemplates.getBindingTemplate().get(j).getAccessPoint() != null) {
										System.out.println("Access Point: " + bindingTemplates.getBindingTemplate().get(j).getAccessPoint().getValue() + " type " + bindingTemplates.getBindingTemplate().get(j).getAccessPoint().getUseType());
										return bindingTemplates.getBindingTemplate().get(j).getAccessPoint().getValue();
									}
								}
							}
						}

					}
				}

			}
		}
		return null;
	}

	private String ListToString(List<Name> name) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < name.size(); i++) {
			sb.append(name.get(i).getValue()).append(" ");
		}
		return sb.toString();
	}

	private String ListToDescString(List<Description> name) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < name.size(); i++) {
			sb.append(name.get(i).getValue()).append(" ");
		}
		return sb.toString();
	}


	

	
}