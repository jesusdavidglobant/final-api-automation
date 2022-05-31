# Bank Transaction API Automation

> Automation test for a mocking of a Transaction Service.

https://user-images.githubusercontent.com/103539437/171072307-a6189ab0-1782-43e1-8160-407b70ee403d.mp4

## Built With

- Java
- RestAssured
- Cucumber

## API Documentation
- API endpoint -> `https://62901378665ea71fe12cf489.mockapi.io/:endpoint`
- `GET` `/bankTransaction` -> repond a list of all bank transactions
- `GET` `/bankTransaction/:id` -> respond a bank transaction by id
- `POST` `/bankTransaction` -> create bank transaction
- `PUT` `/bankTransaction/:id` -> create bank transaction, body with minimal Requirement is need
- `DELETE` `/bankTransaction/:id` -> delete bank transaction by id

```java
// Example Minimal requirements

{
    "name": "Juan",
    "lastName": "Perez",
    "accountNumber": 12131,
    "amount", 999,
    "type": "withdraw",
    "email": "test@test.com",
    "active": true,
    "country": "Colombia"
    "telephone": "+57 1234 ext 1"
}
```

## Authors

👤 **David Alvarez**

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

Feel free to check the [issues page](../../issues/).

## Show your support

Give a ⭐️ if you like this project!

## Acknowledgments

- Hat tip to anyone whose code was used
- Inspiration
- etc

## 📝 License

This project is [MIT](./MIT.md) licensed.
