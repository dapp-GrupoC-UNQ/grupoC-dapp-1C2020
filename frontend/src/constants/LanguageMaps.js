import * as React from "react";

export const LanguageMaps =
    {
        spanish: {
            sideBarStore: 'Comercios',
            categories: 'Rubros',
            discounts: 'Ofertas',
            seeMyCart: 'Ver mi carrito',
            logOut: 'Salir',
            sideBarTitle: 'Busca tu producto'
        },
        english: {
            sideBarStore: 'Stores',
            categories: 'Categories',
            discounts: 'Discount',
            seeMyCart: 'My shopping cart',
            logOut: 'Log out',
            sideBarTitle: 'Find your products'
        }
    }

export const LanguageContext = React.createContext(
    LanguageMaps.spanish // default value
);
