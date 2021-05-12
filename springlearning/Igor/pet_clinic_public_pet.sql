create table pet
(
    name      varchar(30) default NULL::character varying,
    id        serial  not null
        constraint pet_pk
            primary key,
    age       integer,
    "isVirus" boolean not null,
    "ownerId" integer
        constraint pet_owner_id_fk
            references owner
            on update cascade on delete cascade
);

alter table pet
    owner to shkar;

INSERT INTO public.pet (name, id, age, "isVirus", "ownerId") VALUES ('Obama', 1, 58, true, 3);
INSERT INTO public.pet (name, id, age, "isVirus", "ownerId") VALUES ('Macron', 2, 40, false, 3);
INSERT INTO public.pet (name, id, age, "isVirus", "ownerId") VALUES ('Poroshenko', 3, 55, true, 2);
INSERT INTO public.pet (name, id, age, "isVirus", "ownerId") VALUES ('Biden', 4, 78, true, 1);