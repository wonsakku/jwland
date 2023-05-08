const AdminProblemTypeTableLine = ({ problemType, children, radioClickEvent }) => {

    return (
        <tr className="align-middle">
            <td>
                <input type="radio" className="form-check-input subjectProblemTypeId" name="problemTypeId" value={problemType.subjectProblemTypeId} onChange={radioClickEvent} />
            </td>
            <td>
                <input type="text" className="form-control problemTypeName" defaultValue={problemType.problemTypeName} maxLength={30} />
            </td>
            <td>
                <input type="number" className="form-control orderSequence" defaultValue={problemType.orderSequence} />
            </td>
            <td>
                <select className="form-select useYn"
                    defaultValue={problemType.useYn}>
                    <option value="Y">Y</option>
                    <option value="N">N</option>
                </select>
            </td>
            <td>
                {children}
            </td>
        </tr>
    );
}

export default AdminProblemTypeTableLine;