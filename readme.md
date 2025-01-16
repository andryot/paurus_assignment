# Instructions
### 1. In terminal run:
docker run --name assignment_db -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -e POSTGRES_DB=assignment_db -d postgres:latest

## 2. locate the paurus_assignment.jar file and run in terminal
java -jar paurus_assignment.jar

## Part 1
- import postman_collection.json to postman for testing the endpoint 
- If traderId is even I used the general taxation and else the winnings taxation

## Part 2
### Notes
- the insertion will be executed on runtime
- min and max 'date_insert' are printed in the console
- I used copy method for inserting so that is why the date_insert is the same in all entries

