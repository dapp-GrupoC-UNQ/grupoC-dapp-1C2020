# grupoC-dapp-1C2020 
[![Build Status](https://travis-ci.com/dapp-GrupoC-UNQ/grupoC-dapp-1C2020.svg?branch=master)](https://travis-ci.com/dapp-GrupoC-UNQ/grupoC-dapp-1C2020)

RELEASE_NOTE

TAG entrega_1

NEW FEATURES:

DOMINIO:
-	Tiendas registradas que tienen un rubro, días y horarios de atención y un domicilio.
- Se pueden agregar productos a un comercio, actualizarles su precio y el stock. Se verifica que no se pueda agregar el mismo producto a un comercio mas de una vez. Validaciones para que esos productos no tengan ni precio ni stock negativo.
-	En los comercios un usuario puede realizar compras, abonarlas con distintos medios de pago, que dependen de la tienda y elegir el método de retiro (envío a domicilio o retirar en local).
- Validaciones para que un usuario no pueda llevarse mas mercaderia de la que hay en el comercio de un determinado producto.
-	Las compras que realice cada usuario quedarán guardadas en caso de que éste desee comprar los mismos productos en el futuro.
-	Los comercios poseen un sistema para entregar al usuario el siguiente turno disponible para retirar su compra.
-	Los comercios pueden ofrecer a los usuarios ofertas por producto o por categoría de productos durante un tiempo determinado.
-	Umbrales por monto total: el usuario puede setear un umbral de monto total de sus compras
- Umbrales por categorias: El usuario puede seter un umbral de dinero por categoria en sus compras. Todos los umbrales pueden activarse y desactivarse.

- CAPA WEB:
- controller de Comercios. Endpoints para obtener todos los comercios desde el frontend, obtener los comercios por determinada categoria, obtener los productos de un comercio y tambin obtener sus descuentos
- controller de Usuarios.Endpoints para validar que un usuario esté registrado o no para acceder a la aplicacion. Endpoint para registrar un usuario (no vendedor).

- FRONTEND: 
- vistas de landpage y homepage. Se puede registrar un usuario desde la landpage
- se puede buscar por comercios, categorias y ofertas desde la homepage.

NOTES:
Features que faltan:
-Integracion con codacy y deploy en heroku
-No se notifica vía mail al usuario el horario otorgado para retirar su compra en la tienda.
-No se puede comprar productos en más de una tienda por compra.


-KNOWN ISSUES:

-El sistema de turnos entrega los turnos en orden correlativo, pero cuando se agotan los de un dia (ej miércoles) en vez de comenzar a entregar los del jueves, vuelve a dar los primeros del miércoles.
