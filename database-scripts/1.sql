# create database aswit;
# use aswit;

create table user(
    id int auto_increment primary key,
    login varchar(50),
    password varchar(50));


create table podcast(
    id int auto_increment primary key,
    title varchar(500) ,
    url varchar(500),
    season varchar(3)
);


alter table podcast add column episode varchar(3);
alter table podcast add column img_link varchar(200);
alter table podcast add column description varchar(5000);


create table dictionary(
    id int auto_increment primary key,
    code varchar(100) not null,
    value varchar(500) not null
);

create table document(
    id int auto_increment primary key,
    document_type int,
    document blob not null
);

create table podcast_document(
    id int auto_increment primary key,
    podcast_id int not null,
    document_id int not null
);
ALTER TABLE document ADD CONSTRAINT fk_document_dictionary_id
    FOREIGN KEY (document_type) REFERENCES dictionary (id);

ALTER TABLE podcast_document ADD CONSTRAINT fk_podcast_document_document_id
    FOREIGN KEY (document_id) REFERENCES document (id);
ALTER TABLE podcast_document ADD CONSTRAINT fk_podcast_document_podcast_id
    FOREIGN KEY (podcast_id) REFERENCES podcast (id);



alter table podcast_document add column name varchar(200);
alter table document add column name varchar(200);

create table newsletter_email(
    id int auto_increment primary key,
    email varchar(200) ,
    active int,
    create_date datetime
);
