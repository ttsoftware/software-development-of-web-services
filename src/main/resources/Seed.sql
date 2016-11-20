DROP TABLE IF EXISTS FlightReservation;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS HotelReservation;
DROP TABLE IF EXISTS CreditCardInfoType;
DROP TABLE IF EXISTS Booking;
DROP TABLE IF EXISTS Itinerary;

CREATE TABLE Flight (
  id                 INTEGER PRIMARY KEY AUTOINCREMENT,
  startAirport       VARCHAR(20) UNIQUE NOT NULL,
  destinationAirport VARCHAR(20)        NOT NULL,
  carrier            VARCHAR(20)        NOT NULL,
  start              DATETIME           NOT NULL,
  end                DATETIME           NOT NULL
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
  price               BIGINT              DEFAULT NULL,
  opens               BIGINT              DEFAULT NULL,
  closes              BIGINT              DEFAULT NULL
);

CREATE TABLE CreditCardInfoType (
  id              INTEGER PRIMARY KEY AUTOINCREMENT,
  name            VARCHAR(20) NOT NULL,
  number          VARCHAR(50) UNIQUE NOT NULL,
  expirationMonth INT         NOT NULL,
  expirationYear  INT         NOT NULL
);

CREATE TABLE HotelReservation (
  id                    INTEGER PRIMARY KEY AUTOINCREMENT,
  bookingNumber         VARCHAR(20) NOT NULL,
  fk_creditCardInfoType INT                 DEFAULT NULL,
  fk_hotel              INT                 DEFAULT NULL,
  FOREIGN KEY (fk_creditCardInfoType) REFERENCES CreditCardInfoType (id),
  FOREIGN KEY (fk_hotel) REFERENCES Hotel (id)
);

CREATE TABLE Booking (
  id            INTEGER PRIMARY KEY AUTOINCREMENT,
  price         BIGINT      NOT NULL,
  date          BIGINT      NOT NULL,
  bookingNumber VARCHAR(20) NOT NULL,
  bookingStatus INT         NOT NULL,
  bookingType   INT         NOT NULL,
  fk_itinerary  INT                 DEFAULT NULL,
  FOREIGN KEY (fk_itinerary) REFERENCES Itinerary (id),
  UNIQUE (bookingNumber, bookingType)
);

CREATE TABLE Itinerary (
  id INTEGER PRIMARY KEY AUTOINCREMENT
);

INSERT INTO Itinerary (id) VALUES (1);
INSERT INTO Itinerary (id) VALUES (2);

INSERT INTO Booking (id, price, date, bookingNumber, bookingStatus, bookingType, fk_itinerary)
VALUES (1, 100, datetime("2016-11-07"), "1234", 1, 1, 1);

INSERT INTO CreditCardInfoType (name, number, expirationMonth, expirationYear) VALUES ("Tobiasen Inge", "50408823", 9, 10);
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Danglen", "copenhagen", "penis", 50.00, 1482706800000, 1483138800000);

INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (1, "Copenhagen", "Berlin", "Air Berlin", datetime("2016-11-07"), datetime("2016-11-07"));

INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (2, "Berlin", "Copenhagen", "Air Berlin", datetime("2016-11-12"), datetime("2016-11-12"));

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("1234", "LameDuck", 100, 1);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("1235", "LameDuck", 100, 2);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("2234", "LameDuck", 100, 1);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("3234", "LameDuck", 100, 1);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("4234", "LameDuck", 100, 1);