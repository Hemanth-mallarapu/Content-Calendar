CREATE TABLE IF NOT EXISTS Content (
    --id INTEGER AUTO_INCREMENT,
    id SERIAL PRIMARY KEY,
    title varchar (255) NOT NULL,
    description text,
    status varchar (20) NOT NULL,
    content_type varchar (50) NOT NULL,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    url varchar (255),
    --primary key (id)
);



