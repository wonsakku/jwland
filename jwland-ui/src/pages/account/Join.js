import "./join.css";
import bootstrapLogo from "./bootstrap-logo.svg";
import axios from "axios";
import serverUrl from "../../serverUrl";
import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";

const Join = () => {

    const [schools, setSchools] = useState([]);
    const [grades, setGrades] = useState([]);
    const history = useHistory();

    useEffect(() => {
        getSchools();
        getGrades();
    }, []);

    const getSchools = () => {
        axios.get(serverUrl + "/common/schools")
            .then((res) => {
                const data = res.data.data;
                console.log(data);
                setSchools(data);
            });
    }


    const getGrades = () => {
        axios.get(serverUrl + "/common/target-grades")
            .then((res) => {
                const data = res.data.data;
                console.log(data);
                setGrades(data);
            });
    }

    const join = (e) => {
        const name = document.querySelector("#name").value;
        const loginId = document.querySelector("#loginId").value;
        const password = document.querySelector("#password").value;
        const schoolCode = document.querySelector("#school").value;
        const gradeCode = document.querySelector("#grade").value;

        if (!window.confirm("등록하시겠습니까?")) {
            return;
        }

        axios.post(serverUrl + "/join", {
            name,
            loginId,
            password,
            schoolCode,
            gradeCode
        }).then(res => {
            alert("등록되었습니다.");
            history.push("/");
        }).catch((error) => {
            const responseData = error.response.data;
            const errorMessage = responseData.data;
            alert(errorMessage);
        });
    }



    return (

        <>
            <div className="container bg-body-tertiary">
                <main>
                    <div className="py-5 text-center">
                        <img className="d-block mx-auto mb-4" src={bootstrapLogo} alt="" width="72" height="57" />
                        <h2>회원가입</h2>
                    </div>

                    <div className="container justify-content-center row">
                        <form className="needs-validation col-8">
                            <div className="row g-3">
                                <div className="col-12">
                                    <label htmlFor="name" className="form-label">이름</label>
                                    <div className="input-group has-validation">
                                        <input type="text" className="form-control" id="name" placeholder="Username" required />
                                    </div>
                                </div>

                                <div className="col-12">
                                    <label htmlFor="loginId" className="form-label">아이디</label>
                                    <input type="text" className="form-control" id="loginId" placeholder="your ID" required />
                                </div>

                                <div className="col-12">
                                    <label htmlFor="password" className="form-label">비밀번호</label>
                                    <input type="password" className="form-control" id="password" placeholder="password" required />
                                </div>

                                <div className="col-md-6">
                                    <label htmlFor="school" className="form-label">학교</label>
                                    <select className="form-select" id="school" required>
                                        <option value="">Choose...</option>
                                        {schools.map(school => {
                                            return (
                                                <option value={school.code} key={school.code}>{school.name}</option>
                                            );
                                        })}

                                    </select>
                                </div>

                                <div className="col-md-6">
                                    <label htmlFor="grade" className="form-label">학년</label>
                                    <select className="form-select" id="grade" required>
                                        <option value="">Choose...</option>
                                        {grades.map(grade => {
                                            return (
                                                <option value={grade.code} key={grade.code}>{grade.name}</option>
                                            );
                                        })}
                                    </select>
                                </div>
                            </div>

                            <hr className="my-4" />

                            <button className="w-100 btn btn-success btn-lg" type="button" onClick={join}>회원가입</button>
                        </form>
                    </div>

                </main>

                <footer className="my-5 pt-5 text-body-secondary text-center text-small">
                    <p className="mb-1">&copy; 2017–2023 Company Name</p>
                    <ul className="list-inline">
                        <li className="list-inline-item"><a href="#">Privacy</a></li>
                        <li className="list-inline-item"><a href="#">Terms</a></li>
                        <li className="list-inline-item"><a href="#">Support</a></li>
                    </ul>
                </footer>
            </div>
        </>
    );

}

export default Join;