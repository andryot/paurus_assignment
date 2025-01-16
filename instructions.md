# Instructions
## Part 1
- open 
## Part 2
### 1. In terminal run:
docker run --name assignment_db -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=assignment_db -d postgres:latest

## 2. locate the .jar file and run
java -jar 'file_name.jar'

#### example: java -jar target/paurus_assignment-0.0.1-SNAPSHOT.jar


# Notes
- min and max 'date_insert' are printed in the console
- I used copy method for inserting so the date_insert is the same in all entries

