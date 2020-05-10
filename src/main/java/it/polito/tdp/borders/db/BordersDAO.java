package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public Map<Integer,Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		Map<Integer,Country> result = new HashMap<Integer,Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				if(!result.containsKey(rs.getInt("ccode"))) {
					result.put(rs.getInt("ccode"), new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme")));
				}
				
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	/*
	 	Dobbiamo selezionare solo i collegamenti via terra
	 */
	public List<Border> getCountryPairs(int anno, Map<Integer,Country> paesi) {
		
		String sql = "Select state1no, state2no, year " + 
				"from contiguity " + 
				"where year<=? and conttype=1";
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				
				result.add(new Border(paesi.get(rs.getInt("state1no")),paesi.get(rs.getInt("state1no")),anno));
				
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
}
