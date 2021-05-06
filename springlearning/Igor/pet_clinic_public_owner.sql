create table owner
(
    id    serial not null
        constraint owner_pk
            primary key,
    name  varchar(30) default NULL::character varying,
    age   integer,
    phone bigint
        constraint owner_phone_key
            unique
);

alter table owner
    owner to shkar;

INSERT INTO public.owner (id, name, age, phone) VALUES (1, 'Parker', 16, 91112312323);
INSERT INTO public.owner (id, name, age, phone) VALUES (2, 'Tor', 37, null);
INSERT INTO public.owner (id, name, age, phone) VALUES (3, 'Loki', 29, null);