import java.time.LocalDate;

public class Entry {
    private int id;
    private String patient;
    private String symptom;
    private String diagnose;
    private LocalDate datum;
    private String krankenhaus;

    public Entry(int id, String patient, String symptom, String diagnose, LocalDate datum, String krankenhaus) {
        this.id = id;
        this.patient = patient;
        this.symptom = symptom;
        this.diagnose = diagnose;
        this.datum = datum;
        this.krankenhaus = krankenhaus;
    }

    public int getId() {
        return id;
    }

    public String getPatient() {
        return patient;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public String getKrankenhaus() {
        return krankenhaus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public void setKrankenhaus(String krankenhaus) {
        this.krankenhaus = krankenhaus;
    }
}
