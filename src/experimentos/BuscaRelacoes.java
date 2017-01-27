package experimentos;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import ufjf.GetVideosUFJF;
import ufjf.Video;

public class BuscaRelacoes {
	
	public static ArrayList<Video> getVideosRelacionados(){
        try {
            //Recupera todos os ids de videos
            ArrayList<String> idVideos = GetVideosUFJF.getAllId();
            
            ArrayList<Video> listaVideos = new ArrayList<>();

            for (String idVideo:idVideos){

                Video video = new Video(idVideo);
                
                video.setReferences(GetVideosUFJF.getRefences(video.getId()));
                
                //Relaciona os videos 
                long millis = System.currentTimeMillis();
                
                video.addAllVideoRelacionadoRanking(GetVideosUFJF.getVideosRelacionados(video.getId()));
                //fim do relacionamento, calcula o tempo
                
                video.setTempoRelacionar((System.currentTimeMillis()) - millis);
                
                video.setTempoDBPedia(GetVideosUFJF.getTempoDBPedia(video.getId()));
                
                listaVideos.add(video);
                
                System.out.print(".");//progresso
            }

            return listaVideos;

        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return null;
        }
    }
}
