CREATE TABLE "user" (
	user_id serial primary key NOT NULL,
	first_name varchar(100) NOT NULL,
	last_name varchar(150) NOT NULL,
	email varchar NOT NULL,
	"password" text NOT NULL,
	usertype varchar(20) NOT NULL,
	created_at timestamp DEFAULT now(),
	updated_at timestamp DEFAULT now(),
	deleted_at timestamp
);

CREATE TABLE "book_title" (
	book_title_id serial primary key NOT NULL,
	"name" varchar(100) NOT NULL,
	author varchar(150) NOT NULL,
	created_at timestamp DEFAULT now(),
	updated_at timestamp DEFAULT now(),
	deleted_at timestamp
);

CREATE TABLE "book_copy" (
    book_copy_id SERIAL PRIMARY KEY NOT NULL,
    book_title_id INT REFERENCES book_title(book_title_id),
    "status" VARCHAR(30) NOT NULL,
	created_at timestamp DEFAULT now(),
	updated_at timestamp DEFAULT now(),
	deleted_at timestamp
);

CREATE TABLE borrow (
    borrow_id SERIAL PRIMARY KEY NOT NULL,
    customer_id INT REFERENCES "user"(user_id),
	employee_id INT REFERENCES "user"(user_id),
    "status" VARCHAR(30) NOT NULL,
	created_at timestamp DEFAULT now(),
	updated_at timestamp DEFAULT now(),
	deleted_at timestamp
);

CREATE TABLE borrow_book (
    borrow_book_id SERIAL PRIMARY KEY NOT NULL,
    borrow_id INT REFERENCES borrow(borrow_id),
    book_copy_id INT REFERENCES book_copy(book_copy_id),
	created_at timestamp DEFAULT now(),
	updated_at timestamp DEFAULT now(),
	deleted_at timestamp
);