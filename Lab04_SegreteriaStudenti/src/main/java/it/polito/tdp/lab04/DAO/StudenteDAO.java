package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola) {
		String sql="SELECT * FROM studente WHERE matricola=?";
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			
			Studente studenteCercato=null;
			if(rs.next()) {
				studenteCercato= new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return studenteCercato;
		}catch(SQLException sqle) {
			throw new RuntimeException("Errore DB",sqle);
		}
		
		
	}
	
	public List<Corso> getCorsiStudente(int matricola){
		
		String sql="SELECT c.codins, c.crediti, c.nome, c.pd "+
				"FROM corso AS c, iscrizione AS i, studente AS s "+
				"WHERE i.matricola=s.matricola AND c.codins=i.codins AND s.matricola=? "+
				"ORDER BY c.pd ASC";
		List<Corso> listaCorsi=new LinkedList<Corso>();
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				listaCorsi.add(new Corso(rs.getString("c.codins"),rs.getInt("c.crediti"),rs.getString("c.nome"),rs.getInt("pd")));
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return listaCorsi;
		}catch(SQLException sqle) {
			throw new RuntimeException("Errore DB",sqle);
		}
	}
}
