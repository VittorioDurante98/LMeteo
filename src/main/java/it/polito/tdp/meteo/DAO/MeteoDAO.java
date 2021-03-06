package it.polito.tdp.meteo.DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.meteo.model.Citta;
import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, Citta c) {
		final String sql = "SELECT ?, Data, Umidita FROM situazione WHERE Data=? and localita=?" ;
		List<Rilevamento> rilevamentiMese = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c.getNome());
			st.setString(3, c.getNome());
			Rilevamento r;
			
			for(int i= 0; i<32; i++) {
				String s= "2013-"+mese+"-"+i;
				st.setString(2, s);
				ResultSet rs= st.executeQuery();
				while (rs.next()) {
					r = new Rilevamento(c.getNome(), rs.getDate("Data"), rs.getInt("Umidita"));
					rilevamentiMese.add(r);
				}
			}

			conn.close();
			return rilevamentiMese;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public double getAvgRilevamentiLocalitaMese(int mese, Citta c) {
		double media =0.0;
		int somma=0;
		List<Rilevamento> rilevamenti = new ArrayList<>(this.getAllRilevamentiLocalitaMese(mese, c));
		for(Rilevamento r: rilevamenti) {
			somma+= r.getUmidita();
		}
		media=somma/rilevamenti.size();
		return media;
	}


	public List<Citta> getAllCitta() {
		List<Citta> allCitta = new LinkedList<>();
		final String sql = "SELECT Localita FROM situazione";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Citta c = new Citta(rs.getString("Localita"));
				allCitta.add(c);
			}

			conn.close();
			return allCitta;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
