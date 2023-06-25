package test;

import domain.media.Duration;
import domain.media.MusicRecording;
import domain.media.Track;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DurationTest {

    @Test
    public void testGettersFromTotalSeconds() {
        Duration d = new Duration(3601);
        assertEquals(3601, d.getTotalSeconds());
        assertEquals(1, d.getHours());
        assertEquals(0, d.getMinutes());
        assertEquals(1, d.getSeconds());
    }

    @Test
    public void testTotalSecondsFromHMS() {
        Duration d = new Duration(2, 3, 4);
        assertEquals(7384, d.getTotalSeconds());
    }
}
