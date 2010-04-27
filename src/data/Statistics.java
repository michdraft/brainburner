package data;

import helper.Helpers;
import data.objects.Statistic;
import data.objects.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

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

	public static Statistics getAllStatisticsFromUser(DBConnection connection, User user) {
		String query = String.format("select * from statistic where statistic.userid = %d", user.getId());

		ResultSet result_set = connection.queryDB(query);
		Statistics statistics = new Statistics();

		try {
			while (result_set.next()) {
				statistics.add(new Statistic(result_set.getInt("USERID"),
					result_set.getInt("EXERCISEAREAID"),
					result_set.getInt("PERCENT"),
					new Date(result_set.getLong("LEARNDATE"))));
			}

			return statistics;
		} catch (Exception e) {
			Helpers.debug("getAllStatistics: Error: %s\n", e.getMessage());
			return null;
		}
	}
}
