import { Button, Modal, ModalBody, ModalFooter, ModalHeader, ModalTitle } from "react-bootstrap";
import { useState } from "react";
import * as jwt from "../../../jwt";
import axios from "axios";
import { useEffect } from "react";
import AdminPageExamCard from "../components/AdminPageExamCard";

const AdminExamProblem = () => {

    const [modalShow, setModalShow] = useState(false);
    const [organizations, setOrganizations] = useState([]);
    const [editPopup, setEditPopup] = useState(false);
    const [popupExam, setPopupExam] = useState({});
    const [exams, setExams] = useState([]);
    const [pageable, setPageable] = useState({});

    const handleClose = () => {
        setModalShow(false);
        setPopupExam({});
    };
    const enrollPopup = () => {
        setEditPopup(false);
        setModalShow(true);
    };

    const updatePopup = () => {
        setEditPopup(true);
        setModalShow(true);
    };

    useEffect(() => {
        getOrganizations();
        getExams();
    }, []);


    const getOrganizations = () => {
        axios.get("/admin/exams/organizations", {
            headers: jwt.authHeader()
        }).then(res => {
            setOrganizations(res.data.data);
        }).catch(err => {
            console.log(err.response.data.data);
        })
    }

    const getExams = () => {
        axios.get("/admin/exams", {
            headers: jwt.authHeader()
        }).then(res => {
            setExams(res.data.data.content);
            setPageable(res.data.data.pageable);
        }).catch(err => {
            alert(err.response.data.data);
        })
    }

    const enroll = () => {


        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post("/admin/exams", popupExam, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("등록되었습니다.");
            handleClose();
            getExams();
        }).catch(err => {
            alert(err.response.data.data);
        });
    }

    const update = () => {
        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put("/admin/exams", popupExam, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("수정되었습니다.");
            handleClose();
            getExams();
        }).catch(err => {
            alert(err.response.data.data);
        });
    }

    const deleteExam = () => {

        console.log(popupExam);

        if (!window.confirm("삭제하시겠습니까?")) {
            return;
        }

        axios.delete(`/admin/exams/${popupExam.examId}`, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("삭제되었습니다.");
            handleClose();
            getExams();
        })
    }



    const getExamDetail = (examId) => {
        updatePopup();
        axios.get(`/admin/exams/${examId}`, {
            headers: jwt.authHeader()
        }).then(res => {
            console.log(res.data.data);
            setPopupExam(res.data.data);
        });
    }

    const OrganizationSelectBox = () => {
        return (
            <select className="form-select"
                defaultValue={popupExam.organizationId == null ? "" : popupExam.organizationId}
                onChange={(e) => {
                    popupExam.organizationId = Number(e.target.value);
                }}>
                <option value="" disabled>선택</option>
                {organizations.map(organization => {
                    return <option value={organization.organizationId} key={organization.organizationId}>{organization.name}</option>
                })}
            </select>
        );
    }


    return (
        <>
            <div className="mt-5 d-flex justify-content-between">
                <h2>시험 문제 관리</h2>
                <div className="me-4">
                    <Button className="btn btn-primary" onClick={enrollPopup}>
                        시험 등록
                    </Button>
                </div>
            </div>
            <div className="container mt-5">
                <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3 mx-auto">
                    {exams.map(exam => {
                        return (
                            <div key={exam.examId}>
                                <AdminPageExamCard title={`${exam.year}년 ${exam.month}월 ${exam.organizationName}`}
                                    onClick={(e) => {
                                        e.stopPropagation();
                                        getExamDetail(exam.examId);
                                    }}
                                >
                                    <button className="btn btn-sm btn-outline-primary">시험과목 등록</button>
                                </AdminPageExamCard>
                            </div>
                        )
                    })}
                </div>
            </div>

            <Modal className="modal fade modal-lg" show={modalShow} onHide={handleClose}>
                <ModalHeader>
                    <ModalTitle>시험 등록 팝업</ModalTitle>
                </ModalHeader>
                <ModalBody>
                    <div className="container">
                        <div className="row">
                            <div className="col-2 my-auto text-end">
                                <label htmlFor=""><b>연도</b></label>
                            </div>
                            <div className="col-10">
                                <input type="number" className="form-control" defaultValue={popupExam.year} onChange={(e) => {
                                    popupExam.year = e.target.value;
                                }} />
                            </div>
                        </div>
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label htmlFor=""><b>월</b></label>
                            </div>
                            <div className="col-10">
                                <input type="number" className="form-control" defaultValue={popupExam.month} onChange={e => {
                                    popupExam.month = e.target.value;
                                }} />
                            </div>
                        </div>
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label htmlFor=""><b>주최 기관</b></label>
                            </div>
                            <div className="col-10">
                                <OrganizationSelectBox />

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
                                <button className="btn btn-danger me-1" onClick={deleteExam}>삭제</button>
                            </>
                        }
                        <button className="btn btn-secondary me-1">닫기</button>
                    </div>
                </ModalFooter>
            </Modal>
        </>
    );
}

export default AdminExamProblem;