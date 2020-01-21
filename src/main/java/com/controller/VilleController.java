package com.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.dao.VilleDAO;
import com.dto.client.VilleFrance;

@RestController
@RequestMapping("/path")
public class VilleController {

	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<VilleFrance> get(@RequestParam(required = false, value="codeCommuneINSEE") String codeCommuneINSEE,
			@RequestParam(required = false, value="nomCommune") String nomCommune,
			@RequestParam(required = false, value="codePostal") String codePostal,
			@RequestParam(required = false, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required = false, value="ligne5") String ligne5,
			@RequestParam(required = false, value="latitude") String latitude,
			@RequestParam(required = false, value="longitude") String longitude) {
		
		ArrayList<VilleFrance> villesFrance = null;
		
		VilleDAO villeFranceDAO = new VilleDAO();

		VilleFrance villeFrance = new VilleFrance();
		villeFrance.setCodeCommuneINSEE(getSpace(codeCommuneINSEE));
		villeFrance.setNomCommune(getSpace(nomCommune));
		villeFrance.setCodePostal(getSpace(codePostal));
		villeFrance.setLibelleAcheminement(getSpace(libelleAcheminement));
		villeFrance.setLigne5(getSpace(ligne5));
		villeFrance.setLatitude(getSpace(latitude));
		villeFrance.setLongitude(getSpace(longitude));
		
		villesFrance = villeFranceDAO.trouver(villeFrance);

		return villesFrance;
	}
	
	@RequestMapping(value="/getWithPagination", method=RequestMethod.GET)
	@ResponseBody
	public ArrayList<VilleFrance> getWithPagination(@RequestParam(required = true, value="offset") String offset) {
		
		ArrayList<VilleFrance> villesFrance = null;
		
		VilleDAO villeFranceDAO = new VilleDAO();

		//villesFrance = villeFranceDAO.trouverEntre((int) Integer.parseInt(offset));
				
		return villesFrance;
	}
	
	@RequestMapping(value="/post", method=RequestMethod.POST)
	@ResponseBody
	public void post(@RequestParam(required = true, value="codeCommuneINSEE") String codeCommuneINSEE,
			@RequestParam(required = true, value="nomCommune") String nomCommune,
			@RequestParam(required = true, value="codePostal") String codePostal,
			@RequestParam(required = true, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required = true, value="ligne5") String ligne5,
			@RequestParam(required = true, value="latitude") String latitude,
			@RequestParam(required = true, value="longitude") String longitude) {
		
		VilleFrance villeFrance = new VilleFrance();
		VilleDAO villeFranceDAO = new VilleDAO();
		
		villeFrance.setCodeCommuneINSEE(getSpace(codeCommuneINSEE));
		villeFrance.setNomCommune(getSpace(nomCommune));
		villeFrance.setCodePostal(getSpace(codePostal));
		villeFrance.setLibelleAcheminement(getSpace(libelleAcheminement));
		villeFrance.setLigne5(getSpace(ligne5));
		villeFrance.setLatitude(getSpace(latitude));
		villeFrance.setLongitude(getSpace(longitude));
		
		villeFranceDAO.ajouter(villeFrance);
		
	}
	
	@RequestMapping(value="/put", method=RequestMethod.PUT)
	@ResponseBody
	public void put(@RequestParam(required = true, value="codeCommuneINSEE") String codeCommuneINSEE,
			@RequestParam(required = false, value="nomCommune") String nomCommune,
			@RequestParam(required = false, value="codePostal") String codePostal,
			@RequestParam(required = false, value="libelleAcheminement") String libelleAcheminement,
			@RequestParam(required = false, value="ligne5") String ligne5,
			@RequestParam(required = false, value="latitude") String latitude,
			@RequestParam(required = false, value="longitude") String longitude) {
				
		VilleDAO villeFranceDAO = new VilleDAO();

		VilleFrance villeFrance = new VilleFrance();
		villeFrance.setCodeCommuneINSEE(getSpace(codeCommuneINSEE));
		villeFrance.setNomCommune(getSpace(nomCommune));
		villeFrance.setCodePostal(getSpace(codePostal));
		villeFrance.setLibelleAcheminement(getSpace(libelleAcheminement));
		villeFrance.setLigne5(getSpace(ligne5));
		villeFrance.setLatitude(getSpace(latitude));
		villeFrance.setLongitude(getSpace(longitude));
		
		villeFranceDAO.modifier(villeFrance);
		
	}
	
	@RequestMapping(value="/delete/{codeCommuneINSEE}", method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable("codeCommuneINSEE") String codeCommuneINSEE) {
		VilleDAO villeFranceDAO = new VilleDAO();
		
	villeFranceDAO.supprimer(codeCommuneINSEE);
	}
	

	private String getSpace(String str) {
		if (str == null) {
			return str;
		}
		return str.replaceAll("%20", " ");
	}
	
}
