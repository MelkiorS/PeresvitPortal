USE peresvitDB;
INSERT INTO  resourceType(typeName) VALUES ('TEXT'),('PHOTO'),('VIDEO'),('OTHER');
INSERT INTO  resourceGroupType(groupName, caption) VALUES ('BASE_TECHNIQUE', 'Базовая техника'),('BASE_TECH_COMPLEX', 'Базовая техника комплекс'),('SPECIAL_PHYSICAL_TRAININGS', 'Специальные упражнения'),('GENERAL_PHYSICAL_TRAININGS', 'Базовые упражнения'),('ANOTHER_SUBJECTS', 'Прочее'),('COMPETITION', 'Соревнования');
INSERT INTO  rang(rangName) VALUES ('LEVEL_1'),('LEVEL_2'),('LEVEL_3'),('USER'), ('ADMIN');
INSERT INTO  user(firstName, lastName, email, password, avatarURL , rangId) VALUES ('test', 'test', 'admin', '123456','https://cdn3.iconfinder.com/data/icons/users-6/100/654853-user-men-2-512.png', 5);
INSERT INTO  user(firstName, lastName, email, password, avatarURL , rangId) VALUES ('test', 'test', 'user', '123456','http://s3.amazonaws.com/37assets/svn/765-default-avatar.png', 4);
INSERT INTO  user(firstName, lastName, email, password, avatarURL , rangId) VALUES ('test', 'test', 'user1', '123456','http://s3.amazonaws.com/37assets/svn/765-default-avatar.png', 4);
INSERT INTO  userInfo(userId, infoName, infoValue)  VALUES (2, 'CITY', 'Киев');
INSERT INTO  userInfo(userId, infoName, infoValue)  VALUES (2, 'CLUB', 'Мава');
INSERT INTO  userInfo(userId, infoName, infoValue)  VALUES (2, 'MENTOR', 'Сергей Петрович');
INSERT INTO  userInfo(userId, infoName, infoValue)  VALUES (2, 'ABOUT', 'Удары наношу резко и сильно, мастер на обе ноги');
INSERT INTO  resourceGroup(rangId,resourceGroupTypeId) VALUES (2,2);
INSERT INTO  resource(url,title,resourceGroupId,resourceTypeId) VALUES ('some_url','some_photo1',1,2);
INSERT INTO  resource(url,title,resourceGroupId,resourceTypeId) VALUES ('some_url','some_photo2',1,2);
INSERT INTO  resource(url,title,resourceGroupId,resourceTypeId) VALUES ('some_url','some_doc1',1,1);
INSERT INTO  resource(url,title,resourceGroupId,resourceTypeId) VALUES ('some_url','some_doc2',1,1);
INSERT INTO  resource(url,title,resourceGroupId,resourceTypeId) VALUES ('some_url','some_video',1,3);
INSERT INTO  resourceGroupTypeChaptrer(resourceGroupTypeId,chapterName) VALUES (1,'CHAPTER1');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (1,'CHAPTER1');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (1,'CHAPTER1');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (2,'CHAPTER2');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (2,'CHAPTER2');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (3,'CHAPTER3');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (3,'CHAPTER3');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (4,'CHAPTER4');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (4,'CHAPTER4');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (5,'CHAPTER5');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (5,'CHAPTER5');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (5,'CHAPTER6');
INSERT INTO  resourceGroupTypeChapter(resourceGroupTypeId,chapterName) VALUES (5,'CHAPTER6');
# INSERT INTO  resource(title) VALUES ('avatar');
INSERT INTO  combatArt(combatArtName) VALUES ('Фрі- Файт'),('Тайдзі- Цюань'),('Шаолінь Кунг-фу');
INSERT INTO  city(cityName) VALUES ('Київ'),('Львів'),('Дніпро');
INSERT INTO  club(clubName) VALUES ('Клуб1'),('Клуб2');

INSERT into article(articleName, chapterId, rangId, resourceGroupTypeId, context) VALUES ('Статья 1.1', 1, 1, 1, "Содержимое статьи 1.1");
INSERT into article(articleName, chapterId, rangId, resourceGroupTypeId, context) VALUES ('Статья 1.2', 1, 1, 1, "Содержимое статьи 1.2");
INSERT into article(articleName, chapterId, rangId, resourceGroupTypeId, context) VALUES ('Статья 2.1', 2, 1, 1, "Содержимое статьи 2.1");