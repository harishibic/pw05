//Author: Haris Hibic

package fr.isen.java2.db.daos;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.isen.java2.db.entities.Genre;

public class GenreDao {
	
	
	public List<Genre> listGenres() {
		List<Genre> listOfGenres = new ArrayList<>();
		try(Connection connection = DataSourceFactory.getDataSource().getConnection()){
			try(Statement statement = connection.createStatement()){
				try(ResultSet results = statement.executeQuery("SELECT * FROM genre")){
					while(results.next()) {
						Genre genre = new Genre(results.getInt("idgenre"),results.getString("name"));
						listOfGenres.add(genre);
					}
				}
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfGenres;
	}


	public Genre getGenre(String name) {
	    Genre genre = null;
	    try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
	        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM genre WHERE name = ?")){
	            statement.setString(1, name);
	            
	            try (ResultSet results = statement.executeQuery()) {
	                {
	                	genre = new Genre(results.getInt("idgenre"),results.getString("name"));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return genre;
	}

	public void addGenre(String name) {
		try(Connection connection = DataSourceFactory.getDataSource().getConnection()){
			String query = "INSERT INTO genre(name) VALUES(?)";
			try(PreparedStatement statement =  connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
				statement.setString(1, name);
				statement.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

