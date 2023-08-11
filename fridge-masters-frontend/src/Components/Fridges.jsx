import React, { useEffect, useState } from "react";

function Fridges() {
  let DOMAIN_NAME = "http://localhost:3001"
  const [fridgeContents, setFridgeContents] = useState(null);
  const [itemTypes, setItemTypes] = useState(null);
  const [itemSelected, setItemSelected] = useState(null);
  const [recommendedRecipe, setRecommendedRecipe] = useState(null);

  function fetchKitchenInfo() {
    let fetchReqs = [
      fetch(`${DOMAIN_NAME}/api/items`).then((res) => res.json()),
      fetch(`${DOMAIN_NAME}/api/fridges`).then((res) => res.json()),
    ];
    return Promise.all(fetchReqs);
  }
  useEffect(() => {
    fetchKitchenInfo().then((info) => {
      let [items, fridges] = info;
      setItemTypes(items);
      setFridgeContents(fridges);
    });
  }, []);

  function addHandler(fridgeId, itemSelected) {
    fetch(`http://localhost:3001/api/fridges/${fridgeId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        itemType: itemSelected,
        expirationDate: "2023-11-11",
      }),
    })
      .then(() => fetchKitchenInfo())
      .then((info) => {
        let [items, fridges] = info;
        setFridgeContents(fridges);
        setItemTypes(items);
      });
  }
  function deleteHandler(fridgeId, contentId) {
    fetch(`http://localhost:3001/api/fridges/${fridgeId}/${contentId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then(() => fetchKitchenInfo())
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
              <select onChange={(e) => setItemSelected(e.target.value)}>
                <option selected="selected">
                  Choose an item to add to your fridge
                </option>
                {itemTypes.map((itemtype) => (
                  <option key={itemtype}>{itemtype}</option>
                ))}
              </select>
              <button onClick={() => addHandler(fridge.id, itemSelected)}>
                Add Item
              </button>
              <button onClick={() => recommendRecipe(fridge.id)}>
                Recommend recipe
              </button>
              <div>{getRecipe()}</div>
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
