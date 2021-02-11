import React from "react";
import "./SearchTable.css";
import ReactTable from "react-table-6";
import Modal from "react-modal";
import axios from "axios";

class SearchTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalOpened: false,
            hasComponentChanged: false,
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
                    Header: "sharePointId",
                    accessor: "sharePoint.id",
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
                {
                    Header: "Wypożycz",
                    Cell: ({ row, original }) => {
                        return original.borrowed === false ? (
                            <button
                                onClick={() => {
                                    this.onClickBorrowBook(
                                        original.id,
                                        original.sharePoint.id
                                    );
                                }}
                            >
                                Wypożycz
                            </button>
                        ) : (
                            "Wypożyczona"
                        );
                    },
                },
            ],
        };
        this.onClickBookClose = this.onClickBookClose.bind(this);
        this.onClickBookOpen = this.onClickBookOpen.bind(this);
        this.onClickBorrowBook = this.onClickBorrowBook.bind(this);
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

    async onClickBorrowBook(bookId, sharePointId) {
        console.log("książki id: " + bookId);
        console.log("sharepoint id: " + sharePointId);

        axios
            .post(
                "http://localhost:8889/api/borrowing",
                {
                    book: {
                        id: bookId,
                    },
                    sharePoint: {
                        id: sharePointId,
                    },
                },
                {
                    headers: {
                        authorization:
                            "Basic " + localStorage.getItem("userToken"),
                    },
                }
            )
            .then((res) => {
                if (res.status === 200) {
                    window.alert("Wypożyczono!");
                    console.log("Wypożyczono!");
                    window.location.reload();
                }
            })
            .catch((error) => {
                console.log("NIE WYPOŻYCZONO");
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <br />
                <ReactTable
                    id="book-table"
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
