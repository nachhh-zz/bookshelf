CREATE TABLE `books` (
  `isbn` char(14) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`isbn`)
);

INSERT INTO books values('978-0134494166', 'Clean Architecture', 'By applying universal rules of software architecture, you can
dramatically improve developer productivity throughout the life of any software system. Now,
building upon the success of his best-selling books Clean Code and The Clean Coder, legendary
software craftsman Robert C. Martin (\"Uncle Bob\") reveals those rules and helps you apply
them.');
INSERT INTO books values('978-1617292231', 'Grokking Algorithms"', 'Grokking Algorithms is a fully illustrated, friendly guide that teaches
you how to apply common algorithms to the practical problems you face every day as a programmer.
You''ll start with sorting and searching and, as you build up your skills in thinking
algorithmically, you''ll tackle more complex concerns such as data compression and artificial
intelligence. Each carefully presented example includes helpful diagrams and fully annotated code
samples in Python.');