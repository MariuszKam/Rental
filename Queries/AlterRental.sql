ALTER TABLE Rental.Employee
MODIFY COLUMN Salary DECIMAL(12, 2);

ALTER TABLE Vehicle
RENAME COLUMN `Current_Kilomters` TO `Current_Kilometers`;

ALTER TABLE Rental.Employee
ADD COLUMN Date_of_Birth DATE;

ALTER TABLE Customers
DROP COLUMN Email;

ALTER TABLE Vehicle
ADD COLUMN Manufacturing_Year INT;

ALTER TABLE Rental.Employee
ADD COLUMN Email VARCHAR(45);




