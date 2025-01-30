# MicroProcess_BankSystem
#В проекте исполюзуются:
Spring boot:
Spring Data JPA SQL
MS SQL Server Driver SQL
Spring Web 
база данных: MySql

## Запуск проекта
1. Клонируйте репозиторий
2. Настройте MySQL (создайте БД `microservice_bank`)
3. Создайте таблицы user, transactions
4. SELECT * FROM user
5. Зарегестрируйте нового пользователя
6. SELECT * FROM transactions
7.Обновить лимит пользователя
UPDATE monthly_limit SET goods_limit = 3000 WHERE user_id = 1;
8. Вставить тестовую транзакцию
INSERT INTO transactions (account_from, account_to, currency_shortname, amount, expense_category, datetime)
VALUES (1, 2, 'USD', 500, 'goods', NOW());
9. Запустите сервис:
   ```bash
   mvn spring-boot:run
API доступно по http://localhost:8080
Swagger: http://localhost:8080/swagger-ui.html

##Запросы можете протестировать через Postman:
Основные запросы для проверки:

1. Получение курса валют
GET http://localhost:8080/api/currency-rate/USD
Вернёт текущий курс доллара.
Можно подставлять любую валюту (например, EUR или GBP).

2. Добавление транзакции
POST http://localhost:8080/api/transactions
Body (JSON, raw)
{
  "accountFrom": 1,
  "accountTo": 2,
  "currencyShortname": "USD",
  "amount": 500.0,
  "expenseCategory": "goods"
}
Если сумма превышает лимит, API вернёт ошибку 403.

3. Получение всех транзакций
GET http://localhost:8080/api/transactions
Вернёт список всех транзакций.

5. Проверка превышения лимита
GET http://localhost:8080/api/transactions/exceeding
Покажет только те транзакции, которые превысили лимит.

6. Изменение лимита пользователя
PUT http://localhost:8080/api/limits
Body (JSON, raw)

{
  "userId": 1,
  "goodsLimit": 2000.0,
  "servicesLimit": 3000.0
}
Позволяет установить новые лимиты.

7.Получение лимита пользователя
GET http://localhost:8080/api/limits/1
Покажет текущий лимит пользователя 1.

