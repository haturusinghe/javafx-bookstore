DROP DATABASE IF EXISTS bookstore;

CREATE DATABASE bookstore;

create table BOOK (
	book_id INT NOT NULL AUTO_INCREMENT,
	category_id INT,
	publisher_id INT,
	author_id INT,
	title VARCHAR(50) NOT NULL,
	isbn VARCHAR(50) NOT NULL,
	year VARCHAR(50),
	num_pages INT,
	book_description VARCHAR(50),
	PRIMARY KEY (book_id)
);

/*ALTER TABLE book DROP COLUMN price;*/

create table BOOK (
	isbn VARCHAR(50) NOT NULL PRIMARY KEY,
	category_id INT,
	publisher VARCHAR(50),
	author_id INT,
	title VARCHAR(50) NOT NULL,
	year VARCHAR(50),
	mrp INT NOT NULL,
	num_pages INT,
	lang VARCHAR(50),
	book_description VARCHAR(50)
);
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('952348969-0', 1, 'Denesik-Lang', 8, 'Scolopax minor', 2007, 3477, 848, 'Finnish', 'Multi-lateral upward-trending Graphical User Interface');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('333517190-X', 2, 'McLaughlin and Sons', 7, 'Cacatua tenuirostris', 2001, 645, 924, 'New Zealand Sign Language', 'Compatible impactful open system');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('434699066-5', 4, 'Stroman-Feest', 1, 'Haliaetus leucogaster', 1994, 4709, 350, 'Tswana', 'Team-oriented attitude-oriented knowledge base');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('547303192-7', 4, 'Bosco, Bosco and Gibson', 10, 'unavailable', 1993, 1387, 331, 'Zulu', 'Programmable transitional analyzer');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('938061269-9', 5, 'O''Keefe-Bogisich', 3, 'Phasianus colchicus', 2003, 2256, 442, 'Korean', 'Versatile global core');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('871286938-4', 5, 'Tillman-Considine', 4, 'Trichosurus vulpecula', 1993, 1497, 594, 'Lithuanian', 'Ergonomic fault-tolerant matrices');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('816524505-8', 2, 'Dibbert-Ebert', 5, 'Cacatua galerita', 2005, 1099, 152, 'Tajik', 'Re-contextualized client-driven help-desk');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('049540344-X', 1, 'Parisian-Gutkowski', 5, 'Anas bahamensis', 2007, 4189, 939, 'German', 'Enhanced encompassing frame');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('054443042-5', 4, 'Legros Group', 10, 'Semnopithecus entellus', 2010, 734, 253, 'Belarusian', 'Diverse zero tolerance moderator');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('208020808-X', 3, 'O''Hara-Beahan', 7, 'Paradoxurus hermaphroditus', 1994, 565, 558, 'Tok Pisin', 'Total solution-oriented open system');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('385244820-4', 3, 'Langworth, Carter and Roberts', 3, 'unavailable', 2008, 2666, 321, 'Tetum', 'Intuitive web-enabled definition');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('358320771-8', 1, 'Lind-Wyman', 10, 'Mungos mungo', 2000, 3774, 144, 'Romanian', 'Re-contextualized solution-oriented intranet');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('608566781-7', 2, 'Trantow and Sons', 5, 'Carduelis uropygialis', 2007, 701, 561, 'Quechua', 'Innovative dynamic core');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('012085041-9', 1, 'Stokes-Gottlieb', 5, 'Acridotheres tristis', 2006, 1793, 494, 'Kazakh', 'Proactive encompassing implementation');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('072320758-5', 5, 'Price LLC', 5, 'Morelia spilotes variegata', 1999, 4017, 337, 'Kurdish', 'Implemented upward-trending projection');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('030577984-2', 5, 'Metz, Krajcik and Shanahan', 6, 'Axis axis', 2008, 1795, 426, 'Croatian', 'Enhanced full-range knowledge base');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('450438063-3', 1, 'Bruen-DuBuque', 3, 'Pelecanus occidentalis', 1999, 1528, 897, 'Latvian', 'Advanced didactic Graphical User Interface');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('123126058-0', 3, 'Lueilwitz Inc', 8, 'Geochelone radiata', 2006, 3861, 511, 'Aymara', 'Monitored attitude-oriented middleware');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('018693037-2', 5, 'Carter-Ferry', 7, 'Chelodina longicollis', 1960, 2441, 777, 'Estonian', 'Ameliorated intermediate orchestration');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('322625054-X', 1, 'DuBuque Group', 1, 'Theropithecus gelada', 2006, 2570, 699, 'Haitian Creole', 'Open-source contextually-based interface');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('972096623-8', 4, 'Paucek-Johnston', 4, 'Macropus eugenii', 2008, 4693, 168, 'Oriya', 'Optional secondary analyzer');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('924529817-5', 5, 'Braun-Wolff', 10, 'Dendrocitta vagabunda', 2010, 772, 893, 'Kannada', 'Phased context-sensitive core');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('877824997-X', 4, 'Brown Inc', 4, 'Tamiasciurus hudsonicus', 1996, 4442, 265, 'Gujarati', 'Innovative eco-centric groupware');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('779976853-3', 4, 'Cremin Group', 7, 'Spilogale gracilis', 1999, 4100, 184, 'Irish Gaelic', 'Robust analyzing interface');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('921292247-3', 5, 'Quitzon Group', 10, 'Dusicyon thous', 1997, 1676, 319, 'Persian', 'Total mission-critical function');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('490388383-3', 5, 'Bauch Group', 9, 'Galictis vittata', 2000, 1954, 557, 'Malagasy', 'Extended context-sensitive intranet');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('248019536-8', 3, 'Turner Inc', 4, 'Podargus strigoides', 1999, 1291, 844, 'Northern Sotho', 'Implemented attitude-oriented groupware');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('412756529-2', 5, 'Goyette, Prosacco and Erdman', 5, 'Gyps bengalensis', 2011, 4903, 445, 'Indonesian', 'Intuitive multi-state protocol');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('190332044-5', 4, 'Torp Inc', 10, 'Pan troglodytes', 2002, 109, 598, 'Gagauz', 'Integrated 6th generation superstructure');
insert into BOOK (isbn, category_id, publisher, author_id, title, year, mrp, num_pages, lang, book_description) values ('111917715-4', 1, 'Boyer, Howell and Kautzer', 6, 'Felis libyca', 1967, 3367, 364, 'Assamese', 'Operative asymmetric support');









create table INVENTORY (
	inv_id INT PRIMARY KEY,
	isbn VARCHAR(50),
	list_price INT NOT NULL,
	qty INT NOT NULL,
	min_qty INT NOT NULL
);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (1, '952348969-0', 753, 11, 0);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (2, '333517190-X', 813, 16, 5);

insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (3, '616034976-7', 613, 19, 4);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (4, '659797712-2', 812, 10, 2);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (5, '127834789-5', 519, 23, 5);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (6, '043559031-6', 887, 13, 5);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (7, '815152329-8', 508, 12, 3);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (8, '526863061-X', 758, 1, 4);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (9, '044814690-8', 584, 28, 5);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (10, '279607500-1', 639, 39, 3);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (11, '272007921-9', 793, 38, 0);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (12, '382366220-1', 759, 4, 0);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (13, '593073645-6', 973, 50, 5);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (14, '705599565-5', 618, 47, 4);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (15, '009663442-1', 594, 4, 2);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (16, '016460076-0', 831, 29, 4);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (17, '856499807-6', 709, 32, 0);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (18, '209332315-X', 621, 19, 5);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (19, '154226266-6', 735, 30, 5);
insert into INVENTORY (inv_id, isbn, list_price, qty, min_qty) values (20, '138123505-0', 877, 49, 1);

create table orders (
	order_id INT PRIMARY KEY,
	customer_id INT,
	employee_id INT,
	order_date DATE,
	total_quantity INT,
	total_price INT,
	total_discount INT,
);
insert into orders (order_id, customer_id, employee_id, order_date, total_quantity, total_price, total_discount) values (1, 4, 4, '2020-11-11 15:06:39', 96, 62, 58);
insert into orders (order_id, customer_id, employee_id, order_date, total_quantity, total_price, total_discount) values (2, 5, 5, '2020-10-11 23:53:37', 55, 57, 26);
insert into orders (order_id, customer_id, employee_id, order_date, total_quantity, total_price, total_discount) values (3, 5, 2, '2021-05-25 14:17:40', 13, 100, 54);
insert into orders (order_id, customer_id, employee_id, order_date, total_quantity, total_price, total_discount) values (4, 4, 2, '2020-10-02 14:50:05', 19, 74, 3);
insert into orders (order_id, customer_id, employee_id, order_date, total_quantity, total_price, total_discount) values (5, 1, 3, '2021-01-23 18:32:27', 9, 4, 81);

create table order_details (
	order_detail_id INT,
	book_id INT,
	order_id INT,
	quantity INT,
	unit_price INT,
	discount INT,
	PRIMARY KEY (order_detail_id,book_id,order_id)
);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (1, 5, 1, 2, 840, 441);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (2, 1, 4, 5, 481, 339);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (3, 7, 1, 2, 231, 485);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (4, 5, 4, 4, 585, 439);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (5, 5, 2, 2, 1157, 262);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (6, 4, 2, 5, 1730, 430);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (7, 10, 2, 5, 814, 88);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (8, 2, 2, 3, 1180, 58);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (9, 10, 2, 1, 1667, 485);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (10, 1, 5, 3, 837, 145);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (11, 1, 2, 4, 110, 433);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (12, 10, 5, 2, 1688, 68);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (13, 2, 2, 2, 458, 95);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (14, 10, 5, 1, 981, 223);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (15, 9, 4, 1, 1773, 230);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (16, 8, 1, 3, 335, 292);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (17, 1, 5, 3, 1015, 168);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (18, 1, 3, 3, 986, 421);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (19, 7, 2, 1, 1613, 180);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (20, 6, 4, 5, 1404, 170);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (21, 4, 3, 4, 588, 314);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (22, 5, 5, 5, 1862, 284);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (23, 8, 2, 3, 1112, 489);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (24, 6, 4, 2, 224, 324);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (25, 2, 2, 4, 1489, 285);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (26, 1, 3, 4, 549, 493);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (27, 8, 5, 4, 1753, 403);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (28, 9, 5, 2, 582, 177);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (29, 9, 3, 3, 1734, 121);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (30, 10, 5, 4, 1554, 272);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (31, 7, 1, 1, 1421, 495);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (32, 10, 3, 5, 1234, 470);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (33, 1, 5, 2, 1650, 435);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (34, 9, 3, 3, 275, 58);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (35, 6, 5, 5, 244, 393);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (36, 5, 3, 1, 844, 64);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (37, 3, 5, 2, 497, 252);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (38, 1, 5, 3, 475, 150);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (39, 7, 4, 5, 374, 67);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (40, 6, 5, 1, 851, 99);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (41, 8, 2, 1, 1706, 293);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (42, 2, 2, 5, 846, 195);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (43, 10, 2, 2, 1799, 157);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (44, 1, 3, 4, 1468, 278);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (45, 4, 4, 2, 1045, 381);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (46, 6, 5, 3, 216, 125);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (47, 4, 4, 2, 267, 258);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (48, 7, 4, 2, 1049, 496);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (49, 3, 4, 3, 1347, 488);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (50, 9, 5, 4, 1757, 183);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (51, 8, 1, 3, 377, 273);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (52, 5, 3, 5, 1764, 66);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (53, 10, 1, 4, 103, 80);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (54, 7, 5, 3, 1395, 307);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (55, 10, 4, 2, 375, 458);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (56, 1, 2, 5, 1091, 499);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (57, 4, 3, 3, 1644, 195);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (58, 8, 5, 5, 1942, 281);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (59, 4, 4, 4, 1080, 447);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (60, 2, 1, 1, 1602, 174);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (61, 3, 4, 5, 967, 417);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (62, 6, 5, 3, 445, 303);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (63, 5, 4, 4, 768, 351);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (64, 8, 5, 2, 939, 131);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (65, 5, 4, 3, 1764, 208);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (66, 1, 2, 2, 391, 398);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (67, 8, 3, 5, 1335, 360);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (68, 2, 3, 3, 891, 429);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (69, 4, 4, 3, 1418, 461);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (70, 8, 5, 4, 1236, 244);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (71, 3, 3, 1, 547, 127);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (72, 6, 5, 2, 349, 385);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (73, 8, 1, 1, 1074, 206);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (74, 4, 5, 3, 1309, 323);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (75, 5, 3, 2, 888, 114);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (76, 8, 2, 5, 1221, 499);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (77, 9, 1, 1, 1520, 463);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (78, 6, 2, 5, 319, 310);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (79, 4, 4, 3, 783, 205);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (80, 8, 5, 5, 1036, 197);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (81, 7, 2, 4, 1160, 468);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (82, 1, 1, 2, 954, 237);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (83, 8, 1, 1, 928, 238);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (84, 9, 1, 1, 1423, 393);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (85, 7, 5, 1, 231, 393);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (86, 7, 4, 1, 565, 203);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (87, 1, 5, 1, 1749, 454);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (88, 3, 3, 1, 1429, 192);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (89, 9, 1, 3, 929, 464);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (90, 4, 1, 5, 614, 200);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (91, 8, 3, 4, 1550, 406);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (92, 6, 4, 4, 392, 72);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (93, 1, 3, 4, 580, 473);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (94, 6, 1, 2, 281, 423);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (95, 10, 4, 3, 390, 221);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (96, 9, 2, 5, 522, 61);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (97, 10, 1, 3, 870, 224);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (98, 4, 3, 3, 431, 300);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (99, 3, 2, 5, 167, 256);
insert into order_details (order_detail_id, book_id, order_id, quantity, unit_price, discount) values (100, 7, 4, 3, 1755, 68);