import { useEffect } from "react";
import { Outlet, Link } from "react-router-dom";
function Header(LOGGED_IN_USER) {
  
  return (
    <>
      <div className="header">
        <Link to="/" className="title">
          Fridge Master
        </Link>
        <div>{LOGGED_IN_USER[0]}</div>
        <nav className="navLinks">
          <Link to="/">Home</Link>
          <Link to="/recipes">Recipes</Link>
          <Link to="/login">Login</Link>
          <Link to = "/register">Register</Link>
          <Link to="/fridges">My Fridge</Link>
        </nav>
      </div>
      <Outlet />
    </>
  );
}

export default Header;

/*const Layout = () => (
  <div className="Layout">
    <nav>
      <ul>
        <li className="grow">
          <Link to="/">Employees</Link>
        </li>
        <li>
          <Link to="/create">
            <button type="button">Create Employee</button>
          </Link>
        </li>
      </ul>
    </nav>
    <Outlet />
  </div>
);

export default Layout; */
