USE [Argiope]
GO
DELETE FROM [static].[role]
GO
INSERT [static].[role] ([role_id], [role_name], [description]) VALUES (1, N'ROLE_USER', NULL)
INSERT [static].[role] ([role_id], [role_name], [description]) VALUES (2, N'ROLE_ADMIN', NULL)
