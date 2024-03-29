USE [Argiope]
GO
/****** Object:  Table [dbo].[account]    Script Date: 7/2/2017 1:10:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[account]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[account](
	[account_id] [bigint] IDENTITY(1,1) NOT NULL,
	[username] [varchar](30) NOT NULL,
	[password] [varchar](60) NOT NULL,
	[first_name] [varchar](30) NULL,
	[last_name] [varchar](30) NULL,
	[enabled] [bit] NOT NULL CONSTRAINT [DF_USER_ENABLED]  DEFAULT ((1)),
	[not_expired] [bit] NOT NULL CONSTRAINT [DF_account_expired]  DEFAULT ((1)),
	[not_locked] [bit] NOT NULL CONSTRAINT [DF_account_locked]  DEFAULT ((1)),
	[credentials_not_expired] [bit] NOT NULL CONSTRAINT [DF_account_credentialsExpired]  DEFAULT ((1)),
 CONSTRAINT [PK_USER] PRIMARY KEY CLUSTERED 
(
	[account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
SET ANSI_PADDING OFF
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [IX_USER]    Script Date: 7/2/2017 1:10:42 PM ******/
IF NOT EXISTS (SELECT * FROM sys.indexes WHERE object_id = OBJECT_ID(N'[dbo].[account]') AND name = N'IX_USER')
CREATE UNIQUE NONCLUSTERED INDEX [IX_USER] ON [dbo].[account]
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
