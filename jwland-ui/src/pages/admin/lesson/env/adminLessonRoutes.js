import AdminLessonEdit from "../AdminLessonEdit";
import AdminLessonEnroll from "../AdminLessonEnroll";

const adminLessonRoutes = [
    {
        path: "/admin/lessons/enroll",
        component: AdminLessonEnroll
    },
    {
        path: "/admin/lessons/:lessonId",
        component: AdminLessonEdit
    },

]

export default adminLessonRoutes;