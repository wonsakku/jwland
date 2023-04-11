import settings from "./settings";

export function login(httpHeader) {

    const jwtToken = httpHeader[settings.jwt.authKey];
    localStorage.setItem(settings.jwt.key, jwtToken);

    return true;
}

export function logout() {
    localStorage.removeItem(settings.jwt.key);
}

export function isAdmin() {
    let roles = null;

    try {
        roles = getRoles();
    } catch (err) {
        return false;
    }

    for (let i = 0; i < roles.length; i++) {

        if (roles[i] === settings.jwt.roles.admin) {
            return true;
        }
    }

    return false;
}

export function isLogined() {
    const jwtToken = localStorage.getItem(settings.jwt.key);

    if (jwtToken !== null) {
        return true;
    }

    return false;
}

export function authHeader() {
    const header = new Object();
    header.authorization = localStorage.getItem(settings.jwt.key);
    return header;
}

function getJwtToken() {
    return localStorage.getItem(settings.jwt.key);
}

function getRoles() {
    const loginInfo = getLoginInfo();
    return loginInfo[settings.jwt.account.authKey];
}

function getLoginInfo() {

    const jwtToken = getJwtToken();
    const bearerSubstracted = jwtToken.replace(settings.jwt.bearer, "");
    const tokenInfos = bearerSubstracted.split(".");
    const strLoginInfo = window.atob(tokenInfos[1]);

    return JSON.parse(strLoginInfo);
}