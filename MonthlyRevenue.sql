SELECT 
	YEAR(pay_time) AS year,
	MONTH(pay_time) AS month,
	SUM(amount) AS total_revenue
FROM payments
WHERE pay_time IS NOT NULL
GROUP BY YEAR(pay_time), MONTH(pay_time)
HAVING total_revenue > 0
ORDER BY year DESC, month DESC;


