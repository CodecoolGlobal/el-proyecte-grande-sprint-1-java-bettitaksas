import { useState, useEffect } from 'react';

function Recipes() {
    const [recipes, setRecipes] = useState(null);
    const [update, setUpdate] = useState(0);

    useEffect(() => {
        fetch(`/api/recipes`)
            .then((res) => res.json())
            .then((data) => {setRecipes(data)
            console.log(data)});
    }, [update]);
    return (
        <div>
            {recipes ? (
                <div className='recipes'>
                    {recipes.map((recipe, index) => (
                        <div key={index} className='recipe-box'>
                            <ul key={index}>
                                <li>
                                  <h4>{recipe.name}</h4>
                                    <br />
                                    <div>
                                      <div className='ingreds'>
                                      Ingredients:
                                        {recipe.ingredients.map(
                                            (ingredient, i) => (
                                                <ul key={i}>
                                                    <li>{ingredient.type}</li>
                                                </ul>
                                            )
                                        )}
                                      </div>
                                        {recipe.description}
                                    </div>
                                </li>
                            </ul>
                        </div>
                    ))}
                </div>
            ) : (
                <div>loading...</div>
            )}
        </div>
    );
}

export default Recipes;
