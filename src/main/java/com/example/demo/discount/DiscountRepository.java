package com.example.demo.discount;

import com.example.demo.season.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("SELECT d FROM Discount d WHERE d.contract.contract_Id = :contract_Id")
    List<Discount> findDiscountByContractId(@Param("contract_Id") Long contractId);

}
