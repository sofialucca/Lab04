package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");


				Corso nuovoCorso=new Corso(codins,numeroCrediti,nome,periodoDidattico);
				corsi.add(nuovoCorso);
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(Corso corso) {
		
		String sql="SELECT * "
				+"FROM corso "
				+"WHERE codins=?";
		
		Corso corsoTrovato=null;
		
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setString(1, corso.getCodins());
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				corsoTrovato=new Corso(rs.getString("codins"),rs.getInt("crediti"),rs.getString("nome"),rs.getInt("pd"));
			}
			
			rs.close();
			st.close();
			conn.close();
			return corsoTrovato;
			
		}catch(SQLException sqle) {
			throw new RuntimeException("Errore nel DB",sqle);
		}
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		String sql="SELECT i.matricola, s.cognome, s.nome, s.CDS "
				+"FROM iscrizione AS i,studente AS s "
				+"WHERE i.codins=? AND s.matricola=i.matricola "
				+"ORDER BY s.cognome ASC";
		
		List<Studente> listaStudenti=new LinkedList<>();
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setString(1, corso.getCodins());
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				listaStudenti.add(new Studente(rs.getInt("i.matricola"),rs.getString("s.cognome"),rs.getString("s.nome"),rs.getString("s.CDS")));
			}
			
			rs.close();
			st.close();
			conn.close();
			return listaStudenti;
		}catch(SQLException sqle) {
			throw new RuntimeException("Errore DB",sqle);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(int matricola, Corso corso) {
		
		String sql1="SELECT * "+
				"FROM iscrizione "+
				"WHERE matricola=? AND codins=?";
		
		String sql2="INSERT INTO iscrizione "
				+ "VALUES(?,?)";
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql1);
			
			st.setInt(1, matricola);
			st.setString(2, corso.getCodins());
			
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return false;
			}
			rs.close();
			
			st=conn.prepareStatement(sql2);
			st.setInt(1, matricola);
			st.setString(2, corso.getCodins());
			st.executeUpdate();
			
			st.close();
			conn.close();
			
			return true;
		}catch(SQLException sqle) {
			throw new RuntimeException("Errore DB",sqle);
		}
	}

}
