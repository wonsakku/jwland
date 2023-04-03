import Home from "./pages/Home";
import Admin from "./pages/admin/Admin";
import adminMenu from "./pages/admin/adminMenu";

const routes = [

    {
        path: "/",
        component: Home
    },
    {
        path: "/admin",
        component: Admin
    }
]

adminMenu.forEach(am => routes.push(am));


export default routes;