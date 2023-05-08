import { Link, NavLink } from "react-router-dom";
import * as jwt from "../jwt";
import { useState, useEffect } from "react";

const Header = () => {

    const [logined, setLogined] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);

    useEffect(() => {
        isLoginned();
    }, [logined]);

    const logout = () => {
        jwt.logout();
        setLogined(false);
    }

    const isLoginned = () => {
        setLogined(jwt.isLogined());
        setIsAdmin(jwt.isAdmin());
    }

    return (
        <header className="p-3 text-bg-dark">
            <div className="container">
                <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <NavLink className="me-3 nav-link px-2 text-white" to="/">Home</NavLink>
                        {isAdmin &&
                            <NavLink className="me-3 nav-link px-2 text-white" to="/admin">Admin</NavLink>
                        }
                        {logined &&
                            <>
                                <NavLink className="me-3 nav-link px-2 text-white" to="/">공지사항</NavLink>
                                <NavLink className="me-3 nav-link px-2 text-white" to="/">클리닉/보충</NavLink>
                                <NavLink className="me-3 nav-link px-2 text-white" to="/">기출 및 문제 오타/정오</NavLink>
                                <NavLink className="me-3 nav-link px-2 text-white" to="/">자료실</NavLink>
                                <NavLink className="me-3 nav-link px-2 text-white" to="/">문의</NavLink>
                            </>
                        }

                    </ul>
                    <div className="text-end">
                        {logined ?
                            <>
                                <button type="button" className="btn btn-warning me-2" onClick={logout}>로그아웃</button>
                            </>
                            :
                            <>
                                <Link type="button" className="btn btn-primary me-2" to="/login">로그인</Link>
                                <Link type="button" className="btn btn-success" to="/join">회원가입</Link>
                            </>
                        }
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;