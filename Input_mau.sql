USE btl_database1;

-- 1. persons (10 doctors + 10 staff + 20 patients = 40 người)
INSERT INTO persons (person_id, name, dob, sex, tel, address) VALUES
(1,'Nguyen Van A','1980-05-10','MALE','0900000001','Ha Noi'),
(2,'Tran Thi B','1978-08-22','FEMALE','0900000002','Ha Noi'),
(3,'Le Van C','1985-11-30','MALE','0900000003','Ho Chi Minh City'),
(4,'Pham Thi D','1975-02-15','FEMALE','0900000004','Da Nang'),
(5,'Hoang Van E','1982-07-07','MALE','0900000005','Hai Phong'),
(6,'Vu Thi F','1988-09-19','FEMALE','0900000006','Can Tho'),
(7,'Do Van G','1979-03-25','MALE','0900000007','Hue'),
(8,'Nguyen Thi H','1983-12-05','FEMALE','0900000008','Ha Noi'),
(9,'Bui Van I','1986-06-17','MALE','0900000009','Ho Chi Minh City'),
(10,'Pham Thi J','1990-01-28','FEMALE','0900000010','Ha Noi'),
-- staff
(11,'Nguyen Van K','1984-04-12','MALE','0900000011','Ha Noi'),
(12,'Tran Thi L','1987-09-03','FEMALE','0900000012','Ha Noi'),
(13,'Le Van M','1991-02-20','MALE','0900000013','Ho Chi Minh City'),
(14,'Pham Thi N','1993-07-15','FEMALE','0900000014','Da Nang'),
(15,'Hoang Van O','1989-05-30','MALE','0900000015','Hai Phong'),
(16,'Vu Thi P','1992-11-11','FEMALE','0900000016','Can Tho'),
(17,'Do Van Q','1985-03-02','MALE','0900000017','Hue'),
(18,'Nguyen Thi R','1994-06-09','FEMALE','0900000018','Ha Noi'),
(19,'Bui Van S','1988-10-21','MALE','0900000019','Ho Chi Minh City'),
(20,'Pham Thi T','1990-12-31','FEMALE','0900000020','Ha Noi'),
-- patients
(21,'Patient U1','1995-01-05','MALE','0900000021','Ha Noi'),
(22,'Patient U2','1996-02-10','FEMALE','0900000022','Ha Noi'),
(23,'Patient U3','1997-03-15','MALE','0900000023','Ho Chi Minh City'),
(24,'Patient U4','1998-04-20','FEMALE','0900000024','Da Nang'),
(25,'Patient U5','1999-05-25','MALE','0900000025','Hai Phong'),
(26,'Patient U6','2000-06-30','FEMALE','0900000026','Can Tho'),
(27,'Patient U7','1994-07-07','MALE','0900000027','Hue'),
(28,'Patient U8','1993-08-08','FEMALE','0900000028','Ha Noi'),
(29,'Patient U9','1992-09-09','MALE','0900000029','Ho Chi Minh City'),
(30,'Patient U10','1991-10-10','FEMALE','0900000030','Ha Noi'),
(31,'Patient U11','1990-11-11','MALE','0900000031','Ha Noi'),
(32,'Patient U12','1989-12-12','FEMALE','0900000032','Da Nang'),
(33,'Patient U13','1988-01-13','MALE','0900000033','Ho Chi Minh City'),
(34,'Patient U14','1987-02-14','FEMALE','0900000034','Hai Phong'),
(35,'Patient U15','1986-03-15','MALE','0900000035','Can Tho'),
(36,'Patient U16','1985-04-16','FEMALE','0900000036','Hue'),
(37,'Patient U17','1984-05-17','MALE','0900000037','Ha Noi'),
(38,'Patient U18','1983-06-18','FEMALE','0900000038','Ho Chi Minh City'),
(39,'Patient U19','1982-07-19','MALE','0900000039','Ha Noi'),
(40,'Patient U20','1981-08-20','FEMALE','0900000040','Da Nang');

-- 2. person_roles (mỗi người 1 role)
INSERT INTO person_roles (person_id, role) VALUES
(1,'DOCTOR'),(2,'DOCTOR'),(3,'DOCTOR'),(4,'DOCTOR'),(5,'DOCTOR'),
(6,'DOCTOR'),(7,'DOCTOR'),(8,'DOCTOR'),(9,'DOCTOR'),(10,'DOCTOR'),
(11,'STAFF'),(12,'STAFF'),(13,'STAFF'),(14,'STAFF'),(15,'STAFF'),
(16,'STAFF'),(17,'STAFF'),(18,'STAFF'),(19,'STAFF'),(20,'STAFF'),
(21,'PATIENT'),(22,'PATIENT'),(23,'PATIENT'),(24,'PATIENT'),(25,'PATIENT'),
(26,'PATIENT'),(27,'PATIENT'),(28,'PATIENT'),(29,'PATIENT'),(30,'PATIENT'),
(31,'PATIENT'),(32,'PATIENT'),(33,'PATIENT'),(34,'PATIENT'),(35,'PATIENT'),
(36,'PATIENT'),(37,'PATIENT'),(38,'PATIENT'),(39,'PATIENT'),(40,'PATIENT');

-- 3. doctors (10 bác sĩ)
INSERT INTO doctors (d_person_id, speciality, level) VALUES
(1,'Internal Medicine','STANDARD'),
(2,'Pediatrics','PROFESSOR'),
(3,'Cardiology','STANDARD'),
(4,'Dermatology','STANDARD'),
(5,'Neurology','PROFESSOR'),
(6,'Endocrinology','STANDARD'),
(7,'Gastroenterology','STANDARD'),
(8,'Orthopedics','STANDARD'),
(9,'Ophthalmology','STANDARD'),
(10,'ENT','STANDARD');

-- 4. patients (20)
INSERT INTO patients (p_person_id, first_seen) VALUES
(21,'2024-01-10'),
(22,'2024-01-15'),
(23,'2024-02-05'),
(24,'2024-02-20'),
(25,'2024-03-10'),
(26,'2024-03-25'),
(27,'2024-04-05'),
(28,'2024-04-18'),
(29,'2024-05-02'),
(30,'2024-05-16'),
(31,'2024-06-01'),
(32,'2024-06-10'),
(33,'2024-07-03'),
(34,'2024-07-20'),
(35,'2024-08-08'),
(36,'2024-08-25'),
(37,'2024-09-05'),
(38,'2024-09-18'),
(39,'2024-10-01'),
(40,'2024-10-15');

-- 5. staffs (10)
INSERT INTO staffs (s_person_id, workyear_start) VALUES
(11,'2015-01-01'),
(12,'2016-03-15'),
(13,'2017-06-01'),
(14,'2018-09-20'),
(15,'2019-11-10'),
(16,'2020-02-05'),
(17,'2021-04-18'),
(18,'2021-07-07'),
(19,'2022-01-12'),
(20,'2022-05-25');

-- 6. time_slots (20 slot, mỗi slot 20 phút)
INSERT INTO time_slots (slot_id, start_time, end_time, status, is_active, d_person_id) VALUES
(1,'2025-01-10 09:00:00','2025-01-10 09:20:00','AVAILABLE',1,1),
(2,'2025-01-10 09:20:00','2025-01-10 09:40:00','BOOKED',1,1),
(3,'2025-01-10 09:40:00','2025-01-10 10:00:00','BOOKED',1,2),
(4,'2025-01-10 10:00:00','2025-01-10 10:20:00','AVAILABLE',1,2),
(5,'2025-01-10 10:20:00','2025-01-10 10:40:00','BLOCKED',1,3),
(6,'2025-01-10 10:40:00','2025-01-10 11:00:00','AVAILABLE',1,3),
(7,'2025-01-10 13:00:00','2025-01-10 13:20:00','BOOKED',1,4),
(8,'2025-01-10 13:20:00','2025-01-10 13:40:00','BOOKED',1,4),
(9,'2025-01-10 13:40:00','2025-01-10 14:00:00','AVAILABLE',1,5),
(10,'2025-01-10 14:00:00','2025-01-10 14:20:00','AVAILABLE',1,6),
(11,'2025-01-10 14:20:00','2025-01-10 14:40:00','BOOKED',1,7),
(12,'2025-01-10 14:40:00','2025-01-10 15:00:00','BOOKED',1,8),
(13,'2025-01-11 09:00:00','2025-01-11 09:20:00','AVAILABLE',1,9),
(14,'2025-01-11 09:20:00','2025-01-11 09:40:00','BOOKED',1,10),
(15,'2025-01-11 09:40:00','2025-01-11 10:00:00','BOOKED',1,1),
(16,'2025-01-11 10:00:00','2025-01-11 10:20:00','AVAILABLE',1,2),
(17,'2025-01-11 10:20:00','2025-01-11 10:40:00','AVAILABLE',1,3),
(18,'2025-01-11 10:40:00','2025-01-11 11:00:00','BOOKED',1,4),
(19,'2025-01-11 13:00:00','2025-01-11 13:20:00','BOOKED',1,5),
(20,'2025-01-11 13:20:00','2025-01-11 13:40:00','AVAILABLE',1,6);

-- 7. appointments (24 cuộc hẹn, 1–20 sẽ có encounter, 21–24 là CANCELLED/NO_SHOW)
INSERT INTO appointments (app_id, s_person_id, p_person_id, slot_id, status) VALUES
(1,11,21,2,'CHECKED_IN'),
(2,11,22,3,'CHECKED_IN'),
(3,12,23,7,'CHECKED_IN'),
(4,12,24,8,'CHECKED_IN'),
(5,13,25,11,'CHECKED_IN'),
(6,13,26,12,'CHECKED_IN'),
(7,14,27,14,'CHECKED_IN'),
(8,14,28,15,'CHECKED_IN'),
(9,15,29,18,'CHECKED_IN'),
(10,15,30,19,'CHECKED_IN'),
(11,16,31,1,'CHECKED_IN'),
(12,16,32,4,'CHECKED_IN'),
(13,17,33,6,'CHECKED_IN'),
(14,17,34,9,'CHECKED_IN'),
(15,18,35,10,'CHECKED_IN'),
(16,18,36,13,'CHECKED_IN'),
(17,19,37,16,'CHECKED_IN'),
(18,19,38,17,'CHECKED_IN'),
(19,20,39,5,'CHECKED_IN'),
(20,20,40,20,'CHECKED_IN'),
-- các appointment không tạo encounter
(21,11,21,3,'CANCELLED'),
(22,12,22,7,'NO_SHOW'),
(23,13,23,8,'CANCELLED'),
(24,14,24,11,'NO_SHOW');

-- STANDARD = 300000, PROFESSOR = 500000)

INSERT INTO encounters (encounter_id, app_id, start_time, end_time, diagnosis, symptom, notes, fee) VALUES
(1,1,'2025-01-10 09:25:00','2025-01-10 09:40:00',
 'Common cold','Cough, sore throat','Rest and fluids',300000.00),   -- STANDARD
(2,2,'2025-02-10 09:45:00','2025-02-10 10:00:00',
 'Gastritis','Stomach pain','Avoid spicy food',500000.00),          -- PROFESSOR
(3,3,'2025-03-10 13:05:00','2025-03-10 13:20:00',
 'Hypertension','Headache','Monitor blood pressure',300000.00),     -- STANDARD
(4,4,'2025-04-10 13:25:00','2025-04-10 13:40:00',
 'Diabetes','Thirst, fatigue','Check HbA1c',300000.00),             -- STANDARD
(5,5,'2025-06-10 14:25:00','2025-06-10 14:40:00',
 'Migraine','Severe headache','Painkiller',300000.00),              -- STANDARD
(6,6,'2025-06-22 14:45:00','2025-06-22 15:00:00',
 'Allergic rhinitis','Sneezing','Allergy meds',300000.00),          -- STANDARD
(7,7,'2025-07-05 09:05:00','2025-07-05 09:20:00',
 'Back pain','Low back pain','Physiotherapy',300000.00),            -- STANDARD
(8,8,'2025-07-20 09:25:00','2025-07-20 09:40:00',
 'Flu','Fever, cough','Antiviral',300000.00),                       -- STANDARD
(9,9,'2025-08-08 10:05:00','2025-08-08 10:20:00',
 'Check-up','No symptom','Routine exam',300000.00),                 -- STANDARD
(10,10,'2025-08-25 10:25:00','2025-08-25 10:40:00',
 'Anxiety','Chest tightness','Counseling',500000.00),               -- PROFESSOR
(11,11,'2025-09-05 09:05:00','2025-09-05 09:20:00',
 'Sinusitis','Nasal congestion','Nasal spray',300000.00),           -- STANDARD
(12,12,'2025-09-18 09:25:00','2025-09-18 09:40:00',
 'Bronchitis','Cough','Antibiotics',500000.00),                     -- PROFESSOR
(13,13,'2025-10-01 10:05:00','2025-10-01 10:20:00',
 'Eye infection','Red eye','Eye drops',300000.00),                  -- STANDARD
(14,14,'2025-10-15 10:25:00','2025-10-15 10:40:00',
 'Ear pain','Otitis','Pain relief',500000.00),                      -- PROFESSOR
(15,15,'2025-11-02 11:00:00','2025-11-02 11:20:00',
 'Skin rash','Itching','Topical cream',300000.00),                  -- STANDARD
(16,16,'2025-11-18 11:25:00','2025-11-18 11:45:00',
 'Arthritis','Joint pain','NSAIDs',300000.00),                      -- STANDARD
(17,17,'2025-12-01 09:10:00','2025-12-01 09:30:00',
 'Asthma','Shortness of breath','Inhaler',500000.00),               -- PROFESSOR
(18,18,'2025-12-10 09:35:00','2025-12-10 09:55:00',
 'Gout','Joint pain','Diet control',300000.00),                     -- STANDARD
(19,19,'2025-01-20 10:05:00','2025-01-20 10:20:00',
 'Liver check','No symptom','Monitor labs',300000.00),              -- STANDARD
(20,20,'2025-04-22 10:25:00','2025-04-22 10:40:00',
 'Kidney check','Mild pain','Ultrasound',300000.00);                -- STANDARD

-- 9. procedure_catalogs (10)
INSERT INTO procedure_catalogs (procedure_id, name, type, description, default_price) VALUES
(1,'Complete Blood Count','LAB','Standard CBC test',150000.00),
(2,'Chest X-Ray','IMAGING','X-ray of chest',300000.00),
(3,'Abdominal Ultrasound','IMAGING','Ultrasound of abdomen',400000.00),
(4,'ECG','CARDIO','Electrocardiogram',250000.00),
(5,'Liver Function Test','LAB','Liver enzyme panel',350000.00),
(6,'Kidney Function Test','LAB','Renal function panel',350000.00),
(7,'Blood Glucose Test','LAB','Fasting blood sugar',120000.00),
(8,'Allergy Test','LAB','Basic allergy screen',500000.00),
(9,'Spirometry','PULMO','Lung function test',300000.00),
(10,'Eye Pressure Test','OPHTHA','Glaucoma screening',200000.00);

-- 10. procedure_orders (25 dòng, nhiều encounter có nhiều thủ thuật, vài cái COMPLETED)
INSERT INTO procedure_orders (porder_id, encounter_id, procedure_id, status, result) VALUES
(1,1,1,'COMPLETED','Normal'),
(2,1,7,'COMPLETED','Slightly high'),
(3,2,3,'REQUESTED','Pending'),
(4,2,5,'COMPLETED','Normal'),
(5,3,4,'COMPLETED','Mild abnormality'),
(6,3,2,'REQUESTED','Pending'),
(7,4,7,'COMPLETED','High'),
(8,5,1,'COMPLETED','Normal'),
(9,6,8,'REQUESTED','Pending'),
(10,7,4,'COMPLETED','Normal'),
(11,8,1,'REQUESTED','Pending'),
(12,9,6,'COMPLETED','Slightly impaired'),
(13,10,9,'COMPLETED','Normal'),
(14,11,1,'COMPLETED','Normal'),
(15,12,2,'REQUESTED','Pending'),
(16,13,10,'COMPLETED','Normal'),
(17,14,1,'REQUESTED','Pending'),
(18,15,5,'COMPLETED','Normal'),
(19,16,3,'COMPLETED','Normal'),
(20,17,9,'REQUESTED','Pending'),
(21,18,6,'COMPLETED','Slightly impaired'),
(22,19,5,'REQUESTED','Pending'),
(23,19,1,'COMPLETED','Normal'),
(24,20,6,'COMPLETED','Normal'),
(25,20,2,'REQUESTED','Pending');

-- 11. prescriptions (18 đơn thuốc, 1 số encounter không có đơn)
INSERT INTO prescriptions (prescription_id, encounter_id, title) VALUES
(1,1,'Prescription for common cold'),
(2,2,'Gastritis treatment'),
(3,3,'Hypertension medication'),
(4,4,'Diabetes management'),
(5,5,'Migraine relief'),
(6,6,'Allergy treatment'),
(7,7,'Back pain therapy'),
(8,8,'Flu treatment'),
(9,9,'Routine vitamins'),
(10,10,'Anxiety support'),
(11,11,'Sinusitis treatment'),
(12,12,'Bronchitis treatment'),
(13,13,'Eye infection drops'),
(14,14,'Ear infection therapy'),
(15,15,'Skin rash cream'),
(16,16,'Arthritis pain relief'),
(17,18,'Gout management'),
(18,19,'Liver support');

-- 12. medicines (15)
INSERT INTO medicines (medicine_id, name, strength, unit) VALUES
(1,'Paracetamol','500mg','tablet'),
(2,'Ibuprofen','400mg','tablet'),
(3,'Omeprazole','20mg','capsule'),
(4,'Metformin','500mg','tablet'),
(5,'Losartan','50mg','tablet'),
(6,'Cetirizine','10mg','tablet'),
(7,'Vitamin C','500mg','tablet'),
(8,'Saline Nasal Spray','0.9%','bottle'),
(9,'Azithromycin','500mg','tablet'),
(10,'Alprazolam','0.5mg','tablet'),
(11,'Hydrocortisone Cream','1%','tube'),
(12,'Diclofenac','50mg','tablet'),
(13,'Inhaler Salbutamol','100mcg','inhaler'),
(14,'Allopurinol','300mg','tablet'),
(15,'Eye Drop Ofloxacin','0.3%','bottle');

-- 13. prescription_lines (~30 dòng)
INSERT INTO prescription_lines (prescription_id, medicine_id, dosage, quantity) VALUES
(1,1,'1 tab x3/day',10),
(1,7,'1 tab/day',7),
(2,3,'1 cap/day',14),
(2,1,'1 tab x2/day',14),
(3,5,'1 tab/day',30),
(3,1,'1 tab when pain',20),
(4,4,'1 tab x2/day',60),
(4,7,'1 tab/day',30),
(5,2,'1 tab when pain',15),
(5,1,'1 tab x3/day',9),
(6,6,'1 tab/day',10),
(6,8,'2 spray x2/day',1),
(7,12,'1 tab x2/day',20),
(7,1,'1 tab when pain',10),
(8,9,'1 tab/day',5),
(8,1,'1 tab x3/day',9),
(9,7,'1 tab/day',30),
(10,10,'1 tab at night',14),
(10,7,'1 tab/day',14),
(11,8,'2 spray x3/day',1),
(11,1,'1 tab x3/day',7),
(12,9,'1 tab/day',7),
(12,1,'1 tab x3/day',10),
(13,15,'2 drops x3/day',1),
(14,1,'1 tab x3/day',7),
(15,11,'Apply x2/day',1),
(16,12,'1 tab x2/day',20),
(16,1,'1 tab when pain',10),
(17,14,'1 tab/day',30),
(18,7,'1 tab/day',30);

-- 14. payments
-- Mỗi encounter 1 payment cho phí khám.
-- Encounter có procedure COMPLETED thì có thêm 1 payment cho phí thủ thuật.
INSERT INTO payments (payment_id, encounter_id, s_person_id, amount, method, pay_time) VALUES
-- ====== PHÍ KHÁM (CONSULTATION FEE) ======
-- Encounter 1 (STANDARD = 300000)
(1, 1, 11, 300000.00, 'CASH',    '2025-01-10 09:50:00'),
-- Encounter 2 (PROFESSOR = 500000)
(2, 2, 12, 500000.00, 'CARD',    '2025-02-10 10:05:00'),
-- Encounter 3 (STANDARD)
(3, 3, 13, 300000.00, 'EWALLET', '2025-03-10 13:30:00'),
-- Encounter 4 (STANDARD)
(4, 4, 14, 300000.00, 'CASH',    '2025-04-10 13:45:00'),
-- Encounter 5 (STANDARD)
(5, 5, 15, 300000.00, 'CARD',    '2025-06-10 14:45:00'),
-- Encounter 6 (STANDARD)
(6, 6, 16, 300000.00, 'EWALLET', '2025-06-22 15:10:00'),
-- Encounter 7 (STANDARD)
(7, 7, 17, 300000.00, 'CASH',    '2025-07-05 09:35:00'),
-- Encounter 8 (STANDARD)
(8, 8, 18, 300000.00, 'CARD',    '2025-07-20 09:50:00'),
-- Encounter 9 (STANDARD)
(9, 9, 19, 300000.00, 'EWALLET', '2025-08-08 10:30:00'),
-- Encounter 10 (PROFESSOR)
(10,10, 20, 500000.00, 'CASH',   '2025-08-25 10:50:00'),
-- Encounter 11 (STANDARD)
(11,11, 11, 300000.00, 'CARD',   '2025-09-05 09:25:00'),
-- Encounter 12 (PROFESSOR)
(12,12, 12, 500000.00, 'EWALLET','2025-09-18 09:45:00'),
-- Encounter 13 (STANDARD)
(13,13, 13, 300000.00, 'CASH',   '2025-10-01 10:25:00'),
-- Encounter 14 (PROFESSOR)
(14,14, 14, 500000.00, 'CARD',   '2025-10-15 10:55:00'),
-- Encounter 15 (STANDARD)
(15,15, 15, 300000.00, 'EWALLET','2025-11-02 11:30:00'),
-- Encounter 16 (STANDARD)
(16,16, 16, 300000.00, 'CASH',   '2025-11-18 11:50:00'),
-- Encounter 17 (PROFESSOR)
(17,17, 17, 500000.00, 'CARD',   '2025-12-01 09:40:00'),
-- Encounter 18 (STANDARD)
(18,18, 18, 300000.00, 'EWALLET','2025-12-10 10:00:00'),
-- Encounter 19 (STANDARD)
(19,19, 19, 300000.00, 'CASH',   '2025-01-20 10:30:00'),
-- Encounter 20 (STANDARD)
(20,20, 20, 300000.00, 'CARD',   '2025-04-22 10:50:00'),

-- ====== PHÍ THỦ THUẬT (PROCEDURE FEE, GỘP THEO ENCOUNTER) ======
-- Encounter 1: proc 1 (150000) + proc 7 (120000) = 270000
(21, 1, 11, 270000.00, 'CASH',    '2025-01-11 10:00:00'),
-- Encounter 2: proc 5 (350000)
(22, 2, 12, 350000.00, 'CARD',    '2025-02-11 10:10:00'),
-- Encounter 3: proc 4 (250000)
(23, 3, 13, 250000.00, 'EWALLET', '2025-03-11 10:20:00'),
-- Encounter 4: proc 7 (120000)
(24, 4, 14, 120000.00, 'CASH',    '2025-04-11 10:30:00'),
-- Encounter 5: proc 1 (150000)
(25, 5, 15, 150000.00, 'CARD',    '2025-06-11 10:40:00'),
-- Encounter 7: proc 4 (250000)
(26, 7, 16, 250000.00, 'EWALLET', '2025-07-06 10:50:00'),
-- Encounter 9: proc 6 (350000)
(27, 9, 17, 350000.00, 'CASH',    '2025-08-09 11:00:00'),
-- Encounter 10: proc 9 (300000)
(28,10, 18, 300000.00, 'CARD',    '2025-08-26 11:10:00'),
-- Encounter 11: proc 1 (150000)
(29,11, 19, 150000.00, 'EWALLET', '2025-09-06 11:20:00'),
-- Encounter 13: proc 10 (200000)
(30,13, 20, 200000.00, 'CASH',    '2025-10-02 11:30:00'),
-- Encounter 15: proc 5 (350000)
(31,15, 11, 350000.00, 'CARD',    '2025-11-03 11:40:00'),
-- Encounter 16: proc 3 (400000)
(32,16, 12, 400000.00, 'EWALLET', '2025-11-19 11:50:00'),
-- Encounter 18: proc 6 (350000)
(33,18, 13, 350000.00, 'CASH',    '2025-12-11 12:00:00'),
-- Encounter 19: proc 1 (150000)
(34,19, 14, 150000.00, 'CARD',    '2025-01-21 10:15:00'),
-- Encounter 20: proc 6 (350000)
(35,20, 15, 350000.00, 'EWALLET', '2025-04-23 10:45:00');


