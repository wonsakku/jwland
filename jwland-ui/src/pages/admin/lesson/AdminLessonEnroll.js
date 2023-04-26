import axios from "axios";
import { useEffect, useState } from "react";
import serverUrl from "../../../serverUrl";
import { useHistory } from "react-router-dom";
import AdminLessonTemplate from "../components/AdminLessonTemplate";
import * as jwt from "../../../jwt";


const AdminLessonEnroll = () => {

    const history = useHistory();

    const enrollLesson = (e) => {

        e.preventDefault();
        const lessonName = document.querySelector("#lessonName").value;
        const schoolClassification = document.querySelector("#schoolClassification").value;
        const targetGradeCode = document.querySelector("#targetGrade").value;
        const subjectId = document.querySelector("#subject").value;
        const lessonStatusCode = document.querySelector("#lessonStatus").value;
        const startDate = document.querySelector("#startDate").value;

        if (lessonName.length < 1) {
            alert("수업명을 입력해주세요");
            return;
        }

        if (startDate.length < 1) {
            alert("수업 시작일을 입력해주세요");
            return;
        }

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        const jwtToken = jwt.authHeader();
        console.log(jwtToken);
        const requetBody = {
            lessonName: lessonName,
            schoolClassification: schoolClassification,
            targetGradeCode: targetGradeCode,
            subjectId: subjectId,
            lessonStatusCode: lessonStatusCode,
            startDate: startDate
        };
        console.log(requetBody);

        axios.post("/admin/lessons", requetBody, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("등록되었습니다.");
            history.push("/admin/lessons");
        });
    }


    return (
        <>
            <h2 className="py-5 col-8">수업 등록 페이지</h2>
            <AdminLessonTemplate clickEvent={enrollLesson} />
        </>
    );
}


export default AdminLessonEnroll;