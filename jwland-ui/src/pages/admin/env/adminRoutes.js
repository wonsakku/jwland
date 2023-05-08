import adminMenu from "./adminMenu";
import adminLessonRoutes from "../lesson/env/adminLessonRoutes";
import adminExamRoutes from "../exam/env/adminExamRoutes";
import adminSubjectRoutes from "../subject/env/adminSubjectRoutes";

const adminRoutes = [
    ...adminMenu,
    ...adminLessonRoutes,
    ...adminExamRoutes,
    ...adminSubjectRoutes
]

export default adminRoutes;

