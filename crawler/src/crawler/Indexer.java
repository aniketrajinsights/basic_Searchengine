package crawler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jsoup.nodes.Document;

public class Indexer {
	static Connection connection = null;

	Indexer(Document document, String url) {

		// save important element of document
		String title = document.title();
		String link = url;
		String text = document.text();

		// save these elements to database

		try {
			connection = DatabaseConnection11.getConnection();// ******************

			// value will we dynamic

			PreparedStatement preparedStatement = connection.prepareStatement("Insert into pages values (?, ?, ?);");// *******
			preparedStatement.setString(1, title);
			preparedStatement.setString(2, url);
			preparedStatement.setString(3, text);
			preparedStatement.executeUpdate();

		} catch (SQLException | NullPointerException sqlException) {
			sqlException.printStackTrace();
		}

	}
}
