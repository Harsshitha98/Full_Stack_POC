import { useState , useEffect } from 'react'
import { createDepartment, getDepartment, updateDepartment } from '../services/DepartmentService';
import { useNavigate, useParams } from 'react-router-dom';

const DepartmentComponent = () => {
    const [departmentName, setDepartmentName] = useState('');
    const [departmentDesc, setDepartmentDesc] = useState('');

    const {id} = useParams();

    const [errors, setErrors] = useState({
        departmentName:'',
        departmentDesc:''
    });

    const navigate = useNavigate();

    useEffect(() => {
        if(id) {
            getDepartment(id).then((response) => {
                setDepartmentName(response.data.departmentName),
                setDepartmentDesc(response.data.departmentDesc)
            }).catch (error => {
                console.error(error);
            })
        }
    }, [id]);

    function saveOrUpdateDepartment(e) {
        e.preventDefault();

    if (validateForm()) {
        const department ={departmentName, departmentDesc};
        console.log(department);

        if (id) {
            updateDepartment(id, department).then((response) => {
                console.log(response.data);
            navigate('/departments')     
            }). catch (error => {
                console.error(error)
            })
        } else  {
            createDepartment(department).then((response) => {
                console.log(response.data);
            navigate('/departments')     
            }). catch (error => {
                console.error(error)
            })
        }
    }      
}

function validateForm() {
    let valid = true;

    const errorCopy = {...errors}

    if(departmentName.trim()){
        errorCopy.departmentName = '';
    } else {
        errorCopy.departmentName = 'Department name is required!'
        valid = false;
    }

    if(departmentDesc.trim()){
        errorCopy.departmentDesc = '';
    } else {
        errorCopy.departmentDesc = 'Department description is required!'
        valid = false;
    }

    setErrors(errorCopy);

    return valid;

}

function pageTitle() {
    if (id) {
        return <h2 className='text-center'>Update Department</h2>
    } else {
        return <h2 className='text-center'>Add Department</h2>
    }
}



  return (
    <div className='container'>
        <br /> <br /> 
        <div className='row' >
            <div className='card col-md-6 offset-md-3 offset-md-3'>
            {pageTitle()}
            <div className='card-body'>
                <form>
                    <div className='form-group mb-2'>
                        <label className='form-label'>Department Name:</label>
                        <input type='text'
                         placeholder=' Please Enter Department Name!' 
                         name='departmentName'
                         value={departmentName}
                         className={`form-control ${errors.departmentName ? 'is-invalid' : ''}`}
                         onChange={(e => setDepartmentName(e.target.value))}/>
                         {errors.departmentName && <div className='invalid-feedback'>{errors.departmentName}</div>}
                    </div>

                    <div className='form-group mb-2'>
                        <label className='form-label'>Department Description:</label>
                        <input type='text'
                         placeholder='Please Enter Department Description!' 
                         name='departmentDesc'
                         value={departmentDesc}
                         className={`form-control ${errors.departmentDesc ? 'is-invalid' : ''}`}
                         onChange={(e => setDepartmentDesc(e.target.value))}/>
                         {errors.departmentDesc && <div className='invalid-feedback'>{errors.departmentDesc}</div>}
                    </div>

                    <button  className='btn btn-success mb-2' onClick={saveOrUpdateDepartment}>Submit</button>

                </form>
            </div>
            </div>
        </div>
    </div>
  )
}

export default DepartmentComponent
