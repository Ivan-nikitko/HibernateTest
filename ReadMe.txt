
********* Запуск и настройка *********
Основной файл настройки Hibernate это src/main/resources/hibernate.cfg.xml
Маппинг классов на БД можно осуществлять несколькими способами:

1. Осуществлять настройку через mapping resource в файле настроек Hibernate (см. закомментированная строка в конфиге)
и отдельные xml настроек для каждого класса (см. src/main/resources/pl/nikitko/model/Brand.hbm.xml).
2. В конфиге сделать ссылку на класс (пример  <mapping class="pl.nikitko.model.Brand"></mapping>) и настройку
осуществить проставлением аннотаций над полями.

Во время учебы нам продвигали второй (гибридный) способ,как менее многословный и более наглядный. Насколько я знаю
охват настроек у обоих подходов одинаковый.

******* Запросы к БД *********

Запросы к БД осуществляются через обьект класса Session (в свою очередь полученный из SessionFactory)

Основные способы построения запросов через Session

1. Через методы save/get/update/delete обьекта класса Session
2. Метод createSQLQuery и передача ему строки SQL запроса (см. строка 64 BrandDAO.java)
3. Параметризированный SQL запрос. Похож на preparedStatement в JPA (см. строка 74 BrandDAO.java).
4. Через CriteriaQuery (см. строка 85 BrandDAO.java)
    4.1 Получение у Session обьекта CriteriaBuilder
    4.2 Получение CriteriaQuery у CriteriaBuilder
    4.3 Получение и настройка Predicate у CriteriaBuilder (это и есть настройка запроса к БД)
    4.4 Выполнение запроса Query<E> query = session.createQuery(criteriaQuery);
5. Так же через CriteriaQuery, но в качестве запроса передается массив условий (см. строка 99 BrandDAO.java).


