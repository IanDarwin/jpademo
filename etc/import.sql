-- Data for simple person use
insert into Person(id, firstname, lastname) values(-101, 'Christopher', 'Robin');
insert into Person(id, firstname, lastname) values(-102, 'Ian', 'Darwin');

-- Data for Actors
insert into Person (firstName, lastName, id) values('Harrison', 'Ford', -103)
insert into Actor (id) values (-103)
insert into Person (firstName, lastName, id) values('Mark', 'Hamill', -104)
insert into Actor (id) values (-104)
insert into Person (firstName, lastName, id) values('Carrie', 'Fisher', -105)
insert into Actor (id) values (-105)
insert into Person (id, firstName, lastName) values(-106, 'Ian', 'Darwin')
insert into Actor (id) values (-106)
-- Data for Videos
insert into Recording (id, title, price) values(-200, 'Gone With The Wind', 0)
insert into VideoRecording (id) values (-200)
insert into Recording (id, title, price) values(-201, 'Star Wars IV: A New Hope', 0)
insert into VideoRecording (id) values (-201)
insert into Recording (id, title, price) values(-202, 'Indiana Jones: Raiders of the Lost Ark', 0)
insert into VideoRecording (id) values (-202)
-- join table
insert into Actor_VideoRecording (actors_id, videos_id) values (-103, -201)
insert into Actor_VideoRecording (actors_id, videos_id) values (-104, -201)
insert into Actor_VideoRecording (actors_id, videos_id) values (-105, -201)
insert into Actor_VideoRecording (actors_id, videos_id) values (-103, -202)
