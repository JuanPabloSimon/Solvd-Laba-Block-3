
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `flightAgency` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `flightAgency` DEFAULT CHARACTER SET utf8 ;
USE `flightAgency` ;

-- -----------------------------------------------------
-- Table `flightAgency`.`Airport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flightAgency`.`Airport` (
  `idAirport` INT NOT NULL AUTO_INCREMENT,
  `airportName` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `coordinates` POINT NULL,
  PRIMARY KEY (`idAirport`),
  UNIQUE INDEX `idAirport_UNIQUE` (`idAirport` ASC));


-- -----------------------------------------------------
-- Table `flightAgency`.`Airlines`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flightAgency`.`Airlines` (
  `idAirlines` INT NOT NULL AUTO_INCREMENT,
  `airlineName` VARCHAR(45) NULL,
  PRIMARY KEY (`idAirlines`),
  UNIQUE INDEX `idAirlines_UNIQUE` (`idAirlines` ASC));


-- -----------------------------------------------------
-- Table `flightAgency`.`Flight`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flightAgency`.`Flight` (
  `idFlight` INT NOT NULL AUTO_INCREMENT,
  `departureId` INT NULL,
  `destinationId` INT NULL,
  `price` FLOAT NULL,
  `Airlines_idAirlines` INT NOT NULL,
  PRIMARY KEY (`idFlight`),
  UNIQUE INDEX `idFlight_UNIQUE` (`idFlight` ASC),
  INDEX `fk_Flight_Airlines1_idx` (`Airlines_idAirlines` ASC),
  CONSTRAINT `fk_Flight_Airlines1`
    FOREIGN KEY (`Airlines_idAirlines`)
    REFERENCES `flightAgency`.`Airlines` (`idAirlines`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `flightAgency`.`AirlineInAirport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flightAgency`.`AirlineInAirport` (
  `Airlines_idAirlines` INT NOT NULL,
  `Airport_idAirport` INT NOT NULL,
  PRIMARY KEY (`Airlines_idAirlines`, `Airport_idAirport`),
  INDEX `fk_Airlines_has_Airport_Airport1_idx` (`Airport_idAirport` ASC) ,
  INDEX `fk_Airlines_has_Airport_Airlines1_idx` (`Airlines_idAirlines` ASC) ,
  CONSTRAINT `fk_Airlines_has_Airport_Airlines1`
    FOREIGN KEY (`Airlines_idAirlines`)
    REFERENCES `flightAgency`.`Airlines` (`idAirlines`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Airlines_has_Airport_Airport1`
    FOREIGN KEY (`Airport_idAirport`)
    REFERENCES `flightAgency`.`Airport` (`idAirport`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

