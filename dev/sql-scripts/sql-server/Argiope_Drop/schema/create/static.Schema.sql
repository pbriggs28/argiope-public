USE [Argiope]
GO
/****** Object:  Schema [static]    Script Date: 7/2/2017 1:10:42 PM ******/
IF NOT EXISTS (SELECT * FROM sys.schemas WHERE name = N'static')
EXEC sys.sp_executesql N'CREATE SCHEMA [static]'

GO
