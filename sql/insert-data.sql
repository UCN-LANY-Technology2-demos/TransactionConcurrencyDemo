USE [TransactionDemo]
GO

TRUNCATE TABLE Account;

INSERT INTO Account (Id, Balance) VALUES(1, 100);  
INSERT INTO Account (Id, Balance) VALUES(2, 200);
INSERT INTO Account (Id, Balance) VALUES(3, 300);

TRUNCATE TABLE Users;

INSERT INTO Users (Id, Name, Age) VALUES (1, 'Alice', 20);
INSERT INTO Users (Id, Name, Age) VALUES (2, 'Bob', 23);