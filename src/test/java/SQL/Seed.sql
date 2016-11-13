DROP TABLE IF EXISTS FlightReservation;
DROP TABLE IF EXISTS Flight;

CREATE TABLE Flight (id SERIAL PRIMARY KEY,
                    startAirport VARCHAR(20) UNIQUE NOT NULL,
                    destinationAripor VARCHAR(20) NOT NULL,
                    carrier VARCHAR(20) NOT NULL,
                    start DATE NOT NULL,
                    end DATE NOT NULL);

CREATE TABLE FlightInformation (id SERIAL PRIMARY KEY,
                    bookingNumber VARCHAR(20) UNIQUE NOT NULL,
                    airlineName VARCHAR(20) NOT NULL,
                    price DOUBLE,
                    FOREIGN KEY(FK_flight) REFERENCES Flight(id));




INSERT INTO Flight (id, startAirport, destinationAripor, carrier, start, end)
    VALUES (1, "Copenhagen", "Berlin", "Air Berlin", datetime("2016-11-07"), datetime("2016-11-07"));

INSERT INTO Flight (id, startAirport, destinationAripor, carrier, start, end)
  VALUES (2, "Berlin", "Copenhagen", "Air Berlin", datetime("2016-11-12"), datetime("2016-11-12"));

INSERT INTO FlightReservation(bookingNumber, airlineName, price, FK_flight)
    VALUES ("1234", "LameDuck", 100, 1);

INSERT INTO FlightReservation(bookingNumber, airlineName, price, FK_flight)
  VALUES ("1235", "LameDuck", 100, 2);