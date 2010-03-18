package data;

import java.util.ArrayList;
import data.objects.ExerciseArea;
import helper.Helpers;
import java.sql.ResultSet;

public class ExerciseAreas extends ArrayList<ExerciseArea> {
	public static boolean addExerciseArea(DBConnection connection, ExerciseArea exercisearea,
						String username, String languagename) {
		String query = String.format("insert into exercisearea(areaname) values('%s')", exercisearea.getAreaname());

		if (connection.updateDB(query) == 1) {
			int userid = Users.getUser(connection, username).getId();
			int exerciseareaid = ExerciseAreas.getExerciseArea(connection, exercisearea.getAreaname()).getId();
			String query_user_rel = 
				"insert into user_table_rel(userid, exerciseareaid)" +
				"values(" + userid + "," + exerciseareaid + ")";
			if(connection.updateDB(query_user_rel) == 1) {
				int languageid = Languages.getLanguage(connection, languagename).getId();
				String query_lang_rel = String.format(
					"insert into lang_table_rel(languageid, exerciseareaid)" +
					"values(" + languageid + "," + exerciseareaid + ")");
				if(connection.updateDB(query_lang_rel) == 1) {
					return true;
				} else { return false; }
			} else { return false; }
		} else { return false; }
	}

	public static ExerciseAreas getAllExerciseAreas(DBConnection connection) {
		String query = "select * from exercisearea";

		ResultSet result_set = connection.queryDB(query);
		ExerciseAreas exerciseareas = new ExerciseAreas();

		try {
			while (result_set.next()) {
				exerciseareas.add(new ExerciseArea(result_set.getString("AREANAME"),
								result_set.getInt("ID")));
			}
			return exerciseareas;
		} catch (Exception e) {
			Helpers.debug("getAllExerciseAreas: Error: %s\n", e.getMessage());
			return null;
		}
	}

	public static ExerciseAreas getAllExerciseAreasFromUser(DBConnection connection,
							String username) {

		int userid = Users.getUser(connection, username).getId();

		String query = "select EXERCISEAREA.AREANAME " +
			       "from USER_TABLE_REL join EXERCISEAREA " +
			       "on USER_TABLE_REL.EXERCISEAREAID = EXERCISEAREA.ID " +
			       "where USER_TABLE_REL.USERID = " + userid;

		ResultSet result_set = connection.queryDB(query);
		ExerciseAreas exerciseareas = new ExerciseAreas();

		try {
			while (result_set.next()) {
				exerciseareas.add(new ExerciseArea(result_set.getString("AREANAME")));
			}
			return exerciseareas;
		} catch (Exception e) {
			Helpers.debug("getAllExerciseAreas: Error: %s\n", e.getMessage());
			return null;
		}
	}

	public static ExerciseArea getExerciseArea(DBConnection connection, String areaname) {
		String query = String.format("select * from exercisearea where areaname='%s'", areaname);

		ResultSet result_set = connection.queryDB(query);

		try {
			if (result_set.next()) {
				return new ExerciseArea(result_set.getString("AREANAME"),
						    result_set.getInt("ID"));
			} else {
				return null;
			}
		} catch (Exception e) {
			Helpers.debug("getExerciseArea: Error: %s\n", e.getMessage());
			return null;
		}
	}
}
