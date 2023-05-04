import AdminExamOrganization from "../AdminExamOrganization";
import AdminExamProblem from "../AdminExamProblem";

const adminExamMenu = [
    {
        path: "/admin/exams/organization",
        title: "시험 기관 관리",
        component: AdminExamOrganization
    },
    {
        path: "/admin/exams/problems",
        title: "시험 문제 관리",
        component: AdminExamProblem
    }
]

export default adminExamMenu;