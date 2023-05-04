import { Link } from "react-router-dom";
import ExamModal from "./ExamModal";
import { Button, Modal, ModalBody, ModalFooter, ModalHeader, ModalTitle } from "react-bootstrap";
import { useState } from "react";
import adminExamMenu from "./env/adminExamMenu";
import AdminPageCard from "../components/AdminPageCard";

const AdminExam = () => {

    const [modalShow, setModalShow] = useState(false);
    const handleShow = () => setModalShow(true);
    const handleClose = () => setModalShow(false);


    return (
        <>
            <div className="mt-5 d-flex justify-content-between">
                <h2>시험 관리</h2>
                <div>
                    <Button className="btn btn-primary" onClick={handleShow}>
                        시험 등록
                    </Button>
                    <ExamModal modalShow={modalShow} handleClose={handleClose} />
                </div>
            </div>
            <div className="album py-5 bg-body-tertiary">
                <div className="container">
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">


                        {adminExamMenu.map(menu => {
                            return (
                                <AdminPageCard path={menu.path} title={menu.title} key={menu.path}>
                                    <button type="button" className="btn btn-sm btn-outline-secondary">View</button>
                                    <button type="button" className="btn btn-sm btn-outline-secondary">Edit</button>
                                    <button type="button" className="btn btn-sm btn-outline-secondary">Edit</button>
                                </AdminPageCard>
                            );
                        })}
                    </div>
                </div>
            </div>


        </>

    );
}

export default AdminExam;