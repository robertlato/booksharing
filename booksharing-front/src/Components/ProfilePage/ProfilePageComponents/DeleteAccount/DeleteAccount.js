import React from "react";
import axios from "axios";
import "./DeleteAccount.css";

class DeleteAccount extends React.Component {
    constructor(props) {
        super(props);

        this.onSubmit = this.onClickDelete.bind(this);
    }

    async onClickDelete() {

        var userIsDecided = window.confirm("Czy na pewno chcesz usunąć konto?");

        if (userIsDecided) {
            var loggedUser = localStorage.getItem("userToken");
    
            if (loggedUser != null) {
                axios
                    .delete("http://localhost:8889/api/user", {
                        headers: {
                            authorization:
                                "Basic " + loggedUser,
                        },
                    })
                    .then(res => {
                        if (res.status === 200) {
                            window.alert("Konto zostało usunięte");
                            window.location.href = "/logout";
                        }
                    })
                    .catch((error) => {
                        if (error.response) {
                            window.alert(error.response.data.message);
                        }
                        console.log("Konto nie zostało usunięte");
                        console.log(error);
                    });
            }
        }
    }

    render() {
        return (
            <div>
                <h1>Usuń konto</h1>
                <button onClick={() => this.onClickDelete()} type="button">Usuń konto</button>
            </div>
        );
    }
}

export default DeleteAccount;
