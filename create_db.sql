--TODO normalize tables!!
-- authors table
-- publisher table
CREATE TABLE `books` (
  `isbn` char(14) NOT NULL,
  `title` varchar(255) NOT NULL,
  `subtitle` varchar(255) NOT NULL,
  `authors` varchar(255) NOT NULL,
  `published` text NOT NULL,
  `publisher` varchar(255) NOT NULL,
  `pages` integer NOT NULL,
  `description` varchar(255) NOT NULL,
  `instock` integer NOT NULL,
  PRIMARY KEY (`isbn`)
);

-- This table holds atomic terms for our inverted index
CREATE TABLE `terms` (
  `id` integer NOT NULL,
  `term` varchar(255) NOT NULL,
  `frequency` integer NOT NULL,
  PRIMARY KEY (`id`)
);
  
-- This table works as an inverted index (link between terms and which books contain them in title/description)
CREATE TABLE `terms_books` (
  `id` integer NOT NULL,
  `book_id` varchar(255) NOT NULL,
  `term_id` integer NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY(book_id) REFERENCES books(isbn)
  FOREIGN KEY(term_id) REFERENCES terms(id)
);

INSERT INTO books values('978-0134494166', 
						 'Clean Architecture',
						 'Lambdas, Streams, and functional-style programming',
						 'Raoul-Gabriel Urma',
						 '2014-08-28T00:00:00.000Z',
						 'Manning Publications',
						 424,
						 'By applying universal rules of software architecture, you can
dramatically improve developer productivity throughout the life of any software system. Now,
building upon the success of his best-selling books Clean Code and The Clean Coder, legendary
software craftsman Robert C. Martin (\"Uncle Bob\") reveals those rules and helps you apply
them.', 1);
INSERT INTO books values('978-1617292231', 
						 'Grokking Algorithms',
						 'An illustrated guide for programmers and other curious people',
						 'Aditya Bhargava"',
						 '2016-05-01T00:00:00.000Z',
						 'Manning Publications',
						 256,
						 'Grokking Algorithms is a fully illustrated, friendly guide that teaches
you how to apply common algorithms to the practical problems you face every day as a programmer.
You''ll start with sorting and searching and, as you build up your skills in thinking
algorithmically, you''ll tackle more complex concerns such as data compression and artificial
intelligence. Each carefully presented example includes helpful diagrams and fully annotated code
samples in Python.', 1);

-- some terms (TODO add all)
INSERT INTO terms values(0, 'lambdas', 1);
INSERT INTO terms values(1, 'architecture', 2);
INSERT INTO terms values(2, 'algorithms', 3);
INSERT INTO terms values(3, 'python', 1);
INSERT INTO terms values(4, 'functional', 1);

-- inverted index entries that link books with terms
INSERT INTO terms_books values(0, '978-0134494166', 0);
INSERT INTO terms_books values(1, '978-0134494166', 1);
INSERT INTO terms_books values(2, '978-1617292231', 2);
INSERT INTO terms_books values(3, '978-1617292231', 3);
INSERT INTO terms_books values(4, '978-0134494166', 4);
