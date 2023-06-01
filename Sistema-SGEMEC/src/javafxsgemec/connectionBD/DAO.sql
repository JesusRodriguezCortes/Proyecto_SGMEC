/*///////////////////////////////////////////GESTIÓN DISPOSITIVOS///////////////////////////////////////////*/
/*-------------Crear dispositivo-------------*/
INSERT INTO dispositivo (marca, modelo, usuarioDispositivo, passwordDispositivo, 
errorDispositivo, imagenDispositivo, idEstado, idCliente) 
VALUES(?, ?, ?, ?, ?, ?, ?, ?);

/*-------------Mostrar todos/un dispositivo-------------*/
SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, 
errorDispositivo, imagenDispositivo, dispositivo.idEstado, estadodispositivo.estado,
dispositivo.idCliente,
CONCAT(cliente.nombre,' ',cliente.apellidoPaterno,' ',cliente.apellidoMaterno) AS
nombreUsuario 
FROM dispositivo LEFT JOIN cliente ON dispositivo.idCliente = cliente.idCliente 
LEFT JOIN estadodispositivo ON dispositivo.idEstado = estadodispositivo.idEstado 
WHERE idDispositivo = 1;

/*-------------Mostrar dispositivos de un cliente-------------*/
USE SGEMEC;
SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, 
errorDispositivo, imagenDispositivo, caracteristicas, dispositivo.idCliente, 
CONCAT(cliente.nombre,' ',cliente.apellidoPaterno,' ',cliente.apellidoMaterno) AS 
nombreCliente, dispositivo.idEstadoDispositivo, EstadoDispositivo.nombreEstadoDispositivo 
FROM dispositivo RIGHT JOIN cliente ON dispositivo.idCliente = cliente.idCliente 
LEFT JOIN estadodispositivo ON dispositivo.idEstadoDispositivo = estadodispositivo.idEstadoDispositivo 
WHERE Cliente.idCliente = 1;

SELECT cliente.idUsuario, usuario.nombreUsuario, cliente.idCliente, cliente.nombre
FROM usuario RIGHT JOIN cliente ON cliente.idUsuario = usuario.idUsuario 
WHERE cliente.idUsuario = 1;
SELECT cliente.idUsuario, cliente.idCliente 
FROM usuario RIGHT JOIN cliente ON cliente.idUsuario = usuario.idUsuario 
WHERE cliente.idUsuario = 1;
SELECT cliente.idCliente AS idCliente
FROM usuario RIGHT JOIN cliente ON cliente.idUsuario = usuario.idUsuario 
WHERE usuario.idUsuario = 1;
SELECT cliente.idCliente 
FROM usuario,cliente 
where cliente.idUsuario=usuario.idUsuario and cliente.idUsuario=1;
/*-------------Actualizar datos de un dispositivo-------------*/
UPDATE dispositivo SET marca = ?, modelo = ?, usuarioDispositivo = ?, 
passwordDispositivo = ?, errorDispositivo = ?, imagenDispositivo = ?, 
idEstado = ?, idCliente = ? WHERE idDispositivo = ?;

/*-------------Eliminar dispositivo-------------*/
DELETE FROM dispositivo WHERE idDispositivo = ?;

/*///////////////////////////////////////////GESTIÓN DE USUARIOS///////////////////////////////////////////*/
SELECT * FROM Usuario;

SELECT idUsuario, nombreUsuario, password, roles.nivelAcceso AS nivelDeAcceso 
FROM Usuario 
INNER JOIN roles ON usuario.idRoles = roles.idRoles 
WHERE nombreUsuario = 'cliente' AND password = 'cliente';

SELECT idCliente, nombre, apellidoPaterno, apellidoMaterno, telefono, correoElectronico, 
cliente.idUsuario, usuario.nombreUsuario
FROM cliente LEFT JOIN usuario ON cliente.idUsuario = usuario.idUsuario 
WHERE usuario.idUsuario = 1;
SHOW TABLES;

SELECT *
FROM usuario RIGHT JOIN cliente ON cliente.idUsuario = usuario.idUsuario 
WHERE usuario.idUsuario = 1;
SELECT cliente.idCliente 
FROM usuario RIGHT JOIN cliente ON cliente.idUsuario = usuario.idUsuario 
WHERE usuario.idUsuario = 1;

SELECT idUsuario, nombreUsuario, password, rol.nivelAcceso AS nivelDeAcceso 
FROM Usuario 
INNER JOIN rol ON usuario.idRol = rol.idRol 
WHERE nombreUsuario = 'cliente' AND password = 'cliente'; 
/*///////////////////////////////////////////DIRECCIONES///////////////////////////////////////////*/
SELECT DISTINCT(municipio) FROM DireccionesEmpresa WHERE estado = 'Nuevo León';

SELECT Estado.idEstado, Estado.nombreEstado, Municipio.idMunicipio, Municipio.nombreMunicipio, 
Colonia.idColonia, Colonia.nombreColonia, CodigoPostal.idCodigoPostal, CodigoPostal.codigoPostal, 
Direccion.idDireccion, Direccion.calle, Direccion.numeroExt, Direccion.numeroInt 
FROM Estado LEFT JOIN Municipio ON Estado.idEstado = Municipio.idEstado
LEFT JOIN CodigoPostal ON Municipio.idMunicipio = CodigoPostal.idMunicipio 
LEFT JOIN Colonia ON CodigoPostal.idCodigoPostal = Colonia.idCodigoPostal 
LEFT JOIN Direccion ON Colonia.idDireccion = Direccion.idDireccion 
RIGHT JOIN Cliente ON Cliente.idDireccion = Direccion.idDireccion 
WHERE Cliente.idCliente = 1;

SELECT idEstado, NombreEstado FROM Estado;
SELECT idMunicipio, nombreMunicipio FROM Municipio 
RIGHT JOIN Estado ON Municipio.idEstado = Estado.idEstado 
WHERE Estado.idEstado = 1;
SELECT idCodigoPostal, codigoPostal FROM CodigoPostal 
RIGHT JOIN Municipio ON CodigoPostal.idMunicipio = Municipio.idMunicipio  
WHERE Municipio.idMunicipio = 1;
SELECT idColonia, nombreColonia FROM Colonia 
RIGHT JOIN CodigoPostal ON Colonia.idCodigoPostal = CodigoPostal.idCodigoPostal 
WHERE CodigoPostal.idCodigoPostal = 1;