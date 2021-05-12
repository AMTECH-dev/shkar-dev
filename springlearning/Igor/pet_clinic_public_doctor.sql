create table doctor
(
    id       serial not null
        constraint doctor_pk
            primary key,
    name     varchar(30),
    lastname varchar(30),
    type     varchar(30)
);

alter table doctor
    owner to shkar;

INSERT INTO public.doctor (id, name, lastname, type) VALUES (1, 'Striven', 'Strange', 'surgeon');
INSERT INTO public.doctor (id, name, lastname, type) VALUES (2, 'Natasha', 'Romanov', 'medical sister');
INSERT INTO public.doctor (id, name, lastname, type) VALUES (3, 'Bener', 'Hulk', 'masseur');