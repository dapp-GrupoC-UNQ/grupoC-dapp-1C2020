import * as React from "react";
import LoadingSpinner from "../../../components/loading-spinner/LoadingSpinner";
import {LanguageContext} from "../../../constants/LanguageMaps";
import Category from "./Category";
import withRouter from "react-router-dom/es/withRouter";
import {storeCategories} from "../../../constants/Constants";


class Categories extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: storeCategories,
            loadingEntitiesState: false,
            dataToShow: true
        }
    }

    renderCategories = (category) => <Category category={category} />


    render() {
        return (
            <div className="homepage">
                <div className="entities-panel">
                    {this.state.loadingEntitiesState && <LoadingSpinner isLoading={this.state.loadingEntitiesState}/>}
                    {!this.state.isLoading && this.state.dataToShow &&
                    <div className="entities">
                        {this.state.categories.map(category => this.renderCategories(category))}
                    </div>
                    }
                </div>
            </div>
        )
    }
}
Categories.contextType = LanguageContext;
export default withRouter(Categories);