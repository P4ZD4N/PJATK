SELECT AVG(SAL), DEPT.DEPTNO, LOC, DNAME
FROM EMP
INNER JOIN DEPT
ON EMP.DEPTNO = DEPT.DEPTNO
GROUP BY DEPT.DEPTNO, LOC, DNAME
HAVING AVG(SAL) > (SELECT AVG(SAL) FROM EMP WHERE DEPTNO = 30);