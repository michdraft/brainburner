package data;

import helper.Helpers;
import data.objects.Language;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Languages extends ArrayList<Language> {
	public static boolean addLanguage(DBConnection connection, Language language) {
		String query = String.format("insert into languages(languagename) values('%s')",
			language.getName());

		if (connection.updateDB(query) == 1)
			return true;
		else
			return false;
	}

	public static Languages getAllLanguages(DBConnection connection) {
		String query = "select * from languages";

		ResultSet result_set = connection.queryDB(query);
		Languages languages = new Languages();

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

	public static Language getLanguage(DBConnection connection, String language) {
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
}
