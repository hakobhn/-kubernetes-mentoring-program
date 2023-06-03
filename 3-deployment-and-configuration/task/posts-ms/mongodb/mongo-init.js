db.createUser(
    {
        user: "demo",
        pwd: "dockerdemo123!",
        roles: [
            { role: "readWrite", db: "dockerdemo" }
        ]
    })