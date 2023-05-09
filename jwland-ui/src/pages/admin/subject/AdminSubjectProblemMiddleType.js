import queryString from "query-string";
import { useHistory, useLocation, useParams } from "react-router-dom";
import AdminProblemTypeTable from "../components/AdminProblemTypeTable";
import { useState } from "react";
import AdminProblemTypeTableLine from "../components/AdminProblemTypeTableLine";
import * as jwt from "../../../jwt";
import axios from "axios";
import { useEffect } from "react";

const AdminSubjectProblemMiddleType = () => {

    const { subjectId, parentId } = useParams();
    const { search } = useLocation();
    const queryStrings = queryString.parse(search);
    const subjectName = queryStrings.subjectName;
    const receivedParentName = queryStrings.parentName;
    const problemClassification = "MIDDLE";
    const history = useHistory();


    const [problemTypesDOM, setProblemTypesDOM] = useState([]);
    const [selectedId, setSelectedId] = useState(null);
    const [parentName, setParentName] = useState(queryStrings.parentName);

    useEffect(() => {
        getProblemTypes();
    }, []);


    const addLine = () => {

        const problemTypeIds = document.querySelectorAll(".subjectProblemTypeId");

        for (let i = 0; i < problemTypeIds.length; i++) {
            if (problemTypeIds[i].value === "new") {
                alert("등록되지 않은 라인이 있습니다.");
                return;
            }
        }

        const newProblemType = { subjectProblemTypeId: "new" };
        const newLine = <AdminProblemTypeTableLine key={newProblemType.subjectProblemTypeId} problemType={newProblemType}>
            <button className="btn btn-primary" onClick={enroll}>등록</button>
        </AdminProblemTypeTableLine>
        setProblemTypesDOM([...problemTypesDOM, newLine]);
    }

    const getProblemTypes = () => {
        const url = `/admin/subjects/problem-types?subjectId=${subjectId}&problemClassification=${problemClassification}&parentId=${parentId}`;
        console.log(url);
        axios.get(url, {
            headers: jwt.authHeader()
        }).then(res => {
            const responseData = res.data.data;
            console.log(responseData);
            const problemTypeDOMs = responseData.map(data => {
                return <AdminProblemTypeTableLine key={data.subjectProblemTypeId} problemType={data} radioClickEvent={changeParentInfo}>
                    <button className="btn btn-warning me-2" value={data.subjectProblemTypeId} onClick={update}>수정</button>
                    <button className="btn btn-danger" value={data.subjectProblemTypeId}>삭제</button>
                </AdminProblemTypeTableLine>
            });
            setProblemTypesDOM(problemTypeDOMs);
        }).catch(err => {
            console.log(err);
        })
    }

    const enroll = (e) => {
        const requestBody = defaultRequestBody(e);

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(`/admin/subjects/problem-types`, requestBody, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("등록되었습니다.");
            getProblemTypes();
        }).catch(err => {
            console.log(err);
        });
    }

    const update = (e) => {
        const problemTypeId = e.target.value;
        const requestBody = defaultRequestBody(e);
        requestBody.problemTypeId = problemTypeId;


        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put(`/admin/subjects/problem-types`, requestBody, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("수정되었습니다.");
            getProblemTypes();
        }).catch(err => {
            console.log(err);
        });
    }

    const defaultRequestBody = (e) => {
        const tr = e.target.closest("tr");

        const problemTypeName = tr.querySelector(".problemTypeName").value;
        const orderSequence = tr.querySelector(".orderSequence").value;
        const useYn = tr.querySelector(".useYn").value;

        if (problemTypeName === "") {
            alert("분류명을 입력하세요");
            return;
        }

        return {
            subjectId,
            parentId,
            problemTypeName,
            orderSequence,
            useYn,
            problemClassification
        };
    }

    const changeParentInfo = (e) => {
        setSelectedId(e.target.value);
        const tr = e.target.closest("tr");
        setParentName(receivedParentName + " - " + tr.querySelector(".problemTypeName").value);
    }

    const goToChildrenClassification = (e) => {
        if (selectedId === null) {
            alert("분류 항목을 선택해주세요");
            return;
        }

        history.push(`/admin/subjects/${subjectId}/problem-types/middle/${selectedId}?subjectName=${subjectName}&parentName=${parentName}`);
    }

    return (
        <div className="container">
            <div className="mt-5 d-flex justify-content-between">
                <h2>{subjectName} - {parentName}</h2>
                <div className="my-auto">
                    <button className="btn btn-primary me-2" onClick={addLine}>라인 추가</button>
                    <button className="btn btn-success" onClick={goToChildrenClassification}>소분류 등록</button>
                </div>
            </div>
            <AdminProblemTypeTable >
                {problemTypesDOM}
            </AdminProblemTypeTable>
        </div>
    );
}


export default AdminSubjectProblemMiddleType;