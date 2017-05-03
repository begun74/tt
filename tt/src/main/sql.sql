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

insert into tt_user VALUES(nextval('seq_global'),'order','123');

insert into user_with_role values((select id_user from tt_user where name ='order'),(select id_user_role from tt_user_role where name ='ROLE_ORDERS'))

insert into user_with_role values((select id_user from tt_user where name ='user'),(select id_user_role from tt_user_role where name ='ROLE_USER'))

select u.*,r.name from tt_user u 
	inner join user_with_role ur on u.id_user=ur.tt_user_id
	inner join tt_user_role r on ur.tt_user_role_id= id_user_role;
-- USER, USER_ROLE



ALTER TABLE public.tails DROP CONSTRAINT "idProvider";

ALTER TABLE public.tails
  ADD CONSTRAINT "idProvider" FOREIGN KEY (fk_id_provider)
      REFERENCES public.dir_provider (id_dir_provider) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

select * from dir_nomenclature dn
inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
where dn.code=10002066039

select count(*) from tails;

select * from tails order by create_date, id_tails;

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

delete from statistic_nomencl where id_statistic_nomencl in
(
select sn.id_statistic_nomencl from statistic_nomencl sn 
		inner join dir_nomenclature dn on sn.id_dir_nomenclature = dn.id_dir_nomenclature and sn.id_dir_nomenclature=34303 and date(sn.creation_date)='2017-04-15'
		)

select distinct xxx.* from tails t 
	inner join 
	(
		select dn.*, dp.code as dp_code, dp.name as dp_name from dir_nomenclature dn 
		inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider
		where model like '%0002074803%' or dn.code= 0002074803 or dp.name like '%8 МАРТА%'
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


select count(*) from dir_provider dp
		inner join
		(
			select distinct dp.*, dn.* from dir_nomenclature dn
				inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
				inner join dir_provider dp on dn.fk_id_provider = dp.id_dir_provider and dp.id_dir_provider = 14264
				where t.destruction_date is null 
		)as xxx on xxx.id_dir_provider = dp.id_dir_provider

