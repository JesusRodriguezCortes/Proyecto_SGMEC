CREATE DATABASE SGMECDB;
USE SGMECDB;
SHOW TABLES;
GRANT ALL PRIVILEGES ON SGMECDB.* TO 'AdminSGMEC'@'localhost';

CREATE TABLE Rol(
	idRol int primary key not null auto_increment,
    nivelAcceso nvarchar(50) not null
);


CREATE TABLE Usuario(
	idUsuario int primary key not null auto_increment,
    nombreUsuario nvarchar(50) not null,
    password nvarchar(50) not null,
    idRol int,
    foreign key (idRol) references Rol(idRol)
);


CREATE TABLE Direccion(
	idDireccion int primary key not null auto_increment,
    calle nvarchar(45),
    numeroExt nvarchar(45),
    numeroInt nvarchar(45)
);


CREATE TABLE Estado(
	idEstado int primary key not null auto_increment,
    nombreEstado nvarchar(45)
);


CREATE TABLE Municipio(
	idMunicipio int primary key not null auto_increment,
    nombreMunicipio nvarchar(45),
    idEstado int,
    foreign key(idEstado) references Estado(idEstado)
);


CREATE TABLE CodigoPostal(
	idCodigoPostal int primary key not null auto_increment,
    codigoPostal nvarchar(15),
    idMunicipio int,
    foreign key(idMunicipio) references Municipio(idMunicipio)
);



CREATE TABLE Colonia(
	idColonia int primary key not null auto_increment,
    nombreColonia nvarchar(45),
    idCodigoPostal int, 
    idDireccion int,
    foreign key(idCodigoPostal) references CodigoPostal(idCodigoPostal),
    foreign key(idDireccion) references Direccion(idDireccion)
);



CREATE TABLE Cliente (
	idCliente int primary key not null auto_increment,
    nombre nvarchar(50) not null,
    apellidoPaterno nvarchar(50) not null,
    apellidoMaterno nvarchar(50) not null,
    telefono nvarchar(50) not null,
    correoElectronico nvarchar(50) not null,
    idUsuario int,
    idDireccion int,
    foreign key(idUsuario) references Usuario(idUsuario),
    foreign key(idDireccion) references Direccion(idDireccion)
);


CREATE TABLE EstadoDispositivo(
	idEstadoDispositivo int primary key not null auto_increment,
    nombreEstadoDispositivo nvarchar(15)
);


CREATe TABLE Dispositivo(
	idDispositivo int primary key not null auto_increment,
    marca nvarchar(50),
    modelo nvarchar(50),
    usuarioDispositivo nvarchar(50),
    passwordDispositivo nvarchar(50),
    errorDispositivo nvarchar(250),
    imagenDispositivo blob,
    caracteristicas text,
    idCliente int,
    idEstadoDispositivo int,
    foreign key(idCliente) references Cliente(idCliente),
    foreign key(idEstadoDispositivo) references EstadoDispositivo(idEstadoDispositivo)
);


CREATE TABLE Servicio(
	idServicio int primary key not null auto_increment,
    nombreServicio nvarchar(50)
);


CREATE TABLE TipoRefaccion(
	idTipoRefaccion int  primary key not null auto_increment,
    nombreTipoRefaccion nvarchar(50)
);


CREATE TABLE Proveedor(
	idProveedor int primary key not null auto_increment,
    nombre nvarchar(50),
    correoElectronico nvarchar(50),
    telefono nvarchar(50)
);


CREATE TABLE Sucursal(
	idSucursal int primary key not null auto_increment,
    direccionSucursal nvarchar(250)
);



CREATE TABLE Diagnostico(
	idDiagnostico int primary key not null auto_increment,
    fechaInicio datetime,
    fechaFin datetime,
    resultadoDiagnostico nvarchar(250),
    idDispositivo int,
    idServicio int,
    idSucursal int,
    foreign key(idDispositivo) references Dispositivo(idDispositivo),
    foreign key(idServicio) references Servicio(idServicio),
    foreign key(idSucursal) references Sucursal(idSucursal)
);


CREATE TABLE Paqueteria(
	idPaqueteria int primary key not null auto_increment,
    nombrePaqueteria nvarchar(45)
);

CREATE TABLE SolicitudServicio(
	idSolicitudServicio int primary key not null auto_increment,
    numeroGuia nvarchar(45),
    idDiagnostico int,
    idPaqueteria int,
    foreign key(idDiagnostico) references Diagnostico(idDiagnostico),
    foreign key(idPaqueteria) references Paqueteria(idPaqueteria)
);



CREATE TABLE Cotizacion(
	idCotizacion int primary key not null auto_increment,
    reparacionSoftware double,
    reparacionHardware double,
    actualizaciones double,
    servicioLimpieza double,
    costoRepuesto double,
    manoDeObra double,
    garantia double,
    idDiagnostico int, 
    foreign key(idDiagnostico) references Diagnostico(idDiagnostico)
);



CREATE TABLE PedidoRefacciones(
	idPedidoRefacciones int primary key not null auto_increment,
    fechaPedido nvarchar(50),
    numeroPedido nvarchar(50),
    totalPedido float,
    direccionEntrega nvarchar(250)
);



CREATE TABLE Refaccion(
	idRefaccion int primary key not null auto_increment,
    nombreRefaccion varchar(50),
	codigoRefaccion varchar(50),
    idDiagnostico int,
    idTipoRefaccion int,
    foreign key(idDiagnostico) references Diagnostico(idDiagnostico),
    foreign key(idTipoRefaccion) references TipoRefaccion(idTipoRefaccion)
);


CREATE TABLE RefaccionProveedor(
	idProveedor int,
    idRefaccion int,
    pzasDisponiblesCompra int,
    precioCompra float,
    foreign key(idProveedor) references Proveedor(idProveedor),
    foreign key(idRefaccion) references Refaccion(idRefaccion)
);


CREATE TABLE RefaccionesCompradas(
	idPedidoRefacciones int,
    idRefaccion int,
    refaccionesCompradas int,
    precioNetoRefacciones float,
    foreign key(idPedidoRefacciones) references PedidoRefacciones(idPedidoRefacciones),
    foreign key(idRefaccion) references Refaccion(idRefaccion)
);


CREATE TABLE RefaccionSucursal(
	idRefaccion int,
    idSucursal int,
    pzasDisponiblesVenta int,
    precioVenta float,
    foreign key(idRefaccion) references Refaccion(idRefaccion),
    foreign key(idSucursal) references Sucursal(idSucursal)
);







