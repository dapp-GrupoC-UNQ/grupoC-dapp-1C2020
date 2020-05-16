import * as React from "react";

class Category extends React.Component {
    render() {
        return(
            <div className="entity-card category-card" onClick={() => this.props.onSelectCategory(this.props.category)}>
                <div className='imagen-comercio'>
                    <img src={this.props.category.categoryImageURL}/>
                </div>
                <div className='nombre-comercio'>
                    {this.props.category.categoryName}
                </div>
            </div>
        )
    }
}

export default Category