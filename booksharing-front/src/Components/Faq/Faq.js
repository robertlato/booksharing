import React from "react";
import "./Faq.css";

class Faq extends React.Component {
    render() {
        return (
            <div>
                <h1>FAQ</h1>
                <h2>Co to za aplikacja?</h2>
                <p>
                    Jest to aplikacja 'booksharing', która ma umożliwić
                    użytkownikom udostępnianie i wypożyczanie książek między
                    sobą. Pomysł jest taki, żeby książki nie kurzyły się na
                    półkach, a ludzie przestali kupować nowe egzemplarze, niczym
                    nie różniące się od używanych.
                </p>
                <h2>Dlaczego powstała ta aplikacja?</h2>
                <p>
                    Aplikacja ta powstała w ramach projektu na przedmiot
                    'Projekt grupowy' na PG.
                </p>
                <h2>Kto tworzył aplikację?</h2>
                <p>Zespół 4 osobowy: Robert L. Filip M. Filip K. i Joanna A.</p>
                <h2>Czy jest plan dalszego rozwoju aplikacji?</h2>
                <p>Tak, mamy zamiar rozwinąć ten projekt!</p>
            </div>
        );
    }
}

export default Faq;
