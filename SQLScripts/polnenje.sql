insert into users (firstname,lastname,username,email,userpassword) values 
('Bojan','Trpeski','BojanT','bojan@gmail.com','1234'),
('Darko','Sasanski','DarkoS','darko@gmail.com','1111'),
('Trajko','Trajkovski','TrajkoT','trajko@gmail.com','2222'),
('Petko','Petkovski','PetkoP','petko@gmail.com','3333'),
('Andrej','Todorovski','AndrejT','andrej@gmail.com','4444'),
('Hristijan','Hristovski','HristijanH','hristijan@gmail.com','55555'),
('Stefan','Stefanovski','StefanS','stefan@gmail.com','6666'),
('Dimitar','Dimovski','DimitarD','dimitar@gmail.com','7777'),
('David','Davidovski','DavidD','david@gmail.com','8888');

insert into managers (userid) values 
(2),(3),(6);

insert into workers (userid) values 
(4),(5),(7);

insert into suppliers  (userid,supplierinfo,phone,street,streetnumber,city) values 
(8,'DHL','070123456','Ilindenska',123,'Skopje'),
(9,'FedEx','070555111','Partizanski Oderdi',15,'Skopje'),
(1,'Kargo Express','070789456','Jane Sandanski',55,'Skopje');


insert into orders (status,supplierremark,priority,manageruserid,supplieruserid) values 
('IN_PROGRESS','To be delivered','Low',2,1);
insert into orders (status,managerremark,priority,manageruserid,supplieruserid) values 
('IN_PROGRESS','To be packed','Medium',3,8);
insert into orders (status,datecreated,dateapproved ,priority,manageruserid,supplieruserid) values 
('DELIVERED','2022-10-11 11:53:45','2022-10-12 12:13:14','High',6,9);

insert into articles (description,articlename,maxquantityperlocation) values 
('Del za velosiped','Osovina',10),
('Novi televizori','Samsung TV 4K',15),
('Bluetooth audio','JBL GO 2',50),
('Domasen enterier','Agolna garnitura',5),
('Novi laptopi','Apple M1',100),
('Novi velosipedi','Focus',10),
('Domasen enterier','Biro',20),
('Debela Jakna','Addidas jakna',120),
('Jakna za skijanje','Nike jakna',150);

insert into categories (categoryName,description) values
('Garderoba','Zimski jakni'),
('Tehnologija','Najnovi televizori, kompjuteri i audio uredi'),
('Velosipedizam','Se za velosipedi'),
('Mebel','Mebel za sekoj dom');

insert into locations (locationname,phone,street,streetnumber,city) values 
('Magacin 1','078894563','Metodija Shatorov Sharlo',53,'Skopje'),
('Magacin 2','075321654','Boris Trajkovski',81,'Skopje'),
('Magacin 3','072125874','1.Maj',45,'Bitola');

update workers set locationid =1 where userid =4;
update workers set locationid =2 where userid =5;
update workers set locationid =3 where userid =7;

insert into invoices (customername,customerphone,street,streetnumber,city,workeruserid) values 
('Stojan','0789654123','Bulervar Srbija','155','Skopje',7),
('Marko','070456321','Ruzveltova','3','Skopje',4),
('Kristijan','075897125','ASNOM','47','Veles',5);

insert into invoicedarticles (price,quantity,invoiceid,articleid) values 
(6000,3,1,3),
(50000,1,3,5),
(20000,2,2,7);

insert into storedarticles (quantity,locationid,articleid) values 
(5,1,6),
(10,3,3),
(20,3,8),
(2,2,5),
(8,1,7);

insert into orderedarticles (price,quantity,orderid,locationid,articleid, articlestatus) values 
(100000,2,1,1,4, 'ORDERED'),
(2000,1,2,2,1, 'ORDERED'),
(15000,5,3,3,9, 'DELIVERED');

insert into questions  (questiontext,datecreated,workeruserid,manageruserid) values
('Dali ke moze da naracate uste Focus velosipedi, nemame na zaliha?','2022-10-10 14:15:23',7,6),
('Dali se dosta 100 jakni?','2022-11-11 11:12:12',5,2),
('Dali ke moze da naracate novi JBL GO2 zvucnici, imame uste 10?','2022-11-15 13:51:23',4,3);

insert into answers (questionId,answertext,datecreated) values 
(1,'Da, ke naracam uste 5.','2022-10-11 08:14:14'),
(2,'Dovolno e 100 jakni.','2022-11-12 09:16:23'),
(3,'Sekako, ke naracam 100 parcinja.','2022-11-17 12:42:24');

insert into question_availability_for_storedarticle (questionId,sarticleid) values 
(1,1),
(2,3),
(3,2);

insert into supplier_supplies_category (userid,categoryid) values 
(8,1),
(8,2),
(9,3),
(9,4),
(9,2),
(1,1),
(1,3),
(1,4);

insert into article_belongs_to_category (articleId,categoryid) values 
(1,2),
(2,1),
(3,1),
(4,3),
(5,1),
(6,2),
(7,3),
(8,4),
(9,4);