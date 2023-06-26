# Define a imagem base para o front-end
FROM node:14-alpine as build-frontend

# Cria um diretório de trabalho para o app
WORKDIR /app

# Copia o package.json para o diretório de trabalho
COPY frontend/package*.json ./

# Instala as dependências
RUN npm install

# Copia o resto dos arquivos do front-end
COPY frontend ./

# Compila o código do front-end
RUN npm run build


# Define a imagem base para o back-end
FROM openjdk:14-alpine as build-backend

# Cria um diretório de trabalho para o app
WORKDIR /app

# Copia o arquivo jar do back-end
COPY backend/target/*.jar app.jar


# Define a imagem base para o banco de dados
FROM mysql:8.0

# Define variáveis de ambiente para o banco de dados
ENV MYSQL_DATABASE=db_name \
    MYSQL_USER=db_user \
    MYSQL_PASSWORD=db_password \
    MYSQL_ROOT_PASSWORD=root_password

# Copia o script SQL de criação do banco de dados para a imagem
COPY db_init_script.sql /docker-entrypoint-initdb.d/

# Define a porta que o container deve escutar
EXPOSE 3306


# Define a imagem final para o app completo
FROM openjdk:14-alpine

# Copia os arquivos compilados do front-end para o diretório de trabalho do back-end
COPY --from=build-frontend /app/build /app/src/main/resources/static

# Copia o arquivo jar do back-end
COPY --from=build-backend /app/app.jar /app/app.jar

# Define a porta que o container deve escutar
EXPOSE 8080

# Inicia a aplicação
CMD ["java", "-jar", "/app/app.jar"]
