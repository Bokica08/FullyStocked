drop schema if exists project cascade;
create schema project;

--use project

drop table if exists users cascade;
drop table if exists workers cascade;
drop table if exists managers cascade;
drop table if exists suppliers cascade;
drop table if exists articles cascade;
drop table if exists invoices cascade;
drop table if exists orders cascade;
drop table if exists categories cascade;
drop table if exists locations cascade;
drop table if exists questions cascade;
drop table if exists storedArticles cascade;
drop table if exists invoicedArticles cascade;
drop table if exists orderedArticles cascade;
drop table if exists answers cascade;
drop table if exists question_availability_for_storedarticle cascade;
drop table if exists article_belongs_to_category cascade;
drop table if exists supplier_supplies_category cascade;

-------------------------

create table users(
	
	userId serial primary key,
	firstName varchar(100) not null,
	lastName varchar(100) not null,
	username varchar(100) unique not null,
	email varchar(100) not null,
	userPassword varchar(100) not null
	
);

-------------------------

create table articles(

	articleId serial primary key,
	description varchar(400) not null,
	articleName varchar(100) not null,
	imageURL varchar(200),
	maxQuantityPerLocation integer not null
	constraint ck_maxquantity_gt_0 check (maxQuantityPerLocation>0)

);

-------------------------

create table categories(

	categoryId serial primary key,
	categoryName varchar(100) not null,
	description varchar(400) not null

);

-------------------------

create table locations(

	locationId serial primary key,
	locationName varchar(100) not null,
	phone varchar(20) not null,
	street varchar(100) not null,
	streetNumber integer not null,
	city varchar(20) not null

);

-------------------------

create table workers(

	userId integer primary key,
	locationId integer,
	constraint fk_workers_id foreign key (userId) references users(userId) on delete cascade on update cascade,
	constraint fk_workers_loc foreign key (locationId) references locations(locationId)

);

-------------------------

create table managers(

	userId integer primary key,
	constraint fk_managers_id foreign key (userId) references users(userId) on delete cascade on update cascade

);

-------------------------

create table suppliers(

	userId integer primary key,
	supplierInfo varchar(300) not null,
	phone varchar(20) not null,
	street varchar(100) not null,
	streetNumber integer not null,
	city varchar(100) not null,
	constraint fk_suppliers_id foreign key (userId) references users(userId) on delete cascade on update cascade

);

-------------------------

create table invoices(
	invoiceId serial primary key,
	customerName varchar(100),
	customerPhone varchar(20),
	street varchar(100),
	streetNumber integer,
	city varchar(100),
	dateCreate timestamp default now(),
	workerUserId integer not null,
	constraint fk_invoice_worker foreign key (workerUserId) references workers(userId)
	
);

-------------------------

create table orders(

	orderId serial primary key,
	status varchar(50) not null,
	supplierRemark varchar(400),
	managerRemark varchar(400),
	dateCreated timestamp default now(),
	dateApproved timestamp,
	dateDelivered timestamp,
	priority varchar(50) not null,
	managerUserId integer not null,
	supplierUserId integer not null,
	constraint fk_order_manager foreign key (managerUserId) references managers(userId),
	constraint fk_order_supplier foreign key (supplierUserId) references suppliers(userId)
	
);

-------------------------

create table storedArticles(

	sArticleId serial primary key,
	quantity integer not null,
	locationId integer not null,
	articleId integer not null,
	constraint fk_storedArt_article foreign key (articleId) references articles(articleId),
	constraint fk_storedArt_location foreign key (locationId) references locations(locationId),
	constraint ck_quantity_gt_0 check (quantity>=0)

);

-------------------------

create table invoicedArticles(
	
	iArticleId serial primary key,
	price integer not null,
	quantity integer not null,
	invoiceId integer not null,
	articleId integer not null,
	constraint fk_invoicedArt_article foreign key (articleId) references articles(articleId),
	constraint fk_invoicedArt_invoice foreign key (invoiceId) references invoices(invoiceId),
	constraint ck_price_gt_0 check (price>0),
	constraint ck_quantity_gt_0 check (quantity>0)

);

-------------------------

create table orderedArticles(

	oArticleId serial primary key,
	price integer,
	quantity integer not null,
	articleStatus varchar(50) not null,
	orderId integer not null,
	locationId integer not null,
	articleId integer not null,
	constraint fk_orderedArt_article foreign key (articleId) references articles(articleId),
	constraint fk_orderedArt_location foreign key (locationId) references locations(locationId),
	constraint fk_orderedArt_order foreign key (orderId) references orders(orderId),
	constraint ck_price_gt_0 check (price>0),
	constraint ck_quantity_gt_0 check (quantity>0)

);

-------------------------

create table questions(

	questionId serial primary key,
	questionText varchar(500) not null,
	dateCreated timestamp default now(),
	workerUserId integer not null,
	managerUserId integer not null,
	constraint fk_question_worker foreign key (workerUserId) references workers(userId),
	constraint fk_question_manager foreign key (managerUserId) references managers(userId)

);

-------------------------

create table answers(

	answerId serial,
	questionId integer,
	answerText varchar(500) not null,
	dateCreated timestamp default now(),
	constraint fk_answer_question foreign key (questionId) references questions(questionId) on delete cascade on update cascade,
	constraint pk_answer primary key(questionId, answerId)

);

-------------------------

create table question_availability_for_storedarticle(

	questionId integer,
	sArticleId integer,
	constraint fk_question_sarticle_question foreign key (questionId) references questions(questionId),
	constraint fk_question_sarticle_sarticle foreign key (sArticleId) references storedArticles(sArticleId)

);

-------------------------

create table article_belongs_to_category(

	articleId integer,
	categoryId integer,
	constraint fk_article_category_article foreign key (articleId) references articles(articleId),
	constraint fk_article_category_category foreign key (categoryId) references categories(categoryId)

);

-------------------------

create table supplier_supplies_category(

	userId integer,
	categoryId integer,
	constraint fk_supplier_category_supplier foreign key (userId) references suppliers(userId),
	constraint fk_supplier_category_category foreign key (categoryId) references categories(categoryId)

);

-------------------------

alter table question_availability_for_storedarticle add constraint pk_question_availability_for_storedarticle primary key (questionId, sArticleId);

-------------------------

alter table article_belongs_to_category add constraint pk_article_belongs_to_category primary key (articleId, categoryId);

-------------------------

alter table supplier_supplies_category add constraint pk_supplier_supplies_category primary key (userId, categoryId);
