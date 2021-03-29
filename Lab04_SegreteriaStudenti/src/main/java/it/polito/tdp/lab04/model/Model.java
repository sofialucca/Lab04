package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;

public class Model {

	private CorsoDAO corsoDao;
	
	public Model() {
		corsoDao=new CorsoDAO();
	}
	
	public List<String> getTuttiICorsi(){
		List<String> nomiCorsi=new ArrayList<>();
		for(Corso c:corsoDao.getTuttiICorsi()) {
			nomiCorsi.add(c.getNome());
		}
		return nomiCorsi;
	}
}
