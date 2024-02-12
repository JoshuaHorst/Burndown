INSERT INTO role(id,permissions,role_name) VALUES (1,'all','Projekt Manager');
INSERT INTO role(id,permissions,role_name) VALUES (2,'all','Produckt Owner');
INSERT INTO role(id,permissions,role_name) VALUES (3,'all','Developer');
INSERT INTO role(id,permissions,role_name) VALUES (4,'all','Consultant');

INSERT INTO "user"(id,role_user) VALUES (1001,1);
INSERT INTO "user"(id,role_user) VALUES (1002,2);
INSERT INTO "user"(id,role_user) VALUES (1003,3);
INSERT INTO "user"(id,role_user) VALUES (1004,4);

INSERT INTO  personaldata(user_id, birthday, name) VALUES (1001,'1980-07-01' ,'Herbert');
INSERT INTO  personaldata(user_id, birthday, name) VALUES (1002,'1981-07-01' ,'Hubert');
INSERT INTO  personaldata(user_id, birthday, name) VALUES (1003,'1982-07-01' ,'Hellen');
INSERT INTO  personaldata(user_id, birthday, name) VALUES (1004,'1983-07-01' ,'Walter');

INSERT INTO produckt_backlog(id, name) Values (1, 'Test');

INSERT INTO sprint(id, start, "end", id_user) VALUES (1, '2020-07-01','2020-09-01',1001);

INSERT INTO sprint_backlog(sprint_id) VALUES (1);

INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (100, 'TEST1', false, 12,'2020-07-01','2020-08-01',1,1003,1,1001); 
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (101, 'TEST1', false, 15,'2020-07-01','2020-08-01',1,1003,1,1001); 
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (102, 'TEST1', false, 17,'2020-07-01','2020-07-12',1,1003,1,1001);
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (103, 'TEST1', false, 19,'2020-07-01','2020-07-13',1,1003,1,1001);
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (104, 'TEST1', false, 23,'2020-07-01','2020-07-14',1,1003,1,1001);
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (105, 'TEST1', false, 27,'2020-07-01','2020-07-15',1,1003,1,1001);
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (106, 'TEST1', false, 26,'2020-07-01','2020-07-16',1,1003,1,1001);
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (107, 'TEST1', false, 25,'2020-07-01','2020-07-17',1,1003,1,1001);
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (108, 'TEST1', false, 16,'2020-07-01','2020-07-18',1,1003,1,1001);
INSERT INTO story(id, description, done, storypoints, creation_date, closing_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (109, 'TEST1', false, 24,'2020-07-01','2020-07-19',1,1003,1,1001);