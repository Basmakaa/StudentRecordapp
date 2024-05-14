INSERT INTO student (id, name, dateofbirth)
SELECT * FROM (
    SELECT 10009 AS id, 'Ranga' AS name, 'E1234567' AS dateofbirth
    UNION ALL
    SELECT 10001 AS id, 'Ravi' AS name, 'A1234568' AS dateofbirth
) AS s
WHERE NOT EXISTS (
    SELECT 1 FROM student WHERE id = s.id
);

