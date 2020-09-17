package br.com.zup.bootcamp.proposta.service;

import br.com.zup.bootcamp.proposta.model.BureauStatusRequest;
import br.com.zup.bootcamp.proposta.model.BureauStatusResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BureauService {

    private final WebClient webClient;

    public BureauService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:9999")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public BureauStatusResponse requestBureauStatus(String document) {
        BureauStatusRequest bureauStatusRequest = new BureauStatusRequest();
        bureauStatusRequest.setDocumento(document);
        BureauStatusResponse response =  webClient
                .post()
                .uri("/api/solicitacao")
                .body(Mono.just(bureauStatusRequest), BureauStatusRequest.class)
                .retrieve()
                .bodyToMono(BureauStatusResponse.class)
                .block();
        return response;
    }

    public Boolean isEligible(String document) {
        BureauStatusResponse bureauStatusResponse = requestBureauStatus(document);
        return bureauStatusResponse.getResultadoSolicitacao().equalsIgnoreCase("SEM_RESTRICAO");
    }
}
