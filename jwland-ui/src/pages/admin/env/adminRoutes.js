import adminMenu from "./adminMenu";
import adminLessonRoutes from "../lesson/env/adminLessonRoutes";

const adminRoutes = [
    ...adminMenu,
    ...adminLessonRoutes
]

export default adminRoutes;

