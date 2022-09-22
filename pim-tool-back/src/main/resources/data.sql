

INSERT INTO EMPLOYEE (BIRTH_DATE, FIRST_NAME, lAST_NAME, VERSION, VISA)
VALUES ('2001-10-11', 'An', 'Le DUC', 0, 'EDA'),
       ('2001-10-11', 'An', 'Le DUC', 0, 'EDB'),
       ('2001-10-11', 'An', 'Le DUC', 0, 'EDC'),
       ('2001-10-11', 'An', 'Le DUC', 0, 'EDD');


INSERT INTO GROUPS(GROUP_LEADER_ID, VERSION)
VALUES (1, 0),
       (2, 0),
       (3, 0);


INSERT INTO PROJECT (CUSTOMER, END_DATE, PROJECT_NAME, PROJECT_NUMBER, START_DATE, STATUS, VERSION, GROUP_ID)
VALUES ('Les Retaites Populaires', '2023-07-20', 'Facturation / Encaissements', 3116, '2022-07-07', 'NEW', 0, 1),
       ('GKB', '2022-07-20', 'GKBWEB', 3118, '2021-07-20', 'FIN', 0, 1),
       ('MGB Tourism', '2022-06-20', 'MGBAHN-Maint2015', 7157, '2021-07-20', 'INP', 0, 2),
       ('SOMED-SPITEX MAINT', '2023-07-20', 'SOMED-SPITEX MAINT', 7174, '2022-07-20', 'NEW', 0, 3),
       ('Les Retaites Populaires', '2022-07-20', 'ARCHIMEDE-2015-3.14', 7199, '2021-07-20', 'PLA', 0, 1),
       ('Les gas', '2023-07-20', 'frantes', 4116, '2022-07-07', 'NEW', 0, 1),
       ('GKA mks', '2022-07-20', 'GKBWEB', 4118, '2021-07-20', 'FIN', 0, 1),
       ('leopold', '2022-06-20', 'fc 370', 4157, '2021-07-20', 'INP', 0, 2),
       (' MAIN', '2023-07-20', 'SOMED-SPITEX MAINT', 4174, '2022-07-20', 'NEW', 0, 3),
       ('mario', '2022-07-20', 'dead cells', 4199, '2021-07-20', 'PLA', 0, 1),
       ('defect', '2022-07-20', 'image', 8118, '2021-07-20', 'FIN', 0, 1),
       ('silent', '2022-06-20', 'world', 8157, '2021-07-20', 'INP', 0, 2);

INSERT INTO PROJECT_EMPLOYEE (PROJECT_ID, EMPLOYEE_ID)
VALUES (1, 1),
       (1, 2);
