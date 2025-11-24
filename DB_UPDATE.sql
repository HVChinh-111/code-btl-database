CREATE DATABASE IF NOT EXISTS btl_database1;
USE btl_database1;

-- 1. personss
CREATE TABLE IF NOT EXISTS persons
(
    person_id CHAR(10) PRIMARY KEY,
    name VARCHAR(30),
    dob DATE,
    sex ENUM('MALE', 'FEMALE'),
    tel VARCHAR(15),
    address VARCHAR(50)
);

-- 2. person_roles
CREATE TABLE IF NOT EXISTS person_roles (
  person_id CHAR(10),
  role SET('DOCTOR','PATIENT','STAFF'),
  PRIMARY KEY (person_id, role),
  FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- 3. doctors
CREATE TABLE IF NOT EXISTS doctors
(
    d_person CHAR(10) PRIMARY KEY,
    speciality VARCHAR(30),
    level ENUM('STANDARD', 'PROFESSOR'),
    FOREIGN KEY (d_person) REFERENCES persons(person_id)
);

-- 4. patients
CREATE TABLE IF NOT EXISTS patients
(
    p_person CHAR(10) PRIMARY KEY,
    first_seen DATE,
    FOREIGN KEY (p_person) REFERENCES persons(person_id)
);

-- 5. staffs
CREATE TABLE IF NOT EXISTS staffs
(
    s_person CHAR(10) PRIMARY KEY,
    workyear_start DATE,
    FOREIGN KEY (s_person) REFERENCES persons(person_id)
);

-- 6. time_slots
CREATE TABLE IF NOT EXISTS time_slots
(
    slot_id CHAR(10) PRIMARY KEY,
    start_time DATETIME,
    end_time DATETIME,
    status ENUM('AVAILABLE', 'BOOKED', 'BLOCKED'),
    is_active BOOLEAN,
    d_person CHAR(10),
    FOREIGN KEY (d_person) REFERENCES doctors(d_person)
);

-- 7. appointments
CREATE TABLE IF NOT EXISTS appointments
(
    app_id CHAR(10) PRIMARY KEY,
    s_person CHAR(10),
    p_person CHAR(10),
    slot_id CHAR(10),
    status ENUM('BOOKED', 'CONFIRMED', 'CHECKED_IN', 'COMPLETED', 'CANCELLED', 'NO_SHOW'),
    FOREIGN KEY (s_person) REFERENCES staffs(s_person),
    FOREIGN KEY (p_person) REFERENCES patients(p_person),
    FOREIGN KEY (slot_id) REFERENCES time_slots(slot_id)
);

-- 8. encounters
CREATE TABLE IF NOT EXISTS encounters
(
    encounter_id CHAR(10) PRIMARY KEY,
    app_id CHAR(10),
    start_time DATETIME,
    end_time DATETIME,
    diagnosis VARCHAR(100),
    symtom VARCHAR(100),
    notes VARCHAR(100),
    status VARCHAR(15),
    d_person CHAR(10),
    FOREIGN KEY (d_person) REFERENCES doctors(d_person),
    FOREIGN KEY (app_id) REFERENCES appointments(app_id)
);

-- 9. procedure_catalogs
CREATE TABLE IF NOT EXISTS procedure_catalogs
(
    procedure_id CHAR(10) PRIMARY KEY,
    name VARCHAR(50),
    type VARCHAR(20),
    description VARCHAR(100),
    default_price DECIMAL(10,2)
);

-- 10. procedure_orders
CREATE TABLE IF NOT EXISTS procedure_orders
(
    porder_id CHAR(10) PRIMARY KEY,
    encounter_id CHAR(10),
    procedure_id CHAR(10),
    performed_by CHAR(10),
    status VARCHAR(15),
    result VARCHAR(100),
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id),
    FOREIGN KEY (procedure_id) REFERENCES procedure_catalogs(procedure_id),
    FOREIGN KEY (performed_by) REFERENCES doctors(d_person)
);

-- 11. prescriptions
CREATE TABLE IF NOT EXISTS prescriptions
(
    prescription_id CHAR(10) PRIMARY KEY,
    encounter_id CHAR(10),
    fee DECIMAL(10,2),
    notes VARCHAR(100),
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id)
);

-- 12. medicines
CREATE TABLE IF NOT EXISTS medicines
(
    medicine_id CHAR(10) PRIMARY KEY,
    name VARCHAR(50),
    strength VARCHAR(20),
    unit VARCHAR(10)
);

-- 13. prescription_items
CREATE TABLE IF NOT EXISTS prescription_items
(
    line_id CHAR(10) PRIMARY KEY,
    prescription_id CHAR(10),
    medicine_id CHAR(10),
    instruction VARCHAR(20),
    quantity INT,
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
    FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
);

-- 14. payments
CREATE TABLE IF NOT EXISTS payments
(
    payment_id CHAR(10) PRIMARY KEY,
    encounter_id CHAR(10),
    amount DECIMAL(10,2),
    method VARCHAR(20),
    pay_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id)
);
