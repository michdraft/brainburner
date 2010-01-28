package data.objects;

import data.DBConnection;

public interface DataObject {
	public boolean insert(DBConnection connection);
	public boolean update(DBConnection connection);
	public boolean drop(DBConnection connection);
}
