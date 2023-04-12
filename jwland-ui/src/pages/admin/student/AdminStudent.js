import { useState, useEffect } from "react";
import axios from "axios";
import * as jwt from "../../../jwt";

const AdminStudent = () => {

    const [accounts, setAccounts] = useState([]);
    const [accountIds, setAccountIds] = useState([]);

    useEffect(() => {
        getAccounts();
    }, []);

    const getAccounts = () => {
        const headers = jwt.authHeader();
        axios.get("/admin/accounts", {
            headers: headers
        })
            .then(res => {
                const data = res.data.data;
                setAccounts(data.content);
            });
    }

    const toggleAccountId = (e) => {
        if (e.target.checked) {
            setAccountIds([...accountIds, e.target.value]);
            return;
        }

        setAccountIds(accountIds.filter(accountId => accountId !== e.target.value));
        return;
    }

    const updateAccountStatus = (e) => {

        if (!window.confirm("수정하시겠습니까?")) {
            return;
        }

        axios.put("/admin/accounts/account-status/update", {
            accountIds,
            accountStatusName: e.target.value
        }, {
            headers: jwt.authHeader()
        }).then(res => {
            alert("수정되었습니다.");

            for (let i = 0; i < accountIds.length; i++) {
                const checkBox = document.querySelector("#accountId_" + accountIds[i]);
                checkBox.checked = false;
            }

            setAccountIds([]);
            getAccounts();
        });
    }


    return (
        <>
            <div className="d-flex justify-content-between mt-5">
                <h2 className="">학생 관리</h2>
                <div className="">
                    <button className="btn btn-success me-1" value="APPROVED" onClick={updateAccountStatus}>승인</button>
                    <button className="btn btn-warning me-1" value="DORMANT" onClick={updateAccountStatus}>휴면</button>
                    <button className="btn btn-danger me-1" value="DELETED" onClick={updateAccountStatus}>삭제</button>
                </div>
            </div>
            <table className="table mt-5 text-center">
                <thead>
                    <tr className="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">이름</th>
                        <th scope="col">아이디</th>
                        <th scope="col">학교</th>
                        <th scope="col">학년</th>
                        <th scope="col">상태</th>
                    </tr>
                </thead>
                <tbody>
                    {accounts.map(account => {
                        return (

                            <tr key={account.id}>
                                <td><input type="checkbox" value={account.id} onChange={toggleAccountId} id={`accountId_${account.id}`} /></td>
                                <td>{account.name}</td>
                                <td>{account.loginId}</td>
                                <td>{account.school}</td>
                                <td>{account.grade}</td>
                                <td>{account.accountStatus}</td>
                            </tr>
                        )
                    })}

                </tbody>
            </table>
        </>
    );
}

export default AdminStudent;