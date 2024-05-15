
INSERT INTO student (id, name, dateofbirth)
SELECT * FROM (
    SELECT 10009 AS id, 'Ranga' AS name, CAST('2001-03-01' AS DATE) AS dateofbirth
    UNION ALL
    SELECT 10001 AS id, 'Ravi' AS name, CAST('2001-03-01' AS DATE) AS dateofbirth
) AS s
WHERE NOT EXISTS (
    SELECT 1 FROM student WHERE id = s.id
);


