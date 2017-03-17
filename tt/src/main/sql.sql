INSERT INTO tt.user (id_user,name,password) VALUES(nextval('seq_global'),'bvv','pass');

-- DROP TABLE "dir_gender", "dir_nomencl_group_root", "dir_nomencl_group",  "dir_nomenclature", "dir_provider", "store", "tails", "tt_user" CASCADE;

SELECT table_name
FROM information_schema.tables where table_schema='public'
ORDER BY table_schema,table_name;

SELECT * from tt.user;

SELECT nextval('seq_global');

INSERT INTO dir_gender (id_dir_gender, name) VALUES(nextval('seq_global'),'девичий');


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
