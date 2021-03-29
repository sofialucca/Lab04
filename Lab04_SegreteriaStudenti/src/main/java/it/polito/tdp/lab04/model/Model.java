package it.polito.tdp.lab04.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao=new CorsoDAO();
		studenteDao=new StudenteDAO();
	}
	
	public List<String> getTuttiICorsi(){
		List<String> nomiCorsi=new ArrayList<>();
		for(Corso c:corsoDao.getTuttiICorsi()) {
			nomiCorsi.add(c.getNome());
		}
		return nomiCorsi;
	}
	
	public Studente getStudente(int matricola) {
		return studenteDao.getStudente(matricola);
	}
	
	
}
