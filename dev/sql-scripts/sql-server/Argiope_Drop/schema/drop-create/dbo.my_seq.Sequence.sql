USE [Argiope]
GO
USE [Argiope]
GO
/****** Object:  Sequence [dbo].[my_seq]    Script Date: 7/2/2017 1:11:02 PM ******/
IF  EXISTS (SELECT * FROM sys.sequences WHERE name = N'my_seq')
DROP SEQUENCE [dbo].[my_seq]
GO
USE [Argiope]
GO
/****** Object:  Sequence [dbo].[my_seq]    Script Date: 7/2/2017 1:11:02 PM ******/
IF NOT EXISTS (SELECT * FROM sys.sequences WHERE name = N'my_seq')
BEGIN
CREATE SEQUENCE [dbo].[my_seq] 
 AS [int]
 START WITH 1
 INCREMENT BY 20
 MINVALUE -2147483648
 MAXVALUE 2147483647
 CACHE 
END

GO
