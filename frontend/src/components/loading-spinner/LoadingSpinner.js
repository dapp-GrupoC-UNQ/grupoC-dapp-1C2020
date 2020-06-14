import * as React from "react";
import Loader from 'react-loader-spinner'
import './loadingspinner.scss'
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"
import {LanguageContext} from "../../constants/LanguageMaps";

class LoadingSpinner extends React.Component {
    render() {
        return (
            <div className="loading-spinner-container">
                {this.props.isLoading &&
                <div>
                    <Loader
                        type="Oval"
                        color="#e5c902"
                        height={100}
                        width={100}
                    />
                    <span>{this.context.loading}</span>
                </div>
                }
            </div>
        )
    }
}
LoadingSpinner.contextType = LanguageContext;
export default LoadingSpinner;