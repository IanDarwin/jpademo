package dao;

import java.util.List;

import domain.model.MusicRecording;


public interface MusicDao {

	void saveMusicRecording(MusicRecording recording);
	void deleteMusicRecording(MusicRecording recording);
	MusicRecording getMusicRecording(long id);
	void close();
	List<MusicRecording> listMusicRecordings();
	List<MusicRecording> findRecordingsByPrice(double d);
}
