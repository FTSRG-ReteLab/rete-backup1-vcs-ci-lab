package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private ScheduledExecutorService timeUnitCounter;
	private int referenceSpeed = 0;
	private int speedLimit = 0;

	public TrainControllerImpl(){
		timeUnitCounter = Executors.newScheduledThreadPool(1);
		timeUnitCounter.scheduleAtFixedRate(this::followSpeed, 0, 1, TimeUnit.SECONDS);

	}

	@Override
	public void followSpeed() {
		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
		    if(referenceSpeed+step > 0) {
                referenceSpeed += step;
            } else {
		        referenceSpeed = 0;
            }
		}
		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
	}

}
