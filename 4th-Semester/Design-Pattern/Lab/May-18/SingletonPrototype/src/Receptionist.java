public class Receptionist {

    private final HospitalDatabaseManager db;
    private final HospitalLogger          logger;
    private final AuthenticationManager   auth;
    private final String                  userId;

    public Receptionist(String userId) {
        this.userId = userId;
        this.db     = HospitalDatabaseManager.getInstance();
        this.logger = HospitalLogger.getInstance();
        this.auth   = AuthenticationManager.getInstance();
        auth.authenticate(userId, "Receptionist");
    }

    public void registerPatient(String patientId, String name, String age) {
        db.connect();
        db.executeQuery("INSERT INTO patients (id, name, age) VALUES ('"
                + patientId + "', '" + name + "', '" + age + "')");
        logger.log("Receptionist", "Registered patient: " + name + " [ID:" + patientId + "]");
        System.out.println("[Receptionist] Patient '" + name + "' registered.");
    }

    public void assignRoom(String patientId, String roomNumber) {
        db.updatePatientRecord(patientId, "room", roomNumber);
        logger.log("Receptionist", "Room " + roomNumber + " assigned to patient " + patientId);
        System.out.println("[Receptionist] Room " + roomNumber + " assigned to " + patientId);
    }
}