import * as React from "react";
import withRouter from "react-router-dom/es/withRouter";

class Category extends React.Component {

    goToStoresWithACategory = () =>  this.props.history.push('/stores?category=' + this.props.category.value)

    render() {
        return(
            <div className="entity-card category-card" onClick={() => this.goToStoresWithACategory()}>
                <div className='imagen-comercio'>
                    <img src={this.props.category.categoryImageURL}/>
                </div>
                <div className='nombre-comercio'>
                    {this.props.category.label}
                </div>
            </div>
        )
    }
}

export default withRouter(Category)