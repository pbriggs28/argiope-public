USE [Argiope]
GO
DELETE FROM [dbo].[account]
GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([account_id], [username], [password], [first_name], [last_name], [enabled], [not_expired], [not_locked], [credentials_not_expired]) VALUES (25, N'sa', N'$2a$10$bCyUqxZyz3dB5XXZYFjAoO3fJD.EJF0rKW2p.vmp1fxdlcS0jKR1e', N'PasswordIs', N'sa', 1, 1, 1, 1)
INSERT [dbo].[account] ([account_id], [username], [password], [first_name], [last_name], [enabled], [not_expired], [not_locked], [credentials_not_expired]) VALUES (26, N'AutomationTesting01', N'$2a$10$mv93l9Qv5WmvMs6WTzRFqOmy6hmURiKzx1JewlxRY8UBRXIJbCOvi', N'Automation', N'Testing', 1, 1, 1, 1)
SET IDENTITY_INSERT [dbo].[account] OFF
