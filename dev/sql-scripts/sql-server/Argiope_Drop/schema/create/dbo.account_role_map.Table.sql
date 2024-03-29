USE [Argiope]
GO
/****** Object:  Table [dbo].[account_role_map]    Script Date: 7/2/2017 1:10:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[account_role_map]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[account_role_map](
	[account_id] [bigint] NOT NULL,
	[role_id] [int] NOT NULL,
 CONSTRAINT [PK_USER_ROLE_1] PRIMARY KEY CLUSTERED 
(
	[account_id] ASC,
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ROLE_ID]') AND parent_object_id = OBJECT_ID(N'[dbo].[account_role_map]'))
ALTER TABLE [dbo].[account_role_map]  WITH CHECK ADD  CONSTRAINT [FK_ROLE_ID] FOREIGN KEY([role_id])
REFERENCES [static].[role] ([role_id])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_ROLE_ID]') AND parent_object_id = OBJECT_ID(N'[dbo].[account_role_map]'))
ALTER TABLE [dbo].[account_role_map] CHECK CONSTRAINT [FK_ROLE_ID]
GO
IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_USER_ID]') AND parent_object_id = OBJECT_ID(N'[dbo].[account_role_map]'))
ALTER TABLE [dbo].[account_role_map]  WITH CHECK ADD  CONSTRAINT [FK_USER_ID] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([account_id])
GO
IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_USER_ID]') AND parent_object_id = OBJECT_ID(N'[dbo].[account_role_map]'))
ALTER TABLE [dbo].[account_role_map] CHECK CONSTRAINT [FK_USER_ID]
GO
