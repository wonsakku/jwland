import axios from "axios";
import { useEffect, useState } from "react";
import serverUrl from "../../../serverUrl";
import { useHistory, useParams } from "react-router-dom";
import AdminLessonTemplate from "../components/AdminLessonTemplate";

const AdminLessonEdit = () => {

    const history = useHistory();
    const { lessonId } = useParams();

    const [lesson, setLesson] = useState([]);
    useEffect(() => {
        getLesson();
    }, []);

    const getLesson = () => {
        axios.get(serverUrl + `/lessons/${lessonId}`)
            .then(res => {
                setLesson(res.data.data);
            });
    }


    const updateLesson = (e) => {

        e.preventDefault();
        const lessonName = document.querySelector("#lessonName").value;
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

        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put(serverUrl + "/lessons", {
            id: lessonId,
            lessonName: lessonName,
            targetGradeCode: targetGradeCode,
            subjectId: subjectId,
            lessonStatusCode: lessonStatusCode,
            startDate: startDate
        }).then(res => {
            alert("수정되었습니다.");
            history.push("/admin/lessons");
        });
    }

    const deleteLesson = (e) => {
        e.preventDefault();

        if (!window.confirm("삭제하시겠습니까?")) {
            return;
        }

        axios.delete(serverUrl + `/lessons/${lessonId}`).then(() => {
            alert("삭제되었습니다.");
            history.push("/admin/lessons");
        }).catch((error) => {
            const res = error.response;
            const data = res.data;
            const errorMessage = data.data;
            alert(errorMessage);
        });
    }


    return (
        <>
            <h2 className="py-5">수업 수정 페이지</h2>
            <AdminLessonTemplate clickEvent={updateLesson} editPage={true} lesson={lesson} deleteLesson={deleteLesson} />
        </>
    );
}


export default AdminLessonEdit;