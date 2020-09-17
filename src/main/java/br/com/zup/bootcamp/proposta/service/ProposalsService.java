package br.com.zup.bootcamp.proposta.service;

import br.com.zup.bootcamp.proposta.model.Proposal;
import br.com.zup.bootcamp.proposta.model.ProposalCreateRequest;
import br.com.zup.bootcamp.proposta.repository.ProposalsRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProposalsService {

    private ProposalsRepository proposalsRepository;
    private BureauService bureauService;

    public ProposalsService(
            ProposalsRepository proposalsRepository,
            BureauService bureauService
    ) {
        this.proposalsRepository = proposalsRepository;
        this.bureauService = bureauService;
    }

    public Proposal createProposal(@Valid ProposalCreateRequest proposalCreateRequest) {
        if (!bureauService.isEligible(proposalCreateRequest.getDocument())) {
            throw new RuntimeException();
        }
        Proposal proposal = new Proposal(
                proposalCreateRequest.getDocument(),
                proposalCreateRequest.getEmail(),
                proposalCreateRequest.getName(),
                proposalCreateRequest.getAddress(),
                BigDecimal.valueOf(proposalCreateRequest.getSalary())
        );
        return proposalsRepository.save(proposal);
    }

    public List<Proposal> getProposals() {
        return proposalsRepository.findAll();
    }

}
