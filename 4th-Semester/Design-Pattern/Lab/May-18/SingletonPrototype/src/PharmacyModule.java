public class PharmacyModule {

    private final HospitalDatabaseManager db;
    private final HospitalLogger          logger;

    public PharmacyModule() {
        this.db     = HospitalDatabaseManager.getInstance();
        this.logger = HospitalLogger.getInstance();
    }

    public void dispenseMedicine(String patientId, PrescriptionTemplate rx) {
        db.connect();
        for (String medicine : rx.getMedicineList()) {
            db.executeQuery("UPDATE inventory SET stock = stock - 1 WHERE medicine = '"
                + medicine + "'");
            System.out.println("[Pharmacy] Dispensed: " + medicine + " → Patient: " + patientId);
        }
        logger.log("PharmacyModule", "Medicines dispensed to patient " + patientId);
    }

    public void checkInventory(String medicine) {
        db.connect();
        String result = db.executeQuery("SELECT stock FROM inventory WHERE medicine = '"
            + medicine + "'");
        System.out.println("[Pharmacy] Inventory check — " + medicine + ": " + result);
        logger.log("PharmacyModule", "Inventory checked for: " + medicine);
    }
}