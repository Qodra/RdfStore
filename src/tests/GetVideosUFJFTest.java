package tests;

import org.junit.Ignore;
import org.junit.Test;
import org.openrdf.query.algebra.Str;

import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

public class GetVideosUFJFTest {

    @Test
    public void getReferencesTest(){


        ArrayList<String> references = null;
        try {
            /*ArrayList<String> videos = GetVideosUFJF.getAllId();
            for (String v:videos){
                references = GetVideosUFJF.getRefences(v);
            }
            */

            references = GetVideosUFJF.getRefences("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/qui125/aula16/qui125_aula16.xml");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        for (String ref:references){
            System.out.println(references);
        }

    }

    @Test
    public void getTitleTest(){
        String s = null;
        try {
            s = GetVideosUFJF.getTitle("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }

    @Test
    public void getCourseTest(){
        String s = null;
        try {
            s = GetVideosUFJF.getCourse("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }

    @Test
    public void getKeywordsTest(){
        ArrayList<String> s = null;
        try {
            s = GetVideosUFJF.getKeywords("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(s);
    }

    @Test
    public void getDateTest(){
        Date d;
        try {
            d = GetVideosUFJF.getDate("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencias_exatas/DCC122/Aula26/video26.xml");
        } catch (
                UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllIdsTest(){
        try{
            ArrayList<String> ids = GetVideosUFJF.getAllId();

            for (String id:ids){
                System.out.println(id);
            }
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

    }

    @Test
    public void getIdByReferenceTest(){
        try {
            ArrayList<String> ids = GetVideosUFJF.getIdByReference("http://pt.dbpedia.org/resource/Teoria_dos_grafos");

            for (String id:ids){
                System.out.println(id);
            }
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getIdByReferencesTest(){
        try {
            //Recupera todos os ids de videos
            ArrayList<String> idVideos = GetVideosUFJF.getAllId();

            //para cada id de video procura os videos que tem algum recurso semelhante
            for (String idVideo:idVideos){
                ArrayList<String> idVideosRelacionados = GetVideosUFJF.getIdByReference(idVideo);

                for (String id:idVideosRelacionados){
                    System.out.println(id);
                }
            }
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    @Test
    public void getCategoriesTest(){
        /*try {
            //Recupera todos os ids de videos
            ArrayList<String> idVideos = null; GetVideosUFJF.getAllId();

            //para cada id de video procura os videos que tem algum recurso semelhante
            for (String idVideo:idVideos){
                ArrayList<String> categories = GetVideosUFJF.getCategories(idVideo);
                System.out.println("Video: "+idVideo);
                System.out.println("Categorias:");
                for (String id:categories){
                    System.out.println(id);
                }
            }
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }*/
    }

    @Test
    public void getVideosByCategoriesTest(){
    	/*   try {
            //Recupera todos os ids de videos
            ArrayList<String> idVideos = GetVideosUFJF.getAllId();

            ArrayList<Video> base = new ArrayList<>();

            //para cada id de video procura os videos que tem algum recurso semelhante
            for (String idVideo:idVideos){
                ArrayList<String> categories = GetVideosUFJF.getCategories(idVideo);

                Video video = new Video(idVideo);

                for (String category:categories){
                    ArrayList<String> videosRelacionados = GetVideosUFJF.getVideosByCategories(category);

                    for (String videoRelacionado:videosRelacionados){
                        video.addRelacionado(videoRelacionado);
                    }
                }

                base.add(video);
            }

            for (Video video:base){
                idVideos = video.getVideosRelacionados();

                System.out.println();
                System.out.println("video: "+video.getId());
                int i = 1;
                for (String id:idVideos){
                    System.out.println("Relacionado "+i+": "+id);
                    i++;
                }
            }

        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }*/
    }

    @Test
    @Ignore
    public void getRelatedToTest()throws UnsupportedEncodingException{
        ArrayList<String> videos = GetVideosUFJF.getRelatedTo("<http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencia_da_computacao/DCC116/Aula06/Aula06.xml>");

        for(String v:videos){
            System.out.println(v);
        }
    }
    
    @Test
    @Ignore
    public void getVideosRelacionados()throws UnsupportedEncodingException{
    	GetVideosUFJF.getVideosRelacionados("http://videoaula.rnp.br/rioflashclient.php?xmlfile=//dados/conversao_html5/instituicao/ufjf/ciencia_da_computacao/DCC116/Aula01/Aula01.xml");
    }
}
