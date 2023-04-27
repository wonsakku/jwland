import AdminAttendanceManage from "../AdminAttendanceManage";
import AdminLessonEdit from "../AdminLessonEdit";
import AdminLessonEnroll from "../AdminLessonEnroll";
import AdminLessonStudentManage from "../AdminLessonStudentManage";


const adminLessonRoutes = [
    {
        path: "/admin/lessons/enroll",
        component: AdminLessonEnroll
    },
    {
        path: "/admin/lessons/:lessonId",
        component: AdminLessonEdit
    },
    {
        path: "/admin/lessons/:lessonId/students",
        component: AdminLessonStudentManage
    },
    {
        path: "/admin/lessons/:lessonId/attendance",
        component: AdminAttendanceManage
    }

]

export default adminLessonRoutes;