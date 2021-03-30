package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
