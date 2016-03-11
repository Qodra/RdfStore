import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.JMSException;


public class ActiveMQ implements ExceptionListener{
	//ActiveMQ Constants
		private static final String AMQ_SERVER = "tcp://200.131.219.35:61616";
		private static final String AMQ_USERNAME = "admin";
		private static final String AMQ_PASSWORD = "DAmgNj";
		
	
	public void registerConsumer(String FILA){		
		try {
			 // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(AMQ_USERNAME, AMQ_PASSWORD, AMQ_SERVER);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(FILA);
           

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);
           
            //criando o listener
            
            MessageListener listner = new MessageListener() {
                public void onMessage(Message message) {
                	try {
                        if (message instanceof TextMessage) {
                            TextMessage textMessage = (TextMessage) message;
                            String text = textMessage.getText() + "";
                            Allegro qodra = new Allegro();
                            qodra.addNt(text);
                            qodra.closeAll();
                        }
                    } catch (Exception e) {
                        System.out.println("Caught:" + e);
                        e.printStackTrace();
                    } 
                }	    
            };
            consumer.setMessageListener(listner);
            /*
            // Wait for a message
            String msg;
            Message message = consumer.receive(1000);
            
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                msg = text;
            } else {
                msg = "" + message;
            }

            consumer.close();
            session.close();
            connection.close();
            
            return msg;
            */
       } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }	
	}
	
    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
}
	