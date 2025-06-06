# Банк "Стар"
## Сервис рекомендаций продуктов банка
### Назначение приложения
Приложение предназначено для генерации в адрес клиентов банка рекомендаций продуктов банка на основе анализа совершенных клиентом финансовых операций, зафиксированных в информационной системе банка.
Доступ клиентов к рекомендациям производится посредством API или в интерактивном режиме через телеграм-бот.
Описание API и команд телеграм-бота представлено в WiKi
### Предварительные требования
1. Компьютер должен иметь доступ в интернет
2. На компьютере должен быть установлен Open JDK 17
3. Компьютер должен иметь доступ к серверу с установленной СУБД PostgreSQL, или СУБД дожна быть установлена локально
4. Комьютер должен иметь доступ с установленной СУБД банковского приложения, или файл данных с выгруженными из БД банковского приложения транзакциями должен быть расположен на локальном диске
### Компиляция и сборка приложения
1. Клонировать проект в локальную папку.
2. Скопировать файл настроек ***properties.env*** в каталоге проекта в ***properties-local.env***
3. Отредактировать файл настроек ***properties-local.env*** в каталоге проекта, установив требуемые параметры приложения
   + STAR_SERVER_PORT - порт, на котором запускается приложение
   + STAR_RECOMMENDATIONS_DB_URL - JDBC-строка подключение к SQL-серверу банковского приложения (или к локальному файлу с данными)
   + STAR_DATASOURCE_URL - JDBC-строка подключение к SQL-серверу приложения
   + STAR_DATASOURCE_USERNAME - логин пользователя SQL-сервера приложения
   + STAR_DATASOURCE_PASSWORD - пароль пользователя SQL-сервера приложения
   + STAR_TELEGRAMBOT_TOKEN - токен для подключения к API телеграм-бота
4. В терминале, находясь в каталоге проекта, выполнить команду:
   в системе Linux 
   ``` 
   ./build
   ```
   в системе windows
   ``` 
   build.cmd
   ```
   
### Запуск поиложения
1. Перед запуском командного файла нужно отредактировать в нем строки с настройками переменных окружения так, как это был сделано при компиляции.
2. Отредактировать файл настроек ***properties.env*** в каталоге проекта, установив требуемые параметры приложения (как и в случае компиляции проекта)
3. В терминале, находясь в каталоге проекта, выполнить команду:
   в системе Linux
   ``` 
   ./service-start
   ```
   в системе windows
   ``` 
   service-start.cmd
   ```

   
   


