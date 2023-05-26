CREATE DATABASE SGMEC;
USE SGMEC;
CREATE USER 'AdminSGMEC'@'localhost' IDENTIFIED BY 'AdminSGMEC';
GRANT ALL PRIVILEGES ON SGMEC TO 'AdminSGMEC'@'localhost';
show tables;
CREATE TABLE Roles(
	idRoles int primary key not null auto_increment,
    nivelAcceso varchar(50)
);
describe Roles;

CREATE TABLE Usuario(
	idUsuario int primary key not null auto_increment,
    nombreUsuario varchar(25) not null,
    password varchar(50),
    idRoles int,
    foreign key (idRoles) references Roles(idRoles)
);
describe Usuario;

CREATE TABLE Cliente (
	idCliente int primary key not null auto_increment,
    nombre varchar(50) not null,
    apellidoPaterno varchar(50) not null,
    direccion varchar(50) not null,
    telefono varchar(50) not null,
    correoElectronico varchar(50) not null,
    idUsuario int,
    foreign key(idUsuario) references Usuario(idUsuario)
);

CREATe TABLE Dispositivo(
	idDispositivo int primary key not null auto_increment,
    marca varchar(50),
    modelo varchar(50),
    usuarioDispositivo varchar(50),
    passwordDispositivo varchar(50),
    errorDispositivo varchar(250),
    estado varchar(50),
    imagenDispositivo blob,
    idCliente int,
    foreign key(idCliente) references Cliente(idCliente)
);

CREATE TABLE Servicio(
	idTipoServicio int primary key not null auto_increment,
    nombre varchar(50)
);

CREATE TABLE Refaccion(
	idRefaccion int primary key not null auto_increment,
    nombre varchar(50),
    tipoRefaccion varchar(50),
    pzasDisponibles int,
    precioCompra float,
    precioVenta float,
    idProveedor int,
    foreign key(idProveedor) references Proveedor(idProveedor)
);

CREATE TABLE Proveedor(
	idProveedor int primary key not null auto_increment,
    nombre varchar(50),
    correoElectronico varchar(50),
    telefono varchar(50)
);

CREATE TABLE RefaccionesCompradas(
	idPedidoRefacciones int,
    idRefaccion int,
    foreign key(idPedidoRefacciones) references PedidoRefacciones(idPedidoRefacciones),
    foreign key(idRefaccion) references Refaccion(idRefaccion)
);

CREATE TABLE PedidoRefacciones(
	idPedidoRefacciones int primary key not null auto_increment,
    fechaPedido datetime,
    numeroPedido varchar(50),
    totalPedido float
);


CREATE TABLE Diagnostico(
	idDiagnostico int primary key not null auto_increment,
    cotizacion float,
    fechaInicio datetime,
    fechaFin datetime,
    resultadoDiagnostico varchar(250),
    idDispositivo int,
    idServicio int,
    idRefaccion int,
    foreign key(idDispositivo) references Dispositivo(idDispositivo),
    foreign key(idServicio) references Servicio(idServicio),
    foreign key(idRefaccion) references Refaccion(idRefaccion)
);





