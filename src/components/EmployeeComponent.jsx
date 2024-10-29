import React, { useEffect, useState } from "react";
import { createEmployee, getEmployee, updateEmployee } from "../services/EmployeeService";
import { useNavigate, useParams } from "react-router-dom";

const EmployeeComponent = () => {
  const [name, setName] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [mobile, setMobile] = useState("");
 
  const { id } = useParams();

  const [errors, setErrors] = useState({
    name:"",
    firstName: "",
    lastName: "",
    email: "",
    mobile:""
  });

  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      getEmployee(id)
        .then((response) => {
          setName(response.data.name),
          setFirstName(response.data.firstName),
          setLastName(response.data.lastName),
          setEmail(response.data.email),
          setMobile(response.data.mobile)
        })
        .catch((error) => {
          console.error(error);
        });
    }
  }, [id]);

  function saveOrUpdateEmployee(e) {
    e.preventDefault();

    if (validateForm()) {
      const employee = {name, firstName, lastName, email, mobile};
      console.log(employee);

      if (id) {
        updateEmployee(id, employee)
          .then((response) => {
            console.log(response.data);
            navigate("/employees");
          })
          .catch((error) => {
            console.error(error);
          });
      } else {
        createEmployee(employee)
          .then((response) => {
            console.log(response.data);
            navigate("/employees");
          })
          .catch((error) => {
            console.error(error);
          });
      }
    }
  }

  function validateForm() {
    let valid = true;

    const errorCopy = { ...errors };

    if (name.trim()) {
      errorCopy.name = "";
    } else {
      errorCopy.name = "Name is required!";
      valid = false;
    }

    if (firstName.trim()) {
      errorCopy.firstName = "";
    } else {
      errorCopy.firstName = "First name is required!";
      valid = false;
    }

    if (lastName.trim()) {
      errorCopy.lastName = "";
    } else {
      errorCopy.lastName = "Last name is required!";
      valid = false;
    }

    if (email.trim()) {
      errorCopy.email = "";
    } else {
      errorCopy.email = "Email name is required!";
      valid = false;
    }

    if (mobile.trim()) {
      errorCopy.mobile = "";
    } else {
      errorCopy.mobile = "Mobile is required!";
      valid = false;
    }
    setErrors(errorCopy);

    return valid;
  }

  function pageTitle() {
    if (id) {
      return <h2 className="text-center">Update Employee</h2>;
    } else {
      return <h2 className="text-center">Add Employee</h2>;
    }
  }

  return (
    <div className="container">
      <br /> <br />
      <div className="row">
        <div className="card col-md-6 offset-md-3 offset-md-3">
          {pageTitle()}
          <div className="card-body">
            <form>

            <div className="form-group mb-2">
                <label className="form-label"> Name:</label>
                <input
                  type="text"
                  placeholder="Enter Name"
                  name="name"
                  value={name}
                  className={`form-control ${
                    errors.name ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setName(e.target.value)}
                />
                {errors.name && (
                  <div className="invalid-feedback">{errors.name}</div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">First Name:</label>
                <input
                  type="text"
                  placeholder="Enter First Name"
                  name="firstName"
                  value={firstName}
                  className={`form-control ${
                    errors.firstName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setFirstName(e.target.value)}
                />
                {errors.firstName && (
                  <div className="invalid-feedback">{errors.firstName}</div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Last Name:</label>
                <input
                  type="text"
                  placeholder="Enter Last Name"
                  name="lastName"
                  value={lastName}
                  className={`form-control ${
                    errors.lastName ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setLastName(e.target.value)}
                />
                {errors.lastName && (
                  <div className="invalid-feedback">{errors.lastName}</div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label">Email-Id:</label>
                <input
                  type="text"
                  placeholder="Enter Email Id"
                  name="email"
                  value={email}
                  className={`form-control ${errors.email ? "is-invalid" : ""}`}
                  onChange={(e) => setEmail(e.target.value)}
                />
                {errors.email && (
                  <div className="invalid-feedback">{errors.email}</div>
                )}
              </div>

              <div className="form-group mb-2">
                <label className="form-label"> Mobile No:</label>
                <input
                  type="text"
                  placeholder="Enter Mobile Number"
                  name="mobile"
                  value={mobile}
                  className={`form-control ${
                    errors.mobile ? "is-invalid" : ""
                  }`}
                  onChange={(e) => setMobile(e.target.value)}
                />
                {errors.mobile && (
                  <div className="invalid-feedback">{errors.mobile}</div>
                )}
              </div>

              <button
                className="btn btn-success mb-2"
                onClick={saveOrUpdateEmployee}>
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EmployeeComponent;
