package tests;

import dbpedia.DBPedia;

import org.junit.Test;

import ufjf.GetVideosUFJF;
import ufjf.Video;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DBPediaTest {

    @Test
    public void getPropertiesTest() throws UnsupportedEncodingException {
        String str = DBPedia.getProperties("<http://dbpedia.org/resource/Problem_solving>", "dcterms:subject");

        str = str.replaceAll("[\"\']","");

        String[] resources =  str.split("\n");

        for (String s:resources) {
            //if ((DBPedia.languageIsPt(s)) || (DBPedia.languageIsEn(s)))
                System.out.println(s);
        }
    }

    @Test
    public void getResourcesTest() throws UnsupportedEncodingException {
        DBPedia.getResource("<http://dbpedia.org/resource/Heuristic>",10);
    }

    @Test
    public void getResourcesSameAsTest() throws UnsupportedEncodingException {
        ArrayList<String> sameAs =  DBPedia.getResourceSameAs("http://pt.dbpedia.org/resource/Categoria:Solução_de_problemas");

        for (String s:sameAs){
            System.out.println(s);
        }
    }

    @Test
    public void getResourcesSamenAsPTTest() throws UnsupportedEncodingException {
        ArrayList<String> sameAs =  DBPedia.getResourceSameAsPT("http://dbpedia.org/resource/Category:Problem_solving");

        for (String s:sameAs){
            System.out.println(s);
        }
    }

    @Test
    public void getBroaderCategoryTest() throws UnsupportedEncodingException{
        ArrayList<String> broaderCategories = DBPedia.getBroaderCategory("http://dbpedia.org/resource/Category:Problem_solving");

        for (String s:broaderCategories) {

            System.out.println(s);
        }
    }

    @Test
    public void getIsBroaderOfCategoryTest() throws UnsupportedEncodingException{
        ArrayList<String> broaderCategories = DBPedia.getIsBroaderOfCategory("http://dbpedia.org/resource/Category:Problem_solving");

        for (String s:broaderCategories) {

            System.out.println(s);
        }
    }

    @Test
    public void getResourceByCategoryTest() {
        ArrayList<String> resources = DBPedia.getResourcesByCategory("http://dbpedia.org/resource/Category:Problem_solving");

        if (resources != null) {
            for (String s: resources){
                System.out.println(s);
            }
        }
    }
    @Test
    public void getCategoryByResourceTest() {
        ArrayList<String> categories = DBPedia.getCategoryByResource("http://dbpedia.org/resource/Problem_solving");

        if (categories != null) {
            for (String s: categories){
                System.out.println(s);
            }
        }
    }

    @Test
    public void getClassByResourceTest(){
        ArrayList<String> classes = DBPedia.getClassByResource("http://dbpedia.org/resource/Evolutionary_algorithm");

        if (classes != null){
            for (String s: classes){
                System.out.println(s);
            }

        }
    }

    @Test
    public void getResourceByClassTest(){
        ArrayList<String> resources = DBPedia.getResourcesByClass("http://dbpedia.org/class/yago/Abstraction100002137");

        if (resources != null){
            for (String s: resources){
                System.out.println(s);
            }

        }
    }

    @Test
    public void getResourcesRelatedTest() throws Exception {
        ArrayList<String> base = GetVideosUFJF.getAllId();

        for (String b: base){
            Video video = new Video(b);
            System.out.println(b);

            DBPedia.getResourcesRelated(video, 0,0);
        }
    }

    @Test
    public void getLabelspfTest() throws UnsupportedEncodingException{

       // ArrayList<String> base = GetVideosUFJF.getAllId();


        DBPedia.getLabelpt("http://dbpedia.org/resource/Algorithm");

    }
}

