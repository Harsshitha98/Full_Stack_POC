import './App.css'
import EmployeeComponent from './components/EmployeeComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import ListDepartmentComponent from './components/ListDepartmentComponent'
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import DepartmentComponent from './components/DepartmentComponent'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUserLoggedIn } from './services/AuthService'


function App() {

  
  function AuthenticatedRoute({children}){

    const isAuth = isUserLoggedIn();

    if(isAuth) {
      return children;
    }

    return <Navigate to="/" />

  }
  
  return (
    <>
      <BrowserRouter>
      <div className="background-cover">
          < HeaderComponent />
          <Routes>
              {/* //http://localhost:3000 */}
              <Route path='/' element = {<LoginComponent />}></Route>
              {/* //http://localhost:3000/employees */}
              <Route path='/employees' element = {
                <AuthenticatedRoute>
                <ListEmployeeComponent />
                </AuthenticatedRoute>
                }></Route>
              {/* //http://localhost:3000/add-employee */}
              <Route path='/add-employee' element = {
                 <AuthenticatedRoute>
                  <EmployeeComponent />
                 </AuthenticatedRoute>
                }></Route>
               {/* //http://localhost:3000/update-employee/1 */}
               <Route path='/update-employee/:id' element = {
                 <AuthenticatedRoute>
                 <EmployeeComponent />
                </AuthenticatedRoute>
                }></Route>
               {/* //http://localhost:3000/delete-employee/1 */}
               <Route path='/delete-employee/:id' element = {
                 <AuthenticatedRoute>
                 <EmployeeComponent />
                </AuthenticatedRoute>
               }></Route>

              {/* //http://localhost:3000 */}
              <Route path='/' element = {<ListDepartmentComponent />}></Route>
              {/* //http://localhost:3000/departments */}
              <Route path='/departments' element = {
                <AuthenticatedRoute>
                  <ListDepartmentComponent />
                </AuthenticatedRoute>
                }></Route>
              {/* //http://localhost:3000/add-department */}
              <Route path='/add-department' element = {
                <AuthenticatedRoute>
                  <DepartmentComponent />
                </AuthenticatedRoute>}></Route>
              {/* //http://localhost:3000/update-department/1 */}
              <Route path='/update-department/:id' element = {
                <AuthenticatedRoute>
                  <DepartmentComponent />
                </AuthenticatedRoute>}></Route>
              {/* //http://localhost:3000/delete-department/1 */}
              <Route path='/delete-department/:id' element = {
                <AuthenticatedRoute>
                <DepartmentComponent />
              </AuthenticatedRoute>
              }></Route>

              {/* //http://localhost:3000/register */}
              <Route path='/register' element = {<RegisterComponent />}></Route>
              {/* //http://localhost:3000/login */}
              <Route path='/login' element = {<LoginComponent />}></Route>


          </Routes> 
          < FooterComponent />
          </div>
      </BrowserRouter>
    </>
  )
}

export default App
