import { Link, NavLink } from "react-router-dom";

const Header = () => {

    return (
        <header className="p-3 text-bg-dark">
            <div className="container">
                <div className="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <ul className="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <NavLink className="me-3 nav-link px-2 text-white" to="/">Home</NavLink>
                        <NavLink className="me-3 nav-link px-2 text-white" to="/admin">Admin</NavLink>
                        <NavLink className="me-3 nav-link px-2 text-white" to="/">공지사항</NavLink>
                        <NavLink className="me-3 nav-link px-2 text-white" to="/">클리닉/보충</NavLink>
                        <NavLink className="me-3 nav-link px-2 text-white" to="/">기출 및 문제 오타/정오</NavLink>
                        <NavLink className="me-3 nav-link px-2 text-white" to="/">자료실</NavLink>
                        <NavLink className="me-3 nav-link px-2 text-white" to="/">문의</NavLink>
                    </ul>

                    {/* <form className="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                        <input type="search" className="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search" />
                    </form> */}

                    <div className="text-end">
                        <button type="button" className="btn btn-outline-light me-2">Login</button>
                        <button type="button" className="btn btn-warning">Sign-up</button>
                    </div>
                </div>
            </div>
        </header>
    );
}

export default Header;