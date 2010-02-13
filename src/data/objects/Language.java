package data.objects;

/**
 * Language.java, package: data.objects
 * This class capsulates the language data objects from the db.
 */
public class Language {

	private int id;
	private String name;

	public Language(String name) {
		this.name = name;
	}

	public Language(String name, int id) {
		this.id = id;
		this.name = name;
	}

	/**
	 * return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
