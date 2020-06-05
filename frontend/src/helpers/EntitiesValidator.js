const EntitiesValidator = () => {
    const validateClientUser = (state) => {
        return !!state.username && !!state.password && !!state.address;
    }

    const validateStoreAdmin = (state) => {
        return !!state.storeName && !!state.address && !!state.username && !!state.password
            && !!state.rubros && !!state.openingDays && !!state.openingTime && !!state.closingTime && !!state.paymentMethods && !!state.deliveryDistance;
    }

    return {
        validateClientUser: validateClientUser,
        validateStoreAdmin: validateStoreAdmin
    }
}

export default EntitiesValidator;