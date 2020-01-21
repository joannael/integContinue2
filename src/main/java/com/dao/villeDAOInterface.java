package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.Ville;

public interface villeDAOInterface {
	
	public ArrayList<Ville> trouver(Ville villeFrance) throws SQLException;
	public void ajouter(Ville villeFrance);
	public void modifier(Ville villeFrance) throws SQLException;
	public void supprimer(String codeCommuneINSEE) throws SQLException;
	public Connection creerConnexion();
	public Ville map(ResultSet resultSet) throws SQLException;
	

}
