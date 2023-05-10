import AdminExamSubject from "../AdminExamSubject";
import AdminExamSubjectProblem from "../AdminExamSubjectProblem";
import adminExamMenu from "./adminExamMenu";

const adminExamRoutes = [
    {
        "path": "/admin/exams/:examId/subjects",
        component: AdminExamSubject
    },
    {
        "path": "/admin/exams/:examId/subjects/:examSubjectId/problems",
        component: AdminExamSubjectProblem
    },
    ...adminExamMenu
]

export default adminExamRoutes;