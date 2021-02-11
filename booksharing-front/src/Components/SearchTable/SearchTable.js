import React from "react";
import "./SearchTable.css";
import ReactTable from "react-table-6";
import Modal from "react-modal";

class SearchTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalOpened: false,
            foundBook: "",
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
                    Header: "bookId",
                    accessor: "id",
                    show: false,
                },
                {
                    Header: "Tytuł",
                    accessor: "title",
                    minWidth: 200,
                },
                {
                    id: "Authors",
                    Header: "Autorzy",
                    accessor: (data) => {
                        let authorAsString = "";
                        authorAsString = data.authors.map((item) => {
                            let dummy = "";
                            dummy = dummy + item.firstName;
                            dummy = dummy + " ";
                            dummy = dummy + item.lastName;
                            return dummy;
                        });
                        return authorAsString;
                    },
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
                    Cell: ({ row }) => {
                        return (
                            <button
                                onClick={() => {
                                    this.onClickBookOpen(row.id);
                                }}
                            >
                                Szczegóły
                            </button>
                        );
                    },
                },
            ],
        };
        this.onClickBookClose = this.onClickBookClose.bind(this);
        this.onClickBookOpen = this.onClickBookOpen.bind(this);
    }

    onClickBookOpen(index) {
        const foundBook = this.props.data.find((book) => book.id === index);

        this.setState({
            isModalOpened: !this.state.isModalOpened,
            foundBook: foundBook,
        });
    }

    onClickBookClose() {
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
                <Modal
                    isOpen={this.state.isModalOpened}
                    style={{
                        overlay: {
                            position: "fixed",
                            top: 0,
                            left: 0,
                            right: 0,
                            bottom: 0,
                            backgroundColor: "rgba(255, 255, 255, 0.75)",
                            width: "60%",
                        },
                    }}
                >
                    <h2>Tytuł: {this.state.foundBook.title}</h2>
                    <h3>
                        Autor:
                        {this.state.foundBook.authors &&
                            this.state.foundBook.authors.map((author) => {
                                return author.firstName + " " + author.lastName;
                            })}
                    </h3>
                    <h3>
                        Wydawnictwo:
                        {this.state.foundBook.publisher &&
                            this.state.foundBook.publisher.name}
                    </h3>
                    <h3>
                        Stan:
                        {this.state.foundBook.borrowed === false && "Dostępna"}
                        {this.state.foundBook.borrowed === true &&
                            "Wypożyczona"}
                    </h3>
                    <h3>
                        Dostępna w mieście:
                        {this.state.foundBook.sharePoint &&
                            this.state.foundBook.sharePoint.address.city}
                    </h3>
                    <h3>
                        Właściciel:
                        {this.state.foundBook.sharePoint &&
                            this.state.foundBook.sharePoint.user.email}
                    </h3>

                    <button onClick={this.onClickBookClose}> ZAMKNIJ </button>
                </Modal>
                <br />
            </div>
        );
    }
}

export default SearchTable;
