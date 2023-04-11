import Home from "./pages/Home";
import Join from "./pages/account/Join";
import Login from "./pages/account/Login";
import Admin from "./pages/admin/Admin";
import adminRoutes from "./pages/admin/env/adminRoutes";

const routes = [

    {
        path: "/",
        component: Home
    },
    {
        path: "/admin",
        component: Admin,
        accessible: "admin"
    },
    {
        path: "/join",
        component: Join
    },
    {
        path: "/login",
        component: Login
    },


    ...adminRoutes,

]

export default routes;