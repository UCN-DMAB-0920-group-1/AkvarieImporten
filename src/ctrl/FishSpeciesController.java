package ctrl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.Database;
import db.IFishSpeciesDB;
import exception.DataAccessException;
import model.FishSpecies;

public class FishSpeciesController {
	private Map<Integer, FishSpecies> speciesMatches;
	private IFishSpeciesDB fishSpeciesDB;

	public FishSpeciesController() {
		this.fishSpeciesDB = Database.getInstance().fishSpeciesDB();
	}

	public Map<Integer, FishSpecies> searchFishSpecies(String searchInput) throws SQLException {
		this.speciesMatches = fishSpeciesDB.getFishSpecies(searchInput);
		return new HashMap<Integer, FishSpecies>(speciesMatches);

	}

	public FishSpecies getFishSpecies(int fishSpeciesId){
		FishSpecies res = null;
		res = speciesMatches.get(fishSpeciesId);
		return res;
	}
}
