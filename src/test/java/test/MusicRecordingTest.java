package test;

import domain.media.Duration;
import domain.media.MusicRecording;
import domain.media.Track;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MusicRecordingTest {

    @Test
    public void testGetDuration() {
        Track[] tracks = new Track[] {
                new Track("Track 1", new Duration(0, 0, 42)),
                new Track("Track 2", new Duration(18)),
                new Track("Empty", new Duration(0)),
        };
        MusicRecording mrec =
                new MusicRecording("Foo", tracks, "Woozy",
                        12.50,"Jazz", "cover123.jpg");
        assertEquals(60, mrec.getDuration().getTotalSeconds());
    }
}
