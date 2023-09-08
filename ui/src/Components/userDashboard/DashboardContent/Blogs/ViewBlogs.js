import React, { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

const ViewBlogs = () => {
  const [selectedDate, setSelectedDate] = useState(null);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };
  return (
    <>
      <div>
        <div>
          <h4>Filter</h4>
          <div className="d-flex">
            <div className="box">
              <div>
                <h6>
                  Filter by Date &nbsp; &nbsp;&nbsp;
                  <i
                    className="fa fa-calendar"
                    style={{ fontSize: "22px" }}
                  ></i>
                </h6>
                <DatePicker
                  selected={selectedDate}
                  onChange={handleDateChange}
                  dateFormat="yyyy-MM-dd"
                  isClearable
                  placeholderText="Select a date"
                />
              </div>
            </div>
            &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp;
            <div className="box">
              <div>
                <h6>
                  Filter by Category &nbsp; &nbsp;&nbsp;
                  <i className="fa fa-cubes" style={{ fontSize: "22px" }}></i>
                </h6>
                <select
                  class="form-select form-select-sm"
                  aria-label="Small select example"
                >
                  <option selected> Select Category</option>
                  <option value="1">One</option>
                  <option value="2">Two</option>
                  <option value="3">Three</option>
                </select>
              </div>
            </div>
            &nbsp; &nbsp;&nbsp;
          </div>
        </div>
      </div>
      <div
        className="container-fluid mt-5"
        style={{ boxShadow: "5px 5px 10px rgba(0, 0, 0, 0.5)" }}
      >
        <div>
          <div>
            <div>
              <div className="layout">
                <h6 style={{ padding: "10px" }}>
                  Blog by - suraj kumar{" "}
                  <span style={{ float: "right" }}>7 year ago</span>
                </h6>
              </div>
              <div>
                <div className="d-flex mt-3">
                  <img
                    className="p-2 mb-5"
                    src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4eB77Kb-RA7YEELNvUmu9Moy4weggsSGzbxTVpVJoAUqMTNs1HZngt4gAJR4jpl-Qviw&usqp=CAU"
                  />
                  <div>
                    <h4 className="p-3">What is Java? </h4>
                    <p className="p-1">
                      Java is a high-level programming language that was
                      developed by James Gosling in the year 1982. It is based
                      on the principles of object-oriented programming and can
                      be used to develop large-scale applications. The following
                      article will cover all the popular Core Java interview
                      questions, String Handling interview questions, Java 8
                      interview questions, Java multithreading interview
                      questions, Java OOPs interview questions, Java exception
                      handling interview questions, collections interview
                      questions, and some frequently asked Java coding interview
                      questions. Go through all the questions to enhance your
                      chances of performing well in the interviews. The
                      questions will revolve around the basic, core & advanced
                      fundamentals of Java.
                    </p>
                    <div className="mb-3" style={{ float: "right" }}>
                      <button className="btn btn-danger">
                        Delete blog &nbsp;&nbsp;<i class="fa fa-trash"></i>
                      </button>
                      &nbsp;&nbsp;
                      <button className="btn btn-primary">
                        View blog &nbsp;&nbsp;<i class="fa fa-eye"></i>
                      </button>
                      &nbsp;&nbsp;
                      <button className="btn btn-info">
                        Edit This &nbsp;&nbsp;<i class="fa fa-edit"></i>
                      </button>
                      &nbsp;&nbsp;
                      <button className="btn btn-success">
                        View This &nbsp;&nbsp;<i class="fa fa-desktop"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ViewBlogs;
