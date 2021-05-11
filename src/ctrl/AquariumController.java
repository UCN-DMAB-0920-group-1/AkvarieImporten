package ctrl;

import java.util.List;

import db.IAquariumDB;
import db.dao.AquariumDB;
import exception.DataAccessException;
import model.Aquarium;

public class AquariumController {
    private List<Aquarium> aquariumMatches;
    private IAquariumDB aquariumDB;

    public AquariumController() throws DataAccessException {
	this.aquariumDB = new AquariumDB();
    }

    public List<Aquarium> searchAquarium(String searchInput) {
	aquariumMatches = aquariumDB.getAquarium(searchInput);
	return aquariumMatches;
    }

    public Aquarium getAquarium(int aquariumID) {
	Aquarium res = aquariumMatches.parallelStream().filter(x -> x.getId() == aquariumID).findFirst().get();
	return res;
    }
}
