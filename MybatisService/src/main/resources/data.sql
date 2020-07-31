INSERT INTO User (userId, password, name, provider)
VALUES ('bigone', '{bcrypt}$2a$10$sZuNHYyGq0AAzcc4F/dEwOX07ZahrxND3qdPVMKGEoJ/fCtFykVea', 'bigone', null);

INSERT INTO ProcessStepType (stepType, stepDesc)
VALUES (1, '신분증인증'),
       (2, '이체인증'),
       (3, '인증단어인증');

INSERT INTO StateCode (stateCode, stateDesc)
VALUES (1, 'wait'),
       (2, 'success'),
       (3, 'timeout'),
       (4, 'failure');

INSERT INTO PREAGREE ( USERNO, AGREE, ACCOUNTPASSWORD )
VALUES (1, 1, '5212');
