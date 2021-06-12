import React from "react";
import "./SearchTable.css";
import ReactTable from "react-table-6";
import Modal from "react-modal";
import axios from "axios";
import AuthenticationService from "../../service/AuthenticationService";

class SearchTable extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isModalOpened: false,
            hasComponentChanged: false,
            foundBook: "",
            foundBookRateResponse: null,
            foundBookReviewResponse: [],
            isBookRateLoaded: false,
            isBookReviewLoaded: false,
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
        this.loadBookRate = this.loadBookRate.bind(this);
        this.loadBookReview = this.loadBookReview.bind(this);
    }

    onClickBookOpen(index) {
        const foundBook = this.props.data.find((book) => book.id === index);

        this.setState({
            isModalOpened: !this.state.isModalOpened,
            foundBook: foundBook,
        });
        this.loadBookRate(index);
        this.loadBookReview(index);
    }

    onClickBookClose() {
        this.setState({
            isModalOpened: !this.state.isModalOpened,
        });
    }

    loadBookRate(index) {
        const BASE_URL = "http://localhost:8889/api/rating/avg/";
        const EXPANDED_URL = BASE_URL + index;

        axios
            .get(EXPANDED_URL, {
                headers: {
                    authorization: "Basic " + localStorage.getItem("userToken"),
                },
            })
            .then((res) => {
                console.log(res);
                if (res.status >= 200 && res.status <= 210) {
                    console.log("działa: załadowanie oceny");
                    this.setState({
                        foundBookRateResponse: res.data,
                        isBookRateLoaded: true,
                    });
                }
            })
            .catch((error) => {
                console.log("NIE DZIAŁA: ZAŁADOWANIE OCENY");
                console.log(error);
            });
    }

    loadBookReview(index) {
        const BASE_URL = "http://localhost:8889/api/review/";
        const EXPANDED_URL = BASE_URL + index;

        axios
            .get(EXPANDED_URL, {
                headers: {
                    authorization: "Basic " + localStorage.getItem("userToken"),
                },
            })
            .then((res) => {
                console.log(res);
                if (res.status >= 200 && res.status <= 210) {
                    console.log("działa: załadowanie opini");
                    this.setState({
                        foundBookReviewResponse: res.data,
                        isBookReviewLoaded: true,
                    });
                }
            })
            .catch((error) => {
                console.log("NIE DZIAŁA: ZAŁADOWANIE OPINI");
                console.log(error);
            });
    }

    async onClickBorrowBook(bookId, sharePointId) {
        if (AuthenticationService.isUserLoggedIn() === false) {
            window.alert("Musisz się zalogować!");
        } else {
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
                            top: 200,
                            left: 350,
                            right: 0,
                            bottom: 0,
                            backgroundColor: "rgba(255, 255, 255, 0.75)",
                            width: "60%",
                            height: "60%",
                        },
                        content: {
                            textAlign: "center",
                            position: "absolute",
                            top: "40px",
                            left: "40px",
                            right: "40px",
                            bottom: "40px",
                            border: "1px solid #ccc",
                            background: "#fff",
                            overflow: "auto",
                            WebkitOverflowScrolling: "touch",
                            borderRadius: "4px",
                            outline: "none",
                            padding: "20px",
                        },
                    }}
                >
                    <h2>Tytuł: {this.state.foundBook.title}</h2>
                    <h3>
                        Autor:{" "}
                        {this.state.foundBook.authors &&
                            this.state.foundBook.authors.map((author) => {
                                return author.firstName + " " + author.lastName;
                            })}
                    </h3>
                    <h3>
                        Wydawnictwo:{" "}
                        {this.state.foundBook.publisher &&
                            this.state.foundBook.publisher.name}
                    </h3>
                    <h3>
                        Stan:{" "}
                        {this.state.foundBook.borrowed === false && "Dostępna"}
                        {this.state.foundBook.borrowed === true &&
                            "Wypożyczona"}
                    </h3>
                    <h3>
                        Dostępna pod adresem:{" "}
                        {this.state.foundBook.sharePoint ? (
                            <div>
                                {
                                    this.state.foundBook.sharePoint.address
                                        .country
                                }{" "}
                                {this.state.foundBook.sharePoint.address.city}
                                {" ul."}
                                {this.state.foundBook.sharePoint.address.street}
                                {" nr "}
                                {
                                    this.state.foundBook.sharePoint.address
                                        .houseNumber
                                }
                            </div>
                        ) : (
                            "adres się nie załadował, przepraszamy"
                        )}
                    </h3>
                    <h3>
                        Właściciel:{" "}
                        {this.state.foundBook.sharePoint &&
                            this.state.foundBook.sharePoint.user.email}
                    </h3>
                    <h3>
                        Ocena:
                        {this.state.isBookRateLoaded === true ? (
                            <div>{this.state.foundBookRateResponse.avg}/5</div>
                        ) : (
                            "ocena się nie załadowała, przepraszamy"
                        )}
                    </h3>
                    <h3>Opinie:</h3>
                    {this.state.foundBookReviewResponse.map((item) => (
                        <div id="reviewDiv">
                            {" "}
                            <p id="reviewText"> {item.review}</p>{" "}
                        </div>
                    ))}

                    <button onClick={this.onClickBookClose}> ZAMKNIJ </button>
                </Modal>
                <br />
            </div>
        );
    }
}

export default SearchTable;
