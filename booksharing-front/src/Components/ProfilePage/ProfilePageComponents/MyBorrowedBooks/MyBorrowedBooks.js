import React from "react";
import "./MyBorrowedBooks.css";
import axios from "axios";
import ReactTable from "react-table-6";
import Modal from "react-modal";

class MyBorrowedBooks extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            isModalOpened: false,
            rate: 0,
            review: "",
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
                    accessor: "book.title",
                    minWidth: 200,
                },
                {
                    id: "Authors",
                    Header: "Autorzy",
                    accessor: (data) => {
                        let authorAsString = "";
                        authorAsString = data.book.authors.map((item) => {
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
                    accessor: "book.publisher.name",
                },
                {
                    Header: "Data wypożyczenia",
                    accessor: "checkOutDate",
                    Cell: (row) => {
                        return this.timeFilter(row.value);
                    },
                },
                {
                    Header: "Data oddania",
                    accessor: "checkInDate",
                    Cell: (row) => {
                        return row.value == null
                            ? "Nie oddano"
                            : this.timeFilter(row.value);
                    },
                },
                {
                    Header: "Pożyczono od",
                    accessor: "book.sharepoint.user.email",
                },
                {
                    Header: "Oceń",
                    Cell: ({ row, original }) => {
                        return (
                            <button
                                onClick={() => {
                                    this.onClickModal(original.book.id);
                                }}
                            >
                                Oceń
                            </button>
                        );
                    },
                },
            ],
        };

        this.loadUserBooks = this.loadUserBooks.bind(this);
        this.timeFilter = this.timeFilter.bind(this);
        this.onClickModal = this.onClickModal.bind(this);
        this.onClickRate = this.onClickRate.bind(this);
        this.onClickReview = this.onClickReview.bind(this);
        this.onChangeRate = this.onChangeRate.bind(this);
        this.onReviewChange = this.onReviewChange.bind(this);
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
        return timeString;
    }

    loadUserBooks() {
        const BASE_URL = "http://localhost:8889/api/user/borrowings";

        axios
            .get(BASE_URL, {
                headers: {
                    authorization: "Basic " + localStorage.getItem("userToken"),
                },
            })
            .then((res) => {
                console.log(res);
                if (res.status >= 200 && res.status <= 210) {
                    console.log("działa: moje wypożyczenia");
                    this.setState({ userBooks: res.data });
                }
            })
            .catch((error) => {
                console.log("NIE DZIAŁA: MOJE WYPOŻYCZENIA");
                console.log(error);
            });
    }

    onClickModal(bookId) {
        this.setState({
            isModalOpened: !this.state.isModalOpened,
            bookIdWithRating: bookId,
        });
    }

    onChangeRate(event) {
        this.setState({
            rate: event.target.value,
        });
    }

    onReviewChange(event) {
        this.setState({ review: event.target.value });
    }

    async onClickRate(event) {
        event.preventDefault();

        axios
            .post(
                "http://localhost:8889/api/rating/add",
                {
                    rating: this.state.rate,
                    book: {
                        id: this.state.bookIdWithRating,
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
                if (res.status >= 200 && res.status <= 210) {
                    window.alert("Dodano ocenę!");
                    console.log("dodano ocenę");
                    window.location.reload();
                }
            })
            .catch((error) => {
                console.log("NIE DODANO OCENY");
                console.log(error);
            });
    }

    async onClickReview(event) {
        event.preventDefault();

        axios
            .post(
                "http://localhost:8889/api/review/add",
                {
                    review: this.state.review,
                    book: {
                        id: this.state.bookIdWithRating,
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
                if (res.status >= 200 && res.status <= 210) {
                    window.alert("Dodano opinię!");
                    console.log("dodano opinię");
                    window.location.reload();
                }
            })
            .catch((error) => {
                console.log("NIE DODANO OPINI");
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <h1>Wypożyczone książki</h1>
                <br />
                <ReactTable
                    data={this.state.userBooks}
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
                    <form id="rate-form">
                        <label>
                            <p>Ocena</p>
                            <div onChange={this.onChangeRate}>
                                <input type="radio" value="1" name="rate" /> 1
                                <input type="radio" value="2" name="rate" /> 2
                                <input type="radio" value="3" name="rate" /> 3
                                <input type="radio" value="4" name="rate" /> 4
                                <input type="radio" value="5" name="rate" /> 5
                            </div>
                        </label>
                        <br />
                        <br />
                        <button onClick={this.onClickRate}>Oceń</button>
                    </form>
                    <form id="review-form">
                        <label>
                            <p>Recenzja</p>
                            <textarea
                                rows={5}
                                cols={50}
                                value={this.state.value}
                                onChange={this.onReviewChange}
                            />
                        </label>
                        <br />
                        <br />
                        <button onClick={this.onClickReview}>
                            Dodaj Opinie
                        </button>
                    </form>
                    <button onClick={this.onClickModal}> Zamknij </button>
                </Modal>
            </div>
        );
    }
}

export default MyBorrowedBooks;
