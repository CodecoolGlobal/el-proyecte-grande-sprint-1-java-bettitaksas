import React, { useEffect, useState } from 'react';

function Fridges() {
  const [fridgeContents, setFridgeContents] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:3001/api/fridges`)
      .then((res) => res.json())
      .then((fridgeData) => setFridgeContents(fridgeData));
  }, []);

  console.log(fridgeContents);

  if (fridgeContents) {
    console.log("item", fridgeContents[0].fridgeItems.map(el => el.type));
  }

  return (
    <div>
      {fridgeContents ? (
        <>
          {fridgeContents.map((fridge) => (
            <div key={fridge.id}>
              <h2>{fridge.name}</h2>
              <ul>
                {fridge.fridgeItems.map((contents) => (
                  <li key={contents.id}>
                    Type: {contents.type}, Expiry: {contents.expirationDate}
                  </li>
                ))}
              </ul>
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
