# JSON Server

Install JSON Server

```
npm install -g json-server
```

Create a `db.json` file with test data

```json
{
  "users": [
    {
      "firstName": "Daria",
      "lastName": "Boris",
      "subjectId": 1,
      "id": 1
    },
    {
      "firstName": "Beatrice",
      "lastName": "Morari",
      "subjectId": 2,
      "id": 2
    },
    {
      "firstName": "Rita",
      "lastName": "Dexter",
      "subjectId": 1,
      "id": 3
    }
  ],
  "subjects": [
    {
      "id": 1,
      "name": "Automation"
    },
    {
      "id": 2,
      "name": "Programming"
    }
  ]
}
```

Start JSON Server

```bash
json-server --watch db.json
```

```

Also when doing requests, it's good to know that:

- If you make POST, PUT, PATCH or DELETE requests, changes will be automatically and safely saved to `db.json` using [lowdb](https://github.com/typicode/lowdb).
- Your request body JSON should be object enclosed, just like the GET output. (for example `{"name": "Foobar"}`)
- Id values are not mutable. Any `id` value in the body of your PUT or PATCH request will be ignored. Only a value set in a POST request will be respected, but only if not already taken.
- A POST, PUT or PATCH request should include a `Content-Type: application/json` header to use the JSON in the request body. Otherwise it will return a 2XX status code, but without changes being made to the data.
