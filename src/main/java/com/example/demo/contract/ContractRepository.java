package com.example.demo.contract;

import com.example.demo.season.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c WHERE c.hotel.hotel_Id = :hotelId")
    List<Contract> findContractsByHotelId(Long hotelId);
}

/*List<Season> findSeasonByContract_ContractId(Long contract_Id);
@Query("SELECT s FROM Season s WHERE s.contract.contract_Id = :contract_Id")
List<Season> findSeasonsByContractId(@Param("contract_Id") Long contract_Id);
*/