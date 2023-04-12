import AdminSubject from "../subject/AdminSubject";
import AdminLesson from "../lesson/AdminLesson";
import AdminStudent from "../student/AdminStudent";

const adminMenu = [
    {
        path: "/admin/subjects",
        title: "과목 관리",
        component: AdminSubject
    },
    {
        path: "/admin/lessons",
        title: "수업 관리",
        component: AdminLesson
    },
    {
        path: "/admin/students",
        title: "학생 관리",
        component: AdminStudent
    },
    {
        path: "/admin/exams",
        title: "시험 관리",
        component: ""
    }
]

export default adminMenu;