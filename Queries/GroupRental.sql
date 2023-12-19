/*Sum all spending by client in company*/
SELECT Customer.id, Customer.First_Name, Customer.Last_Name, SUM(Rental_Deal.Total_Cost) AS Total_Cost
FROM Customer
JOIN Rental_Deal ON Customer.id = Rental_Deal.Customer_id
GROUP BY Customer.id, Customer.First_Name, Customer.Last_Name;

/*This one shows average salary by position*/
SELECT Position, AVG(Contract.Salary) AS Average_Salary
FROM Employee
RIGHT JOIN Contract ON Employee.Contract_id = Contract.id
GROUP BY Position;

/*Tells how many vehicles of each type company has*/
SELECT Vehicle_Type.Type_Name, COUNT(Vehicle.id) AS Vehicle_Count
FROM Vehicle_Type
LEFT JOIN Vehicle ON Vehicle_Type.id = Vehicle.Vehicle_Type_id
GROUP BY Vehicle_Type.Type_Name;

/*How much we had to spend for vehicle maintenance so far in company*/
SELECT Vehicle.id, Vehicle.Registration_Number, SUM(Maintenance.Cost) AS Total_Maintenance_Cost
FROM Vehicle
LEFT JOIN Maintenance ON Vehicle.id = Maintenance.Vehicle_id
GROUP BY Vehicle.id;

/*Here average cost of maintenance per vehicle*/
SELECT Vehicle.id, Vehicle.Registration_Number, AVG(Maintenance.Cost) AS Max_Maintenance_Cost
FROM Vehicle
LEFT JOIN Maintenance ON Vehicle.id = Maintenance.Vehicle_id
GROUP BY Vehicle.id;

/*Information about, which insurance company on avrage gives us better price*/
SELECT Insurance_Company.Insurance_Name, AVG(Insurance.Cost) AS Average_Policy_Cost
FROM Insurance_Company
LEFT JOIN Insurance ON Insurance_Company.id = Insurance.Insurance_Company_id
GROUP BY Insurance_Company.Insurance_Name;

/* Avg rating of each vehicle in company*/
SELECT Vehicle.id AS Vehicle_id, AVG(Feedback.Rate) AS Avg_Rating
FROM Vehicle
LEFT JOIN Rental_Deal_has_Vehicle ON Vehicle.id = Rental_Deal_has_Vehicle.Vehicle_id
LEFT JOIN Rental_Deal ON Rental_Deal_has_Vehicle.Rental_Deal_id = Rental_Deal.id
LEFT JOIN Feedback ON Rental_Deal.id = Feedback.Rental_Deal_id
GROUP BY Vehicle.id;

/*With having*/

/*Sum all spending by client in company, but if he took more than one deal*/
SELECT Customer_id, SUM(Total_Cost) AS Total_Rental_Cost
FROM Rental_Deal
GROUP BY Customer_id
HAVING COUNT(id) > 1;

/*Doesn't show null ratings*/
SELECT Vehicle.id AS Vehicle_id, AVG(Feedback.Rate) AS Avg_Rating
FROM Vehicle
LEFT JOIN Rental_Deal_has_Vehicle ON Vehicle.id = Rental_Deal_has_Vehicle.Vehicle_id
LEFT JOIN Rental_Deal ON Rental_Deal_has_Vehicle.Rental_Deal_id = Rental_Deal.id
LEFT JOIN Feedback ON Rental_Deal.id = Feedback.Rental_Deal_id
GROUP BY Vehicle.id
HAVING Avg_Rating IS NOT NULL;

/*Shows deals above 500 for each customer*/
SELECT Customer_id, COUNT(id) AS Rental_Deal_Total
FROM Rental_Deal
GROUP BY Customer_id
HAVING SUM(Total_Cost) > 500;

/*Shows maintenance just for cost above 20*/
SELECT Vehicle.id, Vehicle.Registration_Number, SUM(Maintenance.Cost) AS Total_Maintenance_Cost
FROM Vehicle
LEFT JOIN Maintenance ON Vehicle.id = Maintenance.Vehicle_id
GROUP BY Vehicle.id
HAVING Total_Maintenance_Cost > 20;










