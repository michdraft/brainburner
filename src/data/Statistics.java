package data;

import helper.Helpers;
import data.objects.Statistic;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Statistics extends ArrayList<Statistic> {
	public static boolean addStatistic(DBConnection connection, Statistic statistic) {
		String query = String.format("insert into statistic(userid, " +
			"exerciseareaid, percent, learndate) values(" +
			"%d, %d, %d, %d)",
			statistic.getUserid(), statistic.getExerciseareaid(),
			statistic.getPercent(), statistic.getDate().getTime());

		Helpers.debug("query: %s", query);
		if (connection.updateDB(query) == 1)
			return true;
		else
			return false;
	}

	/*
	public static Statistics getAllStatistics(DBConnection connection) {
		String query = "select * from languages";

		ResultSet result_set = connection.queryDB(query);
		Statistics languages = new Statistics();

		try {
			while (result_set.next()) {
				languages.add(new Language(result_set.getString("LANGUAGENAME"),
							   result_set.getInt("ID")));
			}

			return languages;
		} catch (Exception e) {
			Helpers.debug("getAllLanguages: Error: %s\n", e.getMessage());
			return null;
		}
	}

	public static Language getStatistic(DBConnection connection, String language) {
		String query = String.format("select * from languages where languagename='%s'", language);

		ResultSet result_set = connection.queryDB(query);

		try {
			if (result_set.next()) {
				return new Language(result_set.getString("LANGUAGENAME"),
						    result_set.getInt("ID"));
			} else {
				return null;
			}
		} catch (Exception e) {
			Helpers.debug("getLanguage: Error: %s\n", e.getMessage());
			return null;
		}
	}
	*/
}
