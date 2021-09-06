DROP DATABASE IF EXISTS bookstore;

CREATE DATABASE bookstore;

-- create table BOOK (
-- 	book_id INT NOT NULL AUTO_INCREMENT,
-- 	category_id INT,
-- 	publisher_id INT,
-- 	author_id INT,
-- 	title VARCHAR(50) NOT NULL,
-- 	isbn VARCHAR(50) NOT NULL,
-- 	year VARCHAR(50),
-- 	num_pages INT,
-- 	book_description VARCHAR(50),
-- 	PRIMARY KEY (book_id)
-- );

/*ALTER TABLE book DROP COLUMN price;*/

create table book (
	book_id INT PRIMARY KEY,
	isbn VARCHAR(50),
	category_id INT,
	publisher VARCHAR(50),
	author_id INT,
	title VARCHAR(50),
	b_year YEAR,
	mrp INT,
	num_pages INT,
	lang VARCHAR(50),
	book_description VARCHAR(50)
);
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (1, '226424542-5', 4, 'Fisher Inc', 10, 'Phalacrocorax albiventer', 1991, 547, 842, 'Chinese', 'Switchable asynchronous utilisation');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (2, '782809134-6', 2, 'Ferry-Dietrich', 3, 'Cordylus giganteus', 2004, 2787, 586, 'Hungarian', 'Proactive user-facing circuit');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (3, '526316243-X', 5, 'Spencer, Quigley and Stanton', 5, 'Ninox superciliaris', 2006, 1768, 830, 'Icelandic', 'Optimized uniform groupware');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (4, '791417617-6', 5, 'Breitenberg, Goodwin and Quitzon', 2, 'Geochelone elephantopus', 1990, 1371, 565, 'Hungarian', 'User-friendly asynchronous neural-net');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (5, '532310026-5', 2, 'Reichel LLC', 9, 'Gorilla gorilla', 2009, 1741, 374, 'Kazakh', 'Persistent asynchronous conglomeration');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (6, '106780385-8', 2, 'Connelly, Baumbach and Aufderhar', 1, 'Creagrus furcatus', 2003, 3821, 578, 'Montenegrin', 'Profit-focused client-driven info-mediaries');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (7, '566332181-0', 2, 'Cremin, Ondricka and Schumm', 7, 'Manouria emys', 2006, 2296, 925, 'Fijian', 'Implemented 3rd generation forecast');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (8, '545924208-8', 1, 'Zemlak Group', 9, 'Chlamydosaurus kingii', 1984, 2072, 549, 'Kyrgyz', 'Digitized object-oriented focus group');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (9, '893818838-8', 5, 'Harber, Kozey and Douglas', 5, 'unavailable', 2012, 1414, 305, 'Marathi', 'Reactive didactic workforce');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (10, '204386047-X', 4, 'Pouros, Murray and Glover', 6, 'Macropus robustus', 2002, 2371, 221, 'Oriya', 'Open-source intermediate data-warehouse');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (11, '061028428-2', 4, 'Bosco, Kub and Kiehn', 4, 'Canis aureus', 2006, 722, 298, 'Tajik', 'Optimized next generation utilisation');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (12, '191235481-0', 5, 'Windler-Wehner', 6, 'Diceros bicornis', 2009, 2010, 794, 'Thai', 'Networked holistic software');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (13, '476984142-6', 3, 'Pouros, Skiles and Grimes', 4, 'Acrobates pygmaeus', 2002, 4404, 862, 'Nepali', 'Reactive user-facing productivity');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (14, '936524938-4', 4, 'Casper Inc', 6, 'Phalacrocorax niger', 1996, 240, 724, 'Telugu', 'Extended systematic model');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (15, '622089167-8', 5, 'Blick-Stamm', 10, 'Sylvilagus floridanus', 2000, 869, 301, 'Norwegian', 'Public-key human-resource system engine');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (16, '744784190-0', 5, 'Metz and Sons', 6, 'Echimys chrysurus', 2001, 2963, 442, 'Kurdish', 'Right-sized content-based contingency');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (17, '543309810-9', 1, 'Hamill-Adams', 8, 'Amblyrhynchus cristatus', 2007, 3401, 162, 'Oriya', 'Open-source analyzing parallelism');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (18, '651982624-3', 2, 'Hermann and Sons', 3, 'Ardea golieth', 1993, 290, 768, 'Indonesian', 'Robust systematic focus group');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (19, '316159158-5', 3, 'Hayes, Beatty and Bergnaum', 8, 'Motacilla aguimp', 1984, 1689, 677, 'Irish Gaelic', 'Persevering reciprocal knowledge user');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (20, '986247367-3', 1, 'Gorczany, Harber and Grimes', 3, 'Kobus leche robertsi', 1993, 2210, 128, 'Yiddish', 'Cross-group encompassing paradigm');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (21, '990755377-8', 1, 'Murazik Group', 4, 'Bison bison', 2000, 3711, 296, 'Afrikaans', 'Assimilated dynamic solution');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (22, '841884138-9', 3, 'Murray-Padberg', 4, 'Chloephaga melanoptera', 2009, 926, 142, 'Kannada', 'Automated grid-enabled architecture');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (23, '171323191-3', 2, 'Cummerata Group', 2, 'Anser anser', 2005, 2488, 784, 'Greek', 'Reactive stable focus group');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (24, '973849306-4', 5, 'Gusikowski, Jacobs and Labadie', 10, 'Cacatua tenuirostris', 1990, 2149, 275, 'West Frisian', 'Expanded multi-state software');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (25, '069938420-6', 1, 'Hoppe-Mills', 1, 'Francolinus swainsonii', 2009, 868, 875, 'Spanish', 'Upgradable solution-oriented customer loyalty');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (26, '424759974-7', 1, 'Zboncak-Blick', 5, 'Axis axis', 2005, 2821, 747, 'Japanese', 'Seamless incremental extranet');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (27, '719479191-9', 2, 'Kozey-Graham', 8, 'Genetta genetta', 2011, 1494, 697, 'Mongolian', 'Ergonomic impactful info-mediaries');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (28, '748800431-0', 2, 'Pfeffer Group', 6, 'Macropus fuliginosus', 2000, 4931, 263, 'Yiddish', 'Secured tertiary focus group');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (29, '015858967-X', 1, 'Schmidt Group', 6, 'Sula dactylatra', 1993, 3783, 536, 'Malagasy', 'Open-architected optimal framework');
insert into book (book_id, isbn, category_id, publisher, author_id, title, b_year, mrp, num_pages, lang, book_description) values (30, '788542204-6', 1, 'West, White and Reichert', 5, 'Threskionis aethiopicus', 2003, 239, 606, 'Hiri Motu', 'Advanced fresh-thinking analyzer');

create table INVENTORY (
	inv_id INT PRIMARY KEY,
	book_id INT,
	list_price INT,
	qty INT,
	min_qty INT
);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (1, 1, 865, 9, 10);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (2, 2, 898, 17, 2);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (3, 3, 767, 5, 6);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (4, 4, 824, 10, 5);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (5, 5, 874, 4, 2);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (6, 6, 932, 5, 6);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (7, 7, 925, 3, 10);
insert into INVENTORY (inv_id, book_id, list_price, qty, min_qty) values (8, 8, 835, 28, 2);

create table orders (
	order_id INT PRIMARY KEY,
	customer_id INT,
	employee_id INT,
	order_date DATE,
	total_quantity INT,
	total_price INT,
	total_discount INT
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

-- create table order_details (
-- 	order_id INT,
-- 	book_id INT,
-- 	quantity INT,
-- 	unit_price INT,
-- 	discount INT,
-- 	PRIMARY KEY (book_id,order_id)
-- );

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

create table book_category (
	category_id INT PRIMARY KEY,
	category_name VARCHAR(50)
);
insert into book_category (category_id, category_name) values (1, 'application');
insert into book_category (category_id, category_name) values (2, 'Synergized');
insert into book_category (category_id, category_name) values (3, 'systemic');
insert into book_category (category_id, category_name) values (4, 'Mandatory');
insert into book_category (category_id, category_name) values (5, 'ability');
insert into book_category (category_id, category_name) values (6, 'Sharable');
insert into book_category (category_id, category_name) values (7, 'context-sensitive');
insert into book_category (category_id, category_name) values (8, 'Digitized');
insert into book_category (category_id, category_name) values (9, 'tangible');
insert into book_category (category_id, category_name) values (10, 'Synergized');

create table author (
	author_id INT PRIMARY KEY,
	author_name VARCHAR(50)
);
insert into author (author_id, author_name) values (1, 'Amity Dockrill');
insert into author (author_id, author_name) values (2, 'Bale Bahike');
insert into author (author_id, author_name) values (3, 'Winnifred Filchakov');
insert into author (author_id, author_name) values (4, 'Devora Gallehawk');
insert into author (author_id, author_name) values (5, 'Ivor Livingstone');
insert into author (author_id, author_name) values (6, 'Had Brixey');
insert into author (author_id, author_name) values (7, 'Reamonn Riatt');
insert into author (author_id, author_name) values (8, 'Lazarus Strode');
insert into author (author_id, author_name) values (9, 'Dylan Heake');
insert into author (author_id, author_name) values (10, 'Barbara Pilmore');

create table category (
	category_id INT PRIMARY KEY,
	category_name VARCHAR(50),
	low_value INT,
	up_value INT
);

insert into category (category_id, category_name,low_value,up_value) values (1, 'Less than 25k',0,25000);
insert into category (category_id, category_name,low_value,up_value) values (2, 'greater than 25k',25000,150000);

create table customer(
                        customer_id INT PRIMARY KEY,
                        customer_name VARCHAR(50),
                        location VARCHAR(50) ,
                        mobile VARCHAR(12),
                        email  varchar(60)
);
INSERT into customer VALUES ('01','Nimal','Colombo' , '0714331372','nimal1998@gmail.com');
INSERT into customer VALUES ('02','Kamal','Kelaniya' , '0714345672','kamal1997@gmail.com');
INSERT into customer VALUES ('03','Rayan','Matara' , '0714331372','nimal1993@gmail.com');