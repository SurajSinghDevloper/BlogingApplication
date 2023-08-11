import React from "react";
import "./Card.css";
const CardsModal = (props) => {
  return (
    <>
      <div className="props.className">
        <div className="card">
          <img src={props.link} className="card-img-top" alt="props.alt" />
          <div className="card-body">
            <h5 className="card-title">{props.title}</h5>
            <p className="card-text">{props.content}</p>
          </div>
        </div>
      </div>
    </>
  );
};

export default CardsModal;
