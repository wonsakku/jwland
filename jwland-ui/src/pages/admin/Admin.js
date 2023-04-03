import { BrowserRouter as Router, Route, Switch, Link } from "react-router-dom";
import adminMenu from "./adminMenu";

const Admin = () => {
    return (
        <>
            <h2 className="py-5">ADMIN PAGE</h2>
            <div className="album py-5 bg-body-tertiary">
                <div className="container">
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                        {adminMenu.map(menu => {
                            return (
                                <div className="col" key={menu.path}>
                                    <div className="card shadow-sm">
                                        <Link to={menu.path} >
                                            <svg className="bd-placeholder-img card-img-top"
                                                width="100%"
                                                height="225"
                                                role="img"
                                                aria-label="Placeholder: Thumbnail"
                                                preserveAspectRatio="xMidYMid slice"
                                                focusable="false">
                                                <title>{menu.title}</title>
                                                <rect width="100%" height="100%" fill="#55595c" />
                                                <text x="42%" y="50%" fill="#eceeef" dy=".3em">{menu.title}</text>
                                            </svg>
                                        </Link>
                                        <div className="card-body">
                                            <div className="d-flex justify-content-between align-items-center">
                                                <div className="btn-group">
                                                    <button type="button" className="btn btn-sm btn-outline-secondary">View</button>
                                                    <button type="button" className="btn btn-sm btn-outline-secondary">Edit</button>
                                                    <button type="button" className="btn btn-sm btn-outline-secondary">Edit</button>
                                                </div>
                                                <small className="text-body-secondary"></small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            );
                        })}
                    </div>
                </div>
            </div>
        </>
    );
}

export default Admin;