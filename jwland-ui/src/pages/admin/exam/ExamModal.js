import { Button, Modal, ModalBody, ModalFooter, ModalHeader, ModalTitle } from "react-bootstrap";

const ExamModal = ({ modalShow, handleClose }) => {




    return (
        <>


            <Modal className="modal fade modal-lg" show={modalShow} onHide={handleClose}>
                <ModalHeader>
                    <ModalTitle>시험 등록 팝업</ModalTitle>
                </ModalHeader>
                <ModalBody>
                    <div className="container">
                        <div className="row">
                            <div className="col-2 my-auto text-end">
                                <label for=""><b>연도</b></label>
                            </div>
                            <div className="col-10">
                                <input type="text" className="form-control" />
                            </div>
                        </div>
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label for=""><b>월</b></label>
                            </div>
                            <div className="col-10">
                                <input type="text" className="form-control" />
                            </div>
                        </div>
                        <div className="row mt-3">
                            <div className="col-2 my-auto text-end">
                                <label for=""><b>주최 기관</b></label>
                            </div>
                            <div className="col-10">
                                <input type="text" className="form-control" />
                            </div>
                        </div>



                    </div>
                </ModalBody>
                <ModalFooter>
                    닫기
                </ModalFooter>
            </Modal>
        </>
    );
}




export default ExamModal;