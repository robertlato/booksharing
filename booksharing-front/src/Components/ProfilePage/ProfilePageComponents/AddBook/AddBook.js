import React from "react";
import axios from "axios";
import "./AddBook.css";
import AuthenticationService from "../../../../service/AuthenticationService";

class AddBook extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            sharePointOwnerEmail: AuthenticationService.getLoggedInUserName(),
            title: "",
            releasedate: "",
            isbn: "",
            publishername: "",
            authorsfirstname: "",
            authorslastname: "",
            genrename: "",
        };

        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }
    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value,
        });
    }

    async onSubmit(event) {
        event.preventDefault();

        axios
            .post(
                "http://localhost:8889/api/book",
                {
                    releaseDate: this.state.releasedate,
                    title: this.state.title,
                    isbn: this.state.isbn,
                    publisher: {
                        name: this.state.publishername,
                    },
                    authors: [
                        {
                            firstName: this.state.authorsfirstname,
                            lastName: this.state.authorslastname,
                        },
                    ],
                    genre: {
                        name: this.state.genrename,
                    },
                    sharePoint: {
                        user: {
                            email: this.state.sharePointOwnerEmail,
                        },
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
                    window.alert("Dodano książkę!");
                    document.getElementById("book-form").reset();
                }
            })
            .catch((error) => {
                console.log("nie dodało książki");
                console.log(error);
            });
    }

    render() {
        return (
            <div>
                <h1>Dodaj książkę</h1>
                <form id="book-form">
                    <label>
                        <p>Tytuł</p>
                        <input
                            type="text"
                            name="title"
                            placeholder="Tytuł"
                            onChange={this.onChange}
                        />
                        <p>Rok wydania</p>
                        <input
                            type="text"
                            name="releasedate"
                            placeholder="YYYY-MM-DD"
                            onChange={this.onChange}
                        />
                        <p>ISBN</p>
                        <input
                            type="text"
                            name="isbn"
                            placeholder="ISBN"
                            onChange={this.onChange}
                        />
                        <p>Kategoria</p>
                        <input
                            type="text"
                            name="genrename"
                            placeholder="Kategoria"
                            onChange={this.onChange}
                        />
                    </label>
                    <label>
                        <p>Wydawnictwo</p>
                        <input
                            type="text"
                            name="publishername"
                            placeholder="Nazwa"
                            onChange={this.onChange}
                        />
                    </label>
                    <label>
                        <p>Autor</p>
                        <input
                            type="text"
                            name="authorsfirstname"
                            placeholder="Imię"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="authorslastname"
                            placeholder="Nazwisko"
                            onChange={this.onChange}
                        />
                    </label>
                    <br />
                    <button onClick={this.onSubmit}>Dodaj Książkę</button>
                </form>
            </div>
        );
    }
}

export default AddBook;
