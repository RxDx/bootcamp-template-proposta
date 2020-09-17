package br.com.zup.bootcamp.proposta.controller;

import br.com.zup.bootcamp.proposta.model.Proposal;
import br.com.zup.bootcamp.proposta.model.ProposalCreateRequest;
import br.com.zup.bootcamp.proposta.model.ProposalCreateResponse;
import br.com.zup.bootcamp.proposta.service.ProposalsService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/proposals", "/propostas"})
public class ProposalsController {

    private ProposalsService proposalsService;

    public ProposalsController(ProposalsService proposalsService) {
        this.proposalsService = proposalsService;
    }

    @PostMapping
    public ResponseEntity<ProposalCreateResponse> create(
            @RequestBody @Valid ProposalCreateRequest proposalCreateRequest,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        try {
            Proposal proposal = proposalsService.createProposal(proposalCreateRequest);
            ProposalCreateResponse proposalCreateResponse = new ProposalCreateResponse(proposal);
            URI entityURI = uriComponentsBuilder.path("/propostas/{id}").build(proposal.getId());
            return ResponseEntity.created(entityURI).body(proposalCreateResponse);
        } catch (ConstraintViolationException | DataIntegrityViolationException exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProposalCreateResponse>> all() {
        List<ProposalCreateResponse> response = new ArrayList();
        proposalsService.getProposals().forEach(proposal ->
                response.add(new ProposalCreateResponse(proposal))
        );
        return ResponseEntity.ok(response);
    }

}