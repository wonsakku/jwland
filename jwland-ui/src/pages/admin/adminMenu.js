import AdminSubject from "./subject/AdminSubject";

const adminMenu = [
    {
        path: "/admin/subjects",
        title: "과목 관리",
        component: AdminSubject
    },
    {
        path: "/admin/lessons",
        title: "수업 관리",
        component: ""
    },
    {
        path: "/admin/students",
        title: "학생 관리",
        component: ""
    },
    {
        path: "/admin/exams",
        title: "시험 관리",
        component: ""
    }
]

export default adminMenu;