import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.TableCellRenderer;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.soap.SOAPHeaderBlock;
import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;





public class DosVentanas extends JFrame{
	

  
	private static JFrame ventanaPrincipal;
	private static JDialog ventanaSecundariaTiempo;
	private static JDialog ventanaSecundariaDistancia;
	private static JTextField distanciaField;
	
	private static  JRadioButton planet1;
	private static  JRadioButton planet2;
	private static  JRadioButton planet3;
	private static  JRadioButton planet4;
	private static  JRadioButton planet5;
	
	private static JRadioButton vehiculo1;
	private static JRadioButton vehiculo2;
	private static JRadioButton vehiculo3;
	private static JRadioButton vehiculo4;
	private static JRadioButton vehiculo5;
	
	private static JRadioButton unidad1;
	private static JRadioButton unidad2;
	private static JRadioButton unidad3;
	
	private static ServiceClient serviceClient;
	
	static String planeta = null;
	static String segundoParametro = null;
	static String servizo = null;
	static boolean espera = true;
	
	
	
	public static void main(String[] args) throws AxisFault {
		ventanaPrincipal = new DosVentanas();
			ventanaPrincipal.setVisible(true);
			/*
	         * Establcer conexion co servizo Tempo Viaxe*/
	    //    try
	      //  {
		while(true)
		{
		int a = 0;
		while(espera)
			{	
			System.out.print("");
				if(planeta != null && segundoParametro !=null)
				{	espera = false;

				}
			}
		
	            String miñaConsulta1 = planeta;
	            String miñaConsulta2 = segundoParametro;
	            
	            planeta = null;
	            segundoParametro = null;
	            espera = true;
	            /**********ELEMENTO NESCESARIOS PARA ESTBLCER A CONEXION***********/
	            OMElement consulta = null;
	            OMElement contido1 = null;
	      //   OMElement contido2 = null;
	            OMFactory factory = OMAbstractFactory.getOMFactory();
	            OMNamespace nameSpace = factory.createOMNamespace("http://ws.apache.org/axis2", "ns");//Non estou seguro de que sexa asi
	            serviceClient = new ServiceClient();
	            /**********************************************************************/
	            
	            /********************CREAMOS FIOS PARA CADA PETICION***************************/
	            MultiThreadedHttpConnectionManager conmgr = new MultiThreadedHttpConnectionManager();
	            conmgr.getParams().setDefaultMaxConnectionsPerHost(10);
	            HttpClient client = new HttpClient(conmgr);
	            /******************************************************************************/
	            Browse buscar = new Browse();
	            /***********ESTABLECEMOS AS OPCIONS DA NOSA CONEXION*************************/
	            Options opcions = new Options();
	            opcions.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, client);
	            String end = buscar.Browse("Orquestrador");
	            opcions.setTo(new EndpointReference(buscar.Browse("Orquestrador")));
	            opcions.setAction("urn:"+servizo);
	            opcions.setTransportInProtocol(Constants.TRANSPORT_HTTP);

	            serviceClient.setOptions(opcions);
	            crearCabecera();
	            /*****************************************************************************/
	        
	            /***********USAMOS A FUNCION BUSCAR NA CACHE DO SERVIZO CACHE******************/
	            consulta = factory.createOMElement(servizo, nameSpace);
	            contido1 = factory.createOMElement("args0", nameSpace);
	            contido1.setText(miñaConsulta1);  
	            consulta.addChild(contido1);
	           contido1 = factory.createOMElement("args1", nameSpace);
	            contido1.setText(miñaConsulta2);
	            consulta.addChild(contido1);
	          //  serviceClient.sendRobust(consulta); //Enviamos
	          
	            /******************************************************************************/
	        
	            /**Obtemos a resposta da cache***/
	          //  OMElement resposta = factory.createOMElement("getTitular", nameSpace); //Creamos o elemento resposta
	    	//	String titular = serviceClient.sendReceive(resposta).getFirstElement().getText(); //obtemos a resposta en formato String
	          String respostaTempo= serviceClient.sendReceive(consulta).getFirstElement().getText();
	           System.out.println(respostaTempo);
		}
	        }
		

	
	private static void crearCabecera() {
		// Creating a SOAPFactory
		org.apache.axiom.soap.SOAPFactory factory = OMAbstractFactory.getSOAP11Factory();
		// Creating a namespace for the header
		OMNamespace ns = factory.createOMNamespace("http://ws.apache.org/axis2", "ns");
		// creating a SOAP header block

		SOAPHeaderBlock pass = factory.createSOAPHeaderBlock("meuID", ns);

		pass.setText("AntonSergioTomas");

		// adding the created header block to ServiceClient
		serviceClient.addHeader(pass);
	}
      /*  catch(Exception e) {
          System.out.println("ERRO");
        }*/
	
	
	   private static final long serialVersionUID = -6650115843758904110L;
	     
	    private static final String pathImage="Imaxes/espazo1.jpg";
	     
	    public static final short WINDOW_WIDTH = 930;
	    public static final short WINDOW_HEIGTH = 420;
	 
	    protected JPanel panelPrincipal  =null;
	
	public DosVentanas()
	{
	 
		// Construcción de ventana principal
		super("Ventana principal");	
	
		JPanel panelPrincipal = new JPanel(){
            private static final long serialVersionUID = 1L;
            
            ImageIcon imageBackground = new ImageIcon(pathImage);
            
               @Override
               public void paint(Graphics graphics) {
                   graphics.drawImage(imageBackground.getImage(), 0, 0,getWidth(),getHeight(),null);
                   super.paint(graphics);
               }
       };
		//JPanel panelPrincipal = new JPanel();
		//ventanaPrincipal.add(panelPrincipal, BorderLayout.CENTER);
		//ventanaPrincipal.repaint();
       panelPrincipal.setOpaque(false);
       panelPrincipal.setForeground(Color.white);
       JScrollPane scrollPane= new JScrollPane(panelPrincipal);
       getContentPane().add(scrollPane);
        
       this.setSize(WINDOW_WIDTH,WINDOW_HEIGTH);
		panelPrincipal.setLayout(null);
		
		//ImageIcon imageBackground = new ImageIcon(caminhoImaxe);
		//Graphics g = null;
		//g.drawImage(imageBackground.getImage(), 0, 0,500,500,null);
		//panelPrincipal.paint(g);
		JButton ButtonTiempo = new JButton("Servizo Viaxe a Monequiland");
		ButtonTiempo.setBounds(10, 10, 160, 25);
		ButtonTiempo.setSize(280,30);
		
		ActionListener SelectService1 = new SelectTimeService();
		ButtonTiempo.addActionListener(SelectService1);
		panelPrincipal.add(ButtonTiempo);
		
		JButton ButtonDistancia = new JButton("Servizo Aventura a Marte");
		ButtonDistancia.setBounds(10, 100, 160, 25);
		ButtonDistancia.setSize(280,30);
		
		ActionListener SelectService2 = new SelectDistanceService();
		ButtonDistancia.addActionListener(SelectService2);
		panelPrincipal.add(ButtonDistancia);
		panelPrincipal.setVisible(true);		
		
		//Cremamos la ventana para el servicio tiempo
		ventanaSecundariaTiempo = new JDialog(ventanaPrincipal,"Servizo Viaxe a Monequiland");
		//JPanel panelPrincipal = new JPanel();
		//ventanaPrincipal.add(panelPrincipal, BorderLayout.CENTER);
		//ventanaPrincipal.repaint();
     //  ventanaSecundariaTiempo.setOpaque(false);
       ventanaSecundariaTiempo.setForeground(Color.white);
     //  JScrollPane scrollPane1= new JScrollPane(ventanaSecundariaTiempo);
      // getContentPane().add(scrollPane1);
        
       ventanaSecundariaTiempo.setSize(WINDOW_WIDTH,WINDOW_HEIGTH);
	//	ventanaSecundariaTiempo.setSize(500, 500);
		

		//Cremamos la ventana para el servicio distancia
		ventanaSecundariaDistancia = new JDialog(ventanaPrincipal,"Servizo Aventura a Marte");
		ventanaSecundariaDistancia.setSize(WINDOW_WIDTH,WINDOW_HEIGTH);
		
		
		//*********************************************************************** SERVICIO DISANCIA ***********************************************************************************************

		//Creamos el panel para el servicio distancia
		JPanel panelDistancia = new JPanel(){
            private static final long serialVersionUID = 1L;
            
            ImageIcon imageBackground = new ImageIcon("Imaxes/marte.png");
            
               @Override
               public void paint(Graphics graphics) {
                   graphics.drawImage(imageBackground.getImage(), 0, 0,getWidth(),getHeight(),null);
                   super.paint(graphics);
               }
       };
		//JPanel panelPrincipal = new JPanel();
		//ventanaPrincipal.add(panelPrincipal, BorderLayout.CENTER);
		//ventanaPrincipal.repaint();
       panelDistancia.setOpaque(false);
       panelDistancia.setForeground(Color.white);
       
        
       this.setSize(WINDOW_WIDTH,WINDOW_HEIGTH);
		ventanaSecundariaDistancia.add(panelDistancia);
		panelDistancia.setLayout(null);
		
		//Creamos el label de las unidades
		JLabel unitsLabel = new JLabel("Soles");
		unitsLabel.setForeground(Color.ORANGE);
		unitsLabel.setBounds(200, 10, 160, 25);
		panelDistancia.add(unitsLabel);
		
		//Creamos los botones de las unidades
		unidad1 = new  JRadioButton("100",true);
		unidad1.setBounds(10, 40, 160, 25);
		unidad2 = new  JRadioButton("500",false);
		unidad2.setBounds(10, 70, 160, 25);
		unidad3 = new  JRadioButton("1000",false);
		unidad3.setBounds(10, 100, 160, 25);
		 
		//Metemos todos los botones de las unidades en el mismo grupo de botones
		ButtonGroup grupoDistancia = new ButtonGroup();
		grupoDistancia.add(unidad1);
		grupoDistancia.add(unidad2);
		grupoDistancia.add(unidad3);
		
		//Metemos los botones de los planetas en el panel del Servicio Distancia
		panelDistancia.add(unidad1);
		panelDistancia.add(unidad2);
		panelDistancia.add(unidad3);
		
		//Creamos el botón que eviará los datos para el servicio Distancia
		JButton SendDistanceButton = new JButton("Enviar");
		SendDistanceButton.setBounds(10, 140, 160, 25);
		ActionListener SendDistanceButtonListener = new SendDistanceButtonListener();
		SendDistanceButton.addActionListener(SendDistanceButtonListener);
		panelDistancia.add(SendDistanceButton);
		
		//Creamos el botón de retorno para el servicio Distancia
		JButton returnButtonDistance = new JButton("Abre principal");
		returnButtonDistance.setBounds(200, 140, 160, 25);
		ActionListener ReturnButtonListener2 = new ReturnButtonListener();
		returnButtonDistance.addActionListener(ReturnButtonListener2);
		panelDistancia.add(returnButtonDistance);	
		panelDistancia.setVisible(true);

		//*********************************************************************** SERVICIO TIEMPO *************************************************************************************************

		//Creamos el panel para el servicio tiempo
		JPanel panelTiempo = new JPanel(){
            private static final long serialVersionUID = 1L;
            
            ImageIcon imageBackground = new ImageIcon("Imaxes/planetaDescoñecido.jpg");
            
               @Override
               public void paint(Graphics graphics) {
                   graphics.drawImage(imageBackground.getImage(), 0, 0,getWidth(),getHeight(),null);
                   super.paint(graphics);
               }
       };
		//JPanel panelPrincipal = new JPanel();
		//ventanaPrincipal.add(panelPrincipal, BorderLayout.CENTER);
		//ventanaPrincipal.repaint();
       panelTiempo.setOpaque(false);
       panelTiempo.setForeground(Color.white);
		ventanaSecundariaTiempo.add(panelTiempo);
		panelTiempo.setLayout(null);

		//Creamos el label de los vehículos
		JLabel vehicleLabel = new JLabel("Vehiculo");
		vehicleLabel.setForeground(Color.ORANGE);
		vehicleLabel.setBounds(200, 10, 160, 25);
		panelTiempo.add(vehicleLabel);
		
		//Creamos los botones de los vehículos
		vehiculo1 = new JRadioButton("Carrero Blanco",true);
		vehiculo1.setBounds(200, 40, 160, 25);
		vehiculo2 = new JRadioButton("Vitrasa",true);
		vehiculo2.setBounds(200, 70, 160, 25);
		vehiculo3 = new JRadioButton("Cohete Espacial",true);
		vehiculo3.setBounds(200, 100, 160, 25);
		vehiculo4 = new JRadioButton("Estrela da Morte",false);
		vehiculo4.setBounds(200, 130, 160, 25);
		vehiculo5 = new JRadioButton("Falcon Milenario",false);
		vehiculo5.setBounds(200, 160, 160, 25);
		 
		//Metemos todos los botones de los vehículos en el mismo grupo de botones
		ButtonGroup grupoVehiculos = new ButtonGroup();
		grupoVehiculos.add(vehiculo1);
		grupoVehiculos.add(vehiculo2);
		grupoVehiculos.add(vehiculo3);
		grupoVehiculos.add(vehiculo4);
		grupoVehiculos.add(vehiculo5);
		 
		//Metemos los botones de los vehículos en el panel del Servicio Tiempo
		panelTiempo.add(vehiculo1);
		panelTiempo.add(vehiculo2);
		panelTiempo.add(vehiculo3);
		panelTiempo.add(vehiculo4);
		panelTiempo.add(vehiculo5);
	     
		//Creamos el label de los Planetas
		JLabel planetLabel2 = new JLabel("Planeta");
		planetLabel2.setForeground(Color.ORANGE);
		planetLabel2.setBounds(10, 10, 160, 25);
		panelTiempo.add(planetLabel2);
			
		//Creamos los botones de los Planetas
		planet1 = new  JRadioButton("Mercurio",true);
		planet1.setBounds(10, 40, 160, 25);
		planet2 = new  JRadioButton("Venus",false);
		planet2.setBounds(10, 70, 160, 25);
		planet3 = new  JRadioButton("Marte",false);
		planet3.setBounds(10, 100, 160, 25);
		planet4 = new  JRadioButton("Icarus",false);
		planet4.setBounds(10, 130, 160, 25);
		planet5 = new  JRadioButton("Universo Observábel",false);
		planet5.setBounds(10, 160, 160, 25);
		 
		//Metemos todos los botones de los Planetas en el mismo grupo de botones
		ButtonGroup grupoPlanetas2 = new ButtonGroup();
		grupoPlanetas2.add(planet1);
		grupoPlanetas2.add(planet2);
		grupoPlanetas2.add(planet3);
		grupoPlanetas2.add(planet4);
		grupoPlanetas2.add(planet5);
		
		//Metemos los botones de los planetas en el panel del Servicio Ditancia
		panelTiempo.add(planet1);
		panelTiempo.add(planet2);
		panelTiempo.add(planet3);
		panelTiempo.add(planet4);
		panelTiempo.add(planet5);
		
		//Creamos el botón que eviará los datos para el servicio Tiempo
        JButton SendTimeButton = new JButton("Enviar");
        SendTimeButton.setBounds(10, 200, 160, 25);
		ActionListener SendTimeButtonListener = new SendTimeButtonListener();
		SendTimeButton.addActionListener(SendTimeButtonListener);
		panelTiempo.add(SendTimeButton);
		
		//Creamos el botón de retorno para el servicio Tiempo
		JButton returnButtonTime = new JButton("Abre principal");
		returnButtonTime.setBounds(200, 200, 160, 25);
		ActionListener ReturnButtonListener1 = new ReturnButtonListener();
		returnButtonTime.addActionListener(ReturnButtonListener1);
		panelTiempo.add(returnButtonTime);
		panelTiempo.setVisible(true);
		
		//*****************************************************************************************************************************************************************************************
		
		// Mostrar la ventana principal
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//this.setVisible(true);
	}
	
	

	
	//Hacer que el botón abra la ventana secundaria del servicio Tiempo y cierre la principal
		
	 public static class SelectTimeService implements ActionListener {

		  public void actionPerformed(ActionEvent e) { 
			ventanaPrincipal.setVisible(false);
			ventanaSecundariaTiempo.setVisible(true);
		 }	
	 }
	 
	//Hacer que el botón abra la ventana secundaria del servicio Distancia y cierre la principal
	 public static class SelectDistanceService implements ActionListener {

		  public void actionPerformed(ActionEvent e) { 
			ventanaPrincipal.setVisible(false);
			ventanaSecundariaDistancia.setVisible(true);
		 }	
	 }
		
	//Hacer que el botón abra la ventana principal y cierre las secundarias
	 public static class ReturnButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ventanaPrincipal.setVisible(true);
			ventanaSecundariaTiempo.setVisible(false);
			ventanaSecundariaDistancia.setVisible(false);
		}
			
		
	}
	 
	 //Hacer que el botón ponga el valor a la variable vehiculo que le corresponde
	 public static class SendTimeButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		   if(planet1.isSelected()==true) planeta = "Mercurio";
		   if(planet2.isSelected()==true) planeta = "Venus";
		   if(planet3.isSelected()==true) planeta = "Marte";
		   if(planet4.isSelected()==true) planeta = "Icarus";
		   if(planet5.isSelected()==true) planeta = "Universo Observabel";
		   System.out.println(planeta);	 
	       if(vehiculo1.isSelected()==true) segundoParametro = "Carrero Blanco";
	       if(vehiculo2.isSelected()==true) segundoParametro = "Vitrasa";
	       if(vehiculo3.isSelected()==true) segundoParametro = "Cohete Espacial";
	       if(vehiculo4.isSelected()==true) segundoParametro = "Estrela da Morte";
	       if(vehiculo5.isSelected()==true) segundoParametro = "Falcon Milenario";
	       System.out.println(segundoParametro); 
	       
	       servizo = "viaxeAMonequiland";
	     //  if(distancia!=null) System.out.println(distancia);
		}
			
		
	}
	 
	 public static class SendDistanceButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			planeta = "Marte";
			System.out.println(planeta);	  
		    if(unidad1.isSelected()==true) segundoParametro = "100";
		    if(unidad2.isSelected()==true) segundoParametro = "500";
		    if(unidad3.isSelected()==true) segundoParametro = "1000";

		    System.out.println(segundoParametro);	
		       
		    servizo = "distanciaAMonequiland";
		     /*  //ESTO ES PARA PROBAR LA DISTANCIA, DEBEMOS BORRARLO MÁS TARDE!!
		       if(planet1.isSelected()==true) distancia = "100000";
		       if(planet2.isSelected()==true) distancia = "98978675";
		       if(planet3.isSelected()==true) distancia = "35487968";*/
		    //   distanciaField.setText(distancia);	  
		}
			
		
	}

}

