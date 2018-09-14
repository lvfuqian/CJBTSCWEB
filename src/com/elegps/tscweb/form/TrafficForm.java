package com.elegps.tscweb.form;

import org.apache.struts.action.ActionForm;

import com.elegps.tscweb.model.PositionLast;
import com.elegps.tscweb.model.Vehicle;

public class TrafficForm extends ActionForm {
	private Vehicle vehicle;
	private PositionLast positionLast;

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public PositionLast getPositionLast() {
		return positionLast;
	}

	public void setPositionLast(PositionLast positionLast) {
		this.positionLast = positionLast;
	}

}
