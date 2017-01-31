package ufjf;

//import org.joda.time.DateTime;


import java.awt.List;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;

import org.apache.commons.codec.binary.StringUtils;

import utils.HttpRequest;

public class GetVideosUFJF {

    private static final String SERVER_URL = "http://localhost:10035";
    

    private static final String requisicaoUFJF(String sujeito, String DCTERMS) throws UnsupportedEncodingException{

        String textoEncode = " ?o where {<"+sujeito+"> "+DCTERMS+" ?o}";

        StringBuilder requisicaoQodra = new StringBuilder();

        requisicaoQodra.append(SERVER_URL).append("/repositories/qodra?query=select");

        requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));



        return HttpRequest.get(requisicaoQodra.toString())
                .accept("application/json").body();
    }

    public static final String getTitle(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:title>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getCourse(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:course>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getAbstract(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:abstract>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getPublischer(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:publisher";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getCreator(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:creator";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getLicense(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:license>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getLanguage(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:language>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final String getEducationLevel(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "dcterms:educationLevel";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        return valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

    }

    public static final Date getDate(String sujeito) throws UnsupportedEncodingException{
       /* final String DCTERMS = "<dcterms:date>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        //DateTime d = DateTime.parse(valores[1].replace("[", "").replaceAll("\"", "").replaceAll("<", "").replaceAll(">", "").replaceAll("}", "").replaceAll("]]", ""));

        Calendar c = Calendar.getInstance();

        Calendar calendar = Calendar.getInstance();
        calendar.set(d.getYear(), d.getMonthOfYear() - 1, d.getDayOfMonth());
        return calendar.getTime();*/
    	return null;
    }

    public static final long getTempoDBPedia(String sujeito)throws UnsupportedEncodingException{
    	
    	final String DCTERMS = "<dcterms:time>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        valores[1] = utils.StringUtils.apenasNumeros(valores[1]);
    	
        if (valores[1].isEmpty()) return 0l;
        
        else return Long.parseLong(valores[1]);
    }
    
    public static final ArrayList<String> getKeywords(String sujeito)throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:keyword>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonQodra.split("values\":");

        valores[1] = valores[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");
        valores = valores[1].split(",");

        ArrayList<String> keyWords = new ArrayList<String>();

        for (String s:valores){
            keyWords.add(s);
        }

        return keyWords;
    }


    public static final ArrayList<String> getRefences(String sujeito)throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:references>";

        String gsonArrayQodra = requisicaoUFJF(sujeito, DCTERMS);

        String valores[] = gsonArrayQodra.split("\"values\":");

        valores[1] = valores[1].replace("[","").replaceAll("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

        valores = valores[1].split("],");

        ArrayList<String> references = new ArrayList<String>();
//System.out.println("Video "+ sujeito);
        for (String valor:valores) {
            //valor = valor.replace("page","resource");
            valor.replaceAll("[\\\\]", "%");
            valor = URLDecoder.decode(valor, "UTF-8");
            references.add(valor);

  //          System.out.println(valor);
        }

        return references;
    }

    public static final ArrayList<String> getAllId() throws UnsupportedEncodingException {

        String textoEncode = " distinct ?s {?s <dcterms:relatedto> ?o} order by ?s";

        StringBuilder requisicaoQodra = new StringBuilder();

        requisicaoQodra.append(SERVER_URL).append("/repositories/qodra?query=select");

        requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));

        String gsonArrayQodra = HttpRequest.get(requisicaoQodra.toString())
                .accept("application/json").body();

        String retorno[] = gsonArrayQodra.split("\"values\":");

        retorno[1] = retorno[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

        retorno = retorno[1].split("],");

        ArrayList<String> ids = new ArrayList<String>();

        for (String ret:retorno) {
            ids.add(ret);
        }

        return ids;
    }

    public static final ArrayList<String> getIdByReference(String reference) throws UnsupportedEncodingException{
        String textoEncode = " ?s where{?s <dcterms:references> <"+reference+">}";

        StringBuilder requisicaoQodra = new StringBuilder();

        requisicaoQodra.append(SERVER_URL).append("/repositories/qodra?query=select");

        requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));

        String gsonArrayQodra = HttpRequest.get(requisicaoQodra.toString())
                .accept("application/json").body();

        String retorno[] = gsonArrayQodra.split("\"values\":");

        retorno[1] = retorno[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

        retorno = retorno[1].split("],");


        ArrayList<String> ids = new ArrayList<String>();

        for (String ret:retorno) {

            if (! "]".equals(ret))
                ids.add(ret);
        }

        return ids;
    }

    public static final ArrayList<String> getCategories(String sujeito) throws UnsupportedEncodingException{
        System.out.println("get categories for: "+sujeito);
        String textoEncode = " ?o where {<"+sujeito+"> <dcterms:category> ?o} ";
               // "union {<"+sujeito+"> <dcterms:referencesEn> ?o}}";

        StringBuilder requisicaoQodra = new StringBuilder();

        requisicaoQodra.append(SERVER_URL).append("/repositories/qodra?query=select");

        requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));

        String gsonArrayQodra = HttpRequest.get(requisicaoQodra.toString()).accept("application/json").body();

        String valores[] = gsonArrayQodra.split("\"values\":");

        valores[1] = valores[1].replace("[","").replaceAll("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

        valores = valores[1].split("],");


        ArrayList<String> categories = new ArrayList<String>();

        for (String valor:valores) {
            valor = valor.replace("page","resource");
            valor = URLDecoder.decode(valor, "UTF-8");

            if (valor.startsWith("http")) {
                categories.add(valor.replaceAll(" ",""));
            }
        }

        return categories;
    }

    public static final ArrayList<String> getVideosByCategories(String category) throws UnsupportedEncodingException{
        String textoEncode = " distinct ?s {?s <dcterms:category> <"+category+">} ";
                //"union {?s <dcterms:referencesEn> <"+category+">}}";

        StringBuilder requisicaoQodra = new StringBuilder();

        requisicaoQodra.append(SERVER_URL).append("/repositories/qodra?query=select");

        requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));

        String gsonArrayQodra = HttpRequest.get(requisicaoQodra.toString())
                .accept("application/json").body();

        String retorno[] = gsonArrayQodra.split("\"values\":");

        retorno[1] = retorno[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

        retorno = retorno[1].split("],");

        ArrayList<String> videosdaCategoria = new ArrayList<String>();

        //System.out.println("Categoria: " + category);
        for (String ret:retorno) {
            if ("]".equals(ret)) continue;
            //System.out.println("Relacionado: " + ret);
            videosdaCategoria.add(ret);
        }

        return videosdaCategoria;
    }

    public static final ArrayList<String> getVideosByReference(ArrayList<String> references) throws UnsupportedEncodingException {
        ArrayList<String> ids = new ArrayList<String>();


        for (String reference:references){
            ArrayList<String> retorno = getIdByReference(reference);

            for (String ret:retorno) {
                ids.add(ret);
            }
        }

        return ids;
    }

    public static final ArrayList<String> getRelatedTo(String sujeito) throws UnsupportedEncodingException{
        final String DCTERMS = "<dcterms:relatedto>";

        String gsonQodra = requisicaoUFJF(sujeito, DCTERMS);

       // String valores[] = gsonQodra.split("values\":");

        String retorno[] = gsonQodra.split("\"values\":");

        retorno[1] = retorno[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","");

        retorno = retorno[1].split("],");

        ArrayList<String> videosRelacionados = new ArrayList<String>();

        //System.out.println("Categoria: " + category);
        for (String ret:retorno) {
            if ("]".equals(ret)) continue;
            //System.out.println("Relacionado: " + ret);
            videosRelacionados.add(ret);
        }


        return videosRelacionados;

    }
    
    public static final ArrayList<Video> getVideosRelacionados(String sujeito)throws UnsupportedEncodingException{
      	
    	 String textoEncode = " ?relacionado (COUNT(?category) AS ?total) "
    	 		+ "WHERE{ "
    	 		+ "<"+sujeito+"> <dcterms:category> ?category. "
    	 		+ "?relacionado <dcterms:category> ?category. "
    	 		+ "} "
    			+ "GROUP BY ?video ?relacionado "
                + "ORDER BY  DESC(?total) " ;

         StringBuilder requisicaoQodra = new StringBuilder();

         requisicaoQodra.append(SERVER_URL).append("/repositories/qodra?query=select");

         requisicaoQodra.append(URLEncoder.encode(textoEncode, "UTF-8"));

         
         String gsonQodra = HttpRequest.get(requisicaoQodra.toString())
                 .accept("application/json").body();

         //System.out.println("gson: " + gsonQodra);

          String retorno[] = gsonQodra.split("\"values\":");
          
          retorno[1] = retorno[1].replace("[","").replace("\"","").replace("<", "").replace(">","").replace("}","").replace("]]","").replace("^^http://www.w3.org/2001/XMLSchema#integer", "");
          
    
          retorno = retorno[1].split("\\],");
          
          String properties[];
          
          ArrayList<Video> videosRelacionados = new ArrayList<>();
          videosRelacionados.clear();
 
          for (String ret:retorno) {
        	 properties = ret.split(",");
        	 if (properties[0].equals(sujeito)) continue;
             Video v = new Video(properties[0]);
             properties[1] = properties[1].replace("\\", "");
             v.setTotalCategoriaRelacionadas(Integer.parseInt(properties[1]));
             videosRelacionados.add(v);
          }


          return videosRelacionados;
    	
    }

    

    }
