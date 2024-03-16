## uniEmpresarial

### Descripción
uniEmpresarial es una plataforma de gestión universitaria diseñada para facilitar la administración de universidades, directores, estudiantes y seccionales.

### Usuarios de prueba
Puedes acceder a la página de prueba [aquí](https://uniempresarial.onrender.com/).

1. **Juan**
   - Correo: juan@gmail.com
   - Clave: 123
2. **Jose**
   - Correo: jose@gmail.com
   - Clave: 123
3. **Estudiante**
   - Correo: estudiante@gmail.com
   - Clave: 123

### Temas a tener en cuenta
1. **Gestión de universidades y directores**
   - Una universidad no se puede eliminar si está asociada a un director.
   - De forma inversa, un director no se puede eliminar si está asociado a una universidad.
   - Antes de registrarse, se debe indicar que el administrador ya creó universidades y directores.
   
2. **Uso de imágenes**
   - Se requiere crear una cuenta en [imgBB](https://imgbb.com/signup) para poder usar su API y subir imágenes.
   - Es necesario añadir las dependencias correspondientes en el archivo `pom.xml`.
   - Se debe incluir la APIKEY proporcionada por el sitio en los controladores donde se utilice el servicio.
    
3. **Seguridad**
   - Al implementar el login y el registro, se deben añadir las dependencias de Spring Security en el `pom.xml`.
   - Se debe agregar la importación de seguridad en la etiqueta HTML de la mayoría de los archivos HTML.
    
4. **Despliegue**
   - Todos los estudiantes deben tener cuenta en GitHub.
   - Se requieren cuentas en FL0 y Onrender para la creación de la base de datos y el despliegue del proyecto, respectivamente.
   - Se debe modificar el archivo Dockerfile para cada estudiante, cambiando el nombre de la carpeta de trabajo.
   - Es importante asegurarse de que la versión de la aplicación en el Dockerfile coincida con la del archivo pom.xml.
    
5. **Manejo de errores**
   - Los errores tanto en páginas como en controladores se manejan al final del desarrollo, para no restar visibilidad a funcionalidades principales como el login o el registro de usuarios.
   - La estructura de los archivos HTML se puede definir desde el principio para facilitar el desarrollo.

6. **Observaciones**
   - El sistema tiene ciertas "falencias" que pueden ser consideradas como oportunidades de aprendizaje para los estudiantes.

### Tecnologías utilizadas
- Spring Boot
- HTML
- CSS
- JavaScript
- Docker
- PostgreSQL

### Contribuyentes
- El equipo de Desarrollo empresarial

![Landing Page](https://i.ibb.co/ABC123/logo.png)
