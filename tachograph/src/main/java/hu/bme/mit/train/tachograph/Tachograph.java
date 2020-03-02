package hu.bme.mit.train.tachograph;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Date;

public class Tachograph {
    private Table<Date, Integer, Integer> log = HashBasedTable.create();

    public void addLog(Date date, Integer joystickPosition, Integer referenceSpeed){
        log.put(date, joystickPosition, referenceSpeed);
    }

    public int getSize(){
        return log.size();
    }

}
