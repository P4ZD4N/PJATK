SELECT ENAME, SAL, DEPTNO
FROM EMP e
WHERE SAL > ALL (SELECT SAL FROM EMP WHERE DEPTNO = 30);