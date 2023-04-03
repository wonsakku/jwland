const AdminSubjectCard = ({ subject, children }) => {

    return (
        <div className="card mb-3 cursor-pointer mb-2" key={subject.id} id={subject.id}>
            <div className="card-body py-2 row subject-container">
                <div className="col-8">
                    <input className="form-control subject-name" defaultValue={subject.name} />
                </div>
                <div className="col-2">
                    <select className="form-select use-yn" aria-label="Default select example" defaultValue={subject.useYn}>
                        <option defaultValue="Y">Y</option>
                        <option defaultValue="N">N</option>
                    </select>
                </div>
                <div className="col-2 text-center">
                    {children}
                </div>
            </div>
        </div>
    );
}

export default AdminSubjectCard;
