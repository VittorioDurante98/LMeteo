package it.polito.tdp.meteo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.meteo.DAO.MeteoDAO;

public class Model {
	
	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	private MeteoDAO mdao= new MeteoDAO();
	private int livello;
	private Map<Date,String> spostamenti = new TreeMap<>();
	private int numGiorniTo;
	private int numGiorniMi;
	private int numGiorniGe;
	private int sommaTo=0;
	private int sommaGe=0;
	private int sommaMi=0;
	List<Rilevamento> parziale= null;
	
	public Model() {
		this.livello=0;
		this.sommaTo=0;
		this.sommaMi=0;
		this.sommaGe=0;
		this.numGiorniGe=0;
		this.numGiorniMi=0;
		this.numGiorniTo=0;
	}

	// of course you can change the String output with what you think works best
	public String getUmiditaMedia(int mese) {
		Citta c1 = new Citta("Torino");
		Citta c2 = new Citta("Milano");
		Citta c3 = new Citta("Genova");
		
		double mt= mdao.getAvgRilevamentiLocalitaMese(mese, c1);
		double mg= mdao.getAvgRilevamentiLocalitaMese(mese, c2);
		double mm= mdao.getAvgRilevamentiLocalitaMese(mese, c3);
		
		String s= "Torino: "+mt+"\nGenova: "+mg+"\nMilano: "+mm+"\n";
		return s;
	}
	
	// of course you can change the String output with what you think works best
	public Map<Date,String> trovaSequenza(int mese) {
		List<Citta> leCitta=  new ArrayList<>();
		for(Citta c: leCitta)
			c.setRilevamenti(mdao.getAllRilevamentiLocalitaMese(mese, c));
		
	
		cerca(parziale,livello);
		
		
		return spostamenti;
	}
	
	public void cerca(List<Rilevamento> parziale, int livello) {
		
		
		if(livello==15)
			return;
		
		for(int i=0; i<15; i++) {
			
		}
		
	}

}
