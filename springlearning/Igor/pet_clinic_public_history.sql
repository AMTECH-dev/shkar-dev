create table history
(
    id         serial not null
        constraint history_pk
            primary key,
    "ownerId"  integer
        constraint history_owner_id_fk
            references owner
            on update cascade on delete cascade,
    "doctorId" integer
        constraint history_doctor_id_fk
            references doctor
            on update cascade on delete cascade
);

alter table history
    owner to shkar;

