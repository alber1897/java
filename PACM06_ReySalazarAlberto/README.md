
    ********************************************************
    ***                                                  ***
    ***      PAC DESARROLLO - INSTRUCCIONES DE USO       ***
    ***                                                  ***
    ********************************************************

    1- Para probar esta aplicación, en primer lugar debemos importar la carpeta de proyecto
    en el IDE que más nos guste, posteriormente ir al archivo de configuración de Hibernate
    (src/main/resources/hibernate.cfg.xml), donde pondremos usuario y contraseña de nuestro
    MySQL. Una vez hecho esto podemos iniciar la aplicación desde la clase Main.

    (ASEGURAR TENER MYSQL ENCENDIDO CON EL CORRESPONDIENTE PUERTO)

    2- Este proyecto se encuentra configurado de forma que si la base de datos no existe 
    automáticamente el programa la va a crear, con las tablas y relaciones correspondientes.

    3- Una vez desplegada la aplicación desde la clase Main, podremos ver por consola un menu* en donde nos saldrán
    las diferentes opciones que tenemos para esta biblioteca que hemos creado. 

    IMPORTANTE: Los metodos han sido creados de manera estructurada en clases externas al Main con el finde una mayor limpieza y eficacia en el codigo. Lo único que se maneja desde la clase principal es las llamadas a estos y que la opción del menú que se ha elegido sea válida.

    *MENU: Una vez desplegada la aplicación nos aparece en la consola un menu escrito con diferentes opciones las cuales estarán explicadas justo debajo de este párrafo, a continuación del menu nos pedirá introducir un número el cual debe ser el de la función que deseemos realizar.

    IMPORTANTE 2: Con el fin de poder realizar unas pruebas correctas, se recomienda el uso de las funciones 1 y 6 del menú como primeras opciones del programa para poder disponer de libros y lectores con los cuales tratar el resto de datos y funciones. 

        3.1 - Insertar Libro/Lector: Método que nos permitirá la creación de nuevos ejemplares/lectores, marcando la opción  del menu se activa esta función, la cual nos requerira por consola una serie de datos para la creación del libro/lector. 

            3.1.2 - PARÁMETROS REQUERIDOS: 

                -Libro: Título, Autor, Año de publicación
                -Lector: Nombre, Apellido, Email

        3.2 - Listado de Libros/Lectores: Nos mostrará por pantalla el conjunto de libros/lectores totales que disponemos en la base de datos al marcar la opción 2. Los libros/lectores se muestran de uno en uno mediante un bucle que recorre una lista la cual nos devuelve este método.

        3.3 - Libro/Lector por ID: Para este método se utiliza la ID pa la hora de busca un libro/lector concreto, puesto que es la clave primaria de esta tabla. Al marcar la opción 3 del menu deberemos proporcionar la id del libro/lector a buscar a través de la consola, y esta nos devolverá el resultado en caso de que el libro exista o un mensaje por defecto en caso de que no este.

        IMPORTANTE: Para poder saber la id del libro/lector se recomienda utilizar la opción listar del menu, de esta forma nos sacará todos los libros/lectores disponibles.

        3.4 - Eliminar Libro/Lector por ID: Para realizar esta opción, al igual que en la anterior, deberemos proporciona la ID de libro/lector que queremos eliminar, podemos ayudarnos del mensaje anteriormente escrito para la obtención del ID correcto. Es un método el cual debe ser tratado con cuidado, puesto que elimina datos definitivamente y puede ser delicado.

        3.5 - Modificar Libro/Lector por ID: La modificación de datos al igual que el listado y eliminación nos solicitará una id para poder editar los datos concretos de un ejemplar. Una vez ofrezcamos esta ID, se nos mostrará por consola una serie de mensajes solicitando los nuevos datos, cuando hayamos realizado estos pasos, se nos mostrará un mensaje de éxito en la modificación u error en su defecto.

        *****************
        *** CONSULTAS ***
        *****************

        3.6 - Libros actualmente prestados a un lector: Con esta función podemos obtener datos más especificos, al iniciarla desde el menu, se nos solicitará una ID de lector, a través de la cual podemos obtener todos los libros que ACTUALMENTE tiene un lector prestados.

        3.7 - Libros disponibles: Este método nos ofrece el listado de libros que actualmente no estan prestados en el sistema. Al marca la opcion 12 del menu, automaticamente saldrá el listado completo, puesto que no requiere de ningún parámetro.

        3.8 - Historial de prestamos: Para obtener el historial de prestamos de un lector, al iniciar el método, se nos pedirá una ID del lector en el cual estamos interesados en conocer sus prestamos totales. Esto va a devolver un listado de todos los prestamos que ha realizado el lector, tanto que esten en activos como los ya finalizados.

        3.9 - Prestar Libro: Para poder prestar un Libro deberemos conocer la id de este y la ID del lector el cual va a tomar el ejemplar como prestado. Estas 2 opciones junto con la fecha de devolución seran preguntadas en la consola, una vez respondidas si todo ha ido correctamente nos saldrá un mensaje satisfactorio. El método se encarga también de modificar la disponibilidad del libro con el fin de que no haya 2 préstamos al mismo tiempo con el mismo libro.

        3.10 - Devolución de libro: Esta función es la encargada de poner fin a un préstamo, nos solicitará por consola el ID del libro que queremos devolver y en caso que este libro se encontrase prestado, su disponibilidad cambiará y el préstamo se dara por finalizado en la base de datos.


    Por último la aplicación cerrará automáticamente todas las conexiones con el fin de mayor seguridad y liberación de recursos.

    Alberto Rey Salazar
    PAC desarrollo Acceso a Datos    