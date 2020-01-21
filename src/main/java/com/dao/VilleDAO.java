package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.config.JDBCConfigurationSol1;
import com.dto.Ville;

public class VilleDAO implements villeDAOInterface{
	
	String codeCommuneINSEE, nomCommune, codePostal,libelleAcheminement,ligne5,latitude, longitude;
	
	public void getterVille(Ville villeFrance) {
		this.codeCommuneINSEE= villeFrance.getCodeCommuneINSEE();
		this.nomCommune = villeFrance.getNomCommune();
		this.codePostal = villeFrance.getCodePostal();
		this.libelleAcheminement = villeFrance.getLibelleAcheminement();
		this.ligne5 = villeFrance.getLigne5();
		this.latitude = villeFrance.getLatitude();
		this.longitude = villeFrance.getLongitude();		
	}
	
	public ArrayList<Ville> trouver(Ville villeFrance) throws SQLException {
		
	
		
		ArrayList<Ville> villesFrance = new ArrayList<Ville>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		getterVille(villeFrance);
		
		try {
			connection = this.creerConnexion();
			String query = "SELECT * FROM ville_france WHERE " +
			(codeCommuneINSEE == null ? "Code_commune_INSEE IS NOT NULL " : "Code_commune_INSEE = ? ") + 
			(nomCommune == null ? "AND Nom_commune IS NOT NULL " : "AND Nom_commune = ? ") +
			(codePostal == null ? "AND Code_postal IS NOT NULL " : "AND Code_postal	= ? ") +
			(libelleAcheminement == null ? "AND Libelle_acheminement IS NOT NULL " : "AND Libelle_acheminement = ? ") +
			(ligne5 == null ? "AND Ligne_5 IS NOT NULL " : "AND Ligne_5 = ? ") +
			(latitude == null ? "AND Latitude IS NOT NULL " : "AND Latitude = ? ") +
			(longitude == null ? "AND Longitude IS NOT NULL " : "AND Longitude = ? ");
						
			preparedStatement = connection.prepareStatement(query);			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				villesFrance.add(this.map(resultSet));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			preparedStatement.close();		
			resultSet.close();
			connection.close();			
		}
		
		return villesFrance;
		
	}
	

	
	public void ajouter(Ville villeFrance) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		getterVille(villeFrance);

		try {
			connection = this.creerConnexion();
			String query = "INSERT INTO ville_france VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, codeCommuneINSEE);
			preparedStatement.setString(2, nomCommune);
			preparedStatement.setString(3, codePostal);
			preparedStatement.setString(4, libelleAcheminement);
			preparedStatement.setString(5, ligne5);
			preparedStatement.setString(6, latitude);
			preparedStatement.setString(7, longitude);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void modifier(Ville villeFrance) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		getterVille(villeFrance);

		
		try {
			connection = this.creerConnexion();
			String query = "UPDATE ville_france SET Nom_commune = ?, Code_postal = ?,"
					+ " Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ?"
					+ " WHERE Code_commune_INSEE = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, nomCommune);
			preparedStatement.setString(2, codePostal);
			preparedStatement.setString(3, libelleAcheminement);
			preparedStatement.setString(4, ligne5);
			preparedStatement.setString(5, latitude);
			preparedStatement.setString(6, longitude);
			preparedStatement.setString(7, codeCommuneINSEE);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void supprimer(String codeCommuneINSEE) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = this.creerConnexion();
			String query = "UPDATE ville_france SET isActive = 0 WHERE Code_commune_INSEE = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, codeCommuneINSEE);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection creerConnexion() {
		JDBCConfigurationSol1 connect = new JDBCConfigurationSol1();
		Connection connection = null;
		
		try {
			connection = connect.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public Ville map(ResultSet resultSet) throws SQLException {
		Ville ville = new Ville();
		
		ville.setCodeCommuneINSEE(resultSet.getString("Code_commune_INSEE"));
		ville.setNomCommune(resultSet.getString("Nom_commune"));
		ville.setCodePostal(resultSet.getString("Code_postal"));
		ville.setLibelleAcheminement(resultSet.getString("Libelle_acheminement"));
		ville.setLigne5(resultSet.getString("Ligne_5"));
		ville.setLatitude(resultSet.getString("Latitude"));
		ville.setLongitude(resultSet.getString("Longitude"));
		
		return ville;
	}

}
