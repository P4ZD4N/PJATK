SELECT EMP.ENAME, EMP.JOB, LOC FROM EMP INNER JOIN DEPT ON EMP.DEPTNO = DEPT.DEPTNO WHERE DEPT.LOC = 'DALLAS';