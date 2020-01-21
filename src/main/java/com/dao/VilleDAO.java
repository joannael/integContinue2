package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.config.JDBCConfigurationSol1;
import com.dto.client.VilleFrance;

public class VilleDAO {
	
public ArrayList<VilleFrance> trouver(VilleFrance villeFrance) {
		
		ArrayList<VilleFrance> villesFrance = new ArrayList<VilleFrance>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String codeCommuneINSEE = villeFrance.getCodeCommuneINSEE();
		String nomCommune = villeFrance.getNomCommune();
		String codePostal = villeFrance.getCodePostal();
		String libelleAcheminement = villeFrance.getLibelleAcheminement();
		String ligne5 = villeFrance.getLigne5();
		String latitude = villeFrance.getLatitude();
		String longitude = villeFrance.getLongitude();
		
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
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return villesFrance;
		
	}
	

	
	public void ajouter(VilleFrance villeFrance) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String codeCommuneINSEE = villeFrance.getCodeCommuneINSEE();
		String nomCommune = villeFrance.getNomCommune();
		String codePostal = villeFrance.getCodePostal();
		String libelleAcheminement = villeFrance.getLibelleAcheminement();
		String ligne5 = villeFrance.getLigne5();
		String latitude = villeFrance.getLatitude();
		String longitude = villeFrance.getLongitude();
		
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
	
	public void modifier(VilleFrance villeFrance) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String codeCommuneINSEE = villeFrance.getCodeCommuneINSEE();
		String nomCommune = villeFrance.getNomCommune();
		String codePostal = villeFrance.getCodePostal();
		String libelleAcheminement = villeFrance.getLibelleAcheminement();
		String ligne5 = villeFrance.getLigne5();
		String latitude = villeFrance.getLatitude();
		String longitude = villeFrance.getLongitude();
		
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
	
	private Connection creerConnexion() {
		JDBCConfigurationSol1 connect = new JDBCConfigurationSol1();
		Connection connection = null;
		
		try {
			connection = connect.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	private VilleFrance map(ResultSet resultSet) throws SQLException {
		VilleFrance villeFrance = new VilleFrance();
		
		villeFrance.setCodeCommuneINSEE(resultSet.getString("Code_commune_INSEE"));
		villeFrance.setNomCommune(resultSet.getString("Nom_commune"));
		villeFrance.setCodePostal(resultSet.getString("Code_postal"));
		villeFrance.setLibelleAcheminement(resultSet.getString("Libelle_acheminement"));
		villeFrance.setLigne5(resultSet.getString("Ligne_5"));
		villeFrance.setLatitude(resultSet.getString("Latitude"));
		villeFrance.setLongitude(resultSet.getString("Longitude"));
		
		return villeFrance;
	}

}
