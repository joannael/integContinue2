package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.client.VilleFrance;

public interface villeDAOInterface {
	
	public ArrayList<VilleFrance> trouver(VilleFrance villeFrance);
	public void ajouter(VilleFrance villeFrance);
	public void modifier(VilleFrance villeFrance);
	public void supprimer(String codeCommuneINSEE);
	public Connection creerConnexion();
	public VilleFrance map(ResultSet resultSet) throws SQLException;
	

}
