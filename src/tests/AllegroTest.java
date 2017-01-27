package tests;

import org.junit.Test;

import allegroGraph.Allegro;


public class AllegroTest {

    @Test
    public boolean testaConexao(){   
        try {
			Allegro base = new Allegro();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
    }
}
