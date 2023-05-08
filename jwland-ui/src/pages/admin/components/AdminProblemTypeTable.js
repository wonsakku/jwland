const AdminProblemTypeTable = ({ children }) => {
    return (
        <table className="table mt-5 text-center">
            <thead>
                <tr className="table-primary">
                    <th scope="col">선택</th>
                    <th scope="col">분류명</th>
                    <th scope="col">정렬 순서</th>
                    <th scope="col">활성화 여부</th>
                    <th scope="col">버튼</th>
                </tr>
            </thead>
            <tbody>
                {children}
            </tbody>
        </table>
    );
}

export default AdminProblemTypeTable;