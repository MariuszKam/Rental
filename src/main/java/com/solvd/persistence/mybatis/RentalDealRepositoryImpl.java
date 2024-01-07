package com.solvd.persistence.mybatis;

import com.solvd.model.deal.RentalDeal;
import com.solvd.persistence.connection.MyBaitsConfig;
import com.solvd.persistence.deal.RentalDealRepository;

import com.solvd.persistence.utilities.RepositoryUtility;
import org.apache.ibatis.session.SqlSession;


import java.util.Optional;

public class RentalDealRepositoryImpl implements RentalDealRepository {

    private static final Class<RentalDealRepository> REPOSITORY_CLASS = RentalDealRepository.class;

    @Override
    public void create(RentalDeal rentalDeal) {
        try (SqlSession sqlSession = MyBaitsConfig.getSqlSessionFactory().openSession()) {
            String namespace = "com.solvd.persistence.deal.RentalDealRepository";

            sqlSession.insert(namespace + ".create", rentalDeal);

            if (rentalDeal.getVehicles() != null && !rentalDeal.getVehicles().isEmpty()) {
                sqlSession.insert(namespace + ".createRentalHasVehicle", rentalDeal);
            }

            sqlSession.commit();
        }
    }

    @Override
    public Optional<RentalDeal> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, rentalDealRepository -> rentalDealRepository.findById(id));
    }

    @Override
    public Optional<RentalDeal> findByRelatedTableId(String table, Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, rentalDealRepository -> rentalDealRepository.findByRelatedTableId(table, id));
    }
}
