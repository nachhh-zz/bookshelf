FROM java:8

# install packages 
RUN apt-get update && apt-get install -y vim maven nodejs npm git sqlite3 libsqlite3-dev

COPY . /home/bookshelf/

# generate frontend code/package
WORKDIR /home/bookshelf/src/main/resources/assets/bookshelf
RUN npm cache clean -f
RUN npm install -g n
RUN n stable
RUN npm install -g npm@next
RUN mkdir -p /home/node/.npm-global
ENV PATH=/home/node/.npm-global/bin:$PATH
ENV NPM_CONFIG_PREFIX=/home/node/.npm-global
RUN npm install -g @angular/cli
RUN ng build --prod

WORKDIR /home/bookshelf

# generate DB with sample records
RUN sqlite3 bookshelf.db < create_db.sql

# build project and package
# Also generates data sources (model classes from DB)
RUN mvn clean install

# spin up the server with generated jar and config file
CMD java -jar target/bookshelf-1.0-SNAPSHOT.jar server config.yml

