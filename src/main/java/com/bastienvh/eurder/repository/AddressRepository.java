package com.bastienvh.eurder.repository;

import com.bastienvh.eurder.domain.customer.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
