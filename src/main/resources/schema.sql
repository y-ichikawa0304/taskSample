create table if not exists tasklist (
    id varchar(8) primary key,
    task varchar(256),
    deadline varchar(10),
    done boolean
);

create table if not exists accounts (
    id int not null auto_increment primary key,
    accountID varchar(30),
    password varchar(30),
    email varchar(50)
);