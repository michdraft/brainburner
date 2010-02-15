package data.objects;

/**
 * ExcerciseArea.java, package: data.objects
 * This class capsulates the Exercisearea data objects from the db.
 */
public class ExerciseArea {
	private int id;
	private String areaname;

	public ExerciseArea(String areaname)
	{
		this.areaname = areaname;
	}

	public ExerciseArea(String areaname, int id)
	{
		this.id = id;
		this.areaname = areaname;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the areaname
	 */
	public String getAreaname() {
		return areaname;
	}

	/**
	 * @param areaname the areaname to set
	 */
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	@Override
	public String toString()
	{
		return this.areaname;
	}

}
