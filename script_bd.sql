/*-----BASE DE DONNEES JAVA WEB-----*/
/*-----Script----*/

CREATE TABLE CurrentLanguage(
	CurrentLanguage_ID varchar(2) PRIMARY KEY,
	NameLanguage varchar(100) NOT NULL
);
CREATE TABLE Customer(
	Customer_ID integer auto_increment PRIMARY KEY,
	Email varchar(255) NOT NULL UNIQUE,
	Name varchar(255) NOT NULL,
	FirstName varchar(255) NOT NULL,
	PhoneNumber varchar(255),
	BirthDate date NOT NULL,
	Street varchar(255) NOT NULL,
	StreetNumber integer NOT NULL CHECK(StreetNumber > 0),
	PostalCode integer NOT NULL CHECK (PostalCode > 999),
	City varchar(255) NOT NULL,
	Country varchar(255) NOT NULL,
	Password varchar(255) NOT NULL
);

CREATE TABLE Author(
	Author_ID integer auto_increment PRIMARY KEY,
	Name varchar(255) NOT NULL,
	FirstName varchar(255) NOT NULL
);
CREATE TABLE PublishingHouse(
	PublishingHouse_ID integer auto_increment PRIMARY KEY,
	Wording varchar(255) NOT NULL
);
CREATE TABLE Category(
	Category_ID integer auto_increment PRIMARY KEY
);

CREATE TABLE Book(
	Isbn integer PRIMARY KEY,
	Price float NOT NULL CHECK (Price > 0),
	Summary varchar(255) NOT NULL,
	NumberOfPages integer NOT NULL CHECK(NumberOfPages > 0),
	PublicationDate date NOT NULL ,
	Height integer  CHECK(Height > 0),
	Width integer CHECK(Height > 0),
	Thickness integer  CHECK(Thickness > 0),
	Weight integer CHECK(Weight > 0), -- The weight is expressed in grams
	Stock integer CHECK(Stock > 0),
	TypeOfBook varchar(100) NOT NULL,
	FileSize integer CHECK(FileSize > 0), -- The file size is expressed in KB
	Extension varchar(255),
	Author_PB_ID integer,
	PublishingHouse_PB__ID integer,
	Category_PB_ID integer,
	constraint Author_PB_ID_FK FOREIGN KEY(Author_PB_ID) REFERENCES Author(Author_ID),
	constraint PublishingHouse_PB_ID_FK  FOREIGN KEY(PublishingHouse_PB__ID) REFERENCES PublishingHouse(PublishingHouse_ID),
	constraint Category_PB_ID_FK FOREIGN KEY(Category_PB_ID) REFERENCES Category(Category_ID)
);
CREATE TABLE Promotion(
	Promotion_ID integer auto_increment PRIMARY KEY,
	StartDate DATE NOT NULL, -- format YYYY-MM-DD
	EndDate DATE NOT NULL, -- format YYYY-MM-DD
	Percentage float NOT NULL CHECK (Percentage > 0),
	Summary varchar(255) NOT NULL,
	Book_ID integer NOT NULL,
	constraint Book_ID_P_FK foreign key(Book_ID) references Book(Isbn)

);
CREATE TABLE OrderCustomer(
	OrderCustomer_ID integer auto_increment PRIMARY KEY,
	OrderCustomerDate DATE NOT NULL, -- format YYYY-MM-DD
	Customer_ID integer NOT NULL,
	constraint Customer_ID_FK foreign key(Customer_ID) references Customer(Customer_ID)
);

CREATE TABLE CommandLine(
	CommandLine_ID integer auto_increment PRIMARY KEY,
	Quantity integer NOT NULL,
	Book_ID integer,
	OrderCustomer_ID integer,
	constraint Book_ID_CL_FK FOREIGN KEY(Book_ID) REFERENCES Book(Isbn),
	constraint OrderCustomer_ID_FK foreign key(OrderCustomer_ID) references OrderCustomer(OrderCustomer_ID)
);

CREATE TABLE LanguageTranslationTitleOfBook(
	LanguageTranslationTitleOfBook_ID integer auto_increment PRIMARY KEY,
	TranslationTitleOfBook varchar(255) NOT NULL,
	CurrentLanguage_ID varchar(2) NOT NULL,
	Book_ID integer NOT NULL,
	constraint CurrentLanguage_ID_FK foreign key(CurrentLanguage_ID) references CurrentLanguage(CurrentLanguage_ID),
	constraint Book_ID_LT_FK foreign key(Book_ID) references Book(Isbn)
	);

CREATE TABLE LanguageTranslationWordingOfCategory(
	LanguageTranslationWordingOfCategory_ID integer auto_increment PRIMARY KEY,
	TranslationWordingOfCategory varchar(255) NOT NULL,
	CurrentLanguage_ID varchar(2) NOT NULL,
	Category_ID integer NOT NULL,
	constraint CurrentLanguage_ID_WC_FK foreign key(CurrentLanguage_ID) references CurrentLanguage(CurrentLanguage_ID),
	constraint Category_ID_FK foreign key(Category_ID) references Category(Category_ID)
	);
/*-----Insertion----*/
INSERT INTO CurrentLanguage (currentLanguege_id,NameLanguage)
	VALUES
		("en","English"),
		("fr","Français");

		/*MDP CRYPTE DONC MIEUX VIA INSCRIPTION */
INSERT INTO Customer (Email, Name, FirstName, PhoneNumber, BirthDate,
					Street, StreetNumber, PostalCode, City, Country, Password)
	VALUES
		("clemhaut2006@gmail.com", "Hautier", "Clément", "0495190668", "19950620",
			"Avenue de Scailmont", 21, 7170, "Manage", "Belgique", "root"),
		("marineded@gmail.com", "de Dorlodot", "Marine", "0479322677", "19920612",
			"Ruelle Jean-Pierre", 10, 5020, "Temploux", "Belgique", "root");
		
INSERT INTO Author(Name, FirstName)
	VALUES
		-- Twilight
		("Meyer", "Stephenie"),
		-- Dix petits nègres
		("Christie", "Agatha"),
		-- La première nuit / Le premier jour
		("Levy", "Marc"),
		-- La fille du train
		("Hawkins", "Paula"),
		-- Hunger Games
		("Collins", "Suzanne"),
		-- Divergente
		("Roth", "Véronica");
		
INSERT INTO PublishingHouse(Wording)
	VALUES
		-- Twilight
		("Hachette "),
		-- Dix petits nègres
		("Harper"),
		-- La première nuit / Le premier jour
		("Robert Laffont"),
		--La fille du train 
		("Sonatine"),
		-- Hunger Games
		("Pocket Jeunesse"),
		-- Divergente
		("Nathan");
		
INSERT INTO Category(Category_ID)
	VALUES
		-- Fantastique 
		(1),
		-- Policier
		(2),
		-- Romance
		(3);	

INSERT INTO Book(Isbn, Price, Summary, NumberOfPages, PublicationDate, Height, Width, Thickness, Weight, Stock, TypeOfBook, FileSize, Extension, Author_PB_ID, PublishingHouse_PB_ID,Category_PB_ID)
	VALUES
		-- Twilight 1
		(0316015849, 18.00,
			"Isabella Swan, 17 ans, déménage à Forks, petite ville pluvieuse dans l'état de Washington, pour vivre avec son père. Elle s'attend à ce que sa nouvelle vie soit aussi ennuyeuse que la ville elle-même. Or, au lycée, elle est terriblement intriguée.",
			512, "20051102",  NULL, NULL, NULL, NULL,NULL,"E-book", 1653,"epub", 1, 1, 1),
		-- Twilight 2
		(2012012950, 20.20,
			"Rejetée par celui qu'elle aime passionnément, Bella ne s'en relève pas. Fascinée par un vampire, comment pourra-t-elle retrouver goût à la pâle existence humaine ? Bella n'a de goût pour rien, sinon le danger : alors, elle entend la voix...",
			570, "20061102",  NULL, NULL, NULL, NULL,NULL,"E-book", 1227,"epub", 1, 1, 1);
		-- Twilight 3
		(2012014930, 20.20,
			"Deux futurs, deux âmes soeurs... C'était trop pour une seule personne. Je compris que ce n'était pas Edward et Jacob que j'avais essayé de réconcilier, c'étaient les deux parts de moi-même, la Bella d'Edward et la Bella de Jacob... ",
			615, "20071107",NULL, NULL, NULL, NULL,NULL,"E-book", 2049,"epub", 1, 1, 1),
		-- Twilight 4
		(2012016828, 20.20,
			"Bella a fait son choix : elle s'apprête à épouser Edward. Mais le jeune homme honorera-t-il sa part du marché ? Acceptera-t-il de la transformer en vampire et d'accepter de la voir renoncer à sa vie humaine ?",
			759, "20081022", NULL, NULL, NULL, NULL,NULL,"E-book", 1960,"epub",1, 1, 1),
			
		-- Dix petits nègres
		(0062073486, 14.82,
			"Il se passe quelque chose d'anormal. Les dix personnes conviées sur l'ïle du Nègre en ont la certitude. Pourquoi leur hôte est-il absent? Soudain, une voix s'élève, accusant d'un crime chaque invité. Commence alors une ronde mortelle,...",
			320, "20140820", 178, 125, 15, 114, 10,"Paper book",NULL,NULL, 2, 2, 2),
			
		-- La première nuit
		(2021113110, 24.40,
			"L'amour est l'ultime aventure, mais l'aventure n'est pas sans dangers... « Il est une légende qui raconte que l'enfant dans le ventre de sa mère connaît tout du mystère de la Création, de l'origine du monde jusqu'à la fin des temps.",
			485, "20091202", 240, 153, 34, 632, 29,"Paper book", NULL, NULL, 3, 3, 3),
			
		-- Le premier jour
		(2021110010, 24.40,
			"Une grande histoire d'amour. Un grand roman d'aventures. Un étrange objet trouvé dans un volcan éteint va révolutionner tout ce que l'on croit savoir de la naissance du monde. Il est astrophysicien, elle est archéologue. Ils vont vivre une aventure... ",
			512, "20090625", 241, 156, 34, 630, 18,"Paper book",NULL,NULL, 3, 3, 3),
			
		-- La fille du train
		(2055843136, 23.70,
			"Entre la banlieue où elle habite et Londres, Rachel prend le train deux fois par jour : à 8 h 04 le matin, à 17 h 56 le soir. Et chaque jour elle observe, lors d'un arrêt, une jolie maison en contrebas de la voie ferrée. Elle la connaît par coeur,...",
			382, "20150507", 220, 140, 35, 500, 26,"Paper book",NULL,NULL, 4, 4, 2),
			
		-- Hunger Games 1
		(2026260774, 7.80,
			"Dans un futur sombre, sur les ruines des États-Unis, un jeu télévisé est créé pour contrôler le peuple par la terreur. Douze garçons et douze filles tirés au sort participent à cette sinistre téléréalité, que tout le monde est forcé de regarder en direct. ",
			432, "20150604", 178, 109, 31, 283, 31,"Paper book", NULL,NULL, 5, 5, 1),			
		-- Hunger Games 2
		(2066260787, 9.10,
			"Après le succès des derniers Hunger Games, le peuple de Panem est impatient de retrouver Katniss et Peeta pour la Tournée de la victoire. Mais pour Katniss, il s'agit surtout d'une tournée de la dernière chance...",
			432, "20150604", 178, 111, 32, 284, 19,"Paper book",NULL,NULL, 5, 5, 1),
		-- Hunger Games 3
		(2066260790, 7.80,
			"Contre toute attente, Katniss a survécu une seconde fois aux Hunger Games. Mais le Capitole crie vengeance. Katniss doit payer les humiliations qu'elle lui a fait subir. Et le président Snow a été clair : Katniss n'est pas la seule à risquer sa vie...",
			464, "20150604", 178, 109, 33,  304, 19,"Paper book", NULL,NULL, 5, 5, 1),
			
		-- Divergente 1
		(2092552988, 19.40,
			"Tris vit dans un monde post-apocalyptique où la société est divisée en cinq factions. À 16 ans elle doit choisir sa nouvelle appartenance pour le reste de sa vie. Cas rarissime, son test d'aptitudes n'est pas concluant. Elle est divergente...",
			448, "20140320", 227, 163, 34, 519, 24,"Paper book", NULL, NULL, 6, 6, 1),
		-- Divergente 2
		(2092558249, 19.40,
			"Abandonnant une ville à feu et à sang, Tris est en fuite. Grâce à ses facultés de Divergente, elle a réussi à échapper au programme des Érudits qui a manipulé et lancé les soldats Audacieux à l'assaut des Altruistes.",
			460, "20150211", 226, 159, 33, 540, 19,"Paper book", NULL, NULL, 6, 6, 1),
		-- Divergente 3
		(2092565247, 19.40,
			"Le règne des factions a laissé place à une nouvelle dictature. Tris et ses amis refusent de s'y soumettre. Ils doivent s'enfuir. Mais que trouveront-ils au-delà de la clôture ? Et si tout n'était que mensonge ?  ",
			460, "20160225", 227, 157, 35, 548, 14,"Paper book",NULL,NULL, 6, 6, 1);
			
INSERT INTO Promotion(StartDate, EndDate, Percentage,Summary,book_id)
	VALUES
		-- Twilight
		("20161107", "20170131", 0.2,"-20% jusqu'au 31 janvier 2017 !",0316015849 ),
		-- Le premier jour
		("20161107", "20170109", 0.05,"-5% jusqu'au 9 janvier 2017 !",2021110010),
		-- Hunger Games 1
		("20161107", "20170108", 0.1,"-10 % jusqu'au 8 janvier 2017 !",2026260774),
		-- Divergente
		("20161107", "20161230", 0.05,"-5% jusqu'au 30 décembre 2016 !",2092552988);	
		
INSERT INTO LanguageTranslationTitleOfBook(TranslationTitleOfBook, currentLanguage_id,book_id)
	VALUES
		-- Twilight 1
		("Twilight", "en",0316015849),
		("Twilight","fr",0316015849),
		-- Twilight 2
		("New moon", "en",2012012950),
		("Tentation","fr",2012012950),
		-- Twilight 3
		("Eclipse", "en",2012014930),
		("Hésitation","fr",2012014930),
		-- Twilight 4
		("Breaking Dawn","en",2012016828),
		("Révélation","fr",2012016828),
		
		-- Dix petits nègres
		("And Then There Were None","en",0062073486), 
		("Dix petits nègres","fr",0062073486),
		
		-- La première nuit
		("The First Night","en", 2021113110),
		("La première nuit","fr",2021113110),
		
		-- Le premier jour
		("The First Day", "en",2021110010),
		("Le premier jour","fr",2021110010),
		
		-- La fille du train
		("The Girl on the Train","en",2055843136),
		("La fille du train","fr",2055843136),
		
		-- Hunger games 1
		("The Hunger Games", "en",2026260774),
		("Hunger Games","fr",2026260774),
		
		-- Hunger games 2
		("Catching Fire", "en",2066260787),
		("L'Embrasement","fr",2066260787),
		
		-- Hunger games 3
		("Mockingjay", "en",2066260790),
		("La Révolte","fr",2066260790),
		
		-- Divergente 1
		("Divergent", "en",2092552988),
		("Divergent","fr",2092552988),
		
		-- Divergente 2
		("Insurgent", "en",2092558249),
		("L'insurrection","fr",2092558249),
		-- Divergente 3
		("Allegiant","en",2092565247),
		("Au-delà du mur","fr",2092565247);
		
INSERT INTO LanguageTranslationWordingOfCategory(TranslationWordingOfCategory_id, TranslationWordingOfCategory,CurrentLanguage_ID,Category_ID)
	VALUES
		-- Fantastique
		("Fantastic", "en",1),
		("Fantastique","fr",1),
		
		-- Policier 
		("Police novel","en",2),
		("Policier","fr",2),
		
		-- Romance
		("Romance","en",3),
		("Romance","fr",3);