import axios from "axios";
import * as jwt from "../../../jwt";
import { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import queryString from "query-string";
import propTypes from 'prop-types';
import AdminLessonAttendanceEnrollTable from "../components/AdminLessonAttendanceEnrollTable";
import AdminLessonAttendanceEditTable from "../components/AdminLessonAttendanceEditTable";



const AdminAttendanceManage = () => {


    const { lessonId } = useParams();
    const { search } = useLocation();
    const queryStrings = queryString.parse(search);
    const lessonName = queryStrings.lessonName;
    const subjectName = queryStrings.subjectName;
    const [editPage, setEditPage] = useState(false);

    const changeEditStatus = () => {
        setEditPage(!editPage);
    }

    return (
        <>
            <div className="d-flex justify-content-between mt-5">
                <h2>{lessonName} ({subjectName}) 출석관리 - {editPage ? <span>수정페이지</span> : <span>등록페이지</span>}</h2>
                <div className="my-auto">
                    <button className="btn btn-secondary" onClick={changeEditStatus}>전환</button>
                </div>
            </div>

            {editPage ?
                <AdminLessonAttendanceEditTable lessonId={lessonId} />
                :
                <AdminLessonAttendanceEnrollTable lessonId={lessonId} />
            }

        </>
    );
}

export default AdminAttendanceManage;