import axios from "axios";
import { useEffect, useState } from "react";
import { useParams, useLocation } from "react-router-dom";
import * as jwt from "../../../jwt";
import queryString from "query-string";

const AdminLessonStudentManage = () => {

    const [schoolClassifactions, setSchoolClassifactions] = useState([]);
    const [schoolClassification, setSchoolClassifaction] = useState(null);
    const [name, setName] = useState("");
    const [targetGrades, setTargetGrades] = useState([]);
    const [grade, setGrade] = useState("");
    const { lessonId } = useParams();
    const [accounts, setAccounts] = useState([]);
    const [accountIds, setAccountIds] = useState([]);

    const { search } = useLocation();
    const queryStrings = queryString.parse(search);
    const lessonName = queryStrings.lessonName;
    const subjectName = queryStrings.subjectName;


    useEffect(() => {
        getAccountStatus();
    }, []);

    const getAccountStatus = () => {
        axios.get("/common/school-classification")
            .then(res => {
                // console.log(res.data.data);
                setSchoolClassifactions(res.data.data);
            });
    }

    const getGrades = (e) => {
        const schoolClassification = e.target.value;
        if (schoolClassification === "") {
            return;
        }
        setSchoolClassifaction(schoolClassification);
        axios.get(`/common/target-grades?schoolClassification=${schoolClassification}`)
            .then(res => {
                setTargetGrades(res.data.data);
            });

    }

    const findAccounts = () => {

        if (schoolClassification == null) {
            alert("학교 분류를 선택해주세요");
            return;
        }
        if (grade == null) {
            alert("학년을 선택해주세요");
            return;
        }


        const path = `/admin/lessons/${lessonId}/unenrolled-accounts?schoolClassification=${schoolClassification}&grade=${grade}&name=${name}`;
        console.log(path);

        axios.get(path, {
            headers: jwt.authHeader()
        })
            .then(res => {
                const pageData = res.data.data;
                console.log(pageData);
                setAccounts(pageData.content);
            });
    }

    const toggleCheckbox = (e) => {
        const accountId = e.target.value;
        if (e.target.checked) {
            setAccountIds((prev) => [...prev, accountId]);
            return;
        }
        setAccountIds((prevIds) => prevIds.filter(prevId => prevId !== accountId));
    }

    const enrollAcccountToLesson = (e) => {
        console.log(accountIds);

        if (accountIds.length < 1) {
            alert("선택한 계정이 없습니다.");
            return;
        }

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(`/admin/lessons/${lessonId}/accounts/enroll`, {
            accountIds
        }, {
            headers: jwt.authHeader()
        }).then(res => {
            // console.log(res);
            alert("등록되었습니다.");
            setAccountIds([]);
            findAccounts();
        });
    }




    return (
        <>
            <h2 className="mt-5">강의 수강 관리 <small>[{subjectName} - {lessonName}]</small></h2>
            <div className="d-flex justify-content-end">
                <div className="me-2">
                    <select className="form-select"
                        onChange={getGrades}>
                        <option value="">학교 분류 선택</option>
                        {schoolClassifactions.map(classification => {
                            return <option key={classification.code} value={classification.code}>{classification.name}</option>
                        })}
                    </select>
                </div>
                <div className="me-2">
                    <select className="form-select"
                        defaultValue={grade}
                        onChange={(e) => setGrade(e.target.value)}
                    >
                        <option value="">학년 선택</option>
                        {targetGrades.map(targetGrade => {
                            return <option key={targetGrade.code} value={targetGrade.code}>{targetGrade.name}</option>
                        })}
                    </select>
                </div>
                <div className="me-2">
                    <input
                        className="form-control"
                        id="name"
                        onChange={(e) => { setName(e.target.value); }}
                        value={name}
                    />
                </div>
                <div>
                    <button className="btn btn-primary" onClick={findAccounts}>검색</button>
                </div>
            </div>
            <div className="mt-5">
                <div className="mb-3 d-flex justify-content-between">
                    <div className="my-auto">
                        <span><b style={{ color: "red" }}>{accounts.length}</b>명 조회되었습니다.</span>
                    </div>
                    <div>
                        <button className="btn btn-success" onClick={enrollAcccountToLesson}>등록</button>
                    </div>
                </div>
                <table className="table">
                    <thead>
                        <tr className="table-primary text-center">
                            <th>#</th>
                            {/* <th><input type="checkbox" className="form-check-input" /></th> */}
                            <th>이름</th>
                            <th>학교</th>
                            <th>학년</th>
                        </tr>
                    </thead>
                    <tbody>
                        {accounts.map(account => {
                            return (
                                <tr key={account.accountId} className="text-center">
                                    <td>
                                        <input
                                            id={`aaccountId_${account.accountId}`}
                                            value={account.accountId}
                                            className="form-check-input"
                                            type="checkbox"
                                            onChange={toggleCheckbox}
                                        />
                                    </td>
                                    <td>{account.name}</td>
                                    <td>{account.schoolName}</td>
                                    <td>{account.grade}</td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>

        </>
    );
}

export default AdminLessonStudentManage;