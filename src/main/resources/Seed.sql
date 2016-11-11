DROP TABLE IF EXISTS FlightReservation;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS HotelReservation;
DROP TABLE IF EXISTS CreditCardInfoType;

CREATE TABLE Flight (
  id                INTEGER PRIMARY KEY AUTOINCREMENT,
  startAirport      VARCHAR(20) UNIQUE NOT NULL,
  destinationAripor VARCHAR(20)        NOT NULL,
  carrier           VARCHAR(20)        NOT NULL,
  start             DATE               NOT NULL,
  end               DATE               NOT NULL
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