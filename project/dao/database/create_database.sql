CREATE DATABASE IF NOT EXISTS `autoservice` CHARACTER SET UTF8;

CREATE TABLE `autoservice`.`user`
(`id` INT NULL AUTO_INCREMENT,
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(35) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`login` ASC));

CREATE TABLE `autoservice`.`role`
(`id` INT NULL AUTO_INCREMENT,
  `role` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `autoservice`.`userrole`
(`id_user` INT ,
  `id_role` INT,
  CONSTRAINT `userrole_role`
    FOREIGN KEY (`id_role`)
    REFERENCES `autoservice`.`role` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT `userrole_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `autoservice`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
  
  CREATE TABLE `autoservice`.`car`
(`id_car` INT ,
  `id_user` INT,
  `brand` VARCHAR(255) NOT NULL,
  `model` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_car`),
  CONSTRAINT `user_car`
    FOREIGN KEY (`id_user`)
    REFERENCES `autoservice`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
  
   CREATE TABLE `autoservice`.`order`
(`id_user` INT ,
  `id_car` INT,
  `id_bill` INT,
  PRIMARY KEY (`id_bill`),
  CONSTRAINT `order_car`
    FOREIGN KEY (`id_car`)
    REFERENCES `autoservice`.`car` (`id_car`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT `order_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `autoservice`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
    
CREATE TABLE `autoservice`.`service`
(`id_service` INT ,
  `title` VARCHAR(255) NOT NULL,
  `cost` INT,
  `date` DATE,
  `amount` INT,
  PRIMARY KEY (`id_service`));
  
CREATE TABLE `autoservice`.`relation`
(`id_bill` INT ,
`id_service` INT ,
    CONSTRAINT `order_relation`
    FOREIGN KEY (`id_bill`)
    REFERENCES `autoservice`.`order` (`id_bill`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
	CONSTRAINT `relation_service`
    FOREIGN KEY (`id_service`)
    REFERENCES `autoservice`.`service` (`id_service`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);