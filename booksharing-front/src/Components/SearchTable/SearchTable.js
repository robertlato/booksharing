import React from "react";
import "./SearchTable.css";
import ReactTable from "react-table-6";
import Modal from "react-modal";

class SearchTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalOpened: false,
            books: this.props.data,
            columns: [
                {
                    Header: "Id",
                    id: "row",
                    maxWidth: 50,
                    filterable: false,
                    Cell: (row) => {
                        return <div>{row.index + 1}</div>;
                    },
                },
                {
                    Header: "Tytuł",
                    accessor: "title",
                },
                {
                    Header: "Wydawnictwo",
                    accessor: "publisher.name",
                },
                {
                    Header: "Stan",
                    accessor: "borrowed",
                    Cell: (row) => {
                        return row.value === false ? "Dostępna" : "Wypożyczona";
                    },
                },
                {
                    Header: "Więcej informacji",
                    Cell: (row) => {
                        return (
                            <button onClick={this.onClickBook}>PRZYCISK</button>
                        );
                    },
                },
            ],
        };
        this.onClickBook = this.onClickBook.bind(this);
    }

    onClickBook() {
        this.setState({
            isModalOpened: !this.state.isModalOpened,
        });
    }

    render() {
        return (
            <div>
                <br />
                <ReactTable
                    data={this.props.data}
                    columns={this.state.columns}
                    defaultPageSize={5}
                />
                <Modal isOpen={this.state.isModalOpened}>
                    <h2>Tytuł</h2>
                    <button onClick={this.onClickBook}> ZAMKNIJ </button>
                </Modal>
                <button onClick={this.onClickBook}> PRZYCISK </button>
                <br />
            </div>
        );
    }
}

export default SearchTable;
