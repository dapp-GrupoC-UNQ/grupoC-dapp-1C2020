import * as React from "react";
import Loader from 'react-loader-spinner'
import './loadingspinner.scss'
import "react-loader-spinner/dist/loader/css/react-spinner-loader.css"

class LoadingSpinner extends React.Component {
    render() {
        return (
            <div className="loading-spinner-container">
                {this.props.isLoading &&
                <Loader
                    type="Oval"
                    color="#e5c902"
                    height={100}
                    width={100}
                />
                }
            </div>
        )
    }
}
export default LoadingSpinner;