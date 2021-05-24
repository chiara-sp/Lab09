package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<Integer,Country> idMapCountry) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		//Map<Integer,Country> result = new HashMap<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c= new Country( rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				idMapCountry.put(c.getCode(), c);
			}
			
			conn.close();
			//return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(Map<Integer,Country> idMapCountry, int anno) {

		String sql="select state1no as s1, state2no as s2 "
				+ "from contiguity "
				+ "where conttype=1 and year<=?";
		List<Border> result= new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Country c1= idMapCountry.get(rs.getInt("s1"));
				Country c2= idMapCountry.get(rs.getInt("s2"));
				if(c1!=null && c2!=null)
				result.add(new Border(c1,c2));
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
