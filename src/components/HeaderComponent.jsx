import React from "react";
import { NavLink } from "react-router-dom";
import { isUserLoggedIn, logout } from "../services/AuthService";
import { useNavigate } from "react-router-dom";

const HeaderComponent = () => {
  const isAuth = isUserLoggedIn();

  const navigator = useNavigate();

  function handleLogout() {
    logout();
    navigator("/login");
  }

  function handleSearch(e) {
    e.preventDefault(); // Prevent default form submission
    const searchQuery = e.target.search.value.trim(); // Get the search query from the input

    if (searchQuery) {
      // Navigate to the search results page, passing the query as a URL parameter
      navigator(`/search?query=${encodeURIComponent(searchQuery)}`);
    } else {
      // Optionally, you can handle empty search queries (e.g., show a message)
      console.log("Please enter a search term.");
    }
  }

  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
        <a className="navbar-brand" href="https://www.javaguides.net">
          Employee Managment System
        </a>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            {isAuth && (
              <li className="nav-item">
                <NavLink
                  className="nav-link navbar-brand mb-0 h1"
                  to="/employees"
                >
                  Employees
                </NavLink>
              </li>
            )}

            {isAuth && (
              <li className="nav-item">
                <NavLink
                  className="nav-link navbar-brand mb-0 h1"
                  to="/departments"
                >
                  Departments
                </NavLink>
              </li>
            )}
              <form
              className="d-flex ms-auto"
              onSubmit={handleSearch}
              role="search"
            >
              <input
                className="form-control me-2"
                type="search"
                name="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button className="btn btn-outline-success" type="submit">
                Search
              </button>
            </form> 
          </ul>
          <div className="register-link">
            <ul className="navbar-nav">
              {!isAuth && (
                <li className="nav-item">
                  <NavLink
                    className="nav-link navbar-brand mb-0 h1"
                    to="/register"
                  >
                    Register
                  </NavLink>
                </li>
              )}
              {!isAuth && (
                <li className="nav-item">
                  <NavLink
                    className="nav-link navbar-brand mb-0 h1"
                    to="/login"
                  >
                    Login
                  </NavLink>
                </li>
              )}
              {isAuth && (
                <li className="nav-item">
                  <NavLink
                    className="nav-link  navbar-brand mb-0 h1"
                    to="/login"
                    onClick={handleLogout}
                  >
                    Logout
                  </NavLink>
                </li>
              )}
            </ul>
          </div>
        </div>
      </nav>
    </header>
  );
};

export default HeaderComponent;
