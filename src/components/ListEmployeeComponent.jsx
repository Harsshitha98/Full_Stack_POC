import { useState, useEffect } from "react";
import { deleteEmployee, listEmployees } from "../services/EmployeeService";
import React from "react";
import { useNavigate } from "react-router-dom";

const ListEmployeeComponent = ({ searchQuery }) => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigator = useNavigate();

  useEffect(() => {
    getAllEmployee();
  }, []);

  const getAllEmployee = () => {
    setLoading(true);
    listEmployees()
      .then((response) => {
        setEmployees(response.data);
        setError(null);
      })
      .catch((error) => {
        setError("Error fetching employees.");
        console.error(error);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const addNewEmployee = () => {
    navigator("/add-employee");
  };

  const handleUpdateEmployee = (id) => {
    navigator(`/update-employee/${id}`);
  };

  const handleDeleteEmployee = (id) => {
    if (window.confirm("Are you sure you want to delete this employee?")) {
      deleteEmployee(id)
        .then(() => {
          getAllEmployee();
        })
        .catch((error) => {
          setError("Error deleting employee.");
          console.error(error);
        });
    }
  };

  // Filter employees based on search query
  const filteredEmployees = searchQuery
    ? employees.filter(
        (employee) =>
          employee.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
          employee.firstName
            .toLowerCase()
            .includes(searchQuery.toLowerCase()) ||
          employee.lastName.toLowerCase().includes(searchQuery.toLowerCase()) ||
          employee.email.toLowerCase().includes(searchQuery.toLowerCase())
      )
    : employees;

  return (
    <div className="container">
      <h2
        className="text-center"
        style={{ color: "white", fontWeight: "bold", fontStyle: "italic" }}
      >
        List Of Employees
      </h2>

      <button
        type="button"
        className="btn btn-primary mb-2"
        onClick={addNewEmployee}
      >
        Add Employee
      </button>

      {loading && <div className="text-center">Loading...</div>}
      {error && <div className="text-danger">{error}</div>}

      <table className="table table-striped table-bordered">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Id</th>
            <th>Mobile</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredEmployees.map((employee) => (
            <tr key={employee.id}>
              <td>{employee.id}</td>
              <td>{employee.name}</td>
              <td>{employee.firstName}</td>
              <td>{employee.lastName}</td>
              <td>{employee.email}</td>
              <td>{employee.mobile}</td>
              <td>
                <button
                  type="button"
                  className="btn btn-info"
                  onClick={() => handleUpdateEmployee(employee.id)}
                >
                  Update
                </button>
                <button
                  type="button"
                  className="btn btn-danger"
                  onClick={() => handleDeleteEmployee(employee.id)}
                  style={{ marginLeft: "10px" }}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListEmployeeComponent;
