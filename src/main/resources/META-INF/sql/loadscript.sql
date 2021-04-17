-- drop sequence my_poid_sequence;

-- Data for simple person use
insert into Person(id, version, firstname, lastname, ptype) values(-101, 1, 'Christopher', 'Robin', 'P');
insert into Person(id, version, firstname, lastname, ptype) values(-102, 1, 'Ian', 'Darwin', 'P');

-- Data for Actors
insert into Person(id, version, firstName, lastName, ptype) values(-103, 1, 'Harrison', 'Ford', 'A')
insert into Actor(id) values (-103)
insert into Person(id, version, firstName, lastName, ptype) values(-104, 1, 'Mark', 'Hamill', 'A')
insert into Actor(id) values (-104)
insert into Person(id, version, firstName, lastName, ptype) values(-105, 1, 'Carrie', 'Fisher', 'A')
insert into Actor(id) values (-105)
insert into Person(id, version, firstName, lastName, ptype) values(-106, 1, 'Clark', 'Gable', 'A')
insert into Actor(id) values (-106)
insert into Person(id, version, firstName, lastName, ptype) values(-107, 1, 'Vivien', 'Leigh', 'A')
insert into Actor(id) values (-107)

-- Data for Videos
insert into Recording (id, version, title, price) values(-200, 1, 'Gone With The Wind', 0)
insert into VideoRecording (id, year) values (-200, 1939)
insert into Recording (id, version, title, price) values(-201, 1, 'Star Wars IV: A New Hope', 0)
insert into VideoRecording (id, year) values (-201, 1977)
insert into Recording (id, version, title, price) values(-202, 1, 'Indiana Jones: Raiders of the Lost Ark', 0)
insert into VideoRecording (id, year) values (-202, 1981)
insert into Recording (id, version, title, price) values(-203, 1, 'Patriot Games', 0)
insert into VideoRecording (id, year) values (-203, 1992)

-- Video<-->Actors join table
insert into Actor_VideoRecording(actor_id, video_id) values (-103, -201)
insert into Actor_VideoRecording(actor_id, video_id) values (-103, -202)
insert into Actor_VideoRecording(actor_id, video_id) values (-103, -203)
insert into Actor_VideoRecording(actor_id, video_id) values (-104, -201)
insert into Actor_VideoRecording(actor_id, video_id) values (-105, -201)
insert into Actor_VideoRecording(actor_id, video_id) values (-106, -200)
insert into Actor_VideoRecording(actor_id, video_id) values (-107, -200)

-- Gotta have a MusicRecording
insert into Recording (id, version, title, price) values(-301, 1, 'Greatest Hits', 9.67)
insert into MusicRecordings (id, artist_name) values (-301, 'The Java Junquies')

insert into Duration (id, totalseconds) values(-1234, 225);
insert into Tracks (id, title, duration_id, product_id, index_number) values(-1, 'All Hits Medly', -1234, -301, 0)

-- Composite Primary Key demo
-- insert into fish(speciesId, individualId, fishName) values(-255, 001, 'Fred')

-- Sales Reports

-- Sales Reps
insert into Person(id, version, firstName, lastName, ptype) values(-200, 1, 'Bill', 'Gates', 'S')
insert into SalesPerson(id) values (-200)
insert into Person(id, version, firstName, lastName, ptype) values(-201, 1, 'Steve', 'Jobs', 'S')
insert into SalesPerson(id) values (-201)

-- Customers
insert into Person(id, version, firstName, lastName, ptype) values(-150, 1, 'A', 'Byer', 'S')
insert into Customer(id, salesrep_id) values (-150, -200)
insert into Person(id, version, firstName, lastName, ptype) values(-151, 1, 'Top', 'Shopper', 'S')
insert into Customer(id, salesrep_id) values (-151, -201)

-- Sales

insert into sales(id, customer_id, salesrep_id, salesdate, amount) values(-1, -150, -200, '2012-12-12', 123)
insert into sales(id, customer_id, salesrep_id, salesdate, amount) values(-2, -151, -201, '2011-11-11', 334)

