package ufjf;

import java.io.UnsupportedEncodingException;
import java.util.*;

import utils.ComparatorVideosOrdemAsc;
import utils.ComparatorVideosOrdemDec;

public class Video {

    private String id;

    private String title;

    private String course;

    private Date date;

    private ArrayList<String> keywords;

    private String _abstract;

    private String description;

    private String publischer;

    private String creator;

    private String licence;

    private String language;

    private String educationLevel;

    private ArrayList<String> references;

    private ArrayList<String> categories;

    //private ArrayList<String> videosRelacionados;

    private List<Video> videosRelacionadosRank;
    
    private ArrayList<String> benchmark;

    private int totalCategoriaRelacionadas;
    
    private long tempoRelacionar;
    
    private long tempoDBPedia;

    //public void setVideosRelacionados(ArrayList<String> videosRelacionados) {
      //  this.videosRelacionados = videosRelacionados;
    //}
    
    public Video(String id) throws UnsupportedEncodingException {
        this.id = id;
        keywords = new ArrayList<String>();
        references = new ArrayList<String>();
        //videosRelacionados = new ArrayList<>();
        videosRelacionadosRank = new ArrayList<Video>();
        totalCategoriaRelacionadas = 0;

/*
        setReferences(GetVideosUFJF.getRefences(id));

        setCategories(GetVideosUFJF.getCategories(id));

        setBenchmark(GetVideosUFJF.getRelatedTo(id));
        
        setTitle(GetVideosUFJF.getTitle(id));
        
        setCourse(GetVideosUFJF.getCourse(id));

        setCreator(GetVideosUFJF.getCreator(id));

        setDate(GetVideosUFJF.getDate(id));

        setKeywords(GetVideosUFJF.getKeywords(id));

        set_abstract(GetVideosUFJF.getAbstract(id));

        setLanguage(GetVideosUFJF.getLanguage(id));

        setLicence(GetVideosUFJF.getLicense(id));

        setPublischer(GetVideosUFJF.getPublischer(id));
        */
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String couse) {
        this.course = couse;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public String get_abstract() {
        return _abstract;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublischer() {
        return publischer;
    }

    public void setPublischer(String publischer) {
        this.publischer = publischer;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void addReference(String reference){
        references.add(reference);
    }

    public void addKeyword(String keyword){
        keywords.add(keyword);
    }
    
    public void setBenchmark(ArrayList<String> benchmark){
    	this.benchmark = benchmark;
    }

    /*
    * String rssFeedURL = "http://stackoverflow.com";
    this.rssFeedURLS = new ArrayList<String>();
    this.rssFeedURLS.add(rssFeedURL);
    if(this.rssFeedURLs.contains(rssFeedURL)) {
        // this code will execute
    }
    String copyURL = new String(rssFeedURL);
    if(this.rssFeedURLs.contains(copyURL)) {
        // code will still execute because contains() checks equals()
    }
    * */
    public void addRelacionado(String idVideoRelacionado){

        // não relacionar um vídeo com ele mesmo
        if (id.equals(idVideoRelacionado)) return;

        if (!containsId(idVideoRelacionado)) {

            try {
                addVideoRelacionadoRanking(idVideoRelacionado);
            }
            catch (UnsupportedEncodingException E){
                E.printStackTrace();
            }
        }
    }

    public boolean containsId(String id){
        for (Video v: videosRelacionadosRank){
            if (id.equals(v.getId())) return true;
        }
        return false;
    }

    private void addVideoRelacionadoRanking(String idVideo) throws UnsupportedEncodingException{
    	if (this.id.equals(idVideo)) return;
        Video video = new Video(idVideo);
/*
        ArrayList<String> categoriasVideoRelacionado = video.getCategories();

        ArrayList<String> categoriasVideoAtual = this.getCategories();

        for (String categoria: categoriasVideoAtual){
            if (categoriasVideoRelacionado.contains(categoria))
                video.incTotalCategoriaRelacionadas();
        }*/

        videosRelacionadosRank.add(video);
        //System.out.println("Total Categorias Relacionadas: "+video.getTotalCategoriaRelacionadas());
    }
    
    public void addAllVideoRelacionadoRanking(ArrayList<Video> videos){
    	videosRelacionadosRank.addAll(videos);
    }
    
    //public ArrayList<String> getVideosRelacionados(){
    //    return videosRelacionados;
   // }

    public void incTotalCategoriaRelacionadas(){
        totalCategoriaRelacionadas++;
    }

    public void setTotalCategoriaRelacionadas(int total){
        totalCategoriaRelacionadas = total;
    }

    public int getTotalCategoriaRelacionadas(){
        return totalCategoriaRelacionadas;
    }

    public ArrayList<Video> getVideosRelacionadosRank() {
    	ArrayList<Video> aux = new ArrayList();
    	aux.addAll(videosRelacionadosRank);
        return  aux;
    }

    public void setVideosRelacionadosRank(ArrayList<Video> videosRelacionadosRank) {
        this.videosRelacionadosRank = videosRelacionadosRank;
    }

    public void ordenaRelacionadosPorTotalVideosRelacionados(){
        Collections.sort(videosRelacionadosRank, new ComparatorVideosOrdemDec());
    }

    public void poda(int minCategoriasRelacionadas, int maxVideos){
        Collections.sort(videosRelacionadosRank, new ComparatorVideosOrdemAsc());

        Iterator<Video> it = videosRelacionadosRank.iterator();

        while (it.hasNext()){
            if (it.next().getTotalCategoriaRelacionadas() <= minCategoriasRelacionadas){
                if ((videosRelacionadosRank.size() > maxVideos))
                    if (videosRelacionadosRank.size() > 10)
                        it.remove();
            }
        }

    }
    
    
    public void setTempoRelacionar(long tempoRelacionar){
    	this.tempoRelacionar = tempoRelacionar;
    }
    
    public long getTempoRelacionar(){
    	return tempoRelacionar;
    }
    
    public void setTempoDBPedia(long tempoDBPedia){
    	this.tempoDBPedia = tempoDBPedia;
    }
    
    public long getTempoDBPedia(){
    	return tempoDBPedia;
    }
    
    public void imprimirRelacionados(){
    	System.out.println("Video \t Video Relacionado \t Quantidades de categorias");
    	
    	for (Video v: this.getVideosRelacionadosRank()){
    		System.out.println(this.id + "\t"+ v.getId() + "\t" + v.getTotalCategoriaRelacionadas());
    	}
    	
    }

}
