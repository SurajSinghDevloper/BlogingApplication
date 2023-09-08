import React from "react";
import CardsModal from "../../../modals/CardsModal";
import { Container } from "react-bootstrap";

const Lobby = () => {
  return (
    <>
      <div className="d-flex text-center">
        <div className="card">
          <i
            class="fa fa-archive"
            style={{ fontSize: "60px", color: "#007bff", cursor: "pointer" }}
          >
            <br />
            <span>20</span>
          </i>
          <h5>Your Total Blogs</h5>
        </div>

        <div className="card">
          <i
            class="fa fa-archive"
            style={{ fontSize: "60px", color: " #ffc107" }}
          >
            <br />
            <span>2</span>
          </i>
          <h5>Trending Blogs</h5>
        </div>

        <div className="card">
          <i
            class="fa fa-archive"
            style={{ fontSize: "60px", color: "#2F539B" }}
          >
            <br />
            <span>20</span>
          </i>
          <h5>Your Friend's Blogs</h5>
        </div>

        <div className="card">
          <i class="fa fa-group" style={{ fontSize: "60px", color: "#17a2b8" }}>
            <br />
            <span>12</span>
          </i>
          <h5>Your's Follower</h5>
        </div>
        <div className="card">
          <i class="fa fa-group" style={{ fontSize: "60px", color: "#29465B" }}>
            <br />
            <span>12</span>
          </i>
          <h5>You are Following</h5>
        </div>
      </div>
      <hr />
      <h2 className="text-center">Aout Technologies</h2>
      <br />
      <br />
      <Container className="d-flex ml-5">
        <CardsModal
          title="About Java"
          link="data:image/png;base64,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"
        />
        <CardsModal
          title="About Python"
          link="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTg9dk0A1pfmH3mkNZzsb03gjFYnM4ljUcFsERNI7ibHXHFr0ObJwvGokqumxidvBMDM_Y&usqp=CAU"
        />
        <CardsModal
          title="About Java"
          link="data:image/png;base64,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"
        />
        &nbsp;
        <CardsModal
          title="About React-js"
          link="data:image/png;base64,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"
        />
      </Container>
      <div className="mt-4">
        <Container style={{ maxHeight: "100px", width: "860px" }}>
          <img src="https://www.techworth.in/wp-content/uploads/2015/06/blog-banner1.jpg" />
        </Container>
      </div>
    </>
  );
};

export default Lobby;
