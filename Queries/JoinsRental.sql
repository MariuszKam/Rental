SELECT *
FROM Customer
INNER JOIN Rental_Deal ON Customer.id = Rental_Deal.Customer_id;

SELECT *
FROM Contract
LEFT JOIN Employee ON Contract.id = Employee.Contract_id;

SELECT *
FROM Vehicle
RIGHT JOIN Insurance ON Vehicle.id = Insurance.Vehicle_id;

SELECT *
FROM Vehicle
LEFT JOIN Damage_Raport ON Vehicle.id = Damage_raport.vehicle_id
UNION
SELECT *
FROM Vehicle
RIGHT JOIN Damage_Raport ON Vehicle.id = Damage_raport.vehicle_id;

SELECT Customer.First_Name, Feedback.description
FROM Feedback
RIGHT JOIN Customer ON Feedback.Customer_id = Customer.id;






