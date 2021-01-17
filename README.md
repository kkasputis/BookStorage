# BookStorage
Book Storage
Restfull services for book storage.
Also added a simple front-end that uses rest services for easier testing without using "Postman" or other software. 
Front end controller is at localhost:8080

regular book rest service:

add book - localhost:8080/books POST
get book info - localhost:8080/book/{barcode} GET
update - localhost:8080/book/{barcode} PUT

antique book serives:

add antique book - localhost:8080/antique POST
get antique book info - localhost:8080/antique/{barcode} GET
update antique book - localhost:8080/antique/{barcode} PUT

science journal services:

add science journal - localhost:8080/science POST
get science journal info - localhost:8080/science/{barcode} GET
update science journal - localhost:8080/science/{barcode} PUT

price calculation localhost:8080/price/{barcode} GET
