import axios from "axios";
import { useEffect, useState } from "react";
import serverUrl from "../../../serverUrl";
import propTypes from 'prop-types';

const AdminLessonTemplate = ({ clickEvent, editPage, lesson, deleteLesson }) => {

    const [targetGrades, setTargetGrades] = useState([]);
    const [schoolClassifications, setSchoolClassifications] = useState([]);
    const [subjects, setSubjects] = useState([]);
    const [lessonStatus, setLessonStatus] = useState([]);

    useEffect(() => {
        // getTargetGrades();
        getSchoolClassification();
        getSubjects();
        getLessonStatus();
    }, []);

    const getSchoolClassification = () => {
        axios.get("/common/school-classification")
            .then(res => {
                setSchoolClassifications(res.data.data);
            })
    }

    const getTargetGrades = (e) => {
        const schoolClassification = e.target.value
        axios.get(serverUrl + `/common/target-grades?schoolClassification=${schoolClassification}`)
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
        axios.get(serverUrl + "/common/lesson-status")
            .then(res => {
                const data = res.data.data;
                setLessonStatus(data);
            });
    }

    const EventBtn = () => {
        if (editPage) {
            return (
                <>
                    <button type="button" className="btn btn-danger me-3 ms-2" onClick={deleteLesson}>삭제</button>
                    <button type="button" className="btn btn-warning" onClick={clickEvent}>수정</button>
                </>
            );
        }
        return <button type="button" className="btn btn-primary" onClick={clickEvent}>등록</button>;
    }

    return (
        <div className="container">
            <div className="p-5 mb-4 bg-body-tertiary border rounded-3">
                <form>
                    <div className="row p-4 align-items-center">
                        <div className="col-1 d-flex justify-content-end">
                            <label htmlFor="lessonName">수업명</label>
                        </div>
                        <div className="col-11">
                            <input className="form-control" name="lessonName" id="lessonName" defaultValue={lesson ? lesson.lessonName : ""} />
                        </div>
                    </div>

                    <div className="row p-4  align-items-center">
                        <div className="col-1 d-flex justify-content-end">
                            <label>학교 분류</label>
                        </div>
                        <div className="col-11">
                            <select className="form-select"
                                id="schoolClassification"
                                defaultValue={lesson ? lesson.targetGradeCode : ""}
                                key={lesson ? lesson.targetGradeCode : ""}
                                onChange={getTargetGrades}>
                                <option value="" disabled>== 선택 ==</option>
                                {schoolClassifications.map(schoolClassification => {
                                    return (
                                        <option value={schoolClassification.code} key={schoolClassification.code}>{schoolClassification.name}</option>
                                    );
                                })}
                            </select>
                        </div>
                    </div>

                    <div className="row p-4  align-items-center">
                        <div className="col-1 d-flex justify-content-end">
                            <label>대상학년</label>
                        </div>
                        <div className="col-11">
                            <select className="form-select"
                                id="targetGrade"
                                defaultValue={lesson ? lesson.targetGradeCode : ""}
                                key={lesson ? lesson.targetGradeCode : ""}>
                                <option value="" disabled>학년선택</option>
                                {targetGrades.map(grade => {
                                    return (
                                        <option value={grade.code} key={grade.code}>{grade.name}</option>
                                    );
                                })}
                            </select>
                        </div>
                    </div>

                    <div className="row p-4  align-items-center">
                        <div className="col-1 d-flex justify-content-end">
                            <label>과목</label>
                        </div>
                        <div className="col-11">
                            <select className="form-select"
                                id="subject"
                                defaultValue={lesson ? lesson.subjectId : ""}
                                key={lesson ? lesson.subjectId : ""}>
                                <option value="" disabled>과목선택</option>
                                {subjects.map(subject => {
                                    return (
                                        <option value={subject.subjectId} key={subject.subjectId}>{subject.name}</option>
                                    );
                                })}
                            </select>
                        </div>
                    </div>

                    <div className="row p-4  align-items-center">
                        <div className="col-1 d-flex justify-content-end">
                            <label>진행상태</label>
                        </div>
                        <div className="col-11">
                            <select className="form-select"
                                id="lessonStatus"
                                defaultValue={lesson ? lesson.lessonStatusCode : ""}
                                key={lesson ? lesson.lessonStatusCode : ""}>
                                <option value="" disabled>선택</option>
                                {lessonStatus.map(status => {
                                    return (
                                        <option value={status.code} defaultValue={status.code} key={status.code}>{status.name}</option>
                                    );
                                })}
                            </select>
                        </div>
                    </div>

                    <div className="row p-4  align-items-center">
                        <div className="col-1 d-flex justify-content-end">
                            <label htmlFor="startDate">시작일</label>
                        </div>
                        <div className="col-11">
                            <input className="form-control" name="startDate" id="startDate" defaultValue={lesson ? lesson.startDate : ""} />
                        </div>
                    </div>

                    <div className="row mt-4">
                        <div className="col-10"></div>
                        <div className="col-2 d-flex flex-row-reverse">
                            <EventBtn />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}


AdminLessonTemplate.propTypes = {
    editPage: propTypes.bool
}

AdminLessonTemplate.defaultProps = {
    editPage: false
}


export default AdminLessonTemplate;