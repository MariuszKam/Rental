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

    public static ContractService createContractService() {
        return createService(ContractService.class);
    }

    public static CustomerService createCustomerService() {
        return createService(CustomerService.class);
    }

    public static DamageReportService createDamageReportService() {
        return createService(DamageReportService.class);
    }

    public static EmployeeService createEmployeeService() {
        return createService(EmployeeService.class);
    }

    public static FeedbackService createFeedbackService() {
        return createService(FeedbackService.class);
    }

    public static InsuranceCompanyService createInsuranceCompanyService() {
        return createService(InsuranceCompanyService.class);
    }

    public static InsuranceService createInsuranceService() {
        return createService(InsuranceService.class);
    }

    public static MaintenanceService createMaintenanceService() {
        return createService(MaintenanceService.class);
    }

    public static PaymentService createPaymentService() {
        return createService(PaymentService.class);
    }

    public static RentalDealService createRentalDealService() {
        return createService(RentalDealService.class);
    }

    public static StatusService createStatusService() {
        return createService(StatusService.class);
    }

    public static VehicleService createVehicleService() {
        return createService(VehicleService.class);
    }

    public static VehicleTypeService createVehicleTypeService() {
        return createService(VehicleTypeService.class);
    }

    private static <T> T createService(Class<T> serviceInterface) {
        try {
            String type = System.getProperty("implementation");
            if (type == null) {
                throw new RuntimeException("System property 'implementation' not set.");
            }
            String className = "com.solvd.service." + type + "." + serviceInterface.getSimpleName() + "Impl";
            Class<?> serviceClass = Class.forName(className);
            return serviceInterface.cast(serviceClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Unable to create object", e);
        }
    }
}
