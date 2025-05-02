package searchengineapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Search")

public class Search extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {

		// Getting keyword from front-end
		String keyword = request.getParameter("keyword");

		System.out.println(keyword);

		// Getting up connection to database
		Connection connection = DatabaseConnection.getConnection();

		try {

			// add keyword into history table
			PreparedStatement preparedStatement = connection.prepareStatement("Insert into history values(?, ?)");

			preparedStatement.setString(1, keyword);

			preparedStatement.setString(2, "http://localhost:8080/searchengineapp/Search?keyword=" + keyword);
			preparedStatement.executeUpdate();

			// getting result after running ranking Query

			ResultSet resultSet = connection.createStatement().executeQuery(
					"select pageTitle, pageLink, (length(lower(pageText))-length(replace(lower(pageText), '" + keyword
							+ "', \"\")))/length('" + keyword
							+ "') as countoccurence from pages order by countoccurence desc limit 30;");

			ArrayList<SearchResult> results = new ArrayList<SearchResult>();

			// Transferring values from resultSet to arrayList
			while (resultSet.next()) {
				SearchResult searchresult = new SearchResult();
				searchresult.setPageTitle(resultSet.getString("pageTitle"));
				searchresult.setPageLink(resultSet.getString("pageLink"));
				results.add(searchresult);

			}

			// getting result arrayList in console
			for (SearchResult result : results) {
				System.out.println(result.getPageTitle() + "\n" + result.getPageLink() + "\n");
			}

			request.setAttribute("results", results);

			request.getRequestDispatcher("search.jsp").forward(request, response);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}

		catch (IOException ioException) {
			ioException.printStackTrace();
		}
		catch (ServletException servletException) {
			servletException.printStackTrace();
		}

	}

}
