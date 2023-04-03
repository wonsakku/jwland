import Home from "./pages/Home";
import Join from "./pages/account/Join";
import Admin from "./pages/admin/Admin";
import adminRoutes from "./pages/admin/env/adminRoutes";

const routes = [

    {
        path: "/",
        component: Home
    },
    {
        path: "/admin",
        component: Admin
    },
    {
        path: "/join",
        component: Join
    },

    ...adminRoutes,

]

export default routes;