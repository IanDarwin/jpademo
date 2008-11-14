-- Data for simple person use
insert into Person(id, firstname, lastname) values(-101, 'Christopher', 'Robin');
insert into Person(id, firstname, lastname) values(-102, 'Ian', 'Darwin');

-- Data for Actors/Videos 
insert into Person (firstName, lastName, id) values('Harrison', 'Ford', -103)
insert into Actor (id) values (-103)
insert into Person (firstName, lastName, id) values('Mark', 'Hamill', -104)
insert into Actor (id) values (-104)
insert into Person (firstName, lastName, id) values('Carrie', 'Fisher', -105)
insert into Actor (id) values (-105)
insert into Recording (id, title, price) values(-106, 'Star Wars IV: A New Hope', 0)
insert into VideoRecording (id) values (-106)
insert into Recording (id, title, price) values(-107, 'Raiders of the Lost Ark', 0)
insert into VideoRecording (id) values (-107)
-- join table
insert into Actor_VideoRecording (actors_id, videos_id) values (-103, -106)
insert into Actor_VideoRecording (actors_id, videos_id) values (-104, -106)
insert into Actor_VideoRecording (actors_id, videos_id) values (-105, -106)
insert into Actor_VideoRecording (actors_id, videos_id) values (-103, -107)
