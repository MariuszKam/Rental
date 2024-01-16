package com.solvd.factory;

import com.solvd.service.deal.FeedbackService;
import com.solvd.service.deal.PaymentService;
import com.solvd.service.deal.RentalDealService;
import com.solvd.service.deal.StatusService;
import com.solvd.service.persons.customer.CustomerService;
import com.solvd.service.persons.employee.ContractService;
import com.solvd.service.persons.employee.EmployeeService;
import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.vehicle.VehicleTypeService;
import com.solvd.service.vehicle.maintenance.DamageReportService;
import com.solvd.service.vehicle.maintenance.InsuranceCompanyService;
import com.solvd.service.vehicle.maintenance.InsuranceService;
import com.solvd.service.vehicle.maintenance.MaintenanceService;

public class FactoryService {

    public static ContractService createContractService(String type) {
        return createService(type, ContractService.class);
    }

    public static CustomerService createCustomerService(String type) {
        return createService(type, CustomerService.class);
    }

    public static DamageReportService createDamageReportService(String type) {
        return createService(type, DamageReportService.class);
    }

    public static EmployeeService createEmployeeService(String type) {
        return createService(type, EmployeeService.class);
    }

    public static FeedbackService createFeedbackService(String type) {
        return createService(type, FeedbackService.class);
    }

    public static InsuranceCompanyService createInsuranceCompanyService(String type) {
        return createService(type, InsuranceCompanyService.class);
    }

    public static InsuranceService createInsuranceService(String type) {
        return createService(type, InsuranceService.class);
    }

    public static MaintenanceService createMaintenanceService(String type) {
        return createService(type, MaintenanceService.class);
    }

    public static PaymentService createPaymentService(String type) {
        return createService(type, PaymentService.class);
    }

    public static RentalDealService createRentalDealService(String type) {
        return createService(type, RentalDealService.class);
    }

    public static StatusService createStatusService(String type) {
        return createService(type, StatusService.class);
    }

    public static VehicleService createVehicleService(String type) {
        return createService(type, VehicleService.class);
    }

    public static VehicleTypeService createVehicleTypeService(String type) {
        return createService(type, VehicleTypeService.class);
    }

    private static <T> T createService(String type, Class<T> serviceInterface) {
        try {
            String className = "com.solvd.service." + type + "." + serviceInterface.getSimpleName() + "Impl";
            Class<?> serviceClass = Class.forName(className);
            return serviceInterface.cast(serviceClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException(String.format("Unable to create object related to the %s type", type), e);
        }
    }
}
