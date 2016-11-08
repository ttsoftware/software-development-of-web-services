DROP TABLE IF EXISTS FlightInformation;
DROP TABLE IF EXISTS Flight;

CREATE TABLE Flight (
  id                SERIAL PRIMARY KEY,
  startAirport      VARCHAR(20) UNIQUE NOT NULL,
  destinationAripor VARCHAR(20)        NOT NULL,
  carrier           VARCHAR(20)        NOT NULL,
  start             DATE               NOT NULL,
  end               DATE               NOT NULL
);

CREATE TABLE FlightInformation (
  id            SERIAL PRIMARY KEY,
  bookingNumber VARCHAR(20) UNIQUE NOT NULL,
  airlineName   VARCHAR(20)        NOT NULL,
  price         DOUBLE,
  FOREIGN KEY (FK_flight) REFERENCES Flight (id)
);