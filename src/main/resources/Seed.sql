DROP TABLE IF EXISTS FlightReservation;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS HotelReservation;
DROP TABLE IF EXISTS CreditCardInfoType;
DROP TABLE IF EXISTS Booking;
DROP TABLE IF EXISTS Itinerary;

CREATE TABLE Flight (
  id                 INTEGER PRIMARY KEY AUTOINCREMENT,
  startAirport       VARCHAR(20) NOT NULL,
  destinationAirport VARCHAR(20) NOT NULL,
  carrier            VARCHAR(20) NOT NULL,
  start              BIGINT      NOT NULL,
  end                BIGINT      NOT NULL
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
  creditCardGuarantee TINYINT             DEFAULT 0,
  price               BIGINT              DEFAULT NULL,
  opens               BIGINT              DEFAULT NULL,
  closes              BIGINT              DEFAULT NULL
);

CREATE TABLE CreditCardInfoType (
  id              INTEGER PRIMARY KEY AUTOINCREMENT,
  name            VARCHAR(20)        NOT NULL,
  number          VARCHAR(50) UNIQUE NOT NULL,
  expirationMonth INT                NOT NULL,
  expirationYear  INT                NOT NULL
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
  FOREIGN KEY (fk_itinerary) REFERENCES Itinerary (id)
);

CREATE TABLE Itinerary (
  id INTEGER PRIMARY KEY AUTOINCREMENT
);

INSERT INTO Itinerary (id) VALUES (1);

INSERT INTO CreditCardInfoType (name, number, expirationMonth, expirationYear)
VALUES ("Tobiasen Inge", "50408823", 9, 10);

--
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Slumpen", "Copenhagen", "2451", 50.00, 1482706800000, 1483138800000);

-- Flights 2016-11-7 1478473200000
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes, creditCardGuarantee)
VALUES ("D'Angleterre", "Copenhagen", "2454", 1000.00, 1478473200000, 1478473200000, 1);
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Hotel Awesome", "Copenhagen", "2457", 500.00, 1478473200000, 1478473200000);

-- Flights 2016-11-12 1478905200000
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Hotel Awesome German", "Berlin", "3451", 500.00, 1478905200000, 1478905200000);
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Würstel", "Berlin", "3452", 1000, 1478905200000, 1478905200000);
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Hotel Komm Hier", "Berlin", "3454", 200.00, 1478905200000, 1478905200000);


INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("The Queen Berlin", "Berlin", "3422", 200.00, 1478473200000, 1478905200000);
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Kappa", "Berlin", "3411", 200.00, 1478473200000, 1478905200000);

INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("The Queen", "England", "3457", 200.00, 1478905200000, 1479596400000);
INSERT INTO Hotel (name, city, bookingNumber, price, opens, closes)
VALUES ("Hotel Tea", "England", "3459", 200.00, 1478905200000, 1479596400000);

-- Flights 2016-11-7 1478473200000
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (1, "Copenhagen", "Berlin", "Air Berlin", 1478473200000, 1478905200000);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (2, "Copenhagen", "Berlin", "Air Berlin", 1478473200000, 1478905200000);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (3, "Copenhagen", "Berlin", "Air Wannabe Berlin", 1478473200000, 1478905200000);

-- Flights 2016-11-12 1478905200000
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (4, "Berlin", "Copenhagen", "Air Berlin", 1478905200000, 1478905200000);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (5, "Berlin", "Copenhagen", "Air Berlin", 1478905200000, 1478905200000);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (6, "Berlin", "London", "Air UK", 1478905200000, 1478905200000);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (7, "Berlin", "London", "SAS", 1478905200000, 1478905200000);

-- Flights 2016-11-20 1479596400000
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (8, "London", "Amsterdam", "SAS", 1479596400000, 1479596400000);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (9, "London", "Amsterdam", "ZULU", 1479596400000, 1479596400000);
INSERT INTO Flight (id, startAirport, destinationAirport, carrier, start, end)
VALUES (10, "Amsterdam", "Copenhagen", "SAS", 1479596400000, 1479596400000);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("1234", "LameDuck", 100, 1);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("1235", "LameDuck", 100, 2);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("2234", "LameDuck", 100, 3);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("3234", "LameDuck", 100, 4);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("4234", "LameDuck", 100, 5);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("4237", "LameDuck", 100, 6);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("4238", "LameDuck", 100, 7);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("4288", "LameDuck", 100, 8);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("4299", "LameDuck", 100, 9);

INSERT INTO FlightReservation (bookingNumber, airlineName, price, FK_flight)
VALUES ("4200", "LameDuck", 100, 10);