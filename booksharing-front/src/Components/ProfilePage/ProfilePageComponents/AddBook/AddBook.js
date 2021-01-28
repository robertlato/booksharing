import React from "react";
import { Link, Redirect } from "react-router-dom";
import axios from "axios";
import "./AddBook.css";

class AddBook extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            title: "",
            releasedate: "",
            isbn: "",
            publisher: {
                name: "",
                address: {
                    country: "",
                    city: "",
                    postalcode: "",
                    street: "",
                    housenumber: "",
                },
            },
            authors: [
                {
                    firstname: "",
                    lastname: "",
                    secondname: "",
                },
            ],
            genre: {
                name: "",
            },
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

        console.log(this.state);

        axios
            .post("http://localhost:8889/api/book", {
                releaseDate: this.state.releasedate,
                title: this.state.title,
                isbn: this.state.isbn,
                publisher: {
                    name: this.state.publisher.name,
                    address: this.state.publisher.address,
                    // {
                    //     country: this.state.publisher.address.country,
                    //     city: this.state.publisher.address.city,
                    //     postalCode: this.state.publisher.address.postalnode,
                    //     street: this.state.publisher.address.street,
                    //     houseNumber: this.state.publisher.address.housenumber,

                    // },
                },
                authors: {
                    firstName: this.state.authors.firstname,
                    lastName: this.state.authors.lastname,
                    secondName: this.state.authors.secondname,
                },

                genre: {
                    name: this.state.genre.name,
                },
            })
            .then((res) => {
                console.log("książka dodana");
                console.log(res);
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
                <form>
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
                            name="publisher.name"
                            placeholder="Nazwa"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="publisher.adrress.country"
                            placeholder="Kraj"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="publisher.adrresscity"
                            placeholder="Miasto"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="publisher.adrress.postalcode"
                            placeholder="Kod pocztowy"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="publisher.adrress.street"
                            placeholder="Ulica"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="publisher.adrress.housenumber"
                            placeholder="Numer budynku/mieszkania"
                            onChange={this.onChange}
                        />
                    </label>
                    <label>
                        <p>Autor</p>
                        <input
                            type="text"
                            name="firstname"
                            placeholder="Imię"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="secondname"
                            placeholder="Drugie imię"
                            onChange={this.onChange}
                        />
                        <input
                            type="text"
                            name="lastname"
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
