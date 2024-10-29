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

  //Search button and query 
  function handleSearch(event) {
    event.preventDefault(); 
    const searchQuery = event.target.search.value.trim();

    if (searchQuery) {
      navigator(`/search?query=${encodeURIComponent(searchQuery)}`);
    } else {
      console.log("Please enter a search term.");
    }
  }

  return (
    <header>
      <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
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
