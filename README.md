# Prueba Técnica

Para realizar la prueba técnica he desarrollado la aplicación utilizando el lenguaje de programación kotlin.

La arquitectura aplicada es MVVM, y para inyección de dependecias he utilizado hilt.

Con esta app se obtiene un listado de series de televisión atacando a la api: https://developers.themoviedb.org/3/. Va sacando un listado de 20 en 20, con posibilidad
de obtener las 20 siguientes o las 20 anteriores en caso de poder. Los registros que se obtienen de la api se van almacenando en una base de datos room, por si se pierde
la conexión a internet poder seguir consultandolos.

La aplicación tiene la posibilidad de verse tanto con orientación vertical como horizontal e incluso activar el modo oscuro.

Otra característica es que se detecta el idioma configurado en el teléfono y tiene la opción de poderse ver en español o en inglés.
