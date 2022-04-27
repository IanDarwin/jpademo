package dao;

import java.util.List;

/**
 * The interface for Rainforest Recording Data Accessors.
 */
public interface RecordingDao<T> {
	public List<String> getCategories();
	public long saveRecording(T t);
	public T getRecording(long id);
	public List<T> getRecordings(String category);
}
