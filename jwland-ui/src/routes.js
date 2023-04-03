import Home from "./pages/Home";
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

    ...adminRoutes,

]

export default routes;