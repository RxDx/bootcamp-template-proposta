package br.com.zup.bootcamp.proposta.repository;

import br.com.zup.bootcamp.proposta.model.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalsRepository extends JpaRepository<Proposal, Long> {
}
