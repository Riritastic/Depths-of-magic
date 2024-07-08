DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS armor;
DROP TABLE IF EXISTS weapon;
DROP TABLE IF EXISTS monster;
DROP TABLE IF EXISTS hero;

CREATE TABLE hero(
 id INT AUTO_INCREMENT PRIMARY KEY,
 nivel_actual INT NOT NULL,
 vida int not null,
 vida_maxima int not null,
 kills INT NOT NULL DEFAULT 0
);

CREATE TABLE monster(
nombre varchar(15) primary key not null,
vida int not null,
vida_maxima int not null,
daño int not null
);

CREATE TABLE weapon(
nombre varchar(20) PRIMARY KEY not null,
tipo varchar(10) not null,
daño int not null,
rango int not null,
hero_id INT,
 INDEX fk_weapon_hero_idx (hero_id),
    CONSTRAINT fk_weapon_hero
    FOREIGN KEY (hero_id)
    REFERENCES hero(id)
);

CREATE TABLE armor(
nombre varchar(20) primary key not null,
material varchar(15) not null,
resistencia int not null,
hero_id INT,
    INDEX fk_armor_hero_idx (hero_id),
    CONSTRAINT fk_armor_hero
    FOREIGN KEY (hero_id)
    REFERENCES hero(id)
);

CREATE TABLE room(
numero_nivel int primary key not null,
cant_enemigos int not null,
descripcion int not null,
ancho int not null,
alto int not null,
hero_id INT,
 INDEX fk_room_hero_idx (hero_id),
 CONSTRAINT fk_room_hero
 FOREIGN KEY (hero_id)
 REFERENCES hero(id)
);

INSERT INTO hero (nivel_actual, vida, vida_maxima, kills)
VALUES
(1, 200, 200, 0);

INSERT INTO monster (nombre, vida, vida_maxima, daño)
VALUES
('Araña', 70, 70, 20),
('Ghoul', 120, 120, 50),
('militar', 80, 80, 15),
('bruja', 100, 100, 30),
('Goblin',50,50,10),
('Slime',200,200,5);

INSERT INTO weapon (nombre, tipo, daño, rango)
VALUES
('Espada', 'Cuerpo', 40, 5),
('glock 19', 'distancia', 100, 100),
('Daga', 'Cuerpo', 30, 3),
('Hacha', 'Cuerpo', 50, 7);

INSERT INTO armor (nombre, material, resistencia)
VALUES
('yelmo', 'acero', 20),
('Cota de Mithril', 'Cuero', 15),
('Guardia de la Noche', 'diamante', 50),
('Capa', 'Tela', 10);




