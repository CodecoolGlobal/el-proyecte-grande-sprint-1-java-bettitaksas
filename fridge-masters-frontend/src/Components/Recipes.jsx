import { useState, useEffect } from "react";

function Recipes() {
  const [recipes, setRecipes] = useState(null);
  const [update, setUpdate] = useState(0);

  useEffect(() => {
    fetch(`/api/recipes`)
      .then((res) => res.json())
      .then((data) => setRecipes(data));
  }, [update]);
  return (
    <div>
      {recipes ? (
        <div>
          {recipes.map((recipe, index) => (
            <ul>
              <li key={index}>
                {recipe.name}
                <br />
                <div>
                  {" "}
                  Ingredients:
                  {recipe.ingredients.map((ingredient) => (
                    <ul>
                      <li>{ingredient.type}</li>
                    </ul>
                  ))}
                </div>
              </li>
            </ul>
          ))}
        </div>
      ) : (
        <div>loading...</div>
      )}
    </div>
  );
}

export default Recipes;
