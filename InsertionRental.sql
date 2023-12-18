INSERT INTO Customer (First_Name, Last_Name, Phone_Number, Email, Address)
VALUES ('Adam', 'Smith', '506-868-001', 'a.smith@gmail.com', 'Main St. 12');

INSERT INTO Vehicle_Type (Type_Name)
VALUES ('Van');

INSERT INTO Vehicle (Vehicle_Type_id, Model, Registration_Number, Current_Kilomters, Status)
VALUES (1, 'Volkswagen Sharan', 'SB18995', 75000, true);

INSERT INTO Contract (Start_Contract, End_Contract, Salary)
VALUES ('2024-01-01', '2027-12-31', 6500.00);

INSERT INTO Employee (First_Name, Last_Name, Position, Contract_id)
VALUES ('Maria', 'Castile', 'Seller', 1);

INSERT INTO Status (Status)
VALUES ('Active');

INSERT INTO Rental_Deal (Customer_id, Start_Rental, End_Rental, Total_Cost, Employee_id, Status_id)
VALUES (1, '2024-02-01', '2024-02-15', 800.00, 1, 1);

INSERT INTO Vehicle_has_Rental_Deal (Vehicle_id, Rental_Deal_id)
VALUES (1, 1);

INSERT INTO Maintenance (Vehicle_id, Maintenance_Date, Description, Cost)
VALUES (1, '2023-02-10', 'Oil Change', 50.00);

INSERT INTO Insurance_Company (Insurance_Name)
VALUES ('Warta');

INSERT INTO Insurance (Vehicle_id, Insurence_Company, PolicyNumber, Cost, Insurance_Company_id)
VALUES (1, 'P01445684', 1200.00, 1);

INSERT INTO Feedback (Rate, Description, Rental_Deal_id, Customer_id)
VALUES (3, 'Not bad, not terrible', 1, 1);

INSERT INTO Payment (Amount, Payment_Date, Payment_Method, Rental_Deal_id)
VALUES (1200, '2024-02-15', 'Blik', 1);

INSERT INTO Damage_Raport (Raport_Date, Description, Vehicle_id, Rental_Deal_id)
VALUES ('2024-02-15', 'Scratch on the door', 1, 1);
