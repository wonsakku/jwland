import "./login.css";
import bootstrapLogo from "./bootstrap-logo.svg";
import axios from "axios";
import serverUrl from "../../serverUrl";
import { useState } from "react";
import settings from "../../settings";
import * as jwt from "../../jwt";
import { useHistory } from "react-router-dom";

const Login = () => {
    const [token, setToken] = useState(null);
    const history = useHistory();

    const login = () => {

        const loginId = document.querySelector("#loginId").value;
        const password = document.querySelector("#password").value;

        if (loginId === null || loginId.trim().length < 1) {
            alert("ID를 입력해주세요");
            return;
        }

        if (password === null || password.trim().length < 1) {
            alert("password를 입력해주세요.");
            return;
        }

        axios.post("/login", {
            loginId,
            password
        }).then(res => {
            const success = jwt.login(res.headers);
            if (success) {
                history.push("/");
            }
        });
    }

    const test = () => {
        const headers = jwt.authHeader();
        console.log(headers);
        axios.get("/test", {
            headers: headers
        }).then(res => {
            console.log(res);
        })
    }


    return (
        <div className="text-center mt-5">
            <main className="form-signin w-100 m-auto">
                <form>
                    <img className="mb-4" src={bootstrapLogo} alt="" width="72" height="57" />
                    <h1 className="h3 mb-3 fw-normal">Please sign in</h1>

                    <div className="form-floating">
                        <input type="email" className="form-control" id="loginId" required />
                        <label htmlFor="loginId">ID</label>
                    </div>
                    <div className="form-floating mb-3">
                        <input type="password" className="form-control" id="password" required />
                        <label htmlFor="password">Password</label>
                    </div>

                    <button className="w-100 btn btn-lg btn-primary mb-3" type="button" onClick={login}>Sign in</button>
                    <button className="w-100 btn btn-lg btn-warning" type="button" onClick={test}>Test</button>

                </form>
            </main>
        </div>
    );
}

export default Login;