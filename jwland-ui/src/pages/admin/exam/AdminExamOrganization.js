import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import * as jwt from "../../../jwt";

const AdminExamOrganization = () => {

    const [tableLines, setTableLines] = useState([]);
    const [organizationTypes, setOrganizationTypes] = useState([]);
    // const [examOrganizations, setExamOrganizations] = useState([]);


    const getExamOrganizationTypes = () => {
        axios.get("/common/exams/organization-types")
            .then(res => {
                setOrganizationTypes(res.data.data);
            });
    }

    const getOrganizations = () => {
        axios.get("/admin/exams/organizations", {
            headers: jwt.authHeader()
        }).then(res => {

            const lines = res.data.data.map(organization => {
                return <TableLine key={organization.organizationId} organization={organization}>
                    <button className="btn btn-warning me-1" onClick={update}>수정</button>
                    <button className="btn btn-danger me-1" onClick={remove}>삭제</button>
                </TableLine>
            });

            setTableLines(lines);
        });
    }

    useEffect(() => {
        getExamOrganizationTypes();
    }, []);

    useEffect(() => {
        getOrganizations();
    }, [organizationTypes]);

    const addLine = (e) => {
        const organizationIds = document.querySelectorAll(".organizationId");

        for (let i = 0; i < organizationIds.length; i++) {
            if (organizationIds[i].value === 'new-line') {
                alert("등록하지 않는 시험 기관이 있습니다.");
                return;
            }
        }

        const organization = {
            id: "new-line",
            name: ""
        }

        const line = (
            <TableLine key="new" organization={organization} >
                <button className="btn btn-success me-1" onClick={enroll}>등록</button>
            </TableLine>
        );

        setTableLines([...tableLines, line]);
    }

    const TableLine = ({ organization, children }) => {
        return (
            <tr>

                <td>
                    <select
                        className="form-select organizationType"
                        defaultValue={organization.organizationType !== null ? organization.organizationType : ""}
                    >
                        {organizationTypes.map(type => {
                            return <option value={type.code} key={type.code}>{type.name}</option>
                        })}
                    </select>
                </td>
                <td><input type="text" className="form-control name" defaultValue={organization.name} /></td>
                <td><input type="hidden" className="organizationId" value={organization.organizationId} /><input type="text" className="form-control seq" defaultValue={organization.seq} /></td>
                <td>
                    <div className="text-center mx-auto">{children}</div>
                </td>
            </tr>
        );
    }

    const enroll = (e) => {
        const tr = e.target.closest("tr");
        const organizationType = tr.querySelector(".organizationType").value;
        const name = tr.querySelector(".name").value;
        const seq = tr.querySelector(".seq").value;

        if (organizationType === '') {
            alert("기관 종류를 선택하세요");
            return;
        }

        if (name === '') {
            alert("기관명을 입력하세요");
            return;
        }

        if (seq === '') {
            alert("정렬 순서를 입력하세요");
            return;
        }

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        const requestBody = {
            organizationType: organizationType,
            name: name,
            seq: seq
        };

        axios.post("/admin/exams/organizations", requestBody, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("등록되었습니다.");
            getOrganizations();
        }).catch(err => {
            console.log(err);
            alert(err.response.data.data);
        });
    }

    const update = (e) => {
        const tr = e.target.closest("tr");

        const organizationId = tr.querySelector(".organizationId").value;
        const organizationType = tr.querySelector(".organizationType").value;
        const name = tr.querySelector(".name").value;
        const seq = tr.querySelector(".seq").value;

        if (organizationType === '') {
            alert("기관 종류를 선택하세요");
            return;
        }

        if (name === '') {
            alert("기관명을 입력하세요");
            return;
        }

        if (seq === '') {
            alert("정렬 순서를 입력하세요");
            return;
        }


        const updateData = {
            organizationId: organizationId,
            organizationType: organizationType,
            name: name,
            seq: seq
        }

        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put("/admin/exams/organizations", updateData, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("수정되었습니다.");
            getOrganizations();
        }).catch(err => {
            console.log(err);
            alert(err.response.data.data);
        });

    }

    const remove = (e) => {

        if (!window.confirm("삭제하시겠습니까?")) {
            return;
        }

        const tr = e.target.closest("tr");
        const organizationId = tr.querySelector(".organizationId").value;

        axios.delete(`/admin/exams/organizations/${organizationId}`, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("삭제되었습니다.");
            getOrganizations();
        }).catch(err => {
            console.log(err);
            alert(err.response.data.data);
        });
    }


    return (
        <>
            <div className="mt-5 d-flex justify-content-between">
                <h2>시험 기관 관리</h2>
                <div className="my-auto">
                    <button className="btn btn-primary" onClick={addLine}>라인 추가</button>
                </div>
            </div>

            <table className="table mt-5">
                <thead>
                    <tr className="table-primary text-center">
                        <th>기관 종류</th>
                        <th>기관명</th>
                        <th>정렬 순서</th>
                        <th>버튼</th>
                    </tr>
                </thead>
                <tbody id="organization-body">
                    {tableLines}
                </tbody>
            </table>
        </>
    );
}

export default AdminExamOrganization;