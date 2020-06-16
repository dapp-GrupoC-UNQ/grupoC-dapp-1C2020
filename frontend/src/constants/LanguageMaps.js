import * as React from "react";

export const LanguageMaps =
    {
        spanish: {
            sideBarStore: 'Comercios',
            categories: 'Rubros',
            discounts: 'Ofertas',
            seeMyCart: 'Ver mi carrito',
            logOut: 'Salir',
            sideBarTitle: 'Busca tu producto',
            noProducts: '¡Ups! Parece que no hay productos en este comercio',
            noStores: '¡Ups! Parece que no hay comercios que pertenezcan a la categoría seleccionada',
            cartTitle: 'Mi Carrito',
            emptyCartText: 'Su carrito está vacío. Ya compre algo maldita sea',
            loading: 'Cargando...',
            pricePerUnit: 'Precio por unidad:',
            amountInCart: 'Llevas:',
            storeCategories: {
                'CLEANING_SUPPLIES': 'Limpieza',
                'BUTCHER': 'Carniceria',
                'GREENGROCES': 'Verduleria',
                'HYGIENE_PRODUCTS': 'Perfumeria',
                'GROCERY': 'Almacen',
                'BAKERY': 'Panaderia'
            }
        },
        english: {
            sideBarStore: 'Stores',
            categories: 'Categories',
            discounts: 'Discount',
            seeMyCart: 'My shopping cart',
            logOut: 'Log out',
            sideBarTitle: 'Find your products',
            noProducts: 'Ups! Seems like there are no products in this store',
            noStores: 'Ups! Seems like there are no stores for the selected category',
            cartTitle: 'My Cart',
            emptyCartText: 'Your cart is empty. Buy something god damn it!',
            loading: 'Loading...',
            pricePerUnit: 'Price per unit:',
            amountInCart: 'You are taking:',
            storeCategories: {
                'CLEANING_SUPPLIES': 'Cleaning supplies',
                'BUTCHER': 'Butchers',
                'GREENGROCES': 'Greengrocers',
                'HYGIENE_PRODUCTS': 'Hygiene products',
                'GROCERY': 'Grocery',
                'BAKERY': 'Bakery'
            }
        }
    }

export const LanguageContext = React.createContext(
    LanguageMaps.spanish // default value
);
