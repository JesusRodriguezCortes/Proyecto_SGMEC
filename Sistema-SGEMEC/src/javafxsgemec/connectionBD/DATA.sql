use sgemec;
/*---------------ROLES---------------*/
INSERT INTO roles (nivelAcceso) VALUES ('cliente');
INSERT INTO roles (nivelAcceso) VALUES ('administrador');
INSERT INTO roles (nivelAcceso) VALUES ('encargado');
SELECT * FROM roles;

/*---------------USUARIOS---------------*/
INSERT INTO usuario (nombreUsuario, password, idRoles) VALUES ('cliente', 'cliente', 1);
INSERT INTO usuario (nombreUsuario, password, idRoles) VALUES ('admin', 'admin', 2);
INSERT INTO usuario (nombreUsuario, password, idRoles) VALUES ('encargado', 'encargado', 3);
INSERT INTO usuario (nombreUsuario, password, idRoles) VALUES ('cliente1', 'cliente1', 1);
INSERT INTO usuario (nombreUsuario, password, idRoles) VALUES ('admin1', 'admin1', 2);
INSERT INTO usuario (nombreUsuario, password, idRoles) VALUES ('encargado1', 'encargado1', 3);
SELECT * FROM usuario LEFT JOIN roles ON usuario.idRoles = roles.idRoles;

/*---------------CLIENTES---------------*/
INSERT INTO cliente (nombre, apellidoPaterno, apellidoMaterno, direccion, 
telefono, correoElectronico, idUsuario) 
VALUES ('Rodolfo','Fdez','Rguez','Mi casa','2281234567','correorodolfo@correo.com',1);
INSERT INTO cliente (nombre, apellidoPaterno, apellidoMaterno, direccion, 
telefono, correoElectronico, idUsuario) 
VALUES ('Cesar','Basilio','Gómez','Mi casa','2281234568','correocesar@correo.com',4);
SELECT * FROM cliente LEFT JOIN usuario ON cliente.idUsuario = usuario.idUsuario;

/*---------------DISPOSITIVOS---------------*/
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
estado, imagenDispositivo, idCliente) 
VALUES ('Hp', 'Pavilion', 'Viernes', '123456', 'No sirve la pantalla','estado', NULL, 1);
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
estado, imagenDispositivo, idCliente) 
VALUES ('Lenovo', '14IML05', 'Rodolfo Fdez', '123456','No sirve la batería','estado', NULL, 1);
    
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
estado, imagenDispositivo, idCliente) 
VALUES ('Acer', 'coreI5', 'CesarBas', '123456', 'Falla teclado','estado', NULL, 2);
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, errorDispositivo,
estado, imagenDispositivo, idCliente) 
VALUES ('Patito', 'Apache', 'CesarCUM', '123456','No prende','estado', NULL, 2);
            