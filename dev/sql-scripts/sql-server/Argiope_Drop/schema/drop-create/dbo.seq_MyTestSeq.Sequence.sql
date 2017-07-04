USE [Argiope]
GO
USE [Argiope]
GO
/****** Object:  Sequence [dbo].[seq_MyTestSeq]    Script Date: 7/2/2017 1:11:02 PM ******/
IF  EXISTS (SELECT * FROM sys.sequences WHERE name = N'seq_MyTestSeq')
DROP SEQUENCE [dbo].[seq_MyTestSeq]
GO
USE [Argiope]
GO
/****** Object:  Sequence [dbo].[seq_MyTestSeq]    Script Date: 7/2/2017 1:11:02 PM ******/
IF NOT EXISTS (SELECT * FROM sys.sequences WHERE name = N'seq_MyTestSeq')
BEGIN
CREATE SEQUENCE [dbo].[seq_MyTestSeq] 
 AS [bigint]
 START WITH 1
 INCREMENT BY 1
 MINVALUE -9223372036854775808
 MAXVALUE 9223372036854775807
 CACHE 
END

GO
