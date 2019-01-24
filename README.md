# cwierkacz


## Getting started (hot to use endpoints)
Please use swagger or following curl commands

### curl

1. Creating new post (by Bob):
`curl -X POST "http://localhost:8080/messages/Bob" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"text\": \"New message from...\"}"`

2. Viewing the user's (Bob's) wall
`curl -X GET "http://localhost:8080/messages/Bob" -H  "accept: */*"`

3. Starting following user's messages (Alice follows Bob's messages)
`curl -X PUT "http://localhost:8080/users/Alice/followed" -H  "accept: */*" -H  "Content-Type: application/json" -d "{  \"followerId\": \"Bob\"}"`

4. Viewing timeline - list of the messages posted by all the people (Bob) who user (Alice) follow
`curl -X GET "http://localhost:8080/messages/Alice/following" -H  "accept: */*"`


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