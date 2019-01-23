# cwierkacz


## Development

### Docker

#### Building docker image
`./gradlew build docker`

#### Running application with docker image
`docker run -p 8080:8080 lghsbc.cwierkacz/demo`

### Documentation
- swagger-ui [/swagger-ui.html](http://localhost:8080/swagger-ui.html) 

### Healthcheck
- healthcheck [/actuator/health](http://localhost:8080/actuator/health)