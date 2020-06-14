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
            cartTitle: 'Mi Carrito',
            emptyCartText: 'Su carrito está vacío. Ya compre algo maldita sea'
        },
        english: {
            sideBarStore: 'Stores',
            categories: 'Categories',
            discounts: 'Discount',
            seeMyCart: 'My shopping cart',
            logOut: 'Log out',
            sideBarTitle: 'Find your products',
            noProducts: 'Ups! Seems like there are no products in this store',
            cartTitle: 'My Cart',
            emptyCartText: 'Your cart is empty. Buy something god damn it!'
        }
    }

export const LanguageContext = React.createContext(
    LanguageMaps.spanish // default value
);
