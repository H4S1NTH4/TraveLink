package com.example.demo.season;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeasonRepository extends JpaRepository<Season, Long> {

    //List<Season> findSeasonByContract_ContractId(Long contract_Id);
    @Query("SELECT s FROM Season s WHERE s.contract.contract_Id = :contract_Id")
    List<Season> findSeasonsByContractId(@Param("contract_Id") Long contract_Id);

}
