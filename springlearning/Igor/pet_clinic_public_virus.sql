create table virus
(
    id   serial not null
        constraint virus_pk
            primary key,
    name varchar(30)
);

alter table virus
    owner to shkar;

INSERT INTO public.virus (id, name) VALUES (1, 'ковид');
INSERT INTO public.virus (id, name) VALUES (2, 'чесотка');
INSERT INTO public.virus (id, name) VALUES (3, 'геморой');
INSERT INTO public.virus (id, name) VALUES (4, 'клещи');
INSERT INTO public.virus (id, name) VALUES (5, 'импотенция');