import AdminSubjectProblemLargeType from "../AdminSubjectProblemLargeType";
import AdminSubjectProblemMiddleType from "../AdminSubjectProblemMiddleType";
import AdminSubjectProblemSmallType from "../AdminSubjectProblemSmallType";

const adminSubjectRoutes = [
    {
        path: "/admin/subjects/:subjectId/problem-types/large",
        component: AdminSubjectProblemLargeType
    }
    ,
    {
        path: "/admin/subjects/:subjectId/problem-types/large/:parentId",
        component: AdminSubjectProblemMiddleType
    }
    ,
    {
        path: "/admin/subjects/:subjectId/problem-types/middle/:parentId",
        component: AdminSubjectProblemSmallType
    }


]

export default adminSubjectRoutes;