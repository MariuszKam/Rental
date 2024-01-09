package com.solvd;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.deal.Status;
import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Contract;
import com.solvd.model.persons.employee.Employee;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.model.vehicle.VehicleType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.Main.logger;

public class XMLParser {

    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse("src/main/resources/RentalDeal.xml");
            Element rental = document.getDocumentElement();
            //Getting simple fields
            long id = getId(rental);
            LocalDateTime startRental = LocalDateTime.parse(getNodeValue(rental, "startRental"));
            LocalDateTime endRental = LocalDateTime.parse(getNodeValue(rental, "endRental"));
            BigDecimal totalCost = new BigDecimal(getNodeValue(rental, "totalCost"));
            //Getting Objects:
            //Customer
            Element customerElement = (Element) rental.getElementsByTagName("customer").item(0);
            Customer customer = getCustomer(customerElement);
            //Employee
            Element employeeElement = (Element) rental.getElementsByTagName("employee").item(0);
            Employee employee = getEmployee(employeeElement);
            //Status
            Element statusElement = (Element) rental.getElementsByTagName("status").item(0);
            Status status = getStatus(statusElement);
            //Getting List
            Element vehiclesElement = (Element) rental.getElementsByTagName("vehicles").item(0);
            NodeList vehiclesList = vehiclesElement.getElementsByTagName("vehicle");
            List<Vehicle> vehicles = getVehicles(vehiclesList);
            //Finally Creating RentalDeal!
            RentalDeal rentalDeal = new RentalDeal(id, customer, startRental, endRental, totalCost, employee, status, vehicles);
            logger.info(rentalDeal);


        } catch (Exception e) {
            logger.warn(e);
        }
    }

    private static String getNodeValue(Element element, String nodeName) {
        return element.getElementsByTagName(nodeName).item(0).getTextContent();
    }

    private static Customer getCustomer(Element element) {
        Long id = getId(element);
        String firstName = getNodeValue(element, "firstName");
        String lastName = getNodeValue(element, "lastName");
        String phoneNumber = getNodeValue(element, "phoneNumber");
        String email = getNodeValue(element, "email");
        String address = getNodeValue(element, "address");
        return new Customer(id, firstName, lastName, phoneNumber, email, address);
    }

    private static Employee getEmployee(Element element) {
        Long id = getId(element);
        String firstName = getNodeValue(element, "firstName");
        String lastName = getNodeValue(element, "lastName");
        String position = getNodeValue(element, "position");
        Element contractElement = (Element) element.getElementsByTagName("contract").item(0);
        Contract contract = getContract(contractElement);
        return new Employee(id, firstName, lastName, position, contract);
    }

    private static Contract getContract(Element element) {
        Long id = getId(element);
        LocalDateTime startContract = LocalDateTime.parse(getNodeValue(element, "startContract"));
        LocalDateTime endContract = LocalDateTime.parse(getNodeValue(element, "endContract"));
        BigDecimal salary = new BigDecimal(getNodeValue(element, "salary"));
        return new Contract(id, startContract, endContract, salary);
    }

    private static Status getStatus(Element element) {
        Long id = getId(element);
        String status = getNodeValue(element, "status");
        return new Status(id, status);
    }

    private static List<Vehicle> getVehicles(NodeList vehicles) {
        List<Vehicle> list = new ArrayList<>();
        for (int i = 0; i < vehicles.getLength(); i++) {
            Element vehicleElement = (Element) vehicles.item(i);
            Vehicle vehicle = getVehicle(vehicleElement);
            list.add(vehicle);
        }
        return list;
    }

    private static Vehicle getVehicle(Element element) {
        Long id = getId(element);
        Element vehicleTypeElement = (Element) element.getElementsByTagName("vehicleType").item(0);
        VehicleType vehicleType = getVehicleType(vehicleTypeElement);
        String model = getNodeValue(element, "model");
        String registrationNumber = getNodeValue(element, "registrationNumber");
        Long currentKilometers = Long.parseLong(getNodeValue(element, "currentKilometers"));
        boolean available = Boolean.parseBoolean(getNodeValue(element, "available"));
        return new Vehicle(id, vehicleType, model, registrationNumber, currentKilometers, available);
    }

    private static VehicleType getVehicleType(Element element) {
        Long id = getId(element);
        String typeName = getNodeValue(element, "typeName");
        return new VehicleType(id, typeName);
    }

    private static Long getId(Element element) {
        return Long.parseLong(getNodeValue(element, "id"));
    }
}
