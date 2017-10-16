package br.com.spring.data.jpa.querydsl.repository;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.spring.data.jpa.querydsl.domain.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer>,
		QueryDslPredicateExecutor<Address> {
}