package com.solvd;


import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.vehicle.VehicleServiceImpl;
import com.solvd.service.vehicle.VehicleTypeService;
import com.solvd.service.vehicle.VehicleTypeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //Start of app logic
        //GetAllVehicleTypes
        VehicleTypeService vehicleTypeService = new VehicleTypeServiceImpl();
        vehicleTypeService.showAllByNames();

        //GetAllAvailableCars for Customer
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.showAvailableByVehicleType("Pickup");


    }
}