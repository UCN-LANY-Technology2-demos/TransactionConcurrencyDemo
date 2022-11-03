USE [master]
GO

if not exists(select * from syslogins where name = 'student')
begin

CREATE LOGIN [student] WITH PASSWORD=N'P@$$w0rd', DEFAULT_DATABASE=[master], CHECK_EXPIRATION=OFF, CHECK_POLICY=OFF

end


CREATE DATABASE [TransactionDemo]
GO


USE [TransactionDemo]
GO

CREATE USER [student] FOR LOGIN [student] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_datareader] ADD MEMBER [student]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [student]
GO

-- Create tables 

CREATE TABLE [Account](
	[Id] [int] NOT NULL,
	[Balance] [float] NOT NULL,
	[Version] [timestamp] NOT NULL
)
GO

CREATE TABLE [Users](
	[Id] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Age] [int] NOT NULL
) 
GO

