package com.clarim.api.controller;

import com.clarim.api.service.StripeSincronizacaoService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {
    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
    private final StripeSincronizacaoService stripeSincronizacaoService;
    private final String secretWebhook;

    public WebhookController(
            StripeSincronizacaoService sincronizacaoService,
            @Value("${stripe.webhook-secret}") String stripeWebhookSecret) {
        this.stripeSincronizacaoService = sincronizacaoService;
        this.secretWebhook = stripeWebhookSecret;
    }

    @PostMapping("/stripe")
    public ResponseEntity<Void> receber(
            @RequestBody String payload, @RequestHeader("Stripe-Signature") String assinatura) {
        Event evento;

        try {
            evento = Webhook.constructEvent(payload, assinatura, secretWebhook);
        } catch (SignatureVerificationException e) {
            log.error("Erro ao validar assinatura do webhook.", e);
            return ResponseEntity.badRequest().build();
        }

        stripeSincronizacaoService.processar(evento);
        return ResponseEntity.ok().build();
    }
}
