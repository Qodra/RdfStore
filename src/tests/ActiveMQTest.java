package tests;

import org.junit.Test;
import activemq.ActiveMQ;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ActiveMQTest {

    @Test
    public void ActiveMQTest() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("/home/jayme/Documents/query.csv"))
                .useDelimiter("\\n");
        while (scanner.hasNext()) {
            String linha = scanner.next().replaceAll("\r","");
            String[] tripla =  linha.split(",");

            String sujeito = tripla[0].replaceAll("\"","");
            System.out.println(sujeito);
            String predicado = tripla[1].replaceAll("\"", "");
            System.out.println(predicado);
            String objeto = tripla[2].replaceAll("\"","");
            System.out.println(objeto);
            new ActiveMQ().sendMessagetoRdfStore("<"+sujeito+"><"+predicado+"><"+objeto+">");
        }
    }
    
    @Test
    public void ActiveMQConnectTest() {
    	
    }
}
