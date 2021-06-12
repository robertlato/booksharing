import React from "react";
import "./MyLibrary.css";
import axios from "axios";
import ReactTable from "react-table-6";
import AuthenticationService from "../../../../service/AuthenticationService";

class MyLibrary extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userBooks: [],
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
                    Header: "Data wypożyczenia",
                    accessor: "borrowingDate",
                    Cell: (row) => {
                        return this.timeFilter(row.value);
                    },
                },
                {
                    Header: "Wypożyczył/a",
                    accessor: "userWhoBorrowed",
                    Cell: (row) => {
                        return row.value === "" ? "Nie wypożyczono" : row.value;
                    },
                },
                {
                    Header: "Stan",
                    accessor: "borrowed",
                    Cell: ({ row, original }) => {
                        return original.borrowed === false ? (
                            "Dostępna"
                        ) : (
                            <button
                                onClick={() => {
                                    this.onClickBookReturn(original.id);
                                }}
                            >
                                Przyjmij zwrot
                            </button>
                        );
                    },
                },
            ],
        };
        this.loadUserBooks = this.loadUserBooks.bind(this);
        this.onClickBookReturn = this.onClickBookReturn.bind(this);
        this.timeFilter = this.timeFilter.bind(this);
    }

    componentDidMount() {
        this.loadUserBooks();
    }

    timeFilter(timeString) {
        //example: 2021-02-11T16:42:06.484+00:00
        if (timeString !== null) {
            const timeFilteredString = timeString
                .substr(0, 16)
                .replace("T", " ");
            return timeFilteredString;
        }
        return "Nie wypożyczono";
    }

    loadUserBooks() {
        const USER_NAME = AuthenticationService.getLoggedInUserName();
        const BASE_URL = "http://localhost:8889/api/sharepoint/books/";
        const EXPANDED_URL = BASE_URL + USER_NAME;
        axios
            .get(EXPANDED_URL, {
                headers: {
                    authorization: "Basic " + localStorage.getItem("userToken"),
                },
            })
            .then((res) => {
                console.log(res);
                if (res.status === 200) {
                    console.log("działa biblioteka");
                    this.setState({ userBooks: res.data });
                }
            })
            .catch((error) => {
                console.log("NIE DZIAŁA BIBLIOTEKA");
                console.log(error);
            });
    }

    async onClickBookReturn(bookId) {
        axios
            .post(
                "http://localhost:8889/api/borrowing/return",
                {
                    book: {
                        id: bookId,
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
                    window.alert("Zmieniono status!");
                    window.location.reload();
                }
            })
            .catch((error) => {
                console.log("NIE ZMIENIONO STATUSU");
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <h1>Moja biblioteka</h1>
                <br />
                <ReactTable
                    data={this.state.userBooks}
                    columns={this.state.columns}
                    defaultPageSize={5}
                />
            </div>
        );
    }
}

export default MyLibrary;
