# WebShop Projekt
### Projektdeltagere
**Ahmad**  
cph-aa540@cphbusiness.dk  
@AhmadAlkaseb  

**Hanni**  
cph-ha186@cphbusiness.dk  
@HanniSalman  

**Lasse**  
cph-lh225@cphbusiness.dk  
@Lassekh  

**Youssef**  
cph-yb48@cphbusiness.dk  
@Badranyoussef  

### Beskrivelse for hjemmesiden
Brugere kan oprette sig og logge på hjemmesiden. Hver bruger har mulighed for at oprette et salg af et produkt. Man kan kun opdatere og slette ens egne salg. Alle brugere på hjemmesiden kan se andres produkter som er til salg. Herfra kan man kontakte personen via sælgerens kontaktoplysninger.

### Krav til hjemmesiden

- [ ] Components
- [ ] A facade for data fetching etc
- [ ] React Router
- [ ] Hooks: useState, useEffect, useParams
- [ ] Props
- [ ] Conditional Rendering
- [ ] Lifting state
- [ ] Controlled input forms
- [ ] Callbacks
- [ ] Authentication and Authorization (Login/Logout/Register/JWT handling)
- [ ] Error handling (Show error messages from the backend)
- [ ] Loading indicators
- [ ] Use of a CSS framework (styled-components, react-icons etc.)
- [ ] All CRUD operations

### Endpoints documentation 

### Path: .../api/auth
<details>
 <summary><code>POST</code> <code><b>/login</b></code> <code>Login</code></summary>

##### Parameters
None

##### Body
```json
{
  "email": "...",
  "password": "..."
}
```

##### RouteRoles
Anyone

##### Response
application/json

##### Exceptions
- Exception: APIException
- Status: 401

</details>

<details>
 <summary><code>POST</code> <code><b>/register</b></code> <code>Register</code></summary>

##### Parameters
None

##### Body
```json
{
  "email": "...",
  "password": "..."
}
```

##### Response
application/json

##### RouteRoles
Anyone

##### Exceptions
- Exception: APIException
- Status: 422

</details>
<p></p>  

### Path: .../api/items  

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Get all items</code></summary>

##### Parameters
None

##### Body
None

##### Response
application/json

##### RouteRoles
User & Admin

##### Exceptions
- Exception: APIException
- Status: 404
- 
</details>

<details>
 <summary><code>GET</code> <code><b>/{id}</b></code> <code>Get item by id</code></summary>

##### Parameters
{id} = item id  
type = int

##### Body
None

##### Response
application/json

##### RouteRoles
User & Admin

##### Exceptions
- Exception: APIException
- Status: 404

</details>

<details>
 <summary><code>POST</code> <code><b>/</b></code> <code>Upload item</code></summary>

##### Parameters
None

##### Body
```json
{
  "title": "...",
  "description": "...",
  "price": ...,
  "fullName": "...",
  "address": "...",
  "postalCode": ...,
  "phoneNumber": ...,
  "user": {
    "email": "{user_id}"
  }
}
```

##### Response
application/json

##### RouteRoles
User & Admin

##### Exceptions
- Exception: APIException
- Status: 500

</details>

<details>
 <summary><code>PUT</code> <code><b>/{user_id}</b></code> <code>Update item</code></summary>

##### Parameters
{user_id} = user email  
type = String

##### Body
```json
{
  "title": "...",
  "description": "...",
  "price": ...,
  "fullName": "...",
  "address": "...",
  "postalCode": ...,
  "status": ...,
  "phoneNumber": ...,
  }
}
```

##### Response
application/json

##### RouteRoles
User & Admin

##### Exceptions
- Exception: APIException
- Status: 404

</details>

<details>
 <summary><code>DELETE</code> <code><b>/{user_id}</b></code> <code>Delete item</code></summary>

##### Parameters
{user_id} = user email  
type = String

##### Body
```json
(number)
```

##### Response
application/json

##### RouteRoles
User & Admin

##### Exceptions
- Exception: APIException
- Status: 404

</details>

### Links


**Backend repository**  
[Tryk her](https://github.com/AhmadAlkaseb/WebShopBackend)  

**Frontend repository**  
[Tryk her](https://github.com/AhmadAlkaseb/WebShopFrontend)

**PowerPoint Design**  
[Tryk her](https://docs.google.com/presentation/d/1tMKkrRPzy8CNIkY6ZMPVO2yr8kHRrLsOsiXY3-lI6dY/edit#slide=id.p) 

**Hjemmesiden**  
[Tryk her](https://cphbusinessprojekt.dk/)  

**Projektbeskrivelsen**  
[Tryk her](https://github.com/dat3Cph/material/blob/sem2024spring/flowFrontend/week5-project/miniproject.md)  

**Eksamensspørgsmål**  
[Tryk her](https://docs.google.com/document/d/16wdDaEkcoUTti7GsFC0CHU0pBQGzmmDIOXkZgNhGON8/edit)  
