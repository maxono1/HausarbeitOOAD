create table Spiel(
    SpielID int primary key,
    Name varchar(255),
    Beschreibung varchar(2048),
    Preis decimal(5,2),
    Genre varchar(128),
    BewertungProzent int,
    Logo blob,
    Titelbild blob,
    check (preis>=0 AND preis <1000),
    check (BewertungProzent>=0 AND BewertungProzent<=100)
)

create table Nutzer(
    BName varchar(20) primary key,
    password varchar(20),
    guthaben decimal(10,2),
    check (guthaben>=0)
)

create table Rezension(
    SpielID int,
    BName varchar(20),
    UserBewertungProzent int,
    Text varchar(3999),
    foreign key (BName) references Nutzer(BName),
    foreign key (SpielID) references Spiel(SpielID),
    primary key(SpielID,BName),
    check (UserBewertungProzent>=0 AND UserBewertungProzent<=100)
)

create table Nutzer_Besitzt(
    SpielID int,
    BName varchar(20),
    foreign key (BName) references Nutzer(BName),
    foreign key (SpielID) references Spiel(SpielID),
    primary key(SpielID,BName)
)
