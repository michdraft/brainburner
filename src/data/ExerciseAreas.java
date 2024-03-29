package data;

import java.util.ArrayList;
import data.objects.ExerciseArea;
import helper.Helpers;
import java.sql.ResultSet;

public class ExerciseAreas extends ArrayList<ExerciseArea> {
	public static final int STAT_OK = 0;
	public static final int STAT_ERROR = 1;
	public static final int STAT_NOT_UNIQUE = 2;

	public static int addExerciseArea(DBConnection connection, ExerciseArea exercisearea,
						String username, String languagename) {

		ExerciseAreas areas = getAllExerciseAreasFromUser(connection, username);
		for (ExerciseArea area : areas) {
			if (area.getAreaname().equals(exercisearea.getAreaname())) {
				return STAT_NOT_UNIQUE;
			}
		}

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
					return STAT_OK;
				} else {
					return STAT_ERROR;
				}
			} else {
				return STAT_ERROR;
			}
		} else {
			return STAT_ERROR;
		}
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

		String query = String.format("select * from exerciseareaFromUser where exerciseareaFromUser.USERID = %d", userid);

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

	public static int getNumberOfExerciseAreasFromUser(DBConnection connection,
							String username) {

		int userid = Users.getUser(connection, username).getId();
		int number = 0;

		String query = String.format("select count(*) as number " +
			"from exerciseareaFromUser where exerciseareaFromUser.USERID = %d", userid);
		ResultSet result_set = connection.queryDB(query);

		try {
			while(result_set.next()) {
				return result_set.getInt("number");
			}
		} catch (Exception e) {
			Helpers.debug("getNumberOfExerciseAreas: Error: %s\n", e.getMessage());
		}
		
		return number;
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

	public static ExerciseArea getExerciseAreaById(DBConnection connection, int id) {
		String query = String.format("select * from exercisearea where id=%d", id);

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
