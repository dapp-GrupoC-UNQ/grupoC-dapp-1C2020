package com.example.demo.dominio;

import com.example.demo.builders.CompraBuilder;
import com.example.demo.builders.MercaderiaBuilder;
import com.example.demo.model.Compra;
import com.example.demo.model.EnvioADomicilio;
import com.example.demo.model.Mercaderia;
import com.example.demo.model.RetiroEnLocal;
import com.example.demo.model.excepciones.OptionNotAvailableForThisDeliveryType;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CompraTest {

    @Test
    public void unaCompraTieneUnMedioDePago(){
        Compra compra = CompraBuilder.unaCompra().conMedioDePago("Efectivo").build();
        assertEquals("Efectivo", compra.medioDePago());
    }

    @Test
    public void unaCompraTieneUnMontoTotal(){
        Mercaderia fideos = MercaderiaBuilder.unaMercaderia().conProducto("Fideos", "Knorr").conPrecio(40.25).build();
        Mercaderia jugo = MercaderiaBuilder.unaMercaderia().conProducto("Jugo", "Tang").conPrecio(8.0).build();
        Compra compra = CompraBuilder.unaCompra().build();
        compra.agregarMercaderia(fideos);
        compra.agregarMercaderia(jugo);
        assertEquals(48.25, compra.montoTotal());
    }

    @Test
    public void unaCompraTieneUnRetiroEnLocalDebeTenerUnHorarioDeRetiro(){
        LocalDateTime hora = LocalDateTime.of(2020,4,25,10,0);
        RetiroEnLocal retiroEnLocal = new RetiroEnLocal(hora);
        Compra compra = CompraBuilder.unaCompra().conTipoDeEnvio(retiroEnLocal).build();
        assertTrue(compra.turnoDeRetiro().isEqual(hora));
    }

    @Test
    public void siUnaCompraNoTieneProductosEnSuListaElMontoEsCero(){
        Compra compra = CompraBuilder.unaCompra().build();
        assertEquals(0.0, compra.montoTotal());
    }

    @Test
    public void siUnaCompraTieneEnvioADomicilioDebeTenerUnaDireccion(){
        EnvioADomicilio envio = new EnvioADomicilio("Alsina 123");
        Compra compra = CompraBuilder.unaCompra().conTipoDeEnvio(envio).build();
        assertEquals("Alsina 123", compra.direccionDeEnvio());
    }

    @Test
    public void  aPurchaseWithStorePickUpHasNoAddress(){
        LocalDateTime hour = LocalDateTime.of(2020,4,25,10,0);
        RetiroEnLocal retiroEnLocal = new RetiroEnLocal(hour);
        Compra purchase = CompraBuilder.unaCompra().conTipoDeEnvio(retiroEnLocal).build();
        assertThrows(OptionNotAvailableForThisDeliveryType.class, () -> purchase.direccionDeEnvio());
    }

    @Test
    public void aPurchaseWithDeliveryHasNotAPickUpDate(){
        EnvioADomicilio delivery = new EnvioADomicilio("Alsina 123");
        Compra purchase = CompraBuilder.unaCompra().conTipoDeEnvio(delivery).build();
        assertThrows(OptionNotAvailableForThisDeliveryType.class, () -> purchase.turnoDeRetiro());
    }

}