# Usar imagem oficial do OpenJDK 17, leve (slim)
FROM openjdk:24-jdk

# Copiar o arquivo JAR que foi gerado após o build (maven/gradle)
COPY target/Banco-Talentos-0.0.1-SNAPSHOT.jar /app/BancoTalentos.jar

# Comando para rodar o JAR
ENTRYPOINT ["java", "-jar", "/app/BancoTalentos.jar"]