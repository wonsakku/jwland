import axios from "axios";
import { useEffect, useState } from "react";
import serverUrl from "../../../serverUrl";
import { useHistory } from "react-router-dom";

const AdminLessonEnroll = () => {

    const history = useHistory();

    const [targetGrades, setTargetGrades] = useState([]);
    const [subjects, setSubjects] = useState([]);
    const [lessonStatus, setLessonStatus] = useState([]);

    useEffect(() => {
        getTargetGrades();
        getSubjects();
        getLessonStatus();
    }, []);

    const getTargetGrades = () => {
        axios.get(serverUrl + "/lessons/target-grades")
            .then(res => {
                const grades = res.data.data;
                setTargetGrades(grades);
            });
    }

    const getSubjects = () => {
        axios.get(serverUrl + "/lessons/subjects")
            .then(res => {
                const data = res.data.data;
                setSubjects(data);
            });
    }

    const getLessonStatus = () => {
        axios.get(serverUrl + "/lessons/lesson-status")
            .then(res => {
                const data = res.data.data;
                setLessonStatus(data);
            });
    }

    const enrollLesson = (e) => {


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

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(serverUrl + "/lessons", {
            lessonName: lessonName,
            targetGradeCode: targetGradeCode,
            subjectId: subjectId,
            lessonStatusCode: lessonStatusCode,
            startDate: startDate
        }).then(res => {
            alert("등록되었습니다.");
            history.push("/admin/lessons");
        });
    }


    return (
        <>
            <h2 className="py-5">수업 등록 페이지</h2>
            <div className="container">
                <div className="p-5 mb-4 bg-body-tertiary border rounded-3">
                    <form>
                        <div className="row p-4">
                            <div className="col-1">
                                <label htmlFor="lessonName">수업명</label>
                            </div>
                            <div className="col-11">
                                <input className="form-control" name="lessonName" id="lessonName" />
                            </div>
                        </div>

                        <div className="row p-4">
                            <div className="col-1">
                                <label>대상학년</label>
                            </div>
                            <div className="col-11">
                                <select className="form-select" id="targetGrade">
                                    {targetGrades.map(grade => {
                                        return (
                                            <option value={grade.code} key={grade.code}>{grade.name}</option>
                                        );
                                    })}
                                </select>
                            </div>
                        </div>

                        <div className="row p-4">
                            <div className="col-1">
                                <label>과목</label>
                            </div>
                            <div className="col-11">
                                <select className="form-select" id="subject">
                                    {subjects.map(subject => {
                                        return (
                                            <option value={subject.subjectId} key={subject.subjectId}>{subject.name}</option>
                                        );
                                    })}
                                </select>
                            </div>
                        </div>

                        <div className="row p-4">
                            <div className="col-1">
                                <label>진행상태</label>
                            </div>
                            <div className="col-11">
                                <select className="form-select" id="lessonStatus">
                                    {lessonStatus.map(status => {
                                        return (
                                            <option value={status.code} key={status.code}>{status.name}</option>
                                        );
                                    })}
                                </select>
                            </div>
                        </div>

                        <div className="row p-4">
                            <div className="col-1">
                                <label htmlFor="startDate">시작일</label>
                            </div>
                            <div className="col-11">
                                <input className="form-control" name="startDate" id="startDate" />
                            </div>
                        </div>

                        <div className="row mt-4">
                            <div className="col-11"></div>
                            <div className="col-1">
                                <button type="button" className="btn btn-primary" onClick={enrollLesson}>등록</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </>
    );
}


export default AdminLessonEnroll;