import React, { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

function Fridges() {
  const [fridgeContents, setFridgeContents] = useState(null);
  const [itemTypes, setItemTypes] = useState(null);
  const [itemSelected, setItemSelected] = useState(null);
  const [recommendedRecipe, setRecommendedRecipe] = useState(null);
  const [selectedDate, setSelectedDate] = useState(null);

  function fetchInfo() {
    let fetchReqs = [
      fetch(`/api/items`).then((res) => res.json()),
      fetch(`/api/fridges`).then((res) => res.json()),
    ];
    return Promise.all(fetchReqs);
  }

  useEffect(() => {
    fetchInfo().then((info) => {
      let [items, fridges] = info;
      setFridgeContents(fridges);
      setItemTypes(items);
    });
  }, []);

  function addHandler(fridgeId, itemSelected) {
    const formattedDate = selectedDate
      ? selectedDate.toISOString().split("T")[0]
      : new Date().toISOString().split("T")[0];

    fetch(`/api/fridges/${fridgeId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        type: itemSelected,
        expirationDate: formattedDate,
      }),
    })
      .then(() => fetchInfo())
      .then((info) => {
        let [items, fridges] = info;
        setFridgeContents(fridges);
        setItemTypes(items);
      });
  }

  function deleteHandler(fridgeId, contentId) {
    fetch(`/api/fridges/${fridgeId}/${contentId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then(() => fetchInfo())
      .then((info) => {
        let [items, fridges] = info;
        setFridgeContents(fridges);
        setItemTypes(items);
      });
  }

  function recommendRecipe(fridgeId) {
    fetch(`http://localhost:3001/api/recipes/recommendation/${fridgeId}`)
      .then((res) => res.json())
      .then((recipe) => setRecommendedRecipe(recipe));
  }

  function getRecipe() {
    if (recommendedRecipe) {
      return recommendedRecipe.name;
    }
  }

  return (
    <div>
      {fridgeContents ? (
        <>
          {fridgeContents.map((fridge, index) => (
            <div key={fridge.id}>
              {"Fridge: " + (index + 1)}
              <h2>{fridge.name}</h2>
              <ul>
                {fridge.fridgeItems.map((contents) => (
                  <li key={contents.id}>
                    Ingredient: {contents.type} Expires:{" "}
                    {contents.expirationDate}
                    <div onClick={() => deleteHandler(fridge.id, contents.id)}>
                      🗑️
                    </div>
                  </li>
                ))}
              </ul>
              <div className="ItemAdder">
              <select onChange={(e) => setItemSelected(e.target.value)}>
                <option selected="selected">
                  Choose an item to add to your fridge
                </option>
                {itemTypes.map((itemtype) => (
                  <option key={itemtype}>{itemtype}</option>
                ))}
              </select>
              <DatePicker
                selected={selectedDate}
                onChange={(date) => setSelectedDate(date)}
              />
              <button onClick={() => addHandler(fridge.id, itemSelected)}>
                Add Item
              </button>
              <button onClick={() => recommendRecipe(fridge.id)}>
                Recommend recipe
              </button>
              <div>{getRecipe()}</div>
              </div>
            </div>
          ))}
        </>
      ) : (
        <div>loading...</div>
      )}
    </div>
  );
}

export default Fridges;
