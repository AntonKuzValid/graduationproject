# Graduation Project by Anton Kuznetsov
##My task - 
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

##The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

> For windows use `Git Bash`

### Test VoteRestController

**Title** : vote<br>
**URL** : votes/vote<br>
**Method** : PUT<br>
**Data Params** : { { menuId : [integer]} }<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>

#### vote Menu 100004
`curl -s -X PUT -d '{"menuId":"100004"}' -H 'Content-Type: application/json' http://localhost:8080/rest/votes/vote --user user@yandex.ru:password`

### Test RestaurantRestController

**Title** : show Restaurants<br>
**URL** : restaurants<br>
**Method** : GET<br>
**Response Codes**: Success (200 OK), Unauthorized (401)<br>
#### get Restaurants
`curl -s http://localhost:8080/rest/restaurants --user user@yandex.ru:password`

**Title** : show Restaurant with id<br>
**URL** : restaurants/restaurant_id<br>
**Method** : GET<br>
**URL Params** :  Required: restaurant_id=[integer]<br>
**Response Codes**: Success (200 OK), Unauthorized (401)<br>
#### get Restaurant 100000
`curl -s http://localhost:8080/rest/restaurants/100000 --user user@yandex.ru:password`

**Title** : show menus of restaurant with id<br>
**URL** : restaurants/restaurant_id/menu<br>
**Method** : GET<br>
**URL Params** :  Required: restaurant_id=[integer]<br>
**Response Codes**: Success (200 OK), Unauthorized (401)<br>
#### get menus of restaurant 100000
`curl -s http://localhost:8080/rest/restaurants/100000/menu--user user@yandex.ru:password`

**Title** : show menu with id<br>
**URL** : restaurants/menu/menu_id<br>
**Method** : GET<br>
**URL Params** :  Required: menu_id=[integer]<br>
**Response Codes**: Success (200 OK), Unauthorized (401)<br>
#### get menu 100004
`curl -s http://localhost:8080/rest/restaurants/menu/100004--user user@yandex.ru:password`

**Title** : show dishes of menu with id<br>
**URL** : restaurants/menu/menu_id/dishes<br>
**Method** : GET<br>
**URL Params** :  Required: menu_id=[integer]<br>
**Response Codes**: Success (200 OK), Unauthorized (401)<br>
#### get dishes of menu 100004
`curl -s http://localhost:8080/rest/restaurants/menu/100004/dishes--user user@yandex.ru:password`

**Title** : show dish with id<br>
**URL** : restaurants/menu/dishes/dish_id<br>
**Method** : GET<br>
**URL Params** :  Required: dish_id=[integer]<br>
**Response Codes**: Success (200 OK), Unauthorized (401)<br>
#### get dish 100008
`curl -s http://localhost:8080/rest/restaurants/menu/dishes/100008--user user@yandex.ru:password`

**Title** : show dish with id<br>
**URL** : restaurants/restaurant_id/menus/menu_id/rating<br>
**Method** : GET<br>
**URL Params** :  Required: restaurant_id=[integer] and menu_id=[integer]<br>
**Response Codes**: Success (200 OK), Unauthorized (401)<br>
#### get restaurant with rating 100000
`curl -s http://localhost:8080/rest/restaurants/100000/menu/100004/rating--user user@yandex.ru:password`

### Test AdminRestaurantRestController

**Title** : add restaurant<br>
**URL** : admin/restaurants<br>
**Method** : POST<br>
**Data Params** : { { name : [string]} }<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### create Restaurant
`curl -s -X POST -d '{"name":"mac"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin`

**Title** : add menu<br>
**URL** : admin/restaurants/restaurant_id/menu<br>
**Method** : POST<br>
**URL Params** :  Required: restaurant_id=[integer]<br>
**Data Params** : { { date : [date]} }<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### create Menu for restaurant 100000
`curl -s -X POST -d '{"date":"2018-01-01"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100000/menu --user admin@gmail.com:admin`

**Title** : add dish<br>
**URL** : admin/restaurants/menu/menu_id/dish<br>
**Method** : POST<br>
**URL Params** :  Required: menu_id=[integer]<br>
**Data Params** : { { name : [string], price : [integer]} }<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### create Dish for menu 100004
`curl -s -X POST -d '{"name":"meat","price":""100"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/menu/100004/dish --user admin@gmail.com:admin`

**Title** : delete Restaurant with id<br>
**URL** : admin/restaurants/restaurant_id<br>
**Method** : DELETE<br>
**URL Params** :  Required: restaurant_id=[integer]<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### delete Restaurant 100000
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100000 --user admin@gmail.com:admin`

**Title** : delete Menu with id<br>
**URL** : admin/restaurants/menu/menu_id<br>
**Method** : DELETE<br>
**URL Params** :  Required: menu_id=[integer]<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### delete Menu 100004
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/menu/100004 --user admin@gmail.com:admin`

**Title** : delete Dish with id<br>
**URL** : admin/restaurants/menu/dish/dish_id<br>
**Method** : DELETE<br>
**URL Params** :  Required: dish_id=[integer]<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### delete Dish 100008
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/menu/dish/100008 --user admin@gmail.com:admin`

**Title** : update restaurant<br>
**URL** : admin/restaurants/restaurant_id<br>
**Method** : PUT<br>
**URL Params** :  Required: restaurant_id=[integer]<br>
**Data Params** : { { name : [string]} }<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### updtae Restaurant 100000
`curl -s -X PUT -d '{"name":"mac1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/admin/restaurants/100000 --user admin@gmail.com:admin`

**Title** : update dish<br>
**URL** : admin/restaurants/menu/menu_id/dish/dish_id<br>
**Method** : PUT<br>
**URL Params** :  Required: menu_id=[integer] and dish_id=[integer]<br>
**Data Params** : { { name : [string], price : [integer]} }<br>
**Response Codes**: Success (200 OK), Forbidden (403), Unauthorized (401)<br>
#### update Dish 100008 for menu 100004
`curl -s -X PUT -d '{"name":"meat1","price":""1000"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/topjava/rest/admin/restaurants/menu/100004/dish/100008 --user admin@gmail.com:admin`