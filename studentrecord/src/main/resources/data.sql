DROP TABLE IF EXISTS student;

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    dateofbirth DATE
);

INSERT INTO student (name, dateofbirth)
SELECT * FROM (
    SELECT 'Ranga' AS name, CAST('2001-03-01' AS DATE) AS dateofbirth
    UNION ALL
    SELECT 'Ravi' AS name, CAST('2001-03-01' AS DATE) AS dateofbirth
) AS s
WHERE NOT EXISTS (
    SELECT 1 FROM student WHERE name = s.name AND dateofbirth = s.dateofbirth
);

