import Home from "./pages/Home";
import Admin from "./pages/admin/Admin";
import AdminSubject from "./pages/admin/subject/AdminSubject";

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

export default routes;