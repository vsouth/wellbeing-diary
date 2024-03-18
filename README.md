# Wellbeing Diary
"Дневник самочувствия" - выпускной проект для javaschool.

Пользователь может отмечать свое состояние по нескольким критериям в течении дня, получать свою статистику, экспортировать свои записи в .xls

Аналитик имеет доступ к обезличенным записям других пользователей.

Интеграция с погодой.

# Запуск
загрузить зависимости maven

postgresql:

- spring.datasource.username=postgres

- spring.datasource.password=admin

- spring.datasource.url=jdbc:postgresql://localhost:5432/wellbeing-diary


запустить WellbeingDiaryApplication.java

localhost:8080/login
- user (analyst): admin 
- - password: admin

- user (user): admin
- - password: adm



# STATUS
## Пользователь может:
- ☑️ зайти
- ☑️ зарегистрироваться
- ☑️ удалить свой профиль
- ☑️ изменить пароль
- ☑️ изменить свои данные
- ☑️ добавить свежую запись (с сегодняшней датой и погодой)
- ☑️ открыть существующую запись
- ☑️ изменить существующую запись
- ❌ добавить старую запись (со старой датой и погодой)
- ☑️ просматривать список своих записей
- ❌ фильтровать свои записи (по mood, activity, health)
- ☑️ получать свою статистику (по типу погоды)
- ☑️ экспортировать записи .xls
## Аналитик может:
- ☑️ зайти
- ☑️ зарегистрироваться
- ☑️ удалить свой профиль
- ☑️ изменить пароль
- ☑️ изменить свои данные
- ☑️ добавить свежую запись (с сегодняшней датой и погодой)
- ☑️ открыть существующую запись
- ☑️ изменить существующую запись
- ❌ добавить старую запись (со старой датой и погодой)
- ☑️ просматривать список своих записей
- ❌ фильтровать свои записи (по mood, activity, health)
- ☑️ получать свою статистику (по типу погоды)
- ☑️ экспортировать записи .xls
- ☑️ просматривать открытые открытые записи
- ❌ фильтровать открытые записи (по mood, activity, health)
- ☑️ получать открытую статистику (по типу погоды)
- ☑️ экспортировать открытые записи в .xls

