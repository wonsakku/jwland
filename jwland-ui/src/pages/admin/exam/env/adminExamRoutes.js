import AdminExamSubject from "../AdminExamSubject";
import adminExamMenu from "./adminExamMenu";

const adminExamRoutes = [
    {
        "path": "/admin/exams/:examId/subjects",
        component: AdminExamSubject
    },
    ...adminExamMenu
]

export default adminExamRoutes;