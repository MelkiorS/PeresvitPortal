USE peresvitDB;
INSERT INTO  resourceType(typeName) VALUES ('TEXT'),('PHOTO'),('VIDEO');
INSERT INTO  resourceGroupType(groupName) VALUES ('EVENT'),('BASE_TECHNIQUE'),('BASE_TECH_COMPLEX'),('SPECIAL_PHYSICAL_TRAININGS'),('GENERAL_PHYSICAL_TRAININGS'),('ANOTHER_SUBJECTS'),('COMPETITION');
INSERT INTO  rang(rangName) VALUES ('LEVEL_1'),('LEVEL_2'),('LEVEL_3');
INSERT INTO  resourceGroup(rangId,resourceGroupTypeId) VALUES (2,2);
INSERT INTO  user(userId,fname,rangId) VALUES (1,'test',2);
INSERT INTO  resource(title,userId,resourceTypeId) VALUES ('avatar',1,1);
INSERT INTO  resource(title,resourceGroupId,resourceTypeId) VALUES ('avatar',1,1);
--INSERT INTO  resource(title) VALUES ('avatar');