SELECT AVG(SAL) FROM EMP INNER JOIN SALGRADE ON SAL BETWEEN LOSAL AND HISAL WHERE GRADE = 2;