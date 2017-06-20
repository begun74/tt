INSERT INTO tt.user (id_user,name,password) VALUES(nextval('seq_global'),'bvv','pass');

-- DROP TABLE "dir_gender", "dir_nomencl_group_root", "dir_nomencl_group",  "dir_nomenclature", "dir_provider", "store", "tails", "tt_user" CASCADE;

SELECT table_name
FROM information_schema.tables where table_schema='public'
ORDER BY table_schema,table_name;

SELECT * from tt.user;

SELECT nextval('seq_global');

INSERT INTO dir_gender (id_dir_gender, name) VALUES(nextval('seq_global'),'девичий');

select * from dir_provider order by name;


-- USER, USER_ROLE
insert into tt_user_role VALUES(nextval('seq_global'),'ROLE_ADMIN');
insert into tt_user_role VALUES(nextval('seq_global'),'ROLE_USER');
insert into tt_user_role VALUES(nextval('seq_global'),'ROLE_ORDERS');

insert into tt_user VALUES(nextval('seq_global'),'трикотаж','трикотаж033');
insert into user_with_role values((select id_user from tt_user where name ='трикотаж'),(select id_user_role from tt_user_role where name ='ROLE_USER'))

insert into user_with_role values((select id_user from tt_user where name ='order'),(select id_user_role from tt_user_role where name ='ROLE_ORDERS'))



select u.*,r.name from tt_user u 
	inner join user_with_role ur on u.id_user=ur.tt_user_id
	inner join tt_user_role r on ur.tt_user_role_id= id_user_role; 
-- USER, USER_ROLE



ALTER TABLE public.tails DROP CONSTRAINT "idProvider";

ALTER TABLE public.tails
  ADD CONSTRAINT "idProvider" FOREIGN KEY (fk_id_provider)
      REFERENCES public.dir_provider (id_dir_provider) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

select distinct dn.* from dir_nomenclature dn
inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
where dn.code=10002076656

select count(*) from tails;

select * from tails t
	inner join dir_nomenclature dn on dn.id_dir_nomenclature=t.fk_id_nomenclature
	where id_tails=95849 order by create_date, id_tails;

update tails set destruction_date = now() where destruction_date is null;

update tails set destruction_date = null where id_tails >= 17746;


delete from tails where id_tails >= 18834;


select distinct o.* from orders o
	inner join order_items oi on o.id_orders=oi.fk_id_orders
	where oi.destruction_date is null


update order_items set destruction_date = null where id_order_items=16938


-- Выборка популярной номенклатуры за всё время с учётом наличием номенклатуры в остатках --
--

select distinct xxx.count_sn , dn.* 
	from dir_nomenclature dn
	inner join 
	(
		select count(sn.id_dir_nomenclature) as count_sn, sn.id_dir_nomenclature as id_sn from statistic_nomencl sn 
		inner join dir_nomenclature dn on sn.id_dir_nomenclature = dn.id_dir_nomenclature
		group by sn.id_dir_nomenclature
		order by 1 desc
	) as xxx on xxx.id_sn = dn.id_dir_nomenclature
	inner join 
	(
		select t.fk_id_nomenclature as tail_dn from tails t where t.destruction_date is null
	)as yyy on yyy.tail_dn = dn.id_dir_nomenclature
order by 1 desc
limit 4

--			
-- Выборка популярной номенклатуры за всё время с учётом наличием номенклатуры в остатках --


-- Для проверки выборки популярной номенклатуры --
--
select t.*
	from tails t 
	inner join dir_nomenclature dn on t.fk_id_nomenclature = dn.id_dir_nomenclature
	where  t.destruction_date is null and dn.code = 10002077692

update tails t set destruction_date = null where t.fk_id_nomenclature = 34231
--
-- Для проверки выборки популярной номенклатуры --

select distinct xxx.count_sn , dn.* \
			from dir_nomenclature dn \
					inner join \
					(\
						select count(sn.id_dir_nomenclature) as count_sn, sn.id_dir_nomenclature as id_sn from statistic_nomencl sn \
						inner join dir_nomenclature dn on sn.id_dir_nomenclature = dn.id_dir_nomenclature \
						group by sn.id_dir_nomenclature \
					) as xxx on xxx.id_sn = dn.id_dir_nomenclature \
					inner join \
					( \
						select t.fk_id_nomenclature as tail_dn from tails t where t.destruction_date is null \
					)as yyy on yyy.tail_dn = dn.id_dir_nomenclature \
				order by 1 desc \
				limit 4

select distinct  dp.name, dn.* 
			from dir_nomenclature dn 
			inner join 
			( 
					select t.fk_id_nomenclature as tail_dn from tails t where t.destruction_date is null
			)as yyy on yyy.tail_dn = dn.id_dir_nomenclature 
			inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider where dp.id_dir_provider in (select  dn.fk_id_provider from dir_nomenclature dn where dn.id_dir_nomenclature = 20295)
			order by 1 desc 
			OFFSET random()*5
			limit 10
select xxx.name, dn.* from dir_nomenclature dn 
		inner join 
		(
		select distinct dp.name, dn.id_dir_nomenclature 
			from dir_nomenclature dn 
			inner join 
			( 
					select t.fk_id_nomenclature as tail_dn from tails t where t.destruction_date is null
			)as yyy on yyy.tail_dn = dn.id_dir_nomenclature 	
			inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider where dp.id_dir_provider in (select  dn.fk_id_provider from dir_nomenclature dn where dn.id_dir_nomenclature = 20295)
			OFFSET random()*11
		)as xxx on xxx.id_dir_nomenclature = dn.id_dir_nomenclature 
		order by code
OFFSET random()*11
		

SELECT random() FROM generate_series(1, 10);

			select  dn.fk_id_provider from dir_nomenclature dn where dn.id_dir_nomenclature = 20295

select distinct dn.* 
		from dir_provider dp 
		inner join


delete from statistic_nomencl where id_statistic_nomencl in
(
select sn.id_statistic_nomencl from statistic_nomencl sn 
		inner join dir_nomenclature dn on sn.id_dir_nomenclature = dn.id_dir_nomenclature and sn.id_dir_nomenclature=34303 and date(sn.creation_date)='2017-04-15'
		)

select distinct xxx.*, t.firstPrice, t.opt_price, t.rozn_price from tails t 
	inner join 
	(
		select dn.*, dp.code as dp_code, dp.name as dp_name from dir_nomenclature dn 
		inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider
		where model like '%хлопок%' or dn.code= 0002074803 or dp.name like '%хлопок%' or dn.composition like '%хлопок%'
	) as xxx on xxx.id_dir_nomenclature = t.fk_id_nomenclature
	where t.destruction_date is null

select * from dir_nomencl_group dng
	inner join
	(
		select distinct dng.id_dir_nomencl_group from tails t
		inner join dir_nomenclature dn on dn.id_dir_nomenclature=t.fk_id_nomenclature
		inner join dir_nomencl_group dng on dng.id_dir_nomencl_group=dn.fk_id_dir_nomencl_group
		where t.destruction_date is null
	)as xxx on dng.id_dir_nomencl_group=xxx.id_dir_nomencl_group
	order by name

select * from dir_provider dp
	inner join
	(
		select distinct dn.fk_id_provider from tails t
		inner join dir_nomenclature dn on dn.id_dir_nomenclature=t.fk_id_nomenclature
		where t.destruction_date is null
	)as xxx on xxx.fk_id_provider=dp.id_dir_provider
	order by name

select * from dir_nomencl_group dng 
					inner join 
					( 
						select distinct dng.id_dir_nomencl_group from tails t 
						inner join dir_nomenclature dn on dn.id_dir_nomenclature=t.fk_id_nomenclature 
						inner join dir_nomencl_group dng on dng.id_dir_nomencl_group=dn.fk_id_dir_nomencl_group 
						where t.destruction_date is null 
					)as xxx on dng.id_dir_nomencl_group=xxx.id_dir_nomencl_group 
				order by name

select * from dir_nomencl_group_root dngr 
					inner join 
					( 
						select distinct dngr.id_dir_nomencl_group_root from tails t 
						inner join dir_nomenclature dn on dn.id_dir_nomenclature = t.fk_id_nomenclature 
						inner join dir_nomencl_group dng on dng.id_dir_nomencl_group = dn.fk_id_dir_nomencl_group
						inner join dir_nomencl_group_root dngr on dngr.id_dir_nomencl_group_root = dng.fk_dir_nomencl_group_root
						where t.destruction_date is null 
					)as xxx on dngr.id_dir_nomencl_group_root = xxx.id_dir_nomencl_group_root 
				order by name


select * from dir_gender
	group by id_dir_gender



select count(*) from dir_gender dg
		inner join
		(
			select distinct dg.*, dn.* from dir_nomenclature dn
				inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
				inner join dir_gender dg on dn.fk_dir_gender = dg.id_dir_gender and dg.id_dir_gender=58
				where t.destruction_date is null 
		)as xxx on xxx.id_dir_gender = dg.id_dir_gender


select count(*) from dir_nomencl_group dng
		inner join
		(
			select distinct dng.*, dn.* from dir_nomenclature dn
				inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
				inner join dir_nomencl_group dng on dn.fk_id_dir_nomencl_group = dng.id_dir_nomencl_group and dng.id_dir_nomencl_group = 2760
				where t.destruction_date is null 
		)as xxx on xxx.id_dir_nomencl_group = dng.id_dir_nomencl_group


select count(*) from dir_nomencl_group_root dngr
		inner join
		(
			select distinct dngr.*, dn.* from dir_nomenclature dn
				inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
				inner join dir_nomencl_group dng on dn.fk_id_dir_nomencl_group = dng.id_dir_nomencl_group
				inner join dir_nomencl_group_root dngr on dng.fk_dir_nomencl_group_root = dngr.id_dir_nomencl_group_root and dngr.id_dir_nomencl_group_root = 1698
				where t.destruction_date is null 
		)as xxx on xxx.id_dir_nomencl_group_root = dngr.id_dir_nomencl_group_root


select count(*) from dir_provider dp
		inner join
		(
			select distinct dp.*, dn.* from dir_nomenclature dn
				inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
				inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider and dp.id_dir_provider = 14264
				where t.destruction_date is null 
		)as xxx on xxx.id_dir_provider = dp.id_dir_provider



-- ============  search by ... ============= --


select distinct dn.*, t.firstPrice, dngr.sorting, dp.sorting from dir_nomenclature dn
		inner join dir_gender dg on dg.id_dir_gender = dn.fk_dir_gender 
		inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider
		inner join dir_nomencl_group dng on dn.fk_id_dir_nomencl_group = dng.id_dir_nomencl_group 
		inner join dir_nomencl_group_root dngr on dng.fk_dir_nomencl_group_root = dngr.id_dir_nomencl_group_root
		inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
		where t.destruction_date is null 
		order by dp.sorting, dngr.sorting, dn.name

		and dg.id_dir_gender in (58,56)
		and dp.id_dir_provider in(14264)
		
select nextval('seq_statistic')

select * from dir_nomenclature dn where composition is not null
	where code = 10002076234
	and composition is not null



--====================== OAuth2 ==============

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);	


drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);


drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BYTEA,
  refresh_token VARCHAR(255)
);


--

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BYTEA,
  authentication BYTEA
);
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), 
  authentication BYTEA
);
 
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);
 
drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

select distinct dn.*, t.firstPrice , dngr.sorting, t.opt_price, t.rozn_price, dp.sorting   
	from dir_nomenclature dn 
	inner join dir_gender dg on dg.id_dir_gender = dn.fk_dir_gender 
	inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider 
	inner join dir_nomencl_group dng on dn.fk_id_dir_nomencl_group = dng.id_dir_nomencl_group 
	inner join dir_nomencl_group_root dngr on dng.fk_dir_nomencl_group_root = dngr.id_dir_nomencl_group_root 
	inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature 
	where t.destruction_date is null and dg.id_dir_gender in (56) 
	and dngr.id_dir_nomencl_group_root in (8158) 
	order by t.firstPrice asc , dp.sorting, dngr.sorting