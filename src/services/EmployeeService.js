import axios from 'axios';
import { getToken } from './AuthService';

const EMPLOYEE_REST_API_BASE_URL = 'http://localhost:8080/api/employees';

// Add a request interceptor
axios.interceptors.request.use(
    function (config) {
      config.headers["Authorization"] = getToken();
  
      return config;
    },
    function (error) {
      // Do something with request error
      return Promise.reject(error);
    }
  );

export const listEmployees = () => axios.get(EMPLOYEE_REST_API_BASE_URL);

export const createEmployee = (employee) => axios.post(EMPLOYEE_REST_API_BASE_URL, employee);

export const getEmployee = (employeeId) => axios.get(EMPLOYEE_REST_API_BASE_URL + '/' + employeeId);

export const updateEmployee =  (employeeId, employee) => axios.put(EMPLOYEE_REST_API_BASE_URL + '/' + employeeId, employee);

export const deleteEmployee =  (employeeId) => axios.delete(EMPLOYEE_REST_API_BASE_URL + '/' + employeeId);
