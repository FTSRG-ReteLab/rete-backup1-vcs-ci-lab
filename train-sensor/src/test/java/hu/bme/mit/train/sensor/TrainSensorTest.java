package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController trainController;
    TrainUser trainUser;
    TrainSensor trainSensor;

    @Before
    public void before() {
        trainController = mock(TrainController.class);
        trainUser = mock(TrainUser.class);
        trainSensor = new TrainSensorImpl(trainController, trainUser);
    }

    @Test
    public void OverrideSpeedLimitToNegative_SetsAlarmStateTrue(){
        trainSensor.overrideSpeedLimit(-1);
        verify(trainUser).setAlarmState(true);
    }

    @Test
    public void OverrideSpeedLimitToOver500_SetsAlarmStateTrue(){
        trainSensor.overrideSpeedLimit(501);
        verify(trainUser).setAlarmState(true);
    }

    @Test
    public void OverrideSpeedLimitToLessThan50Percent_SetsAlarmStateTrue(){
        when(trainController.getReferenceSpeed()).thenReturn(50);
        trainSensor.overrideSpeedLimit(24);
        verify(trainUser).setAlarmState(true);
    }

    @Test
    public void OverrideSpeedLimitToCorrectValue_SetsAlarmStateFalse(){
        when(trainController.getReferenceSpeed()).thenReturn(50);
        trainSensor.overrideSpeedLimit(26);
        verify(trainUser).setAlarmState(false);
    }
}
