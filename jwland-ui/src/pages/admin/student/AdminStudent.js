import { useState, useEffect } from "react";
import axios from "axios";
import Pagination from "../../../components/Pagination";
import * as jwt from "../../../jwt";

const AdminStudent = () => {

    const [accounts, setAccounts] = useState([]);
    const [pageable, setPageable] = useState(null);
    const [totalPages, setTotalPages] = useState(0);
    const [accountIds, setAccountIds] = useState([]);
    const [accountStatus, setAccountStatus] = useState("APPROVAL_REQUEST");
    const [accountName, setAccountName] = useState("");

    useEffect(() => {
        getAccounts();
    }, []);

    const getAccounts = (page = 0) => {
        const headers = jwt.authHeader();
        let path = "/admin/accounts?page=" + page + "&";

        if (accountStatus !== "") {
            path += "accountStatus=" + accountStatus + "&";
        }

        if (accountName !== "") {
            path += "name=" + accountName;
        }

        axios.get(path, {
            headers: headers
        })
            .then(res => {
                const data = res.data.data;
                console.log(data);
                setAccounts(data.content);
                setPageable(data.pageable);
                setTotalPages(data.totalPages);
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

    const onClickPageButton = (page) => {
        getAccounts(page);
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
            <div className="d-flex justify-content-end row mt-4">
                <div className="col-2 pr-0">
                    <select className="form-select" onChange={(e) => setAccountStatus(e.target.value)} defaultValue={accountStatus}>
                        <option value="">전체</option>
                        <option value="APPROVAL_REQUEST">승인 요청</option>
                        <option value="APPROVED">승인</option>
                        <option value="DORMANT">휴면</option>
                        <option value="DELETED">삭제</option>
                    </select>
                </div>
                <div className="col-3">
                    <input className="form-control"
                        onChange={(e) => setAccountName(e.target.value)}
                        onKeyUp={(e) => { e.key === "Enter" && getAccounts(0) }
                        }
                        value={accountName}></input>
                </div>
                <div className="col-1 d-flex justify-content-end pl-0">
                    <button className="btn btn-primary" style={{ width: "100%" }} onClick={(e) => getAccounts(0)}>조회</button>
                </div>
            </div >
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

            <hr />
            {accounts.length === 0 ?
                <div>조회된 계정이 없습니다.</div>
                :
                <>
                    {accounts.length > 1 && pageable !== null &&
                        <Pagination
                            pageNumber={pageable.pageNumber}
                            totalPages={totalPages}
                            onClick={onClickPageButton}
                            limit={5}
                        />
                    }
                </>
            }

        </>
    );
}

export default AdminStudent;