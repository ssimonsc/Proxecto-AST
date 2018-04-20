/**
 * OrquestradorMessageReceiverInOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package org.apache.ws.axis2;


/**
 *  OrquestradorMessageReceiverInOut message receiver
 */
public class OrquestradorMessageReceiverInOut extends org.apache.axis2.receivers.AbstractInOutMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext,
        org.apache.axis2.context.MessageContext newMsgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            OrquestradorSkeleton skel = (OrquestradorSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("viaxeAMonequiland".equals(methodName)) {
                    org.apache.ws.axis2.ViaxeAMonequilandResponse viaxeAMonequilandResponse9 =
                        null;
                    org.apache.ws.axis2.ViaxeAMonequiland wrappedParam = (org.apache.ws.axis2.ViaxeAMonequiland) fromOM(msgContext.getEnvelope()
                                                                                                                                  .getBody()
                                                                                                                                  .getFirstElement(),
                            org.apache.ws.axis2.ViaxeAMonequiland.class);

                    viaxeAMonequilandResponse9 = skel.viaxeAMonequiland(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            viaxeAMonequilandResponse9, false,
                            new javax.xml.namespace.QName(
                                "http://ws.apache.org/axis2",
                                "viaxeAMonequilandResponse"));
                } else
                 if ("distanciaAMonequiland".equals(methodName)) {
                    org.apache.ws.axis2.DistanciaAMonequilandResponse distanciaAMonequilandResponse11 =
                        null;
                    org.apache.ws.axis2.DistanciaAMonequiland wrappedParam = (org.apache.ws.axis2.DistanciaAMonequiland) fromOM(msgContext.getEnvelope()
                                                                                                                                          .getBody()
                                                                                                                                          .getFirstElement(),
                            org.apache.ws.axis2.DistanciaAMonequiland.class);

                    distanciaAMonequilandResponse11 = skel.distanciaAMonequiland(wrappedParam);

                    envelope = toEnvelope(getSOAPFactory(msgContext),
                            distanciaAMonequilandResponse11, false,
                            new javax.xml.namespace.QName(
                                "http://ws.apache.org/axis2",
                                "distanciaAMonequilandResponse"));
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }

                newMsgContext.setEnvelope(envelope);
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(
        org.apache.ws.axis2.ViaxeAMonequiland param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.apache.ws.axis2.ViaxeAMonequiland.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.apache.ws.axis2.ViaxeAMonequilandResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.apache.ws.axis2.ViaxeAMonequilandResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.apache.ws.axis2.DistanciaAMonequiland param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.apache.ws.axis2.DistanciaAMonequiland.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        org.apache.ws.axis2.DistanciaAMonequilandResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(org.apache.ws.axis2.DistanciaAMonequilandResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.apache.ws.axis2.ViaxeAMonequilandResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.apache.ws.axis2.ViaxeAMonequilandResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.ws.axis2.ViaxeAMonequilandResponse wrapviaxeAMonequiland() {
        org.apache.ws.axis2.ViaxeAMonequilandResponse wrappedElement = new org.apache.ws.axis2.ViaxeAMonequilandResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        org.apache.ws.axis2.DistanciaAMonequilandResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    org.apache.ws.axis2.DistanciaAMonequilandResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.ws.axis2.DistanciaAMonequilandResponse wrapdistanciaAMonequiland() {
        org.apache.ws.axis2.DistanciaAMonequilandResponse wrappedElement = new org.apache.ws.axis2.DistanciaAMonequilandResponse();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (org.apache.ws.axis2.DistanciaAMonequiland.class.equals(type)) {
                return org.apache.ws.axis2.DistanciaAMonequiland.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.apache.ws.axis2.DistanciaAMonequilandResponse.class.equals(
                        type)) {
                return org.apache.ws.axis2.DistanciaAMonequilandResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.apache.ws.axis2.ViaxeAMonequiland.class.equals(type)) {
                return org.apache.ws.axis2.ViaxeAMonequiland.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (org.apache.ws.axis2.ViaxeAMonequilandResponse.class.equals(type)) {
                return org.apache.ws.axis2.ViaxeAMonequilandResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
