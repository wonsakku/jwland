import adminMenu from "./adminMenu";
import adminLessonRoutes from "../lesson/env/adminLessonRoutes";
import adminExamRoutes from "../exam/env/adminExamRoutes";

const adminRoutes = [
    ...adminMenu,
    ...adminLessonRoutes,
    ...adminExamRoutes
]

export default adminRoutes;

