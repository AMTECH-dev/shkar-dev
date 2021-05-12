create table "petVirus"
(
    id        serial not null
        constraint petvirus_pk
            primary key,
    "petId"   integer
        constraint petvirus_pet_id_fk
            references pet
            on update cascade on delete cascade,
    "virusId" integer
        constraint petvirus_virus_id_fk
            references virus
            on update cascade on delete cascade
);

alter table "petVirus"
    owner to shkar;

