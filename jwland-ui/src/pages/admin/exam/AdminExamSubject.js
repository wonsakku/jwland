import { Button, Modal, ModalBody, ModalFooter, ModalHeader, ModalTitle } from "react-bootstrap";
import queryString from "query-string";
import { useEffect, useState } from "react";
import { Link, useLocation, useParams } from "react-router-dom";
import axios from "axios";
import * as jwt from "../../../jwt";
import AdminPageExamCard from "../components/AdminPageExamCard";

const AdminExamSubject = () => {


    const { search } = useLocation();
    const queryStrings = queryString.parse(search);
    const year = queryStrings.year;
    const month = queryStrings.month;
    const organizationName = queryStrings.organizationName;
    const { examId } = useParams();


    const [enrolledSubjects, setEnrolledSubjects] = useState([]);
    const [unenrolledSubjects, setUnenrolledSubjects] = useState([]);
    const [modalShow, setModalShow] = useState(false);
    const [editPopup, setEditPopup] = useState(false);
    const [popupExamSubject, setPopupExamSubject] = useState({ examId: Number(examId) });


    const enrollPopup = () => {
        setEditPopup(false);
        setModalShow(true);
    };

    const updatePopup = (examSubjectId) => {
        const selectedSubject = enrolledSubjects.filter(subject => subject.examSubjectId === examSubjectId)[0];
        popupExamSubject.examSubjectId = selectedSubject.examSubjectId;
        popupExamSubject.subjectId = selectedSubject.subjectId;
        popupExamSubject.subjectName = selectedSubject.subjectName;
        popupExamSubject.problemCount = selectedSubject.problemCount;
        popupExamSubject.perfectScore = selectedSubject.perfectScore;
        popupExamSubject.problemPaperUrl = selectedSubject.problemPaperUrl;

        setEditPopup(true);
        setModalShow(true);
    }

    const handleClose = () => {
        setModalShow(false);
        setPopupExamSubject({ examId: Number(examId) });
    };

    useEffect(() => {
        getSubjects();
    }, []);

    const getSubjects = () => {
        getEnrolledSubjects();
        getUnenrolledSubjects();
    }

    const getEnrolledSubjects = () => {
        axios.get(`/admin/exams/${examId}/enrolled-subjects`, {
            headers: jwt.authHeader()
        }).then(res => {
            setEnrolledSubjects(res.data.data);
        })
    }

    const getUnenrolledSubjects = () => {
        axios.get(`/admin/exams/${examId}/unenrolled-subjects`, {
            headers: jwt.authHeader()
        }).then(res => {
            setUnenrolledSubjects(res.data.data);
        })
    }

    const SubjectSelectBox = () => {
        return (
            <select className="form-select"
                defaultValue={popupExamSubject.subjectId == null ? "" : popupExamSubject.subjectId}
                onChange={(e) => {
                    popupExamSubject.subjectId = Number(e.target.value);
                }}>
                <option value="" disabled>선택</option>
                {unenrolledSubjects.map(subject => {
                    return <option value={subject.subjectId} key={subject.subjectId}>{subject.subjectName}</option>
                })}
            </select>
        );
    }

    const enroll = () => {
        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(`/admin/exams/subjects`, popupExamSubject, {
            headers: jwt.authHeader()
        }).then(res => {
            console.log(res);
            alert("등록되었습니다.");
            handleClose();
            getSubjects();
        }).catch(err => {
            alert(err.response.data.data);
        });

    }

    const update = () => {
        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put("/admin/exams/subjects", popupExamSubject, {
            headers: jwt.authHeader()
        }).then(res => {
            console.log(res);
            alert("수정되었습니다.");
            handleClose();
            getEnrolledSubjects();
        }).catch(err => {

            alert(err.response.data.data);
        });
    }

    const remove = () => {

        if (!window.confirm("삭제하시겠습니까?")) {
            return;
        }

        axios.delete(`/admin/exams/subjects/${popupExamSubject.examSubjectId}`, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("삭제되었습니다.");
            handleClose();
            getSubjects();
        }).catch(err => {

            if (err.status === 401) {
                alert("인증되지 않은 사용자입니다.");
                return;
            }

            alert(err.response.data.data);
        });
    }



    return (
        <>
            <div className="mt-5 d-flex justify-content-between">
                <h2>{year}년 {month}월 {organizationName} - 시험 과목 관리</h2>
                <div>
                    <Button className="btn btn-primary" onClick={enrollPopup}>
                        과목 등록
                    </Button>
                </div>
            </div>
            <div>
                <div className="container mt-5">
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3 mx-auto">

                        {enrolledSubjects.map(subject => {
                            return <AdminPageExamCard key={subject.examSubjectId} title={subject.subjectName} onClick={() => updatePopup(subject.examSubjectId)}>
                                <Link className="btn btn-sm btn-outline-primary"
                                    to={`/admin/exams/${examId}/subjects/${subject.examSubjectId}/problems?year=${year}&month=${month}&organizationName=${organizationName}&subjectName=${subject.subjectName}`}
                                >문제 등록</Link>
                                <button className="btn btn-sm btn-outline-success">해설 등록</button>
                                <button className="btn btn-sm btn-outline-danger">등급컷 등록</button>
                            </AdminPageExamCard>
                        })}
                    </div>
                </div>
            </div>

            <Modal className="modal fade modal-lg" show={modalShow} onHide={handleClose}>
                <ModalHeader>
                    <ModalTitle>시험 과목 등록 팝업</ModalTitle>
                </ModalHeader>
                <ModalBody>
                    <div className="container">
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label htmlFor=""><b>과목</b></label>
                            </div>
                            <div className="col-10">
                                {
                                    editPopup ?
                                        <input type="text" className="form-control" disabled defaultValue={popupExamSubject.subjectName} />
                                        :
                                        <SubjectSelectBox />
                                }
                            </div>
                        </div>
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label htmlFor=""><b>문항수</b></label>
                            </div>
                            <div className="col-10">
                                <input type="number" className="form-control" defaultValue={popupExamSubject.problemCount} onChange={(e) => {
                                    popupExamSubject.problemCount = Number(e.target.value);
                                }} />
                            </div>
                        </div>
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label htmlFor=""><b>만점 점수</b></label>
                            </div>
                            <div className="col-10">
                                <input type="number" className="form-control" defaultValue={popupExamSubject.perfectScore} onChange={e => {
                                    popupExamSubject.perfectScore = Number(e.target.value);
                                }} />
                            </div>
                        </div>
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label htmlFor=""><b>시험지 링크</b></label>
                            </div>
                            <div className="col-10">
                                <input type="text" className="form-control" defaultValue={popupExamSubject.problemPaperUrl} onChange={e => {
                                    popupExamSubject.problemPaperUrl = e.target.value;
                                }} />
                            </div>
                        </div>
                    </div>
                </ModalBody>
                <ModalFooter>
                    <div>
                        {!editPopup ?
                            <button className="btn btn-primary me-1" onClick={enroll}>등록</button>
                            :
                            <>
                                <button className="btn btn-warning me-1" onClick={update}>수정</button>
                                <button className="btn btn-danger me-1" onClick={remove}>삭제</button>
                            </>
                        }
                        <button className="btn btn-secondary me-1">닫기</button>
                    </div>
                </ModalFooter>
            </Modal>
        </>
    );
}

export default AdminExamSubject;