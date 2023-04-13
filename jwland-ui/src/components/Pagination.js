import propTypes from "prop-types";

const Pagination = ({ pageNumber, totalPages, onClick, limit }) => {

    const currentSet = Math.ceil((pageNumber + 1) / limit);
    const lastSet = Math.ceil(totalPages / limit);
    const startPage = limit * (currentSet - 1) + 1;
    const numberOfPageForSet = currentSet === lastSet ? totalPages % limit : limit;

    return (
        <nav aria-label="Page navigation example">
            <ul className="pagination justify-content-center">
                {currentSet !== 1 &&
                    <li className="page-item">
                        <div className="page-link cursor-pointer"
                            onClick={() => onClick(startPage - limit)}>Previous</div>
                    </li>
                }
                {Array(numberOfPageForSet).fill(startPage).map((value, index) => value + index)
                    .map(buttonPageNumber => {
                        return (
                            <li className={`page-item ${(pageNumber + 1) === buttonPageNumber ? 'active' : ''}`} key={buttonPageNumber}>
                                <div
                                    className="page-link cursor-pointer"
                                    onClick={() => {
                                        onClick(buttonPageNumber - 1);
                                    }}>
                                    {buttonPageNumber}
                                </div>
                            </li>
                        );
                    })
                }
                {
                    currentSet !== lastSet &&
                    <li className="page-item cursor-pointer"
                        onClick={() => onClick(startPage + limit)}>
                        <div className="page-link">Next</div>
                    </li>
                }
            </ul>
        </nav>);
}

Pagination.propTypes = {
    pageNumber: propTypes.number,
    totalPages: propTypes.number.isRequired,
    onClick: propTypes.func.isRequired,
    limit: propTypes.number.isRequired,
}

Pagination.defaultProps = {
    pageNumber: 0,
    number: 20,
}


export default Pagination;