INSERT INTO role(id,permissions,role_name) VALUES (1,'all','Projekt Manager');
INSERT INTO role(id,permissions,role_name) VALUES (2,'all','Produckt Owner');
INSERT INTO role(id,permissions,role_name) VALUES (3,'all','Developer');
INSERT INTO role(id,permissions,role_name) VALUES (4,'all','Consultant');

INSERT INTO "user"(id,role_user) VALUES (1,1);
INSERT INTO "user"(id,role_user) VALUES (2,2);
INSERT INTO "user"(id,role_user) VALUES (3,3);
INSERT INTO "user"(id,role_user) VALUES (4,4);

INSERT INTO  personaldata(user_id, birthday, name) VALUES (1,'1980-07-01' ,'Herbert');
INSERT INTO  personaldata(user_id, birthday, name) VALUES (2,'1981-07-01' ,'Hubert');
INSERT INTO  personaldata(user_id, birthday, name) VALUES (3,'1982-07-01' ,'Hellen');
INSERT INTO  personaldata(user_id, birthday, name) VALUES (4,'1983-07-01' ,'Walter');

INSERT INTO produckt_backlog(id, name) Values (1, 'Test');

INSERT INTO sprint(id, start, "end", id_user) VALUES (1, '2020-07-01','2020-08-01',1);

INSERT INTO sprint_backlog(sprint_id) VALUES (1);

INSERT INTO story(id, description, done, storypoints, closing_date, creation_date, id_produktbacklog, story_assignee_user, story_sprintbacklog, storycreator_user) VALUES (1, 'TEST1', false, 0,'2020-08-01',null,1,3,1,1); 


