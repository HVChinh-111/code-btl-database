SELECT 
    d.d_person_id AS doctor_id,
    p.name AS doctor_name,
    p.dob AS date_of_birth,
    COUNT(DISTINCT a.p_person_id) AS number_of_patients,
    COUNT(CASE 
        WHEN a.status = 'CHECKED_IN' THEN a.app_id 
        ELSE NULL 
    END) AS number_of_completed_examinations,
    (COUNT(CASE 
        WHEN a.status = 'CHECKED_IN' THEN a.app_id 
        ELSE NULL 
    END) * CASE 
        WHEN d.level = 'STANDARD' THEN 300000
        WHEN d.level = 'PROFESSOR' THEN 500000
        ELSE 0
    END) AS total_revenue
FROM doctors d
INNER JOIN persons p ON d.d_person_id = p.person_id
LEFT JOIN time_slots ts ON d.d_person_id = ts.d_person_id
LEFT JOIN appointments a ON ts.slot_id = a.slot_id 
    AND ts.start_time >= ? 
    AND ts.start_time <= ?
GROUP BY d.d_person_id, p.name, p.dob, d.level
ORDER BY total_revenue DESC, doctor_name ASC;