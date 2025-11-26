-- Hiển thị
SELECT procedure_id, name, type, description, default_price
FROM procedure_catalogs 
WHERE is_active = TRUE 
ORDER BY procedure_id;

-- Thêm
INSERT INTO procedure_catalogs(name, type, description, default_price) 
VALUES(?, ?, ?, ?);

-- Sửa
UPDATE procedure_catalogs 
SET name = ?, type = ?, description = ?, default_price = ? 
WHERE procedure_id = ?;

-- Xóa mềm
UPDATE procedure_catalogs SET is_active = FALSE
WHERE procedure_id = ?;

-- Tìm kiếm 
SELECT procedure_id, name, type, description, default_price
FROM procedure_catalogs
WHERE is_active = TRUE
	  AND name LIKE ?
      AND type LIKE ?
      AND description LIKE ?
      AND CAST(default_price AS CHAR) LIKE ?
ORDER BY procedure_id;




