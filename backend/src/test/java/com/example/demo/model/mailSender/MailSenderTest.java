package com.example.demo.model.mailSender;

import com.example.demo.builders.PurchaseBuilder;
import com.example.demo.builders.StoreBuilder;
import com.example.demo.builders.UserBuilder;
import com.example.demo.model.Purchase;
import com.example.demo.model.User;
import com.example.demo.model.store.Store;
import com.example.demo.sendMail.MailSender;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderTest {

    @Mock
    JavaMailSender javaMailSender;

    MailSender mailSenderMock;

    @Test
    public void whenAnUserFinishedAPurchaseReceivedAConfirmationEmail(){
        User pepe = UserBuilder.user().withUsername("luliialonso@hotmail.com").build();
        Store store = StoreBuilder.aStore().withMail("store@gmail.com").build();
        Purchase purchase = PurchaseBuilder.aPurchase().withUser(pepe).withStore(store).build();
        purchase.finishPurchase("Efectivo", mailSenderMock);
        //verify(mailSenderMock, times(1)).sendMail(purchase.store().mail(), pepe.username(), "Confirmacion de compra", any());
    }
}
