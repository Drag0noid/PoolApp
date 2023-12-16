package ru.pool.poolapp.models;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AppointmentModel {
    private final StringProperty patientName;
    private final StringProperty patientPolis;
    private final StringProperty paramedicName;
    private final StringProperty clinic;
    private final StringProperty visitTime;
    private final StringProperty patientDiagnosis;

    public AppointmentModel(String patientName, String patientPolis, String paramedicName, String clinic,
                            String visitTime, String patientDiagnosis) {
        this.patientName = new SimpleStringProperty(patientName);
        this.patientPolis = new SimpleStringProperty(patientPolis);
        this.paramedicName = new SimpleStringProperty(paramedicName);
        this.clinic = new SimpleStringProperty(clinic);
        this.visitTime = new SimpleStringProperty(visitTime);
        this.patientDiagnosis = new SimpleStringProperty(patientDiagnosis);
    }

    public String getPatientName() {
        return patientName.get();
    }

    public StringProperty patientNameProperty() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName.set(patientName);
    }

    public String getPatientPolis() {
        return patientPolis.get();
    }

    public StringProperty patientPolisProperty() {
        return patientPolis;
    }

    public void setPatientPolis(String patientPolis) {
        this.patientPolis.set(patientPolis);
    }

    public String getParamedicName() {
        return paramedicName.get();
    }

    public StringProperty paramedicNameProperty() {
        return paramedicName;
    }

    public void setParamedicName(String paramedicName) {
        this.paramedicName.set(paramedicName);
    }

    public String getClinic() {
        return clinic.get();
    }

    public StringProperty clinicProperty() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic.set(clinic);
    }

    public String getVisitTime() {
        return visitTime.get();
    }

    public StringProperty visitTimeProperty() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime.set(visitTime);
    }

    public String getPatientDiagnosis() {
        return patientDiagnosis.get();
    }

    public StringProperty patientDiagnosisProperty() {
        return patientDiagnosis;
    }

    public void setPatientDiagnosis(String patientDiagnosis) {
        this.patientDiagnosis.set(patientDiagnosis);
    }
}