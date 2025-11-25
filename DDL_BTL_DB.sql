CREATE DATABASE IF NOT EXISTS btl_database1;
USE btl_database1;

-- 1. personss
CREATE TABLE IF NOT EXISTS persons
(
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    dob DATE,
    sex ENUM('MALE', 'FEMALE'),
    tel VARCHAR(10),
    address VARCHAR(50)
);

-- 2. person_roles
CREATE TABLE IF NOT EXISTS person_roles (
  person_id INT,
  role ENUM('DOCTOR','PATIENT','STAFF'),
  PRIMARY KEY (person_id, role),
  FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- 3. doctors
CREATE TABLE IF NOT EXISTS doctors
(
    d_person_id INT PRIMARY KEY,
    speciality VARCHAR(30),
    level ENUM('STANDARD', 'PROFESSOR'),
    FOREIGN KEY (d_person_id) REFERENCES persons(person_id)
);

-- 4. patients
CREATE TABLE IF NOT EXISTS patients
(
    p_person_id INT PRIMARY KEY,
    first_seen DATE,
    FOREIGN KEY (p_person_id) REFERENCES persons(person_id)
);

-- 5. staffs
CREATE TABLE IF NOT EXISTS staffs
(
    s_person_id INT PRIMARY KEY,
    workyear_start DATE,
    FOREIGN KEY (s_person_id) REFERENCES persons(person_id)
);

-- 6. time_slots
CREATE TABLE IF NOT EXISTS time_slots
(
    slot_id INT PRIMARY KEY AUTO_INCREMENT,
    start_time DATETIME,
    end_time DATETIME,
    status ENUM('AVAILABLE', 'BOOKED', 'BLOCKED'),
    is_active BOOLEAN,
    d_person_id INT,
    FOREIGN KEY (d_person_id) REFERENCES doctors(d_person_id)
);

-- 7. appointments
CREATE TABLE IF NOT EXISTS appointments
(
    app_id INT PRIMARY KEY AUTO_INCREMENT,
    s_person_id INT,
    p_person_id INT,
    slot_id INT,
    status ENUM('BOOKED', 'CHECKED_IN', 'CANCELLED', 'NO_SHOW'),
    FOREIGN KEY (s_person_id) REFERENCES staffs(s_person_id),
    FOREIGN KEY (p_person_id) REFERENCES patients(p_person_id),
    FOREIGN KEY (slot_id) REFERENCES time_slots(slot_id)
);

-- 8. encounters
CREATE TABLE IF NOT EXISTS encounters
(
    encounter_id INT PRIMARY KEY AUTO_INCREMENT,
    app_id INT,
    start_time DATETIME,
    end_time DATETIME,
    diagnosis VARCHAR(100),
    symtom VARCHAR(100),
    notes VARCHAR(100),
    fee DECIMAL(10,2),
    FOREIGN KEY (app_id) REFERENCES appointments(app_id)
);

-- 9. procedure_catalogs
CREATE TABLE IF NOT EXISTS procedure_catalogs
(
    procedure_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    type VARCHAR(20),
    description VARCHAR(100),
    default_price DECIMAL(10,2),
    is_active BOOLEAN DEFAULT 1
);

-- 10. procedure_orders
CREATE TABLE IF NOT EXISTS procedure_orders
(
    porder_id INT PRIMARY KEY AUTO_INCREMENT,
    encounter_id INT,
    procedure_id INT,
    status ENUM ('REQUESTED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED'),
    result VARCHAR(100),
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id),
    FOREIGN KEY (procedure_id) REFERENCES procedure_catalogs(procedure_id)
);

-- 11. prescriptions
CREATE TABLE IF NOT EXISTS prescriptions
(
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    encounter_id INT,
    title VARCHAR(50),
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id)
);

-- 12. medicines
CREATE TABLE IF NOT EXISTS medicines
(
    medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    strength VARCHAR(30),
    unit VARCHAR(30),
    is_active BOOLEAN DEFAULT 1
);

-- 13. prescription_lines
CREATE TABLE IF NOT EXISTS prescription_lines
(
    prescription_id INT,
    medicine_id INT,
    dosage VARCHAR(30),
    quantity INT,
	PRIMARY KEY (prescription_id, medicine_id),
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
    FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id)
);

-- 14. payments
CREATE TABLE IF NOT EXISTS payments
(
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    encounter_id INT,
    s_person_id INT,
    amount DECIMAL(10,2),
    method ENUM ('CASH', 'CARD', 'EWALLET'),
    pay_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id),
    FOREIGN KEY (s_person_id) REFERENCES staffs(s_person_id)
);
