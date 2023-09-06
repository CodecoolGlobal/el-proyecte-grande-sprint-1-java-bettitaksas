import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
function RecommendedRecipe({LOGGED_IN_USER}){
    const {id} = useParams();
    const [recipe, setRecipe] = useState(null);
    useEffect(() => {
        fetch(`/api/recipes/placeholder/${id}`, {
            headers: {
              Authorization: `Bearer ${LOGGED_IN_USER[3]}`,
              "Content-Type": "application/json",
            },
          })
        .then(res => res.json())
        .then(data => setRecipe(data))
    }, [])
    return ( recipe ? 
    <div>
<div  className='recipe-box'>
                            <ul >
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
                        </div>: <div>loading...</div>   )
}

export default RecommendedRecipe;