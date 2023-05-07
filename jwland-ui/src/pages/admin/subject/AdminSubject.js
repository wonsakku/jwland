import axios from "axios";
import { useState, useEffect } from "react";
import serverUrl from "../../../serverUrl";


const AdminSubject = () => {

    const [subjectsDOM, setSubjectsDOM] = useState([]);

    const renderSubjects = () => {
        axios.get(serverUrl + "/subjects")
            .then(res => {
                const data = res.data;
                const subjects = data.data;

                console.log(subjects);

                const doms = subjects.map(subject => {
                    return (
                        <tr className="align-middle" key={subject.id}>
                            <td>
                                <input type="hidden" value={subject.id} />
                            </td>
                            <td>
                                <input type="text" defaultValue={subject.name} className="form-control subject-name" />
                            </td>
                            <td>
                                <select
                                    className="form-select use-yn"
                                    defaultValue={subject.useYn}>
                                    <option value="Y">Y</option>
                                    <option value="N">N</option>
                                </select>
                            </td>
                            <td><button className="btn btn-outline-success">등록 페이지</button></td>
                            <td>
                                <button className="btn btn-warning me-1" onClick={updateSubject} value={subject.id}>수정</button>
                                <button className="btn btn-danger" onClick={deleteSubject} value={subject.id}>삭제</button>
                            </td>
                        </tr>
                    );
                });
                setSubjectsDOM(doms);
            });
    }

    useEffect(() => {
        renderSubjects();
    }, []);


    const addLine = () => {

        const newSubject = document.querySelector("#new-subject");

        if (newSubject !== null) {
            alert("등록되지 않은 subject가 있습니다.");
            return;
        }

        const subject = {
            id: "new-subject",
            name: ""
        }
        const line = (

            <tr className="align-middle" key={subject.id} id={subject.id}>
                <td>
                    <input type="hidden" value={subject.id} />
                </td>
                <td>
                    <input type="text" defaultValue={subject.name} className="form-control subject-name" />
                </td>
                <td>
                    <select className="form-select use-yn">
                        <option value="Y">Y</option>
                        <option value="N">N</option>
                    </select>
                </td>
                <td></td>
                <td>
                    <button className="btn btn-primary" onClick={enrollSubject}>등록</button>
                </td>
            </tr>
        );

        setSubjectsDOM([...subjectsDOM, line]);
    }


    const enrollSubject = (e) => {

        const tr = e.target.closest("tr");
        const subjectName = tr.querySelector(".subject-name").value;
        const useYn = tr.querySelector(".use-yn").value;


        if (subjectName.trim() === "") {
            alert("subject 명을 입력하세요");
            return;
        }

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post("/subjects", {
            name: subjectName.trim(),
            useYn: useYn
        }).then(res => {
            alert("등록되었습니다.");
            renderSubjects();
        });
    }

    const updateSubject = (e) => {

        const subjectId = e.target.value;
        const tr = e.target.closest("tr");
        const subjectName = tr.querySelector(".subject-name").value;
        const useYn = tr.querySelector(".use-yn").value;

        if (subjectName.trim() === "") {
            alert("subject 명을 입력하세요");
            return;
        }

        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put("/subjects", {
            id: subjectId,
            name: subjectName.trim(),
            useYn: useYn
        }).then(res => {
            alert("수정되었습니다.");
            renderSubjects();
        });

    }


    const deleteSubject = (e) => {
        const subjectId = e.target.value;

        if (!window.confirm("삭제하시겠습니까?")) {
            return;
        }

        axios.delete(serverUrl + `/subjects/${subjectId}`)
            .then(res => {
                alert("삭제되었습니다.");
                renderSubjects();
            })
    }

    return (
        <>
            <div className="d-flex justify-content-between mt-5 mb-5">
                <h2 className="">ADMIN SUBJECT</h2>
                <div>
                    <button className="btn btn-primary me-2" onClick={addLine}>라인추가</button>
                </div>
            </div>
            <div id="subject-root">
                <table className="table mt-5 text-center">
                    <thead>
                        <tr className="table-primary">
                            <th></th>
                            <th scope="col">과목명</th>
                            <th scope="col">사용유무</th>
                            <th scope="col">문제유형 등록페이지</th>
                            <th scope="col">버튼</th>
                        </tr>
                    </thead>
                    <tbody>
                        {subjectsDOM}
                    </tbody>
                </table>
            </div>
        </>
    );
}

export default AdminSubject;
