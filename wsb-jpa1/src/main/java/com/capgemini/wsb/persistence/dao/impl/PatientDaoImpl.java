package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.TreatmentType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByDoctor(String firstName, String lastName) { // TODO - napisac query

        String query = "SELECT patient FROM PatientEntity patient JOIN patient.visits visit WHERE visit.doctor.firstName = :firstName AND visit.doctor.lastName = :lastName";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();

    }

    @Override
    public List<PatientEntity> findPatientsHavingTreatmentType(TreatmentType treatmentType) { // TODO - napisac query
        String query = "SELECT DISTINCT patient FROM PatientEntity patient JOIN patient.visits visit JOIN visit.medicalTreatments medicalTreatment WHERE medicalTreatment.type=:treatmentType";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("treatmentType", treatmentType)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsSharingSameLocationWithDoc(String firstName, String lastName) { // TODO - napisac query

        String query = "SELECT DISTINCT patient FROM PatientEntity patient " +
                "JOIN patient.addresses patientAddress, DoctorEntity doctor JOIN doctor.addresses doctorAddress" +
                " WHERE doctor.firstName=:firstName AND doctor.lastName=:lastName AND patientAddress = doctorAddress";
        return entityManager.createQuery(query, PatientEntity.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName).getResultList();


    }

    @Override
    public List<PatientEntity> findPatientsWithoutLocation() { // TODO - napisac query

        String query = "SELECT patient FROM PatientEntity patient WHERE patient.addresses IS EMPTY";
        return entityManager.createQuery(query, PatientEntity.class).getResultList();
    }
}
