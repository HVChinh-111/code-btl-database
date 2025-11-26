-- Hiển thị
SELECT medicine_id, name, strength, unit
FROM medicines
WHERE is_active = TRUE
ORDER BY medicine_id;

-- Thêm
INSERT INTO medicines(name, strength, unit)
VALUES(?, ?, ?);

-- Sửa
UPDATE medicines
SET name = ?, strength = ?, unit = ?
WHERE medicine_id = ?;

-- Xóa mềm
UPDATE medicines
SET is_active = FALSE
WHERE medicine_id = ?;

-- Tìm kiếm 
SELECT medicine_id, name, strength, unit 
FROM medicines
WHERE is_active = TRUE
	  AND name LIKE ?
      AND strength LIKE ?
      AND unit LIKE ?
ORDER BY medicine_id