<h1 align="center">Trade by bata store</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>

<h1 align="center">
<img src="https://user-images.githubusercontent.com/104917599/224477790-5e215a86-875b-40c2-985e-d113b07fdf22.png" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224477731-d7456e57-9e62-4823-bbba-23c022db5120.png" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224477866-513664a3-ab5f-414d-a8ba-aa92b38a3f7f.png" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224477700-8e135abf-4c55-4348-a25d-9944d51e3d82.png" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224477881-8e665f59-a858-40e9-8dca-6ee2a08e8fbe.png" height="450"/>
</h1>

____


## ShopApp - приложение,которое позволит получить информацию товарах,их цене и скидках

### Аунтефикация: :closed_book:

- Пользователь вводит данные для регистрации,если поля пустые или email введен некорректно,он получит соответсвущую ошибку.
Если все поля заполнены верно,и аккаунта на указанную почту не заведено,пользователь переходит на экран с товарами,иначе пользователь получит ошибку
- Если у пользователя уже есть зарегистрированный аккаунт,то он может перейти на окно логина,нажав на кнопку Log In
Введя корректные данные,пользователь перейдет на экран с товарами,инача получит ошибку

<h1 align="center">
<img src="https://user-images.githubusercontent.com/104917599/224474144-26ac0302-c7c7-44ff-b988-7907fd0bae8d.gif" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224474249-3ebfb297-3f10-48be-a673-37e612d14f35.gif" height="450"/>
</h1>

- Аунтефикация через Google/Apple регистрирует либо авторизует пользователя с данными Google Android

### Ошибки: :red_circle:

#### Регистрация:
- Пользователь не заполнил поля
- Пользователь ввел Email неверно
- Пользователь ввел данные от существующего аккаунта (поля Email и First Name - уникальны)

<h1 align="center">
<img src="https://user-images.githubusercontent.com/104917599/224477061-6f55fb1f-4fdd-4bf5-a2b7-976ffc855f0e.png" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224477065-73827506-1167-46e3-a8a6-ca54a0bb0a3a.png" height="450"/>
<img src= "https://user-images.githubusercontent.com/104917599/224477138-255f8f8f-1e97-4001-adcf-41a0fc995dd8.png" height="450"/>
</h1>

#### Авторизация

- Пользователь не заполнил поля
- Пользователь ввел некорректные данные

<h1 align="center">
<img src="https://user-images.githubusercontent.com/104917599/224477592-332426b2-a704-4875-8d79-f422e8e6666f.png" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224477595-f34f05cc-042f-4318-b972-48e5afffb389.png" height="450"/>
</h1>


____

### Главный экран: :blue_book:

#### Если у пользователя есть интернет соеденение,то происходит загрузка данных,иначе спиоск товаров будет пуст.Загрузка данных происходит с 2х разных API, если запрос к одному из них заканчивается ошибкой,список также будет пуст. В случае,когда запрос не удался,пользователь получает Toast с ошибкой 

#### На экране пользователь может:
- посмотреть категории товаров
- посмотреть последние товары 
- посмотреть товары со скидками 
- воспользоваться поиском товаров 
- перейти на окно с детальной информацией 
#### На окне с детальной информацией пользователь может:
 - посмотреть доступные фотографии 
 - посмотреть доступные цвета 
 - посмотреть описание товара 
 - добавить товар в корзину 
<h1 align="center">
<img src="https://user-images.githubusercontent.com/104917599/224474313-24351398-d15e-4a02-bde3-1f7f79b71cd5.gif" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224474342-1022ce01-8576-43d0-8b98-2edb52e32b74.gif" height="450"/>
</h1>




____

### Экран с персональной информацией: :green_book:

#### Пользователь видит всю доступную информацию о себе:
- видит свои имя и фамилию 
- видит свое изображение 
- видит баланс 
- может изменить свою фотографию 
- может выйти из приложения 

<h1 align="center">
<img src="https://user-images.githubusercontent.com/104917599/224474555-828b67c3-62dd-405e-b2df-bb919299b32f.gif" height="450"/>
<img src="https://user-images.githubusercontent.com/104917599/224474583-c1270a32-b98b-42a7-919c-d0f52d987840.gif" height="450"/>
</h1>


____

## Стек технологий 

*  Clean Architecture

*  MVVM

*  Dagger2

*  Retrofit2, okHttp

*  Room

*  Navigation Component,SafeArgs

*  Kotlin's Coroutines

*  Kotlin's Flows
____


