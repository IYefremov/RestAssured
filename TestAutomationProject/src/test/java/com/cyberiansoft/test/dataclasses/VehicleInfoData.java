package com.cyberiansoft.test.dataclasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VehicleInfoData {

	@JsonProperty("vinNumber")
	String vinNumber;

	@JsonProperty("vehicleMake")
	String vehicleMake;

	@JsonProperty("vehicleModel")
	String vehicleModel;

	@JsonProperty("stockNumber")
	String stockNumber;

	@JsonProperty("vehicleColor")
	String vehicleColor;

	@JsonProperty("vehicleYear")
	String vehicleYear;

	@JsonProperty("vehicleType")
	String vehicleType;

	@JsonProperty("roNumber")
	String roNumber;

	@JsonProperty("poNumber")
	String poNumber;

	@JsonProperty("mileage")
	String mileage;

	@JsonProperty("licPlate")
	String licPlate;

	@JsonProperty("vehicleFuelTankLevel")
	String vehicleFuelTankLevel;

	@JsonProperty("vehicleStock")
	String vehicleStock;

	@JsonProperty("location")
	String location;

	@JsonProperty("trim")
	String trim;

	@JsonProperty("vehicleAdvisor")
	String vehicleAdvisor;

	@JsonProperty("defaultTechnician")
	ServiceTechnician defaultTechnician;

	@JsonProperty("newTechnician")
	ServiceTechnician newTechnician;

	@JsonProperty("newTechnicians")
	List<ServiceTechnician> newTechnicians;

	public String getVINNumber() {
		return vinNumber;
	}

	public String getVehicleMake() {
		return vehicleMake;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public String getVehicleYear() {
		return vehicleYear;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public String getStockNumber() {
		return stockNumber;
	}

	public String getRoNumber() {
		return roNumber;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public String getMileage() {
		return mileage;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public String getVehicleLicensePlate() {
		return licPlate;
	}

	public String getFuelTankLevel() {return vehicleFuelTankLevel;}
}
