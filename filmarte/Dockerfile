# Usa una imagen base oficial de OpenJDK 17 en Alpine (ligera)
FROM openjdk:17-alpine

# Autor de la imagen (opcional, pero es buena práctica)
LABEL maintainer="filmarte"

# Copia el archivo JAR de la carpeta target (asegúrate de que el archivo .jar esté generado)
COPY target/filmarte-0.0.1-SNAPSHOT.jar /filmarte-app.jar

# Establece la entrada del contenedor para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/filmarte-app.jar"]