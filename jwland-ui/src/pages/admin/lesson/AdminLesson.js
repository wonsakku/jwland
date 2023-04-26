import axios from "axios";
import { useEffect, useState } from "react";
import serverUrl from "../../../serverUrl";
import AdminPageCard from "../components/AdminPageCard";
import { Link } from "react-router-dom";

const AdminLesson = () => {

    const [lessons, setLessons] = useState([]);

    useEffect(() => {
        getLessons();
    }, []);

    const getLessons = () => {
        axios.get(serverUrl + "/lessons")
            .then(res => {
                const data = res.data;
                console.log(data);
                setLessons(data.data);
            });
    }




    return (
        <>
            <div className="d-flex justify-content-between py-5 mb-5">
                <h2 className="">수업 관리 페이지</h2>
                <div>
                    <Link className="btn btn-primary me-2" to="/admin/lessons/enroll">수업 등록</Link>
                </div>
            </div>
            <div className="album py-5 bg-body-tertiary">
                <div className="container">
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">

                        {lessons.map(lesson => {
                            return (
                                <AdminPageCard title={lesson.lessonName} path={`/admin/lessons/${lesson.id}`} key={lesson.id}>
                                    <Link
                                        type="button"
                                        className="fw-bold btn btn-outline-primary"
                                        to={`/admin/lessons/${lesson.id}/students?lessonName=${lesson.lessonName}&subjectName=${lesson.subjectName}`}>
                                        학생관리
                                    </Link>
                                    <button type="button" className="fw-bold btn btn-outline-danger">출결관리</button>
                                </AdminPageCard>
                            );
                        })}
                    </div>
                </div>
            </div>

        </>
    );
}

export default AdminLesson;