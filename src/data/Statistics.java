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

	public static ArrayList<Object[]> getAllStatisticsFromUser(DBConnection connection, User user) {
		String query = String.format("select * from getStatistic where getStatistic.userid = %d", user.getId());

		ResultSet result_set = connection.queryDB(query);
		ArrayList<Object[]> list = new ArrayList<Object[]>();

		try {
			while (result_set.next()) {
				Object[] obj = new Object[4];
				obj[0] = result_set.getString("USERNAME");
				obj[1] = result_set.getString("AREANAME");
				obj[2] = result_set.getString("PERCENT");
				obj[3] = new Date(result_set.getLong("LEARNDATE")).toLocaleString();
				list.add(obj);
			}

			return list;
		} catch (Exception e) {
			Helpers.debug("getAllStatistics: Error: %s\n", e.getMessage());
			return null;
		}
	}
}
