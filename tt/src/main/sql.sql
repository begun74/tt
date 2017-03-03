INSERT INTO tt.user (id_user,name,password) VALUES(nextval('seq_global'),'bvv','pass');

-- DROP TABLE "dir_gender", "dir_nomencl_group_root", "dir_nomencl_group",  "dir_nomenclature", "dir_provider", "store", "tails", "tt_user" CASCADE;

SELECT table_name
FROM information_schema.tables where table_schema='public'
ORDER BY table_schema,table_name;

SELECT * from tt.user;

SELECT nextval('seq_global');

INSERT INTO dir_gender (id_dir_gender, name) VALUES(nextval('seq_global'),'�������');

insert into tt_user_role VALUES(nextval('seq_global'),'ROLE_ADMIN');
insert into tt_user_role VALUES(nextval('seq_global'),'ROLE_USER');
insert into tt_user_role VALUES(nextval('seq_global'),'ROLE_ORDERS');

insert into tt_user VALUES(nextval('seq_global'),'bvv','123');

insert into user_with_role values((select id_user from tt_user where name ='bvv'),(select id_user_role from tt_user_role where name ='ROLE_ORDERS'))


ALTER TABLE public.tails DROP CONSTRAINT "idProvider";

ALTER TABLE public.tails
  ADD CONSTRAINT "idProvider" FOREIGN KEY (fk_id_provider)
      REFERENCES public.dir_provider (id_dir_provider) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

select * from dir_nomenclature dn
inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
where dn.code=10002066039

select count(*) from tails;

select * from tails order by fk_id_nomenclature;

update tails set destruction_date = null;

select distinct o.* from orders o
	inner join order_items oi on o.id_orders=oi.fk_id_orders
	where oi.destruction_date is null


update order_items set destruction_date = null where id_order_items=16938
