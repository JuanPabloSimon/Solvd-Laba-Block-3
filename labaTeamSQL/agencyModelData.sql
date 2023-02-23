use flightAgency;

insert into Airport (airportName, city, country, longitude, latitude) values ("John F. Kennedy International Airport", "New York City", "USA", -73.789288, 40.642098), ("London City Airport", "London", "England", 0.458780074157118 ,51.46773895), ("Madrid-Barajas Airport", "Madrid", "Spain", -3.57408062068113, 40.4948384), ("Ezeiza International Airport", "Buenos Aires", "Argentina", -58.5474638280231, -34.816848), ("Tom Jobim International Airport", "Rio de Janeiro", "Brazil", -43.2574758760471, -22.81130575), ("Haneda Airport", "Tokio", "Japan", 139.780587131238, 35.54572095), ("Paris Charles de Gaulle Airport", "Paris", "France", 2.57076947408831, 49.00665965), ("Hong Kong International Airport", "Hong Kong", "China", 113.936478399961, 22.3160874), ("Canberra Airport", "Canberra", "Australia", 149.1936903, -35.304391	), ("Cairo International Airport", "Cairo Governorate", "Egypt", 31.3503879973018, 30.1148264);

insert into Airlines (airlineName) values ("Qatar Airwayas"), ("Emirates"), ("Aerolineas Argeninas"), ("Etihad Airways"), ("Air France"), ("China Southern"), ("British Airways"), ("Japan Airlines");

insert into AirlineInAirport (Airlines_idAirlines, Airport_idAirport) values (1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (1, 2), (2, 2), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (1, 3), (2, 3), (3, 3), (4, 3), (5, 3), (6, 3), (7, 3), (8, 3), (1, 4), (2, 4), (3, 4), (4, 4), (5, 4), (6, 4), (7, 4), (8, 4), (1, 5), (2, 5), (3, 5), (4, 5), (5, 5), (6, 5), (7, 5), (8, 5), (1, 6), (2, 6), (3, 6), (4, 6), (5, 6), (6, 6), (7, 6), (8, 6), (1, 7), (2, 7), (3, 7), (4, 7), (5, 7), (6, 7), (7, 7), (8, 7), (1, 8), (2, 8), (3, 8), (4, 8), (5, 8), (6, 8), (7, 8), (8, 8), (1, 9), (2, 9), (3, 9), (4, 9), (5, 9), (6, 9), (7, 9), (8, 9), (1, 10), (2, 10), (3, 10), (4, 10), (5, 10), (6, 10), (7, 10), (8, 10);


-- insert Flight of Airline ID 1
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price ) values (1, 1, 2, 430), (1, 2, 6, 950), (1, 3, 7, 110), (1, 3, 8, 1300), (1, 5, 7, 890), (1, 6, 8, 500), (1, 7, 1, 425), (1, 7, 3, 90), (1, 7, 6, 1250), (1, 8, 10, 1050), (1, 10, 2, 515);

-- insert Flight of Airline ID 2
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price) values (2, 1, 4, 1000), (2, 1, 5, 1500), (2, 3, 1, 460), (2, 3, 5, 910), (2, 4, 1, 1800), (2, 4, 3, 1930), (2, 5, 3, 950), (2, 5, 7, 900), (2, 8, 3, 1200), (2, 8, 6, 300), (2, 9, 6, 1200), (2, 10, 2, 524);

-- insert Flight of Airline ID 3
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price) values (3, 1, 4, 1090), (3, 1, 3, 460), (3, 3, 4, 1300), (3, 4, 1, 1860), (3, 4, 5, 700), (3, 4, 3, 2030), (3, 5, 4, 400);

-- insert Flight of Airline ID 4
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price) values (4, 1, 2, 460), (4, 2, 1, 400), (4, 2, 7, 150), (4, 2, 10, 350), (4, 2, 8, 800), (4, 5, 3, 989), (4, 7, 2, 70), (4, 8, 10, 1010), (4, 9, 8, 1130), (4, 9, 10, 1620), (4, 10, 8, 890), (4, 10, 9, 1800);

-- insert Flight of Airline ID 5
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price) values (5, 1, 7, 410), (5, 2, 7, 110), (5, 5, 7, 870), (5, 7, 1, 410), (5, 7, 2, 70), (5, 7, 3, 80), (5, 7, 5, 800), (5, 3, 7, 100);

-- insert Flight of Airline ID 6
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price) values (6, 2, 6, 920), (6, 3, 7, 130), (6, 6, 2, 1760), (6, 6, 8, 470), (6, 6, 9, 1400), (6, 6, 7, 1700), (6, 7, 6, 1300), (6, 8, 6, 280), (6, 8, 9, 1000), (6, 9, 6, 1200);

-- insert Flight of Airline ID 7
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price) values (7, 1, 2, 450), (7, 2, 1, 389), (7, 2, 7, 125), (7, 2, 6, 960), (7, 2, 10, 330), (7, 2, 8, 790), (7, 6, 2, 1500), (7, 7, 2, 65), (7, 8, 2, 860), (7, 9, 8, 1200), (7, 10, 2, 500);

-- insert Flight of Airline ID 8
insert into Flight (Airlines_idAirlines, idDepartureAirport, idArrivalAirport, price) values (8, 2, 8, 750), (8, 3, 8, 1240), (8, 6, 8, 485), (8, 6, 9, 1500), (8, 8, 3, 1110), (8, 8, 6, 285), (8, 8, 9, 900), (8, 8, 10, 1000), (8, 8, 2, 840), (8, 9, 8, 1100);