--- view za pregled na sostojbata na site artikli na bilo koja lokacija

create or replace view articles_report as 
	select a.articleid, a.articlename, a.description, a.imageurl, s.quantity, l.locationname, l.locationid, s.sarticleid from articles a
	left join storedarticles s on s.articleid=a.articleid
	left join locations l on l.locationid=s.locationid

--- view za pregled na koi kategorii pripagja sekoj artikl
	
create or replace view articles_and_cats_report as 
	select c.categoryname, c.categoryid, a.articleid, a.articlename from categories c
	left join article_belongs_to_category abtc on abtc.categoryid=c.categoryid
	left join articles a on a.articleid=abtc.articleid

-- view za pregled na artiklite koi moze da gi dostavi sekoj od dobavuvacite 

create or replace view supplier_supplies_article as 
	select articlename, s.userid, a.articleid from articles a
	left join article_belongs_to_category abtc on abtc.articleid=a.articleid
	left join supplier_supplies_category ssc on ssc.categoryid=abtc.categoryid
	left join suppliers s on s.userid=ssc.userid

-- view za pregled na artiklite i nivnata dostapnosta na site lokacii

create or replace view articles_at_location as
	select a.articleid, a.description, a.articlename, a.imageurl, a.maxquantityperlocation, s.sarticleid, s.quantity, s.locationid, l.locationname from articles a 
	left join storedarticles s on s.articleid=a.articleid
	left join locations l on l.locationid=s.locationid

-- view za pregled na artiklite vo sekoja narachka

create or replace view  ordered_articles_report as
	select a.articleid, a.description, a.articlename, a.imageurl, a.maxquantityperlocation, o.oarticleid, o.quantity, o.locationid, o.price, o.articlestatus, o.orderid, s.quantity as storedQuantity from orderedarticles o
	left join articles a on o.articleid=a.articleid
	left join storedarticles s on s.locationid=o.locationid and s.articleid=a.articleid
	
-- view za pregled na artiklite vo sekoja faktura

create or replace view invoiced_articles_report as
	select i.iarticleid, i.invoiceid, i.articleid, i.price, i.quantity,  a.description, a.articlename, a.imageurl, a.maxquantityperlocation  from invoicedarticles i
	left join articles a on a.articleid=i.articleid
	
-- view za pregled na detalni informacii za narachkite

create or replace view orders_report as
	select o.orderid, o.supplierremark, o.managerremark, o.status, o.datecreated, o.priority, o.supplieruserid, s.supplierinfo, o.manageruserid from orders o
	left join suppliers s on s.userid=o.supplieruserid
	
-- view za pregled na detalni informacii za dobavuvacite
	
create or replace view suppliers_report as select u.userid,
    u.username,
    u.firstname,
    u.lastname,
    s.street,
    s.streetnumber,
    s.city,
    s.phone,
    s.supplierinfo
   from users u
     join suppliers s on s.userid = u.userid;
    
-- funkcija za popolnuvanje na razlicnite magacini
 
CREATE OR REPLACE FUNCTION project.populatestorage()
 RETURNS trigger
 LANGUAGE plpgsql
AS $function$
	BEGIN
		insert into project.storedarticles(quantity,locationid,articleid) select 0 as quantity, l.locationid, new.articleid as articleid from project.locations l;
		return new;
	END;
$function$
;

-- triger za avtomatsko popolnuvanje na razlicnite magacini

CREATE TRIGGER populateStorage
    after insert on public.articles
    for each row
    execute procedure public.populatestoragefunc();

     