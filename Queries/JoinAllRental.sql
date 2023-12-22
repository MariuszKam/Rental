SELECT *
FROM Customer
JOIN Rental_Deal ON Customer.id = Rental_Deal.Customer_id
JOIN Employee ON Rental_Deal.Employee_id = Employee.id
JOIN Status ON Rental_Deal.Status_id = Status.id
JOIN Rental_Deal_has_Vehicle ON Rental_Deal.id = Rental_Deal_has_Vehicle.Rental_Deal_id
JOIN Vehicle ON Rental_Deal_has_Vehicle.Vehicle_id = Vehicle.id
JOIN Vehicle_Type ON Vehicle.Vehicle_Type_id = Vehicle_Type.id
JOIN Maintenance ON Vehicle.id = Maintenance.Vehicle_id
JOIN Insurance ON Vehicle.id = Insurance.Vehicle_id
JOIN Insurance_Company ON Insurance.Insurance_Company_id = Insurance_Company.id
JOIN Feedback ON Rental_Deal.id = Feedback.Rental_Deal_id
JOIN Payment ON Rental_Deal.id = Payment.Rental_Deal_id
JOIN Damage_Raport ON Rental_Deal.id = Damage_Raport.Rental_Deal_id
JOIN Contract ON Employee.Contract_id = Contract.id;