create table if not exists Product (
          productId INT PRIMARY KEY AUTO_INCREMENT,
          productName varchar(255),
          price varchar(255)
);

create table if not exists Review (
          reviewId INT PRIMARY KEY AUTO_INCREMENT,
          reviewContent varchar(255),
          rating INT,
          productid INT,
          Foreign Key(productid) REFERENCES Product(productId)
);

