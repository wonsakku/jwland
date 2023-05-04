import axios from "axios";
import * as jwt from "../../../jwt";
import { useState, useEffect } from "react";

const AdminLessonAttendanceEditTable = ({ lessonId }) => {

    // let selectedAttendanceDate = null;
    const [attendanceStatus, setAttendanceStatus] = useState([]);
    const [attendanceDate, setAttendanceDate] = useState([]);
    const [selectedAttendanceDate, setSelectedAttendanceDate] = useState(null);
    const [savedAttendance, setSavedAttendance] = useState([]);
    const [updatingAttendance, setUpdatingAttendance] = useState([]);

    useEffect(() => {
        getAttendanceStatus();
        getAttendanceDate();
    }, []);


    const getAttendanceStatus = () => {
        axios.get("/common/attendance-status")
            .then(res => {
                console.log(res);
                setAttendanceStatus(res.data.data);
            });
    }

    const getAttendanceDate = () => {
        axios.get(`/admin/lessons/${lessonId}/attendance/date`,
            {
                headers: jwt.authHeader()
            }).then(res => {
                console.log(res.data);
                setAttendanceDate(res.data.data);
            });
    }

    const changeAttendanceDate = (e) => {
        const selectedDate = e.target.value;
        setSelectedAttendanceDate(selectedDate);
        getAttendance(selectedDate);
    }

    const getAttendance = (selectedDate) => {
        const url = `/admin/lessons/date/${selectedDate}/attendance`;
        console.log(url);
        axios.get(url, {
            headers: jwt.authHeader()
        }).then(res => {
            setSavedAttendance(res.data.data);
            setUpdatingAttendance(Object.assign([], res.data.data));
        });
    }


    const update = (e) => {

        // console.log(updatingAttendance);
        // console.log(savedAttendance);
        // console.log(updatingAttendance === savedAttendance);


        if (selectedAttendanceDate === null) {
            alert("날짜가 선택되지 않았습니다.");
            return;
        }

        const attendanceSelects = document.querySelectorAll(".attendance-select");
        for (let i = 0; i < attendanceSelects.length; i++) {
            if (attendanceSelects[i].value === '') {
                const seq = attendanceSelects[i].closest("tr").querySelector(".attendance-seq").innerHTML;
                alert("출석이 선택되지 않은 학생이 있습니다. - " + seq);
                return;
            }
        }

        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        const updatingData = updatingAttendance.map(att => {
            return {
                accountId: att.accountId,
                attendanceStatus: att.attendanceStatus
            }
        });




        axios.put(`/admin/lessons/date/${selectedAttendanceDate}/attendance`, {
            attendance: updatingData
        }, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("수정되었습니다.");
            getAttendance(selectedAttendanceDate);
        }).catch(err => {
            alert(err.response.data.data);
        });
    }

    const changeAttendanceStatus = (e) => {

        const selectBox = e.target;
        const accountId = Number(selectBox.closest("tr").querySelector(".accountId").value);

        for (let i = 0; i < updatingAttendance.length; i++) {
            if (updatingAttendance[i].accountId === accountId) {
                updatingAttendance[i].attendanceStatus = selectBox.value;
                break;
            }
        }
    }

    const DateSelectBox = ({ defaultValue, accountId }) => {
        return (
            <select className="form-select"
                defaultValue={defaultValue}
                onChange={changeAttendanceStatus}
            >
                {attendanceStatus.map(status => {
                    return <option value={status.code} key={`attendance_${accountId}_${status.code}`}>{status.name}</option>
                })}
            </select>
        );
    }



    return (
        <div className="mt-5">
            <div className="mb-3 d-flex justify-content-between">
                <div className="my-auto">
                    <span><b style={{ color: "red" }}>{savedAttendance.length}</b>명 조회되었습니다.</span>
                </div>
                <div className="d-flex justify-content-end">
                    <div className="me-3">
                        <select
                            className="form-select"
                            defaultValue=""
                            onChange={changeAttendanceDate}
                        >
                            <option value="" disabled>날짜선택</option>
                            {attendanceDate.map(date => {
                                return (
                                    <option value={date.lessonAttendanceDateId} key={date.lessonAttendanceDateId}>{date.startDate}</option>
                                );
                            })}
                        </select>
                    </div>
                    <div>
                        <button className="btn btn-warning" onClick={update}>수정</button>
                    </div>
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
                    {updatingAttendance.map((att, idx) => {
                        return (
                            <tr key={`${selectedAttendanceDate}_${att.accountId}`} className='text-center align-middle'>
                                <td><input type="hidden" value={att.accountId} className="accountId" />{idx + 1}</td>
                                <td>{att.schoolName}</td>
                                <td>{att.grade}</td>
                                <td>{att.name}</td>
                                <td>
                                    <DateSelectBox
                                        key={`attendance_${selectedAttendanceDate}_${att.accountId}`}
                                        defaultValue={att.attendanceStatus}
                                        accountId={att.accountId}
                                    />
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>

    );
}

export default AdminLessonAttendanceEditTable;