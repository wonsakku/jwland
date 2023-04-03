import { Link } from "react-router-dom";

const AdminPageCard = ({ path, title, children }) => {

    return (
        <div className="col">
            <div className="card shadow-sm">
                <Link to={path} >
                    <svg className="bd-placeholder-img card-img-top"
                        width="100%"
                        height="225"
                        role="img"
                        aria-label="Placeholder: Thumbnail"
                        preserveAspectRatio="xMidYMid slice"
                        focusable="false">
                        <title>{title}</title>
                        <rect width="100%" height="100%" fill="#55595c" />
                        <text x="42%" y="50%" fill="#eceeef" dy=".3em">{title}</text>
                    </svg>
                </Link>
                <div className="card-body">
                    <div className="d-flex justify-content-between align-items-center">
                        <div className="btn-group">
                            {children}
                        </div>
                        <small className="text-body-secondary"></small>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AdminPageCard;