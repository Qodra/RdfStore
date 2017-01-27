package experimentos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ufjf.Video;

public class Analise {
	
	private ArrayList<Video> videos;

    private Base b;

    public Analise() throws UnsupportedEncodingException  {
        b = new Base();
        videos = BuscaRelacoes.getVideosRelacionados();
    }

    public void calcule(BufferedWriter bw, boolean poda) throws IOException{
   
        bw.write("Video \t Referências anotadas \t Esperados \t Retornados Certos \t Total Retornados \t Precisão \t Recall \t TopN \t Tempo DBPedia(ms) \t Tempo Rel(ms)\n");
      
        int i = 1;
        for (Video v: videos){

            b.calcule(i,v, poda, bw);
            i++;

        }

    }
    
    public void imprimeRelacionamentos(BufferedWriter bw) throws IOException{
    	bw.write("\n\nRelacionamentos\nVideo \t Video Relacionado \t Total de Categorias \n");
    	
    	ArrayList<Video> videosRelacionados;
    	
    	for (Video v: videos){
    		videosRelacionados = v.getVideosRelacionadosRank();
    		
    		for (Video vRel: videosRelacionados){
    			bw.write(v.getId()+"\t"+vRel.getId()+"\t"+vRel.getTotalCategoriaRelacionadas()+"\n");
    		}

        }
        
    }

}
