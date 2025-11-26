CREATE DATABASE IF NOT EXISTS btl_database1;
USE btl_database1;

-- 1. personss
CREATE TABLE IF NOT EXISTS persons
(
    person_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    sex ENUM('MALE', 'FEMALE'),
    tel VARCHAR(10) NOT NULL,
    address VARCHAR(255),
    password VARCHAR(30) NOT NULL,
    CHECK (CHAR_LENGTH(tel) = 10)
);

-- 2. person_roles
CREATE TABLE IF NOT EXISTS person_roles (
  person_id INT NOT NULL UNIQUE,
  role ENUM('DOCTOR','PATIENT','STAFF') NOT NULL,
  PRIMARY KEY (person_id, role),
  FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- 3. doctors
CREATE TABLE IF NOT EXISTS doctors
(
    d_person_id INT PRIMARY KEY NOT NULL UNIQUE,
    speciality VARCHAR(30),
    level ENUM('STANDARD', 'PROFESSOR'),
    FOREIGN KEY (d_person_id) REFERENCES persons(person_id)
);

-- 4. patients
CREATE TABLE IF NOT EXISTS patients
(
    p_person_id INT PRIMARY KEY NOT NULL UNIQUE,
    first_seen BOOLEAN,
    FOREIGN KEY (p_person_id) REFERENCES persons(person_id)
);

-- 5. staffs
CREATE TABLE IF NOT EXISTS staffs
(
    s_person_id INT PRIMARY KEY NOT NULL UNIQUE,
    workyear_start DATE,
    FOREIGN KEY (s_person_id) REFERENCES persons(person_id)
);

-- 6. time_slots
CREATE TABLE IF NOT EXISTS time_slots
(
    slot_id INT PRIMARY KEY AUTO_INCREMENT,
	d_person_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status ENUM('AVAILABLE', 'BOOKED', 'BLOCKED') NOT NULL,
    is_active BOOLEAN DEFAULT 1,
    FOREIGN KEY (d_person_id) REFERENCES doctors(d_person_id),
    UNIQUE (d_person_id, start_time, end_time),
    CHECK (end_time > start_time)
);

-- 7. appointments
CREATE TABLE IF NOT EXISTS appointments
(
    app_id INT PRIMARY KEY AUTO_INCREMENT,
    s_person_id INT NOT NULL,
    p_person_id INT NOT NULL,
    slot_id INT NOT NULL,
    status ENUM('BOOKED', 'CHECKED_IN', 'CANCELLED', 'NO_SHOW') NOT NULL,
    FOREIGN KEY (s_person_id) REFERENCES staffs(s_person_id),
    FOREIGN KEY (p_person_id) REFERENCES patients(p_person_id),
    FOREIGN KEY (slot_id) REFERENCES time_slots(slot_id),
    UNIQUE (p_person_id, slot_id)
);

-- 8. encounters
CREATE TABLE IF NOT EXISTS encounters
(
    encounter_id INT PRIMARY KEY AUTO_INCREMENT,
    app_id INT NOT NULL UNIQUE,
    start_time DATETIME,
    end_time DATETIME,
    diagnosis TEXT,
    symptom TEXT,
    notes TEXT,
    fee DECIMAL(10,2),
    FOREIGN KEY (app_id) REFERENCES appointments(app_id),
    CHECK (end_time > start_time)
);

-- 9. procedure_catalogs
CREATE TABLE IF NOT EXISTS procedure_catalogs
(
    procedure_id INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    type VARCHAR(50),
    description TEXT,
    default_price DECIMAL(10,2) NOT NULL,
    is_active BOOLEAN DEFAULT 1
);

-- 10. procedure_orders
CREATE TABLE IF NOT EXISTS procedure_orders
(
    porder_id INT PRIMARY KEY AUTO_INCREMENT,
    encounter_id INT NOT NULL,
    procedure_id INT NOT NULL,
    status ENUM ('REQUESTED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') NOT NULL,
    result TEXT,
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id),
    FOREIGN KEY (procedure_id) REFERENCES procedure_catalogs(procedure_id),
    UNIQUE (encounter_id, procedure_id)
);

-- 11. prescriptions
CREATE TABLE IF NOT EXISTS prescriptions
(
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    encounter_id INT UNIQUE,
    title VARCHAR(100),
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id)
);

-- 12. medicines
CREATE TABLE IF NOT EXISTS medicines
(
    medicine_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    strength VARCHAR(50),
    unit VARCHAR(50),
    is_active BOOLEAN DEFAULT 1
);

-- 13. prescription_lines
CREATE TABLE IF NOT EXISTS prescription_lines
(
    prescription_id INT NOT NULL,
    medicine_id INT NOT NULL,
    dosage VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
	PRIMARY KEY (prescription_id, medicine_id),
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
    FOREIGN KEY (medicine_id) REFERENCES medicines(medicine_id),
    UNIQUE (prescription_id, medicine_id)
);

-- 14. payments
CREATE TABLE IF NOT EXISTS payments
(
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    encounter_id INT NOT NULL,
    s_person_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    method ENUM ('CASH', 'CARD', 'EWALLET') NOT NULL,
    pay_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (encounter_id) REFERENCES encounters(encounter_id),
    FOREIGN KEY (s_person_id) REFERENCES staffs(s_person_id)
);
