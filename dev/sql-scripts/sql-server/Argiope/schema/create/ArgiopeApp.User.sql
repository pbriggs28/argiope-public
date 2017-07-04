USE [Argiope]
GO
/****** Object:  User [ArgiopeApp]    Script Date: 7/2/2017 1:18:21 PM ******/
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = N'ArgiopeApp')
CREATE USER [ArgiopeApp] FOR LOGIN [ArgiopeApp] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_datareader] ADD MEMBER [ArgiopeApp]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [ArgiopeApp]
GO
