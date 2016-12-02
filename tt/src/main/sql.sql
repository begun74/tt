INSERT INTO tt.user (id_user,name,password) VALUES(nextval('seq_global'),'bvv','pass');

-- DROP TABLE "dir_gender", "dir_nomencl_group", "dir_nomencl_group_root", "dir_nomenclature", "dir_provider", "store", "tails", "tt_user";

SELECT table_name
FROM information_schema.tables where table_schema='public'
ORDER BY table_schema,table_name;

SELECT * from tt.user;

SELECT nextval('seq_global');

INSERT INTO dir_gender (id_dir_gender, gender) VALUES(nextval('seq_global'),'женский');


ALTER TABLE public.tails DROP CONSTRAINT "idProvider";

ALTER TABLE public.tails
  ADD CONSTRAINT "idProvider" FOREIGN KEY (fk_id_provider)
      REFERENCES public.dir_provider (id_dir_provider) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

select * from dir_nomenclature dn
inner join tails t on dn.id_dir_nomenclature=t.fk_id_nomenclature
where dn.code=10002066039

update tails  set destruction_date = now()

select distinct t.* from tails t
inner join dir_provider dp on dp.id_dir_provider=t.fk_id_provider
inner join dir_nomenclature dn on dn.id_dir_nomenclature=t.fk_id_nomenclature
inner join dir_gender dg on dg.id_dir_gender=dn.fk_dir_gender and dg.id_dir_gender in (58,56)
where t.destruction_date is  null;