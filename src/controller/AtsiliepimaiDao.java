package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Atsiliepimai;


public class AtsiliepimaiDao {
	public void addAtsiliepimas(Atsiliepimai ats)
	{
		String sql = "INSERT INTO `atsiliepimai`"
				+ "(`miestas`, `vardas`, `pastas`, `atsiliepimai`)"
				+ " VALUES (?, ?, ?, ?)";
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/egzaminas", "root", "");
		PreparedStatement add = myConn.prepareStatement(sql);
		add.setString(1,ats.getMiestas());
		add.setString(2,ats.getVardas());
		add.setString(3,ats.getPastas());
		add.setString(4,ats.getAtsiliepimai());
	
		add.execute();
		add.close();
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	public void showAtsiliepimas(ObservableList<Atsiliepimai> data) {
		String query = "SELECT * FROM atsiliepimai";
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/egzaminas", "root", "");
		PreparedStatement add = myConn.prepareStatement(query);
		ResultSet rs = add.executeQuery();
		while(rs.next()) {
			data.add(new Atsiliepimai(
					rs.getInt("id"),
					rs.getString("miestas"),
					rs.getString("vardas"),
					rs.getString("pastas"),
					rs.getString("atsiliepimai")
					));
		}
		
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	
	public void updateAtsiliepimas(Atsiliepimai ats)
	{
		try {
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/egzaminas", "root", "");
		PreparedStatement upd = myConn.prepareStatement("UPDATE atsiliepimai SET miestas = ?, vardas = ?,pastas = ?,atsiliepimai = ? WHERE id = ?");
		upd.setString(1,ats.getMiestas());
		upd.setString(2,ats.getVardas());
		upd.setString(3,ats.getPastas());
		upd.setString(4,ats.getAtsiliepimai());
		upd.setInt(5,ats.getId());
		upd.executeUpdate();
		upd.close();
		}catch(Exception exc){
			exc.printStackTrace();
		
		}
	}
	
	public void deleteAtsiliepimas(int id)
	{
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/egzaminas", "root", "");
			PreparedStatement del = myConn.prepareStatement("delete FROM atsiliepimai WHERE id = ?");
			del.setInt(1, id);
			del.executeUpdate();
			}catch(Exception exc){
				exc.printStackTrace();
			
			}
	}
	

	
	public ObservableList<Atsiliepimai> searchPokemonaiByName(String pavadinimas){
		String sql = "";
		if (pavadinimas.isEmpty()) {
			sql = "Select * FROM atsiliepimai";
		} else {
			sql = "Select * FROM atsiliepimai WHERE name LIKE '%" + pavadinimas + "%'";	
		}
		
		ObservableList<Atsiliepimai>data = FXCollections.observableArrayList();
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/egzaminas", "root", "");
			PreparedStatement pavad = myConn.prepareStatement(sql);
			
			ResultSet rs = pavad.executeQuery();	
				while(rs.next()){
					data.add(new Atsiliepimai(
							rs.getInt("id"),
							rs.getString("miestas"),
							rs.getString("vardas"),
							rs.getString("pastas"),
							rs.getString("atsiliepimai")			
					));	       		         		         			
				}	
		}catch(Exception exc){
			exc.printStackTrace();	
		}
			return data;
	}
	
}
