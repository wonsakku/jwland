import axios from "axios";
import * as jwt from "../../../jwt";
import { useState, useEffect } from "react";
import { useParams, useLocation } from "react-router-dom";
import queryString from "query-string";
import propTypes from 'prop-types';



const AdminAttendanceManage = () => {


    const { lessonId } = useParams();
    const { search } = useLocation();
    const queryStrings = queryString.parse(search);
    const lessonName = queryStrings.lessonName;
    const subjectName = queryStrings.subjectName;

    const [attendanceStatus, setAttendanceStatus] = useState([]);
    const [enrolledAccounts, setEnrolledAccounts] = useState([]);
    const [attendance, setAttendance] = useState([]);
    const [editPage, setEditPage] = useState(false);

    useEffect(() => {
        getAttendanceStatus();
    }, []);

    const getAttendanceStatus = () => {
        axios.get("/common/attendance-status")
            .then(res => {
                console.log(res);
                setAttendanceStatus(res.data.data);
                getEnolledAccounts();
            });
    }

    const getEnolledAccounts = () => {
        axios.get(`/admin/lessons/${lessonId}/enrolled-accounts`, {
            headers: jwt.authHeader()
        })
            .then(res => {
                console.log(res.data);
                setEnrolledAccounts(res.data.data);
            })
    }

    const enroll = (e) => {
        const attendanceSelects = document.querySelectorAll(".attendance-select");
        for (let i = 0; i < attendanceSelects.length; i++) {
            if (attendanceSelects[i].value === '') {
                const seq = attendanceSelects[i].closest("tr").querySelector(".attendance-seq").innerHTML;
                alert("출석이 선택되지 않은 학생이 있습니다. - " + seq);
                return;
            }
        }

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(`/admin/lessons/${lessonId}/attendance`, {
            lessonId,
            attendance
        }, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("등록되었습니다.");
        }).catch(err => {
            alert(err.response.data.data);
        });


    }

    const changeAttendanceStatus = (e) => {
        const selectBox = e.target;
        const accountId = selectBox.closest("tr").querySelector(".accountId").value;

        const newAttendanceStatus = attendance.filter(attendance => attendance.accountId !== accountId);
        newAttendanceStatus.push({ attendanceStatus: selectBox.value, accountId: accountId });

        setAttendance(newAttendanceStatus);
    }

    const changeEditStatus = () => {
        setEditPage(!editPage);
    }



    return (
        <>
            <div className="d-flex justify-content-between mt-5">
                <h2>{lessonName} ({subjectName}) 출석관리 - {editPage ? <span>수정페이지</span> : <span>등록페이지</span>}</h2>
                <div className="my-auto">
                    <button className="btn btn-secondary" onClick={changeEditStatus}>전환</button>
                </div>
            </div>
            <div className="mt-5">
                <div className="mb-3 d-flex justify-content-between">
                    <div className="my-auto">
                        <span><b style={{ color: "red" }}>{enrolledAccounts.length}</b>명 조회되었습니다.</span>
                    </div>
                    <div>
                        <button className="btn btn-success" onClick={enroll}>등록</button>
                    </div>
                </div>
                <table className="table">
                    <thead>
                        <tr className="table-primary text-center">
                            <th>#</th>
                            <th>학교</th>
                            <th>학년</th>
                            <th>이름</th>
                            <th>출석</th>
                        </tr>
                    </thead>
                    <tbody>
                        {enrolledAccounts.map((account, idx) => {
                            return (
                                <tr key={account.accountId} className="text-center align-middle">
                                    <td><input type="hidden" value={account.accountId} className="accountId" /> <span className="attendance-seq">{idx + 1}</span></td>
                                    <td>{account.schoolName}</td>
                                    <td>{account.grade}</td>
                                    <td>{account.name}</td>
                                    <td>
                                        <select
                                            className="form-select attendance-select"
                                            key={`attendance_${account.accountId}`}
                                            defaultValue={""}
                                            onChange={changeAttendanceStatus}>
                                            <option value="" disabled>선택</option>
                                            {attendanceStatus.map(status => {
                                                return (
                                                    <option value={status.code} key={`attendance_${account.accountId}_${status.code}`}>{status.name}</option>
                                                )
                                            })}
                                        </select>
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        </>
    );
}

// AdminAttendanceManage.protoTypes = {
//     editPage: propTypes.bool
// }

// AdminAttendanceManage.defaultProps = {
//     editPage: false
// }



export default AdminAttendanceManage;