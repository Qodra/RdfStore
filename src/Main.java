
public class Main {

	/**
	 * @param args
	 */
	//Filas
	private static final String CRAWLER = "qodra.Crawler.RdfStore";
	private static final String ASR = "qodra.ASR.RdfStore";
	private static final String ANNOTATIONTOOL = "qodra.AnnotationTool.RdfStore";
	private static final String FEEDBACK = "qodra.Feedback.RdfStore";
	private static final String BUSCA = "qodra.Busca.RdfStore";
	
	public static void main(String[] args)throws InterruptedException {
		// TODO Auto-generated method stub
		try {
		    //crawller
			new ActiveMQ().registerConsumer(CRAWLER);	
			//ASR	
			new ActiveMQ().registerConsumer(ASR);
			//AnnotationTool
			new ActiveMQ().registerConsumer(ANNOTATIONTOOL);
			//Feedback
			new ActiveMQ().registerConsumer(FEEDBACK);
			//Busca
			new ActiveMQ().registerConsumer(BUSCA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
