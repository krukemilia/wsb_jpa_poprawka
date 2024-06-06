package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractDao<DoctorEntity, Long> implements DoctorDao {
    @Override
    public List<DoctorEntity> findBySpecialization(Specialization specialization) { // TODO - napisac query

        String query = "SELECT doctor FROM DoctorEntity doctor WHERE doctor.specialization = :specialization";
        return entityManager.createQuery(query, DoctorEntity.class)
                .setParameter("specialization", specialization).getResultList();
    }

    @Override
    public long countNumOfVisitsWithPatient(String docFirstName, String docLastName, String patientFirstName, String patientLastName) { // TODO - napisac query
        String query = "SELECT count(doctor) FROM DoctorEntity doctor JOIN doctor.visits visit " +
                "WHERE doctor.firstName=:docFirstName AND doctor.lastName=:docLastName " +
                "AND visit.patient.firstName=:patientFirstName AND visit.patient.lastName=:patientLastName";
        return entityManager.
                createQuery(query, Long.class)
                .setParameter("docFirstName", docFirstName)
                .setParameter("docLastName", docLastName)
                .setParameter("patientFirstName", patientFirstName)
                .setParameter("patientLastName", patientLastName).getSingleResult();
    }
}
