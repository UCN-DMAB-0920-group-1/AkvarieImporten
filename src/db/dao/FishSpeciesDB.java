package db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import db.DBConnection;
import db.IFishSpeciesDB;
import exception.DataAccessException;
import model.FishSpecies;

public class FishSpeciesDB implements IFishSpeciesDB {
	// TODO
	static final String Q_GET_FISH_SPECIES = "SELECT id AS species_id, name AS species_name, average_eggs AS species_average_eggs, birth_size AS species_birth_size, grow_rate AS species_grow_rate, minimum_sale_size AS species_minimum_sale_size FROM fish_species WHERE name LIKE ?";

	private PreparedStatement psGetFishSpecies;

	public FishSpeciesDB() throws DataAccessException {
		Connection connection = DBConnection.getInstance().getConnection();

		try {
			psGetFishSpecies = connection.prepareStatement(Q_GET_FISH_SPECIES);
		} catch (SQLException e) {
			throw new DataAccessException("could not create preparedstatement, check your query", null);
		}
	}

	@Override
	public Map<Integer, FishSpecies> getFishSpecies(String searchInput) throws SQLException {
		Map<Integer, FishSpecies> res = null;

		psGetFishSpecies.setString(1, "%" + searchInput + "%");
		
		ResultSet rs = psGetFishSpecies.executeQuery();
		
		res = buildObjects(rs);


		return res;
	}

	protected static Map<Integer, FishSpecies> buildObjects(ResultSet rs) throws SQLException {
		Map<Integer, FishSpecies> res = new HashMap<Integer, FishSpecies>();
		FishSpecies current = null;
		while (rs.next()) {
			current = buildObject(rs);
			res.put(current.getId(), current);
		}
		return res;
	}

	protected static FishSpecies buildObject(ResultSet rs) throws SQLException {
		FishSpecies res = null;
		res = new FishSpecies(rs.getString("species_name"), rs.getInt("species_average_eggs"),
				rs.getDouble("species_birth_size"), rs.getDouble("species_grow_rate"),
				rs.getInt("species_minimum_sale_size"));
		res.setId(rs.getInt("species_id"));
		return res;
	}

}
