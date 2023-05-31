# Tarea: Alquiler de vehículos (v3)
## Profesor: Juan Antonio Muñoz Almansa
## Alumno: Pedro Patricio Cárdenas Figueroa


Desde la empresa que tiene la gestión del alquiler de vehículos nos acaban de dar unos nuevos requisitos a aplicar sobre la última versión que le mostramos y que les gustó bastante. Lo que nos piden es lo siguiente:

Quieren conservar la interfaz de texto de la aplicación.
Quieren también tener una nueva interfaz de usuario gráfica para ejecutar la aplicación.
Tu tarea consiste en dotar a la aplicación de la tarea anterior (con todas sus funcionalidades) de una interfaz gráfica de usuario, utilizando JavaFX. La interfaz se puede diseñar al gusto de cada un@, pero deberá utilizar los componentes más adecuados en cada caso. Cuanto más elaborada esté mayor será la calificación. Para ello debes emplear los diferentes tipos de controles, menús y contenedores que nos proporciona la API de JavaFX. Se pide al menos:

Un menú que nos permita salir de la aplicación, o acceder a las diferentes opciones sobre clientes, vehículos y alquileres.
La gestión de clientes permitirá añadir uno nuevo, modificar y/o borrar uno ya existente y mostrar un listado con los siguientes datos como mínimo:
Nombre y Dni.
Teléfono de contacto.
Botones para modificar y/o eliminar un determinado cliente
Botón para acceder al listado de alquileres de un determinado cliente.
La gestión de vehículos permitirá añadir uno nuevo, borrar uno ya existente y mostrar un listado de todos los vehículos con los siguientes datos como mínimo:
Matrícula.
Marca y modelo.
Tipo de vehículo.
Icono que indique si el vehículo está alquilado o disponible.
Botón para eliminar.
La gestión de alquileres nos permitirá realizar un nuevo alquiler o finalizar un alquiler abierto. También permitirá mostrar un listado con todos los alquileres, mostrando los siguientes datos:
Nombre y Dni del cliente.
Marca, modelo y matrícula del vehículo.
Icono que indique el tipo de vehículo.
Importe del alquiler si ya ha finalizado.
Fecha de entrada.
Fecha de devolución o botón "Devolver" para poder finalizar el alquiler.
NOTA. Como mínimo, en el listado de clientes y vehículos, deberás implementar la funcionalidad de buscar y ordenar de alfabéticamente (orden ascendente y descendente) por el Nombre para los clientes y por el Modelo para los vehículos.

Por tanto, tu tarea va a consistir en completar los siguientes apartados:

Debes realizar un fork del repositorio de tu tarea anterior en otro nuevo llamado ReservasAulas-v4. Dicho repositorio lo clonarás localmente y realizarás las diferentes modificaciones que se piden en esta tarea.
Modifica el fichero de configuración de gradle para que incluya las librerías necesarias para poder trabajar correctamente con JavaFX. Crea un nuevo paquete para la vista gráfica. En principio la ventana principal sólo incluirá el menú adecuado. Cada fichero debe estar en la carpeta adecuada (bien sea un recurso -imagen o .fxml- o un fichero .java). Realiza el commit correspondiente.
Mueve la interfaz textual al paquete adecuado y renombra la aplicación que utilizaba dicha interfaz textual. Realiza un commit.
Crea un nuevo paquete para la vista gráfica. Crea una nueva aplicación que haga uso de dicha interfaz gráfica de usuario y que contenga el menú adecuado. Realiza un commit.
Para seleccionar el tipo de vista, crea un factoría de vistas (TEXTO o GRAFICA) de manera similar a la que ya has implementado para seleccionar el negocio del modelo de datos (MEMORIA o FICHEROS).
Realiza la gestión de clientes tal y como se indica anteriormente. Realiza un commit.
Realiza la gestión de vehículos tal y como se indica anteriormente. Realiza un commit.
Realiza la gestión de alquileres tal y como se indica anteriormente. Realiza un commit.
Al final debes tener dos aplicaciones que utilicen el mismo modelo, pero que una utilice la interfaz textual y otra la interfaz gráfica. Realiza un commit y sube los cambios a tu repositorio remoto.
Se valorará:

La nomenclatura del repositorio de GitHub y del archivo entregado sigue las indicaciones de entrega.
La indentación debe ser correcta en cada uno de los apartados.
El nombre de las variables debe ser adecuado.
Se debe utilizar la clase Entrada para realizar la entrada por teclado.
El proyecto debe pasar todas las pruebas que van en el esqueleto del mismo y toda entrada del programa será validada para evitar que el programa termine abruptamente debido a una excepción.
Se deben utilizar los comentarios adecuados.
Se valorará la corrección ortográfica tanto en los comentarios como en los mensajes que se muestren al usuario.