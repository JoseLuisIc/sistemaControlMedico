# Sistema de Control Médico Básico

Este es un sistema de control médico básico desarrollado en Java 8 y Swing con una base de datos MySQL 8 o 5. Este sistema permite a los usuarios administrar información médica, pacientes, citas y más.

## Autor
Jose Luis Caamal Ic
jcaamalic@gmail.com
Kaab Code
Visita kaabcode.com

## Requisitos

Antes de ejecutar este sistema, asegúrate de tener instalado lo siguiente:

- Java 8 o superior
- MySQL Server 8 o 5
- Bibliotecas JDBC para MySQL (normalmente incluidas en tu IDE o proyecto)

## Configuración de la Base de Datos

1. Crea una base de datos MySQL llamada "control_medico" (puedes elegir otro nombre si lo prefieres).
2. Ejecuta el script SQL `control_medico.sql` para crear las tablas necesarias y cargar datos de ejemplo.

3. sql
mysql -u tu_usuario -p control_medico < control_medico.sql
Actualiza la información de conexión a la base de datos en el archivo ConfiguracionBD.java con tus credenciales de MySQL.
java

private static final String URL = "jdbc:mysql://localhost:3306/control_medico";
private static final String USUARIO = "tu_usuario";
private static final String CONTRASENA = "tu_contraseña";
Ejecución del Sistema
Abre el proyecto en tu IDE Java preferido.
Compila y ejecuta la clase Main.java.
Inicia sesión con las credenciales de administrador proporcionadas en la base de datos de ejemplo.
Características
Gestión de pacientes y su información médica.
Programación de citas médicas.
Registro de tratamientos y diagnósticos.
Registro de médicos y personal de salud.
Seguridad con inicio de sesión.
Informes y estadísticas básicas.
Contribuciones
Si deseas contribuir al desarrollo de este proyecto, siéntete libre de hacerlo. Puedes abrir problemas o enviar solicitudes de extracción. ¡Tu ayuda es bienvenida!

Licencia
Este sistema se distribuye bajo la Licencia MIT. Consulta el archivo LICENSE para obtener más información.

Este sistema es un proyecto de código abierto y no está destinado a un uso en entornos de producción sin una revisión y personalización adecuadas. Asegúrate de cumplir con todas las regulaciones de privacidad y seguridad de datos aplicables antes de implementarlo en un entorno real.