# Usa una imagen base de Debian
FROM debian:latest

# Instala OpenJDK 17 y unzip
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk unzip && \
    apt-get clean;

# Copia el SDK de JavaFX desde tu host al contenedor
COPY openjfx-17.0.10_linux-x64_bin-sdk.zip /opt/javafx-sdk.zip

# Descomprime el SDK de JavaFX y lo mueve al directorio deseado
RUN unzip /opt/javafx-sdk.zip -d /opt/ && \
    mv /opt/javafx-sdk-17.0.10 /opt/javafx && \
    rm /opt/javafx-sdk.zip


# Establece la variable de entorno para JavaFX
ENV PATH_TO_FX=/opt/javafx/lib

# Copia tu aplicación JavaFX al contenedor
COPY ./src /home/aplicacion/src
COPY ./pom.xml /home/aplicacion

# Establece el directorio de trabajo
WORKDIR /home/aplicacion

# Aquí, asumiendo que usas Maven, compila tu proyecto
# Ajusta este paso según tu estructura y herramientas de construcción
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package

# Ejecuta tu aplicación JavaFX (ajusta este comando según sea necesario)
CMD ["/bin/bash"]
