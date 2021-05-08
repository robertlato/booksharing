import React from "react";
import "./Homepage.css";
import LatoMap from "../LatoMap/LatoMap";
// import MyMap from "../MyMap/MyMap";
import ReactHTMLDatalist from "react-html-datalist";
import axios from "axios";
import SearchTable from "../SearchTable/SearchTable";

class Homepage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            bookId: 0,
            searchBy: "",
            wantedItem: "",
            books: [],
            updateMap: false,
        };

        this.onChange = this.onChange.bind(this);
        this.onClickSearch = this.onClickSearch.bind(this);
        this.datalistSwitch = this.dataListSwitch.bind(this);
        this.apiUrlSwitch = this.apiUrlSwitch.bind(this);
    }

    dataListSwitch(value) {
        switch (value) {
            case "1":
                return "title";
            case "2":
                return "lastName";
            case "3":
                return "publisher.name";
            case "4":
                return "isbn";
            case "5":
                return "sharePoint.address.city";
            default:
                return "empty";
        }
    }

    apiUrlSwitch(searchBy) {
        switch (searchBy) {
            case "lastName":
                return "http://localhost:8889/api/books/authors?search=";
            case "sharePoint.address.city":
                return "http://localhost:8889/api?search=";
            default:
                return "http://localhost:8889/api?search=";
        }
    }

    onChange(event) {
        this.setState({
            [event.target.name]: event.target.value.toLowerCase(),
        });
    }

    async onClickSearch(event) {
        event.preventDefault();

        const SEARCH_BY = this.dataListSwitch(this.state.searchBy);
        const WANTED_ITEM = this.state.wantedItem;
        const BASE_URL = this.apiUrlSwitch(SEARCH_BY);
        const EXPANDED_URL = BASE_URL + SEARCH_BY + ":*" + WANTED_ITEM + "*";
        var loggedUser = localStorage.getItem("userToken");

        if (loggedUser == null) {
            axios
                .get(EXPANDED_URL)

                .then((res) => {
                    console.log(res);
                    if (res.status === 200) {
                        console.log("działa wyszukiwarka");
                        console.log(res.data);
                        this.setState({ books: res.data });

                        this.setState({ updateMap: !this.state.updateMap });
                    }
                })
                .catch((error) => {
                    console.log("NIE DZIAŁA WYSZUKIWARKA");
                    console.log(error);
                });
        }
        else {
            axios
                .get(EXPANDED_URL, {
                    headers: {
                        authorization: "Basic " + localStorage.getItem("userToken"),
                    },
                })

                .then((res) => {
                    console.log(res);
                    if (res.status === 200) {
                        console.log("działa wyszukiwarka");
                        console.log(res.data);
                        this.setState({ books: res.data });

                        this.setState({ updateMap: !this.state.updateMap });
                    }
                })
                .catch((error) => {
                    console.log("NIE DZIAŁA WYSZUKIWARKA");
                    console.log(error);
                });
        }

    }

    render() {
        return (
            <>
                <div>
                    <h1>Wyszukiwarka</h1>

                    {/* Tutaj form do wyszukiwarki */}
                    <form id="book-form">
                        <ReactHTMLDatalist
                            name={"searchBy"}
                            onChange={this.onChange}
                            options={[
                                { text: "Tytuł", value: "1" },
                                { text: "Autor", value: "2" },
                                { text: "Wydawnictwo", value: "3" },
                                { text: "ISBN", value: "4" },
                                { text: "Miasto", value: "5" },
                            ]}
                        />
                        <input
                            type="text"
                            name="wantedItem"
                            onChange={this.onChange}
                        />
                        <button onClick={this.onClickSearch}>Wyszukaj</button>
                    </form>
                    <br />
                    <SearchTable data={this.state.books} />
                    <br />
                    {/* <MyMap /> */}
                </div>
                <LatoMap
                    data={this.state.books}
                    updateMap={this.state.updateMap}
                />
            </>
        );
    }
}

export default Homepage;
