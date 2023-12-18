USE Rental;

INSERT INTO Customer (id, First_Name, Last_Name, Phone_Number, Email, Address)
VALUES (1, 'Adam', 'Smith', '506-868-001', 'a.smith@gmail.com', 'Main St. 12');

INSERT INTO Vehicle_Type (id, Type_Name)
VALUES (1, 'Van');

INSERT INTO Vehicle (id, Vehicle_Type_id, Model, Registration_Number, Current_Kilomters, Status)
VALUES (1, 1, 'Volkswagen Sharan', 'SB18995', 75000, true);

INSERT INTO Contract (id, Start_Contract, End_Contract, Salary)
VALUES (1, '2024-01-01', '2027-12-31', 6500.00);

INSERT INTO Employee (id, First_Name, Last_Name, Position, Contract_id)
VALUES (1, 'Maria', 'Castile', 'Seller', 1);

INSERT INTO Status (id, Status)
VALUES (1, 'Active');

INSERT INTO Rental_Deal (id, Customer_id, Start_Rental, End_Rental, Total_Cost, Employee_id, Status_id)
VALUES (1, 1, '2024-02-01', '2024-02-15', 800.00, 1, 1);

INSERT INTO Rental_deal_has_vehicle (id, Rental_Deal_id, Vehicle_id)
VALUES (1, 1, 1);

INSERT INTO Maintenance (id, Vehicle_id, Maintenance_Date, Description, Cost)
VALUES (1, 1, '2023-02-10', 'Oil Change', 50.00);

INSERT INTO Insurance_Company (id, Insurance_Name)
VALUES (1, 'Warta');

INSERT INTO Insurance (id, Vehicle_id, PolicyNumber, Cost, Insurance_Company_id)
VALUES (1, 1, '1445684', 1200.00, 1);

INSERT INTO Feedback (id, Rate, Description, Rental_Deal_id, Customer_id)
VALUES (1, 3, 'Not bad, not terrible', 1, 1);

INSERT INTO Payment (id, Amount, Payment_Date, Payment_Method, Rental_Deal_id)
VALUES (1, 1200, '2024-02-15', 'Blik', 1);

INSERT INTO Damage_Raport (id, Raport_Date, Description, Vehicle_id, Rental_Deal_id)
VALUES (1, '2024-02-15', 'Scratch on the door', 1, 1);
