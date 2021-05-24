package it.polito.tdp.borders.model;

import java.util.List;
import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
    
	private Map<Integer,Country> idMapCountry;
	private BordersDAO dao;
	private SimpleGraph<Country,DefaultEdge> grafo;
	
	public Model() {
		this.dao= new BordersDAO();
		dao.loadAllCountries(idMapCountry);
		
	}
	public void creaGrafo(int anno) {
		
		grafo= new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
		List<Border> confini= dao.getCountryPairs(idMapCountry, anno);
		
		//aggiungo vertici e archi
		if(confini.isEmpty()) {
			throw new RuntimeException("nessuna coppia trovata");
		}
		for(Border b: confini) {
			grafo.addVertex(b.getC1());
			grafo.addVertex(b.getC2());
			grafo.addEdge(b.getC1(), b.getC2());
		}
		
		
	}

}
