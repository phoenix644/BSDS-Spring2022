
drop table if exists LiftRide;
create table LiftRide (
    liftRideID int AUTO_INCREMENT primary key,
    skierID int,
    resortID int,
    seasonID varchar(30),
    dayID varchar(30),
	waitTime int,
    liftID int
   
    );
    