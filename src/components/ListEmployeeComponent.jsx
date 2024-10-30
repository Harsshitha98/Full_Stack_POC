import { useState , useEffect } from 'react'
import { deleteEmployee, listEmployees } from '../services/EmployeeService';
import React from 'react';
import { useNavigate } from 'react-router-dom';


const ListEmployeeComponent = () => {

      const [employees, setEmployees] = useState([]);

      const navigator = useNavigate();

      useEffect(() => {
        getAllEmployee();
       }, [])

       function getAllEmployee () {
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        })
       }

       function addNewEmployee() {
        navigator('/add-employee');
       }

       function handleUpdateEmployee(id) {
        navigator(`/update-employee/${id}`);
       }

       function handleDeleteEmployee(id) {
        console.log(id);
        deleteEmployee(id).then((response) => {
            getAllEmployee()
        }).catch(error => {
            console.error(error);
        })
       }

       
      return (
        <div className='container'>

            <h2 className='text-center' style={{ color: 'white', textAlign: 'center', fontWeight: 'bold', fontStyle: 'italic' }}> List Of Employees </h2>

            <button type='button' class='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>

            <table className='table table-striped table-bordered'>
                <thead>
                   <tr>
                    <th>Employee Id</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email Id</th>
                    <th>Actions</th>
                   </tr>
                </thead>
                <tbody>
                    {
                        employees.map(employee => 
                            <tr key={employee.id}>
                                <td>{employee.id}</td>
                                <td>{employee.firstName}</td>
                                <td>{employee.lastName}</td>
                                <td>{employee.email}</td> 
                                <td>
                                    <button type='button' className='btn btn-info' onClick={() => handleUpdateEmployee(employee.id)}>Update</button>
                                    <button type='button' className='btn btn-danger' onClick={() => handleDeleteEmployee(employee.id)} style={{marginLeft:'10px'}}>Delete</button>
                                </td>
                            </tr>)
                    }
                    <tr>
                    </tr>
                </tbody>
            </table>
        </div>
      )
}

export default ListEmployeeComponent


