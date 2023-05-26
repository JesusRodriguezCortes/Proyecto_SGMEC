USE SGEMEC;
SELECT idDispositivo, marca, modelo, usuarioDispositivo, passwordDispositivo, 
errorDispositivo, estado, imagenDispositivo, dispositivo.idCliente, 
CONCAT(cliente.nombre,' ',cliente.apellidoPaterno) as nombreCliente
FROM dispositivo LEFT JOIN cliente ON dispositivo.idCliente = cliente.idCliente;