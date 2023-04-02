import axios from "axios";
import { useState, useEffect } from "react";
import serverUrl from "../../../serverUrl";
import AdminSubjectCard from "./AdminSubjectCard";
import { element } from "prop-types";
import ReactDOM from 'react-dom';

const AdminSubject = () => {

    const [subjectsDOM, setSubjectsDOM] = useState([]);

    const renderSubjects = () => {
        axios.get(serverUrl + "/subjects")
            .then(res => {
                const data = res.data;
                const subjects = data.data;

                const doms = subjects.map(subject => {
                    return (
                        <AdminSubjectCard subject={subject} key={subject.id}>
                            <button className="btn btn-warning me-1" value={subject.id} onClick={updateSubject}>수정</button>
                            <button className="btn btn-danger" value={subject.id} onClick={deleteSubject}>삭제</button>
                        </AdminSubjectCard>
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

        const subjectRoot = document.querySelector("#subject-root");

        const subject = {
            id: "new-subject",
            name: ""
        }
        const line = (
            <AdminSubjectCard key="new" subject={subject}>
                <button className="btn btn-success me-1" onClick={enrollSubject}>등록</button>
            </AdminSubjectCard>
        );

        setSubjectsDOM([...subjectsDOM, line]);
    }


    const enrollSubject = (e) => {

        const subjectContainer = e.target.closest(".subject-container");
        const subjectName = subjectContainer.querySelector(".subject-name").value;
        const useYn = subjectContainer.querySelector(".use-yn").value;


        if (subjectName.trim() === "") {
            alert("subject 명을 입력하세요");
            return;
        }

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(serverUrl + "/subjects", {
            name: subjectName.trim(),
            useYn: useYn
        }).then(res => {
            alert("등록되었습니다.");
            renderSubjects();
        });
    }

    const updateSubject = (e) => {

        const subjectId = e.target.value;
        const subjectContainer = e.target.closest(".subject-container");
        const subjectName = subjectContainer.querySelector(".subject-name").value;
        const useYn = subjectContainer.querySelector(".use-yn").value;

        if (subjectName.trim() === "") {
            alert("subject 명을 입력하세요");
            return;
        }

        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put(serverUrl + "/subjects", {
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
                {subjectsDOM}
            </div>
        </>
    );
}

export default AdminSubject;
