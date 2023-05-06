import adminMenu from "./env/adminMenu";
import AdminPageCard from "./components/AdminPageCard";

const Admin = () => {

    return (
        <>
            <div className="d-flex justify-content-between mt-5">
                <h2 className="">ADMIN PAGE</h2>
            </div>
            <div className="album py-5 bg-body-tertiary">
                <div className="container">
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                        {adminMenu.map(menu => {
                            return (
                                <AdminPageCard path={menu.path} title={menu.title} key={menu.path}>
                                    <button type="button" className="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" className="btn btn-sm btn-outline-secondary">Edit</button>
                                    <button type="button" className="btn btn-sm btn-outline-secondary">Edit</button>
                                </AdminPageCard>
                            );
                        })}
                    </div>
                </div>
            </div>
        </>
    );
}

export default Admin;