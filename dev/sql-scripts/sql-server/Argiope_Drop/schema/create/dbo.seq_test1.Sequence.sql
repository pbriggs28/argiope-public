USE [Argiope]
GO
USE [Argiope]
GO
/****** Object:  Sequence [dbo].[seq_test1]    Script Date: 7/2/2017 1:10:42 PM ******/
IF NOT EXISTS (SELECT * FROM sys.sequences WHERE name = N'seq_test1')
BEGIN
CREATE SEQUENCE [dbo].[seq_test1] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 20
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
END

GO
