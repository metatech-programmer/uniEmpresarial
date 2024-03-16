# uniEmpresarial

# Usuarios de prueba
    1. Correo: juan@gmail.com   Clave: 123
    2. Correo: jose@gmail.com   Clave: 123
    3. Correo: estudiante@gmail.com   Clave: 123
    


## Temas  a tener en cuenta
    1. Una universidad no se puede eliminar si esta unida a un director
        1.1. De forma inversa un director no se puede eliminar si esta unido a una universidad
        1.2. Cuando uno va a registrarse antes se debe indicar que el administrador desde la base de datos o un link "oculto" ya debió crear universidades y directores, ya que de igual forma no siempre podremos acceder a todos loos recursos tan a la ligera, porque existe un sistema y ese sistema exige un paso a paso.
   
    2. Se debe crear una cuenta en imgBB > https://imgbb.com/signup para poder poder usar su API y subir las imágenes
        2.1. El subir imágenes también implica añadir ciertas dependencias en el archivo de pom.xml
        2.2. Cuando se tenga la APIKEY brindada por el sitio, la colocaremos en donde estamos usando el servicio (universidadController y seccionalController)
    
    3. Al llegar también al apartado del login y el register, se deben añadir las dependencias que están el el pom.xml de SpringSecurity y por ultimo añadir en  la mayoría de los archivos html en la etiqueta html la importación de seguridad, antes de eso no se hace.
    
    4. Si se llega al apartado de despliegue es importante saber que antes todos los estudiantes deberán tener cuenta en GitHub.
        4.1. También deberán tener cuenta en FL0 y Onrender, mientras que el primero servirá para crear la base de datos con PostresSQL, el segundo nos dará el espacio adecuado para solo conectarnos a nuestro repositorio de GitHub y subir el proyecto ya a producción
        4.2. Después de esto si se creara el archivo de DockerFile y allí se deberá cambiar en cada estudiante el nombre de la carpeta de trabajo (Ninguno puede tener el mismo nombre de carpeta)
        4.3. Se deberá confirmar que la version de nuestra app que estamos subiendo en el DockerFile sea la misma que se nos brinda en el archivo pom.xml
    
    5. El apartado de errores tanto paginas como controladores se hacen casi al final al igual que el login o el registro de usuarios, ya que esto le quita visibilidad a errores y no es tan efectivo tenerlo desde un inicio
        5.1. Aunque si se puede armar la estructura de los html desde antes, solo para tenerlos allí.

    6.El sistema armado tiene ciertas "falencias" pero es incentivo para que los estudiantes repiensen siempre en lo que están haciendo y como lo hacen. 

