# TestTask
###Техническое задание
Для обучения и работы приложения необходимо создать БД книг.
Набор данных книги: название, автор, описание, год, изображение обложки, ISBN, количество страниц, жанр, теги.
Ваша задача - подготовить базу данных книг. 
Список книг берется с сайта https://igraslov.store/ и ограничен ими
Для этого необходимо написать сервис, который парсит книги с книжных сайтов (любой из списка, но не без аргументации): лабиринт, литрес, читай-город, ozon, google books, goodreads, библиотеки (электронные), а также сохраняет их в БД.
Важно:
-	должна быть реализована возможность расширять работу с другими сайтами, на которых есть книги. Иными словами, “хочу парсить новый сайт, на котором есть книги, которых нет нигде больше. Могу легко расширить работу сервиса”. 
-	апи для работы с сервисом, которое позволит получить список книг
____
### Проблемы с которыми с толкнулся
Большинство выше указанных сайтов при большом количестве запросов вызывают рекапчу или просят заполнить форму, в которой вы должны указать свои данные
и отправить им на почту. Это такие сайты как литрес, читай-город, google books, ozon, livelib. Электронные библиотеки содержат крайне мало нужной информации для 
корректного выполнения тестового задания(поэтому я рассматривал их в последнюю очередь). https://www.labirint.ru/ - сайт, который содержит всю необходимую информацию и 
который у меня получилось спарсить.
Только при выставленном таймауте и изменённым юзерагентом он позволяет это делать. Единственное, что получилось некорректно это парсинг обложки книги, так как
библиотека Jsoup(для парсинга данных на Java) считывает обложку книги как https://img.labirint.ru/design/emptycover.svg .
Также возникли проблемы при деплоее собранного проекта, так как сервисы, которые использовал ранее(AWS, Heroku) не работают в связи с санкциями. Поэтому в данный момент
ищу сервисы, которые предоставляют готовое окружение для деплоя проекта. В крайнем случае придётся ставить на голый сервер.
### Моё решение
В качестве БД использовал H2, так как она встроена в Spring Boot.
Реализовал REST API, который возвращает список книг в БД по адресу http://localhost:8080/books/amount, где amount - количество нужных книг. Если нужно получить все книги,
то http://localhost:8080/books/.
Решил, что лучшее указать конкретное количество книг, так как если парсить все книги с сайта https://igraslov.store/ и искать информацию на них на сторонних ресурсах, то
уйдет большое количество времени.
 
