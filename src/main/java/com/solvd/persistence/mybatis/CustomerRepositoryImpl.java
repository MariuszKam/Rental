package com.solvd.persistence.mybatis;


import com.solvd.model.persons.customer.Customer;
import com.solvd.persistence.connection.MyBaitsConfig;
import com.solvd.persistence.persons.customer.CustomerRepository;
import com.solvd.persistence.utilities.RepositoryUtility;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void create(Customer customer) {
        RepositoryUtility.executeVoidSQL(CustomerRepository.class, customerRepository -> customerRepository.create(customer));
    }

    @Override
    public Optional<Customer> findById(Long id) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            return customerRepository.findById(id);
        }
    }

    @Override
    public Optional<Customer> findByRentalDealId(Long rentalDealId) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            return customerRepository.findByRentalDealId(rentalDealId);
        }
    }


}