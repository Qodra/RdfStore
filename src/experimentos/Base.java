package experimentos;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import ufjf.GetVideosUFJF;
import ufjf.Video;

public class Base {
	private static final double alfaTopN = 0.8;
	
	private HashMap<String, Video> videos;
	
	private int totalVideos = 0;
	
	
    public Base() throws UnsupportedEncodingException {
        videos = new HashMap<>();
        ArrayList<String> videos = GetVideosUFJF.getAllId();

        ArrayList<String> relacionados;
        for (String id: videos){
            Video v = createVideo(id);

            relacionados = GetVideosUFJF.getRelatedTo(v.getId());

            for (String r:relacionados){
                v.addRelacionado(r);
            }
        }
    }
	
	public double calculeTopN(Video videoConsultado){

        //verificar se o video está na base
        Video benchmark = videos.get(videoConsultado.getId());

        if (benchmark == null) {
            // System.out.println("Não é possivel responder pois não existe referencias para este vídeo");
            return 0d;
        }

        if (benchmark.getVideosRelacionadosRank().size() == 0) return 0d; //não ha referências para este video

        double denominador = 0d;
        double numerador = 0d;

        int potenciaDenominador = 1;
        int potenciaNumerador = 1;

        //ordena os videos relacionados pelo total de categorias em comun

        videoConsultado.ordenaRelacionadosPorTotalVideosRelacionados();


        HashMap<String, Video> videosRetornados = new HashMap();

        for (Video v: videoConsultado.getVideosRelacionadosRank()){

            videosRetornados.put(v.getId(),v);

        }

        Video videoRetornado;

        for (Video ref: benchmark.getVideosRelacionadosRank()){

            videoRetornado = videosRetornados.get(ref.getId());

            if (videoConsultado != null) {

                potenciaNumerador = videoConsultado.getVideosRelacionadosRank().indexOf(videoRetornado)+1;

            }

            if (potenciaNumerador > 0) {
                numerador += Math.pow(alfaTopN, potenciaNumerador);
            }



            denominador += Math.pow(alfaTopN, potenciaDenominador);
            potenciaDenominador ++;
        }

        return numerador / denominador;
    }
	
	public void calcule(int descricao, Video videoConsultado, boolean poda, BufferedWriter bw) throws IOException{
        //verificar se o video está na base
        Video ref = videos.get(videoConsultado.getId());

        if (ref == null) {
            // System.out.println("Não é possivel responder pois não existe referencias para este vídeo");
            return;
        }
        
        if (ref.getVideosRelacionadosRank().size() == 0) return;

        if (poda) {
        	videoConsultado.poda(10,2 * ref.getVideosRelacionadosRank().size());
        }

        if (totalVideos == 0) return;

        int truePositeve = 0, falsePositive = 0 , FN = 0;

        ArrayList<Video> relacionados = videoConsultado.getVideosRelacionadosRank();

        for (Video video: relacionados){

            if (ref.containsId(video.getId())){
                truePositeve ++;
            }
            else{
                falsePositive ++;
            }

        }

        int trueNegative = totalVideos - (ref.getVideosRelacionadosRank().size() + falsePositive);

        float precision = 0f;

        if (truePositeve + falsePositive != 0f ) ;
        
        precision = (float) truePositeve / (truePositeve + falsePositive);

        float recall =  (float) truePositeve / ref.getVideosRelacionadosRank().size();

        float acuracy = (float) (truePositeve + trueNegative) / totalVideos;

        //Impressão dos resultados
        System.out.print(".");//progresso
        bw.write(videoConsultado.getId() + "  \t  " +videoConsultado.getReferences().size()+ "  \t  " + ref.getVideosRelacionadosRank().size() +" \t " + ""+truePositeve +" \t " + ""+videoConsultado.getVideosRelacionadosRank().size()  +" \t " + ""+format(precision) + " \t " + ""+format(recall) + " \t " + ""+ format(calculeTopN(videoConsultado))+"\t"+videoConsultado.getTempoDBPedia()+"\t"+videoConsultado.getTempoRelacionar()+"\n");

    }
	
	private Video createVideo(String id){
        if (id.startsWith("http")){
            try {
                Video v = new Video(id.replaceAll(" ",""));
                addVideo(v);
                return v;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void addVideo(Video v){
        Video existente = videos.get(v.getId());

        if (existente == null){
            videos.put(v.getId(),v);
            totalVideos++;
        }
    }
    
    public static String format(double x) {
        return String.format("%.3f", x);
    }

    private String rpad(String valor){
        /*String espacos10 = "          ";
        valor += espacos10;
        return valor.substring (0, 10);*/
        return valor;
    }

    public int count(){
        return videos.size();
    }
}
