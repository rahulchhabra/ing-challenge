# Spring boot classification app #

### Summary ###

Starting point of this project was from https://github.com/liuben10/spring-boot-react-js-example

Front end uses react + redux and backend relies spring boot.

It displays customer classification based on customerId and month selected by user. There are two endpoints
1. Returns customer classification and translations only for selected month sorted in ascending order.
2. Returns user balance, which remains same irrespective of month entered.

Two main libraries used in backend are
1. opencsv to parse the given data file.
2. easy rules to keep classification logic clean and extendable.

Both backend and frontend apps should be started to test the solution.

### Running ###

To run the front end, cd into ./example-frontend and run

```yarn start```

By default, this will start the front end at http://localhost:3000. If you want to
change the default port, modify package.json and change the start script section to

``` start: export PORT=3006 && react-scripts start```

To run the back end, from the project root directory, run

```mvn spring-boot:run```

By default, this will start the backend server at http://localhost:8080.

To change the port, modify src/main/resources/application.properties to include server.port=xxxx where xxxx is your desired port number.

### Installation ###

In the source directory, run

```mvn install -DskipTests```

In the example-frontend directory run

``` yarn install```

You might have to install the appropriate versions of npm or mvn.

Software versions used
node - 10.4
npm - 6.1 
maven 3.3.9 
JDK version 1.8.0_92 

