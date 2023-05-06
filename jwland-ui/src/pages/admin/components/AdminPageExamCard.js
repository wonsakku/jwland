const AdminPageExamCard = ({ title, children, onClick }) => {

    return (
        <div className="col" style={{ "cursor": "pointer" }}>
            <div className="card shadow-sm">
                <svg className="bd-placeholder-img card-img-top"
                    width="100%"
                    height="225"
                    role="img"
                    aria-label="Placeholder: Thumbnail"
                    preserveAspectRatio="xMidYMid slice"
                    focusable="false"
                    onClick={onClick}>
                    <title>{title}</title>
                    <rect width="100%" height="100%" fill="#55595c" />
                    <text x="50%" y="50%" fill="#eceeef" dy=".3em">{title}</text>
                </svg>
                <div className="card-body">
                    <div className="d-flex justify-content-end align-items-center">
                        <div className="btn-group me-3">
                            {children}
                        </div>
                        <small className="text-body-secondary"></small>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AdminPageExamCard;