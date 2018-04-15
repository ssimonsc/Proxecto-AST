import org.apache.axis2.handlers.AbstractHandler;

import java.util.Iterator;

import org.apache.axiom.soap.impl.llom.soap11.SOAP11HeaderBlockImpl;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.engine.Handler;

public class LogHandler extends AbstractHandler implements Handler{
	
	private String ID = "AntonSergioTomas";
	
	@Override
	public InvocationResponse invoke(MessageContext ctx) throws AxisFault {
		Iterator cabeceira = ctx.getEnvelope().getHeader().getChildren();
		while(cabeceira.hasNext())
		{
			SOAP11HeaderBlockImpl campo = (SOAP11HeaderBlockImpl) cabeceira.next();
			if(campo.localName.equalsIgnoreCase("meuID")&&campo.getText().equalsIgnoreCase(ID))
				return InvocationResponse.CONTINUE;
		}	
		return InvocationResponse.ABORT;
	}

	 public void revoke(MessageContext ctx) {

	    }
	
}
