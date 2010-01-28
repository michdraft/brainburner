package data.objects;

import data.DBConnection;

/**
 * DataObject.java, package: data.objects
 * Interface which has to be implemented by the data object classes.
 */
public interface DataObject {
	public boolean insert(DBConnection connection);
	public boolean update(DBConnection connection);
	public boolean drop(DBConnection connection);
}
