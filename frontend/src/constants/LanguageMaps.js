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
            emptyCartText: 'Su carrito está vacío. Ya compre algo maldita sea',
            loading: 'Cargando...',
            pricePerUnit: 'Precio por unidad:',
            amountInCart: 'Llevas:',
            maximumDeliveryDistance: 'Este comercio hace deliveries de hasta ',
            storeCategories: {
                'HYGIENE_PRODUCTS': 'Higiene personal'
            },
            openingDays: {
                'MONDAY': 'Lunes',
                'TUESDAY': 'Martes',
                'WEDNESDAY': 'Miercoles',
                'THURSDAY': 'Jueves',
                'FRIDAY': 'Viernes',
                'SATURDAY': 'Sabado',
                'SUNDAY': 'Domingo'

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
            cartTitle: 'My Cart',
            emptyCartText: 'Your cart is empty. Buy something god damn it!',
            loading: 'Loading...',
            pricePerUnit: 'Price per unit:',
            amountInCart: 'You are taking:',
            maximumDeliveryDistance: 'This store makes deliveries up to '
        }
    }

export const LanguageContext = React.createContext(
    LanguageMaps.spanish // default value
);
