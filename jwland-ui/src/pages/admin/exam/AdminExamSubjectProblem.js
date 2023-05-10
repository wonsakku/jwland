import axios from "axios";
import queryString from "query-string";
import { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import * as jwt from '../../../jwt';

const AdminExamSubjectProblem = () => {

    const { examId, examSubjectId } = useParams();
    const [trDOMs, setTrDOMs] = useState([]);
    const [largeClassifications, setLargeClassifications] = useState([]);
    const [middleClassifications, setMiddleClassifications] = useState([]);
    const [smallClassifications, setSmallClassifications] = useState([]);
    const [totalSocre, setTotalScore] = useState(0);

    const { search } = useLocation();
    const queryStrings = queryString.parse(search);
    const year = queryStrings.year;
    const month = queryStrings.month;
    const organizationName = queryStrings.organizationName;
    const subjectName = queryStrings.subjectName;

    const LARGE = "LARGE";
    const MIDDLE = "MIDDLE";
    const SMALL = "SMALL";

    useEffect(() => {
        getClassifactions(LARGE);
        getClassifactions(MIDDLE);
        getClassifactions(SMALL);

    }, []);


    const getClassifactions = (classification) => {
        axios.get(`/admin/exams/subjects/${examSubjectId}/problem-types?problemClassification=${classification}`, {
            headers: jwt.authHeader()
        }).then(res => {
            const data = res.data.data;

            if (classification === LARGE) {
                setLargeClassifications(data);
                return;
            }
            if (classification === MIDDLE) {
                setMiddleClassifications(data);
                return;
            }
            if (classification === SMALL) {
                setSmallClassifications(data);
                return;
            }
        }).catch(err => {
            console.log(err);
        });
    };

    const addLine = () => {
        const trCount = document.querySelectorAll("#tbody tr").length;
        const newTr = (
            <tr key={`poblem-${trCount + 1}`}>
                <th scope="row"><input type="hidden" className="examProblemId" value="new" id={`poblem-${trCount + 1}`} /></th>
                <td><input type="number" className="form-control-plaintext text-center problemNumber" value={trCount + 1} disabled /></td>
                <td>
                    <select className="form-select bigClassification" onChange={renderMiddleClassification}>
                        <option>선택</option>
                        {largeClassifications.map(classification => {
                            return <option key={`problem-${trCount + 1}_${classification.subjectProblemTypeId}`} value={classification.subjectProblemTypeId}>{classification.subjectProblemTypeName}</option>
                        })}
                    </select>
                </td>
                <td>
                    <select className="form-select middleClassification" onChange={renderSmallClassification}></select>
                </td>
                <td>
                    <select className="form-select smallClassification"></select>
                </td>
                <td>
                    <input type="number" className="form-control score" onChange={calculateTotalScore}></input>
                </td>
                <td>
                    <input type="number" className="form-control correctAnswerRate"></input>
                </td>
                <td>

                </td>
            </tr>
        );

        setTrDOMs([...trDOMs, newTr]);
    }

    const renderMiddleClassification = (e) => {
        initSmallClassification(e);
        const tr = e.target.closest("tr");
        const selectedLargeClassificaionId = Number(e.target.value);
        const middleClassificationSelectBox = tr.querySelector(".middleClassification");
        const selectedMiddleClassifications = middleClassifications.filter(classification => classification.parentId === selectedLargeClassificaionId)
            .map(classification => {
                return `<option key=${classification.subjectProblemTypeId} value=${classification.subjectProblemTypeId}>${classification.subjectProblemTypeName}</option>`;
            });

        let html = "<option value=''>선택</option>";
        selectedMiddleClassifications.forEach(classification => html += classification);
        middleClassificationSelectBox.innerHTML = html;
    }

    const deleteLine = () => {
        setTrDOMs(trDOMs.splice(0, trDOMs.length - 1));
    }

    const renderSmallClassification = (e) => {
        const tr = e.target.closest("tr");
        const selectedMiddleClassificaionId = Number(e.target.value);
        const smallClassificationSelectBox = tr.querySelector(".smallClassification");
        const selectedSmallClassifications = smallClassifications.filter(classification => classification.parentId === selectedMiddleClassificaionId)
            .map(classification => {
                return `<option key=${classification.subjectProblemTypeId} value=${classification.subjectProblemTypeId}>${classification.subjectProblemTypeName}</option>`;
            });
        let html = "<option value=''>선택</option>";
        selectedSmallClassifications.forEach(classification => html += classification);
        smallClassificationSelectBox.innerHTML = html;
    }

    const initSmallClassification = (e) => {
        const tr = e.target.closest("tr");
        const smallClassificationSelectBox = tr.querySelector(".smallClassification");
        smallClassificationSelectBox.innerHTML = "";
    }

    const calculateTotalScore = (e) => {
        let sum = 0;
        const scoreTrs = document.querySelectorAll(".score");

        for (let i = 0; i < scoreTrs.length; i++) {
            if (scoreTrs[i].value === "") {
                continue;
            }
            sum += Number(scoreTrs[i].value);
        }

        setTotalScore(sum);
    }

    const enrollAll = () => {
        const trs = document.querySelectorAll("#tbody tr");
        const requestBody = new Array();

        for (let i = 0; i < trs.length; i++) {
            const tr = trs[i];
            const examProblemId = tr.querySelector(".examProblemId").value === "new" ? null : tr.querySelector(".examProblemId").value;
            const problemNumber = tr.querySelector(".problemNumber").value;
            const smallClassification = tr.querySelector(".smallClassification").value;
            const score = tr.querySelector(".score").value;
            const correctAnswerRate = tr.querySelector(".correctAnswerRate").value == "" ? null : tr.querySelector(".correctAnswerRate").value;

            if (smallClassification === null || smallClassification === "") {
                alert("소분류가 선택되지 않았습니다. - " + problemNumber + "번");
                return;
            }

            if (score === "" || score === "0") {
                alert("점수가 입력되지 않았습니다. - " + problemNumber + "번");
                return;
            }


            const content = {
                examProblemId,
                problemNumber,
                subjectProblemTypeId: smallClassification,
                score,
                correctAnswerRate
            };

            requestBody.push(content);
        }
        // console.log(requestBody);

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(`/admin/exams/subjects/${examSubjectId}/problems`,
            { examProblems: requestBody },
            { headers: jwt.authHeader() }
        ).then(res => {
            console.log(res);
        }).catch(err => {
            console.log(err);
        });
    }


    return (
        <div className="container">
            <div className="mt-5">
                <h2>{year}년 {month}월 {organizationName} {subjectName} - 문제 등록</h2>
            </div>
            <div className="mt-5  d-flex justify-content-between">
                <div className="align-bottom">
                    <span>문항수 : <b className="text-danger ">{trDOMs.length}</b>, 점수 : <b className="text-danger">{totalSocre}</b></span>
                </div>
                <div className="my-auto">
                    <button className="btn btn-primary me-2" onClick={addLine}>라인추가</button>
                    <button className="btn btn-success me-2" onClick={enrollAll}>전체 등록</button>
                    <button className="btn btn-warning" onClick={deleteLine}>라인 삭제</button>
                </div>

            </div>
            <table className="table mt-3 text-center">
                <colgroup>
                    <col width="0px"></col>
                    <col width="10px"></col>
                    <col width="20%"></col>
                    <col width="20%"></col>
                    <col width="20%"></col>
                    <col width="50px"></col>
                    <col width="50px"></col>
                    <col width="15%"></col>
                </colgroup>
                <thead>
                    <tr className="table-primary">
                        <th></th>
                        <th scope="col">번호</th>
                        <th scope="col">대분류</th>
                        <th scope="col">중분류</th>
                        <th scope="col">소분류</th>
                        <th scope="col">점수</th>
                        <th scope="col">정답률</th>
                        <th scope="col">버튼</th>
                    </tr>
                </thead>
                <tbody id="tbody">
                    {trDOMs}
                </tbody>
            </table>
        </div>
    );
}

export default AdminExamSubjectProblem;