
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import dbpedia.DBPedia;
import experimentos.Analise;
import tests.AllegroTest;
import ufjf.GetVideosUFJF;
import ufjf.Video;
import activemq.ActiveMQ;
import allegroGraph.Allegro;
	
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
		/*//parte do main para se registrar no apache activemq
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
		*/
		try {
			
			AllegroTest teste = new AllegroTest();
			
			if (teste.testaConexao()){
				int alfa = 0;
				int beta = 0;
				getResourcesDBPedia(alfa, beta);
				runTests();
			}
			
			else{
				System.out.println("Não foi possivel conectar-se a: http://localhost:10035\nRepositório: Qodra\nUsuario: super\nSenha: 1234\nVerifique as configurações do Allegrograph");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void runTests() throws Exception{
				
		File f = new File("/home/jayme/Desktop/resultado.csv"); //Altere para 
		
	    OutputStream os = (OutputStream)new FileOutputStream(f);
	    String encoding = "UTF8";
	    OutputStreamWriter osw = new OutputStreamWriter(os, encoding);
	    BufferedWriter bw = new BufferedWriter(osw);
	    
	    System.out.println("\nRelacionando vídeos");
	    
	    Analise a = new Analise();
	    
	    System.out.println("\nCalculando resultados do experimento:"); 
	    
	    boolean poda = false; //alterar para "true" para podar resuldatos
	    
	    a.calcule(bw, poda);
	    
	    System.out.println("\nImprimindo Relacionamentos..."); 
	    
	    a.imprimeRelacionamentos(bw);
	    
	    bw.flush();
	    bw.close();
	    
	    //não funcionanado, deletar categorias e tempo de busca manualmente
	    Allegro a1 = new Allegro();
	    a1.deletePredicate("dcterms:category"); //delete manualmente com: delete where {?s <dcterms:category> ?o}
	    a1.deletePredicate("dcterms:time");     //delete manualmente com: delete where {?s <dcterms:time> ?o}
	    /////////////////////////////////
	    
	    System.out.println("\nExperimento Concluído."); 
	}


	private static void getResourcesDBPedia(int alfa, int beta) throws Exception{
		
		System.out.println("Iniciando experiemento de buscas de vídeos na DBPedia...\nLendo Base de vídeos..."); 
		
		ArrayList<String> idVideos = GetVideosUFJF.getAllId();
		System.out.println("Videos recuperados"); 
		Video video;
		
		for (String id : idVideos){
			System.out.println("\n\nRecuperando referências <dcterms:references> para: "+idVideos); 
			video = new Video(id);
			System.out.print("Referências recuperadas...\nExplorando DBPedia:"); 
			video.setReferences(GetVideosUFJF.getRefences(id));
			
			DBPedia.getResourcesRelated(video, alfa, beta);
			
		}
		
		System.out.println("\n\nBuscas concluídas!"); 
	}

}
