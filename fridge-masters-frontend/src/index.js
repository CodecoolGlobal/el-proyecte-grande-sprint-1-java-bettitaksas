import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Header from "./Components/Header";
import ErrorElement from "./Components/ErrorElement";
import Fridges from "./Components/Fridges";
import Login from "./Components/Login";
import MainPage from "./Components/MainPage";
import Recipes from "./Components/Recipes";
import Register from "./Components/Register";
import { useState } from "react";
import SingleRecipe from "./Components/SingleRecipe";

let LOGGED_IN_USER = [];
const router = createBrowserRouter([
  {
    path: "/",
    element: <Header LOGGED_IN_USER ={LOGGED_IN_USER} />,
    errorElement: <ErrorElement />,
    
    children: [
      {
        path: "/",
        element: <MainPage />,
      },
      {
        path: "/fridges",
        element: <Fridges LOGGED_IN_USER ={LOGGED_IN_USER}/>,
      },
      {
        path: "/login",
        element: <Login LOGGED_IN_USER ={LOGGED_IN_USER} />,
      },
      {
        path: "/register",
        element: <Register />,
      },
      {
        path: "/recipes",
        element: <Recipes/>
      },
      {
        path: "/recipe/:id",
        element: <SingleRecipe />
      }
    ],
  },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
