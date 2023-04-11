const settings = {

    jwt: {
        key: "jwtToken",
        authKey: "authorization",
        bearer: "Bearer ",
        account: {
            idKey: "accountId",
            authKey: "auth"
        },
        roles: {
            admin: "ROLE_ADMIN",
            user: "ROLE_USER"
        }
    },

    server: {
        url: "http://localhost:8080"
    }



}


export default settings;