import './App.css'
import EmployeeComponent from './components/EmployeeComponent'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import RegisterComponent from './components/RegisterComponent'
import LoginComponent from './components/LoginComponent'
import { isUserLoggedIn } from './services/AuthService'
import SearchResult from './components/SearchResult'
import React, { useState } from 'react';


function App() {

  const [searchQuery, setSearchQuery] = useState('');

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
          < HeaderComponent searchQuery={searchQuery} setSearchQuery={setSearchQuery} />
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
             
              {/* //http://localhost:3000/register */}
              <Route path='/register' element = {<RegisterComponent />}></Route>
              {/* //http://localhost:3000/login */}
              <Route path='/login' element = {<LoginComponent />}></Route>

              <Route path="/search" element={<SearchResult />} />
          

          </Routes> 
          < FooterComponent />
          </div>
      </BrowserRouter>
    </>
  )
}

export default App
