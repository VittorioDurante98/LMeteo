package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	private MeteoDAO mdao= new MeteoDAO();
	
	public Model() {

	}

	// of course you can change the String output with what you think works best
	public String getUmiditaMedia(int mese) {
		double mt= mdao.getAvgRilevamentiLocalitaMese(mese, "Torino");
		double mg= mdao.getAvgRilevamentiLocalitaMese(mese, "Genova");
		double mm= mdao.getAvgRilevamentiLocalitaMese(mese, "Milano");
		
		String s= "Torino: "+mt+"\nGenova: "+mg+"\nMilano: "+mm+"\n";
		return s;
	}
	
	// of course you can change the String output with what you think works best
	public String trovaSequenza(int mese) {
		return "TODO!";
	}
	

}
