package hu.bme.mit.train.tachograph;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;

public class TachographTest {

    Tachograph tachograph;

    @Test
    public void addLog() {
        tachograph = new Tachograph();
        tachograph.addLog(new Date(), 2, 2);

        assertEquals(1, tachograph.getSize());
    }
}