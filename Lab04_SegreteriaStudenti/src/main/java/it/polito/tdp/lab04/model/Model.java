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
	
	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	
	public Studente getStudente(int matricola) {
		return studenteDao.getStudente(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso){
		return corsoDao.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		
		return studenteDao.getCorsiStudente(matricola);
	}
	
	public boolean iscriviStudenteACorso(int matricola,Corso corso) {
		return corsoDao.inscriviStudenteACorso(matricola, corso);
	}
}
