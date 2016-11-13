DROP TABLE IF EXISTS FlightReservation;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS HotelReservation;
DROP TABLE IF EXISTS CreditCardInfoType;

CREATE TABLE Flight (
  id                INTEGER PRIMARY KEY AUTOINCREMENT,
  startAirport      VARCHAR(20) UNIQUE NOT NULL,
  destinationAirport VARCHAR(20)        NOT NULL,
  carrier           VARCHAR(20)        NOT NULL,
  start             DATETIME               NOT NULL,
  end               DATETIME              NOT NULL
);

CREATE TABLE FlightReservation (
  id            INTEGER PRIMARY KEY AUTOINCREMENT,
  bookingNumber VARCHAR(20) UNIQUE NOT NULL,
  airlineName   VARCHAR(20)        NOT NULL,
  price         DOUBLE,
  fk_flight     INT                 DEFAULT NULL,
  FOREIGN KEY (fk_flight) REFERENCES Flight (id)
);

CREATE TABLE Hotel (
  id                  INTEGER PRIMARY KEY AUTOINCREMENT,
  name                VARCHAR(100) UNIQUE NOT NULL,
  city                VARCHAR(100)        DEFAULT NULL,
  bookingNumber       VARCHAR(20) UNIQUE  NOT NULL,
  creditCardGuarantee TINYINT             DEFAULT NULL,
  price               BIGINT              DEFAULT NULL
);

CREATE TABLE CreditCardInfoType (
  id     INTEGER PRIMARY KEY AUTOINCREMENT,
  name   VARCHAR(20) NOT NULL,
  number VARCHAR(50) NOT NULL,
  price  BIGINT              DEFAULT NULL
);

CREATE TABLE HotelReservation (
  id                    INTEGER PRIMARY KEY AUTOINCREMENT,
  bookingNumber         VARCHAR(20) UNIQUE  NOT NULL,
  fk_creditCardInfoType INT                 DEFAULT NULL,
  fk_hotel              INT                 DEFAULT NULL,
  FOREIGN KEY (fk_creditCardInfoType) REFERENCES CreditCardInfoType (id),
  FOREIGN KEY (fk_hotel) REFERENCES Hotel (id)
);

INSERT INTO Hotel (name, city, bookingNumber, price) VALUES ("Danglen", "copenhagen", "penis", 50.00);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (1, "Copenhagen", "Berlin", "Air Berlin", datetime("2016-11-07"), datetime("2016-11-07"));

INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (2, "Berlin", "Copenhagen", "Air Berlin", datetime("2016-11-12"), datetime("2016-11-12"));

INSERT INTO FlightReservation(bookingNumber, airlineName, price, FK_flight)
VALUES ("1234", "LameDuck", 100, 1);

INSERT INTO FlightReservation(bookingNumber, airlineName, price, FK_flight)
VALUES ("1235", "LameDuck", 100, 2);

INSERT INTO FlightReservation(bookingNumber, airlineName, price, FK_flight)
VALUES ("2234", "LameDuck", 100, 1);

INSERT INTO FlightReservation(bookingNumber, airlineName, price, FK_flight)
VALUES ("3234", "LameDuck", 100, 1);

INSERT INTO FlightReservation(bookingNumber, airlineName, price, FK_flight)
VALUES ("4234", "LameDuck", 100, 1);