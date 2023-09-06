import { useEffect } from "react";
import { useParams } from "react-router-dom";
function RecommendedRecipe(){
    const {id} = useParams();
    const [recipe, setRecipe] = useEffect(null);
    useEffect(() => {
        fetch(`/api/recipes/${id}`)
        .then(res => res.json())
        .then(data => setRecipe(data))
    })
    return (
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
                        </div>    )
}

export default RecommendedRecipe;