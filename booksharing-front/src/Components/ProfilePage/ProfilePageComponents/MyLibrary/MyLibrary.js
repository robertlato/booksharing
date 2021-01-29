import React from "react";
import "./MyLibrary.css";
import axios from "axios";
import AuthenticationService from "../../../../service/AuthenticationService";

class MyLibrary extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userBooks: [],
        };
        this.loadUserBooks = this.loadUserBooks.bind(this);
    }

    componentDidMount() {
        this.loadUserBooks();
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

    render() {
        return (
            <div>
                <h1>Moja biblioteka</h1>
                <br />
                <ol>
                    {this.state.userBooks.map((book) => (
                        <li key={book.id}> {book.title}</li>
                    ))}
                </ol>
            </div>
        );
    }
}

export default MyLibrary;
