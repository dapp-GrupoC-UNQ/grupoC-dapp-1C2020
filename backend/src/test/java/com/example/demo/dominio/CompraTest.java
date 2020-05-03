package com.example.demo.dominio;

import com.example.demo.builders.AdquiredProductBuilder;
import com.example.demo.builders.CompraBuilder;
import com.example.demo.model.*;
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
    public void aPourchaseHasATotal(){
        AdquiredProduct fideos = AdquiredProductBuilder.aProduct().withName("Fideos").withBrand("Knorr").withPrice(40.25).build();
        AdquiredProduct jugo = AdquiredProductBuilder.aProduct().withName("Jugo").withBrand("Tang").withPrice(40.25).withPrice(8.0).build();
        Compra compra = CompraBuilder.unaCompra().build();
        compra.addAQuiredProduct(fideos);
        compra.addAQuiredProduct(jugo);
        assertEquals(48.25, compra.total());
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
        assertEquals(0.0, compra.total());
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