# CloudCombat

CloudCombat es un juego de estrategia inspirado en el clásico "Hundir la Flota". En este juego, los jugadores se adentran en el mundo de la aviación, donde el objetivo es derribar los aviones enemigos mientras protegen sus propias fuerzas aéreas.


## Lanzar la Aplicación Directamente

Para lanzar la aplicación directamente en tu sistema local sin Docker, asegúrate de tener instalado Maven y luego ejecuta:

```bash
mvn clean install exec:java
```

Esto limpiará cualquier compilación previa, instalará las dependencias y ejecutará la aplicación.

## Proceso con Docker
Para construir y ejecutar la aplicación utilizando Docker, sigue estos pasos:

Construir el Contenedor
Construye la imagen del contenedor con todos los archivos necesarios y configuraciones:

```bash
docker-compose build
```

### Ejecutar Docker Compose en Modo Interactivo
Para ejecutar el contenedor en modo interactivo y acceder a la shell dentro del contenedor:

```bash
docker-compose run mi-aplicacion-javafx /bin/bash
```

Deberías ver una salida similar a esta, indicando que estás dentro del contenedor:

```bash
root@88930ada4fa6:/home/aplicacion#
```

### Configuración del Servidor X para macOS

Antes de lanzar la aplicación en Docker, necesitarás configurar un servidor X para permitir que la aplicación muestre su GUI. En macOS, esto se hace instalando y configurando XQuartz.

### Instalar XQuartz
Descarga e instala XQuartz desde XQuartz Official Website.

### Habilitar Conexiones de Red

Abre XQuartz y en las preferencias, ve a la pestaña 'Seguridad' y asegúrate de marcar la opción "Allow connections from network clients". Esto es necesario para que las aplicaciones dentro de los contenedores Docker puedan mostrar su GUI.

### Permitir Conexiones al Servidor X
Antes de lanzar tu contenedor, ejecuta en tu terminal de Mac:

```bash
xhost +
```

### Deshabilitar Conexiones al Finalizar
Por seguridad, una vez que termines de trabajar con tu contenedor, deberías revocar el acceso al servidor X con:

```bash
xhost -
```