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

    public ProposalsService(ProposalsRepository proposalsRepository) {
        this.proposalsRepository = proposalsRepository;
    }

    public Proposal createProposal(@Valid ProposalCreateRequest proposalCreateRequest) {
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
