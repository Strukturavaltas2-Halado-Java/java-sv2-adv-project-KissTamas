# Marketplace alkalmazás

## Leírás

Ez az alkalmazás egy apróhirdetéseket kezelő rendszer a Facebook hasonló szolgáltatása alapján, 
ahol felhasználók regisztrálhatnak és hirdetéseket hozhatnak létre, majd kezelhetik ezeket.

---

## Felépítés

### User entitás

A `User` entitásban tárolódnak a felhasználók. 
A következő attribútumokkal rendelkezik:

* `id` : Long típusú azonosító
* `name` : A felhasználó neve (kötelező)
* `password` : A felhasználó belépési jelszava (kötelező)
* `email` : A felhasználó email címe (kötelező)

Végpontok: 

| HTTP metódus | Végpont                 | Leírás                                                                |
| ------------ | ----------------------- | --------------------------------------------------------------------- |
| GET          | `"/api/users"`          | lekérdezi az összes felhazsnálót                                      |
| GET          | `"/api/users/{id}"`     | lekérdez egy felhasználót `id` alapján                                |
| POST         | `"/api/users"`          | létrehoz egy új felhasználót                                          |
| PUT          | `"/api/users/{id}"`     | egy meglévő felhasználó adatainak módosítása `id` alapján             |
| DELETE       | `"/api/users/{id}"`     | törli a megadott felhasználót `id` alapján                            |

Email cím megadásakor szintaktikai ellenőrzés fut le, érvénytelen email cím esetén saját kivétel dobódik.

---

### Ad entitás 

Az `Ad` entitás egy felhasználó által létrehozott hirdetést tárol.
A következő attribútumokkal rendelkezik:

* `id` : Long típusú azonosító
* `category` : Felsorolásos típus a hirdetés kategóriájára (kötelező)
* `price` : Irányár (kötelező egész szám, nem lehet negatív)
* `place` : Helyszín, ahol átvehető a hirdetett árú (kötelező)
* `description` : Bővebb leírás a hirdetéshez (kötelező)

A `User` és az `Ad` entitások között kétirányú, 1-n kapcsolat van.

Végpontok:

| HTTP metódus | Végpont                 | Leírás                                                           |
| ------------ | ----------------------- | -----------------------------------------------------------------|
| GET          | `"/api/users/ads"`          | lekérdezi az összes hirdetést                                      |
| GET          | `"/api/users/ads?cat="`     | összes hirdetés lekérdezése a paraméter szerinti kategóriában       |
| GET          | `"/api/users/ads?minprice="`          | összes hirdetés lekérdezése a paraméter szerinti minimum ártól       |
| GET          | `"/api/users/ads?maxprice="`          | összes hirdetés lekérdezése a paraméter szerinti maximum árig       |
| GET          | `"/api/users/ads?place="`          | összes hirdetés lekérdezése a paraméter szerinti átvevőhelyen      |
| GET          | `"/api/users/ads/{id}"`     | lekérdez egy hirdetést `id` alapján                                |
| POST         | `"/api/users/{id}/ads"`          | létrehoz egy új hirdetést felhasználó `id` alapján              |
| PUT          | `"/api/users/ads/{id}"`     | egy meglévő hirdetés adatainak módosítása `id` alapján             |
| DELETE       | `"/api/users/ads/{id}"`     | törli a megadott hirdetést `id` alapján                            |

---

## Technológiai részletek

Az alkalmazás Spring Boot keretrendszerrel készült, hárómrétegű, azaz Controller, Service és Repository rétegből áll, 
önmagában telepíthető és futtatható Docker környezetben. Az adatbázis kezelését a MariaDb végzi. 
Létrehozáskor a The Twelve-Factor App ajánlások voltak szem előtt tartva.

---
