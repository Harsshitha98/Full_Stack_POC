import React from 'react'
import { useState , useEffect } from 'react'
import { deleteDepartment, listDepartments } from '../services/DepartmentService';
import { useNavigate } from 'react-router-dom';



const ListDepartmentComponent = () => {

    const [departments, setDepartments] = useState([]);

      const navigator = useNavigate();

      useEffect(() => {
        getAllDeparmtents();
       }, [])

       function getAllDeparmtents () {
        listDepartments().then((response) => {
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
       }

       function addNewDepartment() {
        navigator('/add-department');
       }

       function handleUpdateDepartment(id) {
        navigator(`/update-department/${id}`);
       }

       function handleDeleteDepartment(id) {
        console.log(id);
        deleteDepartment(id).then((response) => {
            getAllDeparmtents()
        }).catch(error => {
            console.error(error);
        })
       }


  return (
    <div className='container'>

        <h2 className='text-center' style={{ color: 'white', textAlign: 'center', fontWeight: 'bold', fontStyle: 'italic' }}> List Of Departments </h2>

        <button type='button' class='btn btn-primary mb-2' onClick={addNewDepartment}>Add Department</button>

        <table className='table table-striped table-bordered'>
        <thead>
                   <tr>
                    <th>Department Id</th>
                    <th>Department Name</th>
                    <th>Department Desc</th>
                    <th>Actions</th>
                   </tr>
                </thead>

                <tbody>
                    {
                        departments.map(department => 
                            <tr key={department.id}>
                                <td>{department.id}</td>
                                <td>{department.departmentName}</td>
                                <td>{department.departmentDesc}</td>
                                <td>
                                    <button type='button' className='btn btn-info' onClick={() => handleUpdateDepartment(department.id)}>Update</button>
                                    <button type='button' className='btn btn-danger' onClick={() => handleDeleteDepartment(department.id)} style={{marginLeft:'10px'}}>Delete</button>
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

export default ListDepartmentComponent
